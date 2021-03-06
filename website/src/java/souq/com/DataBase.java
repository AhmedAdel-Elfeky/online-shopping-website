/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

/**
 *
 * @author Ahmed
 */
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import javax.websocket.Session;

public class DataBase {

    // init database constants
//    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/e_commerce";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    // init connection object
    private Connection connection;

    // init properties object
    private Properties properties;

    // init the statement
    private Statement statement = null;
    private PreparedStatement prepStatement = null;

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();

            properties.setProperty("user", USERNAME);

            properties.setProperty("password", PASSWORD);
        }

        return properties;
    }

    /**
     * Connect to the database
     *
     * @return Connection
     */
    public Connection connect() {
        if (connection == null) {
            try {
                //  Class.forName(DATABASE_DRIVER);

                connection = (Connection) DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    /**
     * Disconnect database
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                if (statement != null) {
                    statement.close();
                }
                if (prepStatement != null) {
                    prepStatement.close();
                }
                connection = null;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Return the result set when a correct SQL statement is provided
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public ResultSet select(String query) throws SQLException {
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        return resultSet;
    }

    /**
     * Return the status when a SQL query is provided for INSERT, UPDATE or
     * DELETE
     *
     * @param query
     * @return
     * @throws SQLException
     */
    public int DML(String query) throws SQLException {
        prepStatement = connection.prepareStatement(query, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        int result = prepStatement.executeUpdate();
        return result;
    }

    public void getFeaturedDev(JspWriter out, int categId) {
        try {
            this.connect();

            ResultSet rs = this.select("select  name,img_url,price from product where category_id =" + categId + " and featured = 'f'  "
                    + "order by date desc limit 4;");
            while (rs.next()) {
                out.print("<li class=\"span3\">\n"
                        + "   <div class=\"thumbnail\">\n"
                        + "      <i class=\"tag\"></i>\n"
                        + "           <a href=\"product_details.html\"><img style=\"height:150px\" src=\"" + rs.getString(2) + "\" alt=\"\"></a>\n"
                        + "                 <div class=\"caption\">\n"
                        + "                       <h5>" + rs.getString(1) + "</h5>\n"
                        + "                                  <h4><a class=\"btn\" href=\"product_details.html\">VIEW</a> <span class=\"pull-right\">$" + rs.getString(3) + "</span></h4>\n"
                        + "                                    </div>\n"
                        + "                                       </div>\n"
                        + "                                     </li>");
            }
            this.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getLatestProducts(JspWriter out) {
        try {
            this.connect();
            ResultSet rs = this.select(" select name,img_url,price,description,product_id from product  order by date desc limit 9;");
            while (rs.next()) {
                out.print("<li class=\"span3\">\n"
                        + " <div class=\"thumbnail\">\n"
                        + "   <a href=\"ProductDetails?productId=" + rs.getString("product_id") + "\"><img  style=\"height:170px;\" src=" + rs.getString("img_url") + " alt=\"\"/></a>\n"
                        + "   <div class=\"caption\">\n"
                        + "      <h5>" + rs.getString(1) + "</h5>\n"
                        + "\n"
                        + "<h4 style=\"text-align:center;\"><a class=\"btn\" href=\"ProductDetails?productId=" + rs.getString("product_id") + "\"> <i class=\"icon-zoom-in\"></i></a> <button class=\"btn\"  data-price=" + rs.getString("price") + " id='" + rs.getString("product_id") + " ' onclick=\"addToCart(this.getAttribute('data-price'),this.id)\">Add to <i class=\"icon-shopping-cart\"></i></button> <button class=\"btn btn-primary\">" + rs.getString("price") + " $</button></h4>\n"
                        + "</div>\n"
                        + "      </div>\n"
                        + "    </li>");
            }
            this.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void listOrders(JspWriter out, int customer_id) {
        try {
            this.connect();
            ResultSet rs = this.select(" select cop.order_id,o.date from orders o inner join customer_order_product cop "
                    + "on o.order_id = cop.order_id where status = 'confirmed' and cop.customer_id=" + customer_id + " "
                    + "group by cop.order_id,o.date order by cop.order_id  ;");
            List<Integer> conf_orders = new ArrayList<>();
            List<String> dates = new ArrayList<>();
            while (rs.next()) {
                conf_orders.add(rs.getInt(1));
                dates.add(rs.getString(2));
                System.out.println(rs.getString(2));
            }
            if (conf_orders.size() > 0) {
                for (int i = 0; i < conf_orders.size(); i++) {
                    out.print("Order Num&nbsp;:&nbsp;" + i);
                    rs = this.select("  select cop.order_id,p.img_url,cop.quantity,cop.price,cop.product_id,p.name "
                            + "from product p inner join customer_order_product cop on p.product_id=cop.product_id "
                            + "inner join orders o on cop.order_id=o.order_id where cop.order_id = " + conf_orders.get(i) + ";");
                    out.print("<br/>Order Id&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:&nbsp;" + conf_orders.get(i));
                    out.print("<br/>Order Date&nbsp;:&nbsp;" + dates.get(i));
                    out.print("    <table class=\"table table-bordered\">\n");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th width=\"100\">Product</th>");
                    out.print("<th width=\"150\">Name</th>");
                    out.print("<th width=\"30\">Quantity</th>");
                    out.print("<th width=\"100\">Price</th>");
                    out.print("<th>Discount</th>");
                    out.print("<th>Tax</th>");
                    out.print("<th width=\"100\">Total</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tbody>");
                    int total = 0;
                    while (rs.next()) {

                        out.print("<tr>");
                        out.print("<td height=\"50\"> <img width=\"60\"  src=\"" + rs.getString(2) + "\" alt=\"\"/></td>");
                        out.print("<td>" + rs.getString(6) + "</td>");
                        out.print(" <td>");
                        out.print(rs.getInt(3));
                        out.print("  </td>");
                        out.print("<td>$" + rs.getInt(4) + "</td>");
                        out.print("<td>$00.00</td>");
                        out.print("<td>$00.00</td>");

                        out.print("<td>$" + rs.getInt(3) * rs.getInt(4) + "</td>");
                        out.print("</tr>");
                        total += rs.getInt(3) * rs.getInt(4);

                    }

                    out.print(" <tr>");
                    out.print("<td colspan=\"6\" style=\"text-align:right\"><strong>TOTAL =</strong></td>");
                    out.print("<td class=\"label label-important\" style=\"display:block\"> <strong> " + total + " </strong></td>");
                    out.print("</tr>");
                    out.print("</tbody>");
                    out.print(" </table>");
                }
                out.print("<br/>");
                out.print("<br/>");
            } else {
                out.print("<h1> You did not make any orders yet</h1>");
            }
            this.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int getNumOfDev(JspWriter out, HttpServletRequest req, HttpSession s) {
        s = req.getSession(false);
        int mobile = 0;
        int laptop = 0;
        int numOfFeatured = 0;
        try {
            this.connect();
            ResultSet rs1 = this.select("select count(product_id) from product where category_id= 1;");
            ResultSet rs2 = this.select("select count(product_id) from product where category_id= 2;");
            ResultSet rs3 = this.select("select count(product_id) from product where featured= \'f\';");

            while (rs1.next()) {
                mobile = rs1.getInt(1);
            }
            while (rs2.next()) {
                laptop = rs2.getInt(1);
            }
            while (rs3.next()) {
                numOfFeatured = rs3.getInt(1);
            }

            String r = (String) s.getAttribute("role");
            if (r.equals("c") || r.equals("")) {
                out.print(" <li class=\"subMenu open\"><a> ELECTRONICS [" + (laptop + mobile) + "]</a>\n"
                        + "                                <ul>\n"
                        + "                                    <li><a href=\"SearchOnProduct?category=mobile\"><i class=\"icon-chevron-right\"></i>Mobile Phone (" + mobile + ")</a></li>\n"
                        + "                                    <li><a href=\"SearchOnProduct?category=labtop\"><i class=\"icon-chevron-right\"></i>Laptop (" + laptop + ")</a></li>\n"
                        + "                                </ul>\n"
                        + "                            </li>   ");
            } else {
                out.print(" <li class=\"subMenu open\"><a> ELECTRONICS [" + (laptop + mobile) + "]</a>\n"
                        + "                                <ul>\n"
                        + "                                    <li><a href=\"AdminSearchProducts?category=mobile\"><i class=\"icon-chevron-right\"></i>Mobile Phone (" + mobile + ")</a></li>\n"
                        + "                                    <li><a href=\"AdminSearchProducts?category=labtop\"><i class=\"icon-chevron-right\"></i>Laptop (" + laptop + ")</a></li>\n"
                        + "                                </ul>\n"
                        + "                            </li>   ");
            }

            this.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfFeatured;
    }

    public int getNumOfFeatured(JspWriter out) {

        int numOfFeatured = 0;
        try {
            this.connect();

            ResultSet rs = this.select("select count(product_id) from product where featured= \'f\';");

            while (rs.next()) {
                numOfFeatured = rs.getInt(1);
            }
            this.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfFeatured;
    }

    public int getNumOfProducts(String name, String category, String price) {

        int numOfProduct = 0;
        ResultSet rs = null;
        try {
            this.connect();
            if (price.equals("")) {
                rs = this.select("select count(product_id) from product where name Ilike '%" + name + "%' and category_id in (select category_id from category where name Like '%" + category + "%');");
            } else {
                rs = this.select("select count(product_id) from product where name Ilike '%" + name + "%' and category_id in (select category_id from category where name Like '%" + category + "%'and price between " + price + ");");
            }

            if (rs.next()) {
                numOfProduct = rs.getInt(1);
            }
            this.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfProduct;
    }

    public enum featured_type {
        f,
        uf;
    }

    public void addNewProduct(HttpServletResponse response, HttpServletRequest request) {
        int cat_id = Integer.parseInt(request.getParameter("category"));
        String name = request.getParameter("pname");
        java.sql.Date date = java.sql.Date.valueOf(request.getParameter("pdate"));
        int price = Integer.parseInt(request.getParameter("pprice"));
        int quant = Integer.parseInt(request.getParameter("pquantity"));
        String url = request.getParameter("purl");
        String f = request.getParameter("featured");
        String desc = request.getParameter("description");
        String pBrand = request.getParameter("pBrand");
        String pReleased = request.getParameter("pReleased");
        String pPackage = request.getParameter("pPackage");
        String pDisplay = request.getParameter("pDisplay");
        String pFeatures = request.getParameter("pFeatures");

        String AllDesc = desc + ";" + pBrand + ";" + pReleased + ";" + pPackage + ";" + pDisplay + ";" + pFeatures;
        try {
            this.connect();
            prepStatement = connection.prepareStatement("insert into product (category_id,price,description,qunatity,"
                    + "name,img_url,date,featured) values (?,?,?,?,?,?,?, CAST(? AS featured_type))  ",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepStatement.setInt(1, cat_id);
            prepStatement.setInt(2, price);
            prepStatement.setString(3, AllDesc);
            prepStatement.setString(3, desc);
            prepStatement.setInt(4, quant);
            prepStatement.setString(5, name);
            prepStatement.setString(6, url);
            prepStatement.setDate(7, date);
            prepStatement.setString(8, f);
            prepStatement.execute();
            this.disconnect();
        } catch (Exception s) {
            s.printStackTrace();
            System.out.println("there is an error in adding product");
        }
    }

    public ProductInfo getProductInfo(HttpServletResponse response, HttpServletRequest request, int product_id) {
        ProductInfo p = new ProductInfo();
        try {
            this.connect();
            ResultSet rs = this.select("select * from product where product_id = " + product_id + ";");

            while (rs.next()) {
                p.categoryId = rs.getInt(2);
                p.price = rs.getInt(3);
                p.description = rs.getString(4);
                p.qunatity = rs.getInt(5);
                p.name = rs.getString(6);
                p.imgUrl = rs.getString(7);
                p.date = rs.getString(8);
                p.featured = rs.getString(9);
            }
            this.disconnect();
        } catch (Exception s) {
            s.printStackTrace();
            System.out.println("there is an error in adding product");
        }
        return p;
    }

    public void editProduct(HttpServletResponse response, HttpServletRequest request, int productId) {
        int cat_id = Integer.parseInt(request.getParameter("category"));
        String name = request.getParameter("pname");
        java.sql.Date date = java.sql.Date.valueOf(request.getParameter("pdate"));
        int price = Integer.parseInt(request.getParameter("pprice"));
        int quant = Integer.parseInt(request.getParameter("pquantity"));
        String url = request.getParameter("purl");
        String f = request.getParameter("featured");
        String desc = request.getParameter("description");
        String pBrand = request.getParameter("pBrand");
        String pReleased = request.getParameter("pReleased");
        String pPackage = request.getParameter("pPackage");
        String pDisplay = request.getParameter("pDisplay");
        String pFeatures = request.getParameter("pFeatures");

        String AllDesc = desc + ";" + pBrand + ";" + pReleased + ";" + pPackage + ";" + pDisplay + ";" + pFeatures;
        try {
            this.connect();
            prepStatement = connection.prepareStatement("update product SET category_id = ?,price=?,description=?,qunatity=?,"
                    + "name=?,img_url=?,date=?,featured= CAST(? AS featured_type) where product_id = ?  ;",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            prepStatement.setInt(1, cat_id);
            prepStatement.setInt(2, price);
            prepStatement.setString(3, AllDesc);
            prepStatement.setInt(4, quant);
            prepStatement.setString(5, name);
            prepStatement.setString(6, url);
            prepStatement.setDate(7, date);
            prepStatement.setString(8, f);
            prepStatement.setInt(9, productId);
            prepStatement.executeUpdate();
            this.disconnect();
        } catch (Exception s) {
            s.printStackTrace();
            System.out.println("there is an error in adding product");
        }
    }

    public int createOrder(Cookie[] cookie, int customerId) {
        HashMap<Integer, Integer> productarr = new HashMap<>();
        float productPrice = 0;
        int orderId = 0;
        ResultSet rs = null;
        int result = -1;
        this.connect();
        for (Cookie c : cookie) {
            if (c.getName().equals("productInCart") && !(c.getValue().equals(""))) {
                try {
                    result = this.DML("INSERT INTO orders(quantity,status,date) VALUES (" + Integer.parseInt(c.getValue()) + ",'unconfirmed',now());");
                    rs = this.select("select order_id from orders  order by order_id DESC limit(1);");
                    if (rs.next()) {
                        orderId = Integer.parseInt(rs.getString("order_id"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c.getName().startsWith("id") && orderId != 0) {
                productarr.put(Integer.parseInt(c.getName().substring(2)), Integer.parseInt(c.getValue()));
            }
            for (Integer p : productarr.keySet()) {
                try {
                    rs = this.select("select price from product where product_id = " + p + ";");
                    if (rs.next()) {
                        productPrice = Float.parseFloat(rs.getString("price"));
                    }
                    result = this.DML("INSERT INTO customer_order_product(order_id,customer_id,product_id,quantity,price) VALUES (" + orderId + "," + customerId + "," + p + "," + productarr.get(p) + "," + productPrice + ");");
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orderId;
    }

    public void updateOrder(Cookie[] cookie, int orderId, int customerId, String status) {
        int productId = 0;
        int productQuantity = 0;
        ResultSet rs = null;
        float productPrice = 0;
        int result = -1;
        this.connect();
        for (Cookie c : cookie) {
            if (c.getName().equals("productInCart")) {
                try {
                    this.DML("update orders set quantity=" + Integer.parseInt(c.getValue()) + ",status= '" + status + "' where order_id =" + orderId + ";");
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
                productQuantity = Integer.parseInt(c.getValue());
            } else if (c.getName().startsWith("id")) {
                System.out.println("From id:" + c.getName());
                if (c.getValue().equals("0")) {
                    System.out.println("From delete:" + productId);
                    try {
                        productId = Integer.parseInt(c.getName().substring(2));
                        System.out.println("From delete:" + productId);
                        DML("delete from customer_order_product where order_id=" + orderId + "and product_id=" + productId + ";");
//                         rs = this.select("select quantity from orders where order_id = " + orderId + ";");
//                         if(rs.next()){
//                             if(rs.getInt("quantity") == 0)
//                             DML("delet from orders where order_id = " + orderId + ";");
//                         }
//                        
                    } catch (SQLException ex) {
                        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (c.getValue() != "") {
                    productId = Integer.parseInt(c.getName().substring(2));
                    productQuantity = Integer.parseInt(c.getValue());

                    try {
                        result = this.DML("update customer_order_product set quantity=" + productQuantity + " where order_id=" + orderId + " and product_id=" + productId + ";");
                        if (result == 0) {
                            rs = this.select("select price from product where product_id = " + productId + ";");
                            if (rs.next()) {
                                productPrice = Float.parseFloat(rs.getString("price"));
                            }
                            result = this.DML("INSERT INTO customer_order_product VALUES (" + orderId + "," + customerId + "," + productId + "," + productQuantity + "," + productPrice + ");");
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }

    }

    public String confirmOrder(int customerId, int orderId, Cookie[] cookie) {
        ResultSet rs = null;
        int balance;
        int totalOrderPrice = 0;
        this.connect();
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().startsWith("totalPrice")) {
                    totalOrderPrice = Integer.parseInt(c.getValue());
                }
            }
        }
        try {
            rs = this.select("select credit_limit from customer where customer_id=" + customerId + ";");
            if (rs.next()) {
                balance = Integer.parseInt(rs.getString("credit_limit"));
                if (totalOrderPrice < balance) {
                    this.updateOrder(cookie, orderId, customerId, "confirmed");
                    return "confirmed";
                } else {
                    return "unconfirmed";
                }

            }
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String reloadCart(int customerId) {
        return "False";
    }
}
