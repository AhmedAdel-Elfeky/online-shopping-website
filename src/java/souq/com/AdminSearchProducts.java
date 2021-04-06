/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elfek
 */
public class AdminSearchProducts extends HttpServlet {

    
     ResultSet rs = null;
     String name = null;
     String category =null;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        DataBase db = new DataBase();
        RequestDispatcher header = req.getRequestDispatcher("guestHeader.jsp");
        header.include(req, resp);
        HttpSession session = req.getSession(false);
        session.setAttribute("Edditproduct","no");
        session.setAttribute("addproduct","no");
        
        RequestDispatcher sideBar = req.getRequestDispatcher("sideBar.jsp");
        sideBar.include(req, resp);

        RequestDispatcher body = req.getRequestDispatcher("products.html");
        body.include(req, resp);
        String name = req.getParameter("search");
        category = req.getParameter("category");
        if (name == null) {
            name = "";
        }

        if (category == null || category.equals("All") ) {
            category = "";
        }

        db.connect();
        try {
            System.out.println(category);
            System.out.println(name);
            rs = db.select("select name,price,img_url,product_id from product where name Like '%"+name+"%'and category_id in (select category_id from category where name Like '%" + category + "%');");
            while (rs.next()) {
                System.out.println("h");
                out.println("<li class=\"span3\">\n"
                        + "			  <div class=\"thumbnail\">\n"
                        + "				<a href=\"ProductDetails?productId="+rs.getString("product_id")+"\"><img  style=\"height:170px;\" src=" + rs.getString("img_url") + " alt=\"\"/></a>\n"
                        + "				<div class=\"caption\">\n"
                        + "				  <h5>" + rs.getString("name") + "</h5>"
                        + "				   <h4 style=\"text-align:center;\"><a class=\"btn\" href=\"ProductDetails?productId="+rs.getString("product_id")+"\"> <i class=\"icon-zoom-in\"></i></a> <a class=\"btn\" href=\"EditProduct.jsp?product_id="+rs.getInt("product_id")+"\">Edit </a>  <a class=\"btn btn-primary\" style=\"pointer-events: none; cursor: default;\">" + rs.getString("price") + " $</a></h4>\n"
                        + "				</div>\n"
                        + "			  </div>\n"
                        + "			</li>"
                );
            }
            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SearchOnProduct.class.getName()).log(Level.SEVERE, null, ex);
        }

        out.println("	  </ul>\n"
                + "	<hr class=\"soft\"/>\n"
                + "	</div>\n"
                + "</div>\n"
                + "\n"
                + "</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "</div>\n"
                + "<!-- MainBody End ============================= -->");
        RequestDispatcher footer = req.getRequestDispatcher("Footer.html");
        footer.include(req, resp);
    }
}