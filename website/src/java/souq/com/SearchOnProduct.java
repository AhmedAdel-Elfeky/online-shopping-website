/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

//import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELROWAD
 */
@WebServlet(name = "SearchOnProduct", urlPatterns = {"/SearchOnProduct"})
public class SearchOnProduct extends HttpServlet {

    ResultSet rs = null;
    String name = null;
    String category = null;
    String searchedItem = "ALL";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        RequestDispatcher header = req.getRequestDispatcher("guestHeader.jsp");
        header.include(req, resp);

        RequestDispatcher sideBar = req.getRequestDispatcher("sideBar.jsp");
        sideBar.include(req, resp);
        Map<String, String[]> myparam = req.getParameterMap();
        for (String key : myparam.keySet()) {
            System.out.println(key + ":" + myparam.get(key));
        }
        String name = req.getParameter("search");
        String price = req.getParameter("price");
        category = req.getParameter("category");
        if (name == null) {
            name = "";
        }

        if (category == null || category.equals("All")) {
            category = "";
        }
        if (price == null || price.equals("All")) {
            price = "";
        }

        viewSearchedProduct(name, category, out, price);
        RequestDispatcher footer = req.getRequestDispatcher("Footer.html");
        footer.include(req, resp);
    }

    public void viewSearchedProduct(String name, String category, PrintWriter out, String price) {

        DataBase db = new DataBase();
        boolean rsFlag = false;
        int numOfProducts = db.getNumOfProducts(name, category, price);
        if (!name.equals("")) {
            searchedItem = name;
        } else {
            switch (category) {
                case "":
                    searchedItem = "All Available Products";
                    break;
                case "mobile":
                    searchedItem = "Mobiles";
                    break;
                case "labtop":
                    searchedItem = "Laptops";
                    break;
            }
        }

        db.connect();
        try {
            out.println("<div class=\"span9\">\n"
                    + "    <ul class=\"breadcrumb\">\n"
                    + "        <li><a href=\"index.jsp\">Home</a> <span class=\"divider\">/</span></li>\n"
                    + "        <li class=\"active\">Products</li>\n"
                    + "    </ul>\n"
                    + "    <h3>" + searchedItem + "<small class=\"pull-right\">" + numOfProducts + "  " + "products are available </small></h3>	\n"
                    + "    <hr class=\"soft\"/>\n"
                    + "    <hr class=\"soft\"/>\n"
                    + "    <br class=\"clr\"/>\n"
                    + "   \n"
                    + "        <div class=\"tab-pane  active\" id=\"blockView\">\n"
                    + "            <ul class=\"thumbnails\">\n"
                    + "");
            if (price.equals("")) {
                rs = db.select("select name,price,img_url,product_id from product where name ILIKE '%" + name + "%' and category_id in (select category_id from category where name Like '%" + category + "%');");
            } else {
                rs = db.select("select name,price,img_url,product_id from product where name ILIKE '%" + name + "%' and category_id in (select category_id from category where name Like '%" + category + "%' and price between " + price + " );");
            }
            while (rs.next()) {
                rsFlag = true;
                out.println("<li class=\"span3\">\n"
                        + "			  <div class=\"thumbnail\">\n"
                        + "				<a href=\"ProductDetails?productId=" + rs.getString("product_id") + "\"><img  style=\"height:170px;\" src=" + rs.getString("img_url") + " alt=\"\"/></a>\n"
                        + "				<div class=\"caption\">\n"
                        + "				  <h5>" + rs.getString("name") + "</h5>"
                        + "				   <h4 style=\"text-align:center;\"><a class=\"btn\" href=\"ProductDetails?productId=" + rs.getString("product_id") + "\"> <i class=\"icon-zoom-in\"></i></a> <button type=\"submit\" class=\"btn\" data-price=" + rs.getString("price") + " id='" + rs.getString("product_id") + " ' onclick=\"addToCart(this.getAttribute('data-price'),this.id)\" >Add to <i class=\"icon-shopping-cart\"></i></button> <button class=\"btn btn-primary\" href=\"#\">" + rs.getString("price") + " $</button></h4>\n"
                        + "				</div>\n"
                        + "			  </div>\n"
                        + "			</li>"
                );
            }

            if (!rsFlag) {
                out.println("<div style=\"background-color:#F8F1A2;padding:15px 35px;\">0 Results found for" + "<u>" + name + "</u> <div>");
            }
            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SearchOnProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.println("	  </ul>\n"
                + "	<hr class=\"soft\"/>\n"
                + "	</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "<!-- MainBody End ============================= -->");
    }

}
