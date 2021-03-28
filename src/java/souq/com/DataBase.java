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
import javax.servlet.jsp.JspWriter;

public class DataBase {

    // init database constants
//    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:postgresql://localhost/e_commerce";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "01018867111";

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

    public int getNumOfDev(JspWriter out) {
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
            out.print(" <li class=\"subMenu open\"><a> ELECTRONICS [" + (laptop + mobile) + "]</a>\n"
                    + "                                <ul>\n"
                    + "                                    <li><a href=\"SearchOnProduct?category=mobile\"><i class=\"icon-chevron-right\"></i>Mobile Phone (" + mobile + ")</a></li>\n"
                    + "                                    <li><a href=\"SearchOnProduct?category=labtop\"><i class=\"icon-chevron-right\"></i>Laptop (" + laptop + ")</a></li>\n"
                    + "                                </ul>\n"
                    + "                            </li>   ");
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

    public int getNumOfProducts(String name, String category) {

        int numOfProduct = 0;
        try {
            this.connect();

            ResultSet rs = this.select("select count(product_id) from product where name Ilike '%" + name + "%' and category_id in (select category_id from category where name Like '%" + category + "%');");

            if (rs.next()) {
                numOfProduct = rs.getInt(1);
            }
            this.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numOfProduct;
    }

    public int createOrder(Cookie[] cookie, String customerId) {
        int productId = 0 ;
        float productPrice =0 ;
        int productQuantity =0;
        int orderId =0;
        ResultSet rs = null;
        int result = -1;
        this.connect();
        for (Cookie c : cookie) {
            if (c.getName().equals("productInCart")) {
                try {
                    result = this.DML("INSERT INTO orders(quantity,status,date) VALUES (" + Integer.parseInt(c.getValue()) + ",'unconfirmed',now());");
                    rs = this.select("select order_id from orders  order by order_id DESC limit(1);");
                    if (rs.next()) {
                        orderId = Integer.parseInt(rs.getString("order_id"));
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c.getName().startsWith("id")) {
                productId = Integer.parseInt(c.getName().substring(2));
                productQuantity = Integer.parseInt(c.getValue());
                try {
                    rs = this.select("select price from product where product_id = " + productId + ";");
                    if (rs.next()) {
                        productPrice = Float.parseFloat(rs.getString("price"));
                    }
                    result = this.DML("INSERT INTO customer_order_product(order_id,customer_id,product_id,quantity,price) VALUES (" + orderId + "," + customerId + "," + productId + "," + productQuantity + "," + productPrice + ");");
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return orderId;
    }

    public void updateOrder(Cookie[] cookie, int orderId, int customerId) {
        int productId = 0;
        int productQuantity = 0;
        ResultSet rs = null;
        float productPrice = 0;
        int result = -1;
        this.connect();
        for (Cookie c : cookie) {
            if (c.getName().equals("productInCart")) {
                try {
                    this.DML("update orders set quantity=" + Integer.parseInt(c.getValue()) + " where order_id =" + orderId + ";");
                } catch (SQLException ex) {
                    Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
                }
                productQuantity = Integer.parseInt(c.getValue());
            }

             else if (c.getName().startsWith("id")) {
                 System.out.println("From id:" + c.getName());
                if (c.getValue().equals("0")) {
                    System.out.println("From delete:" + productId);
                    try {
                        productId = Integer.parseInt(c.getName().substring(2));
                        System.out.println("From delete:" + productId);
                        DML("delete from customer_order_product where product_id=" + productId + ";");
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
}
