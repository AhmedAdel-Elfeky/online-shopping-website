/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
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
 * @author ELROWAD
 */
public class ProductDetails extends HttpServlet {

    ResultSet rs = null;
    public static String productId;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        RequestDispatcher header = req.getRequestDispatcher("guestHeader.jsp");
        header.include(req, resp);

        RequestDispatcher sideBar = req.getRequestDispatcher("sideBar.jsp");
        sideBar.include(req, resp);
        productId = req.getParameter("productId");

        try {
            viewProductDetails(productId, out, req);
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        RequestDispatcher footer = req.getRequestDispatcher("Footer.html");
        footer.include(req, resp);
    }

    public void viewProductDetails(String id, PrintWriter out, HttpServletRequest req) throws SQLException {

        DataBase db = new DataBase();
        db.connect();
        try {
            rs = db.select("select qunatity,name,price,img_url,description from product where product_id=" + id + ";");
            System.out.println("begin:" + id);
            if (rs.next()) {
                String[] desc = rs.getString("description").split("\\;");
                for (String s : desc) {
                    System.out.println(s);
                }
                HttpSession session = req.getSession(false);
                String r = (String) session.getAttribute("role");
                System.out.println(rs.getInt(1));
                if (r.equals("a")) {
                    out.println("<div class=\"span9\">\n"
                            + "    <ul class=\"breadcrumb\">\n"
                            + "    <li><a href=\"AdminSearchProducts\">Home</a> <span class=\"divider\">/</span></li>\n"
                            + "    <li><a href=\"AdminSearchProducts\">Products</a> <span class=\"divider\">/</span></li>\n"
                            + "    <li class=\"active\">product Details</li>\n"
                            + "    </ul>	\n"
                            + "	<div class=\"row\">	  \n"
                            + "			<div id=\"gallery\" class=\"span3\">\n"
                            + "            <a href='" + rs.getString("img_url") + "' title='" + rs.getString("name") + "'>\n"
                            + "				<img src='" + rs.getString("img_url") + "' style=\"width:100%\" alt='" + rs.getString("name") + "'/>\n"
                            + "            </a>\n"
                            + "			<div id=\"differentview\" class=\"moreOptopm carousel slide\">\n"
                            + "                <div class=\"carousel-inner\">\n"
                            + "                  <div class=\"item\">\n"
                            + "                   <a href=\"themes/images/products/large/f3.jpg\" > <img style=\"width:29%\" src=\"themes/images/products/large/f3.jpg\" alt=\"\"/></a>\n"
                            + "                   <a href=\"themes/images/products/large/f1.jpg\"> <img style=\"width:29%\" src=\"themes/images/products/large/f1.jpg\" alt=\"\"/></a>\n"
                            + "                   <a href=\"themes/images/products/large/f2.jpg\"> <img style=\"width:29%\" src=\"themes/images/products/large/f2.jpg\" alt=\"\"/></a>\n"
                            + "                  </div>\n"
                            + "                </div>\n"
                            + "              <!--  \n"
                            + "			  <a class=\"left carousel-control\" href=\"#myCarousel\" data-slide=\"prev\">‹</a>\n"
                            + "              <a class=\"right carousel-control\" href=\"#myCarousel\" data-slide=\"next\">›</a> \n"
                            + "			  -->\n"
                            + "              </div>"
                            + "<div class=\"btn-toolbar\">\n"
                            + "			  <div class=\"btn-group\">\n"
                            + "			  </div>\n"
                            + "			</div>\n"
                            + "			</div>\n"
                            + "			<div class=\"span6\">\n"
                            + "				<h3>" + rs.getString("name") + "</h3>\n"
                            + "				<hr class=\"soft\"/>\n"
                            + "				<form class=\"form-horizontal qtyFrm\">\n"
                            + "				  <div class=\"control-group\">\n"
                            + "					<label class=\"control-label\"><span>$" + rs.getString("price") + "</span></label>\n"
                            + "					<div class=\"controls\">\n"
                            + "					  <button  class=\"btn btn-large btn-primary pull-right\"> <a href=\"EditProduct.jsp?product_id=" + id + "\">Edit</a> </button>\n"
                            + "					</div>\n"
                            + "				  </div>\n"
                            + "				</form>\n"
                            + "				\n"
                            + "				<hr class=\"soft\"/>\n"
                            + "				<h4>" + rs.getString("qunatity") + " items in stock</h4>\n"
                            + "				<hr class=\"soft clr\"/>\n"
                            + "				<p>" + desc[0] + "</p>\n"
                            + "				<br class=\"clr\"/>\n"
                            + "			<a href=\"#\" name=\"detail\"></a>\n"
                            + "			<hr class=\"soft\"/>\n"
                            + "			</div>\n"
                            + "			\n"
                            + "			<div class=\"span9\">\n"
                            + "            <ul id=\"productDetail\" class=\"nav nav-tabs\">\n"
                            + "              <li class=\"active\"><a href=\"#home\" data-toggle=\"tab\">Product Details</a></li>\n"
                            + "            </ul>\n"
                            + "            <div id=\"myTabContent\" class=\"tab-content\">\n"
                            + "              <div class=\"tab-pane fade active in\" id=\"home\">\n"
                            + "			  <h4>Product Information</h4>\n"
                            + "                <table class=\"table table-bordered\">\n"
                            + "				<tbody>\n"
                            + "				<tr class=\"techSpecRow\"><th colspan=\"2\">Product Details</th></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Brand: </td><td class=\"techSpecTD2\">" + desc[1] + "</td></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Released on:</td><td class=\"techSpecTD2\">" + desc[2] + "</td></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Package:</td><td class=\"techSpecTD2\">" + desc[3] + "</td></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Display:</td><td class=\"techSpecTD2\">" + desc[4] + "</td></tr>\n"
                            + "				</tbody>\n"
                            + "				</table>\n"
                            + "				\n"
                            + "				<h5>Features</h5>\n"
                            + "				<p>" + desc[5] + "</p>\n"
                            + "              </div>");
                } else {
                    out.println("<div class=\"span9\">\n"
                            + "    <ul class=\"breadcrumb\">\n"
                            + "    <li><a href=\"index.html\">Home</a> <span class=\"divider\">/</span></li>\n"
                            + "    <li><a href=\"products.html\">Products</a> <span class=\"divider\">/</span></li>\n"
                            + "    <li class=\"active\">product Details</li>\n"
                            + "    </ul>	\n"
                            + "	<div class=\"row\">	  \n"
                            + "			<div id=\"gallery\" class=\"span3\">\n"
                            + "            <a href='" + rs.getString("img_url") + "' title='" + rs.getString("name") + "'>\n"
                            + "				<img src='" + rs.getString("img_url") + "' style=\"width:100%\" alt='" + rs.getString("name") + "'/>\n"
                            + "            </a>\n"
                            + "			<div id=\"differentview\" class=\"moreOptopm carousel slide\">\n"
                            + "                <div class=\"carousel-inner\">\n"
                            + "                  <div class=\"item\">\n"
                            + "                   <a href=\"themes/images/products/large/f3.jpg\" > <img style=\"width:29%\" src=\"themes/images/products/large/f3.jpg\" alt=\"\"/></a>\n"
                            + "                   <a href=\"themes/images/products/large/f1.jpg\"> <img style=\"width:29%\" src=\"themes/images/products/large/f1.jpg\" alt=\"\"/></a>\n"
                            + "                   <a href=\"themes/images/products/large/f2.jpg\"> <img style=\"width:29%\" src=\"themes/images/products/large/f2.jpg\" alt=\"\"/></a>\n"
                            + "                  </div>\n"
                            + "                </div>\n"
                            + "              <!--  \n"
                            + "			  <a class=\"left carousel-control\" href=\"#myCarousel\" data-slide=\"prev\">‹</a>\n"
                            + "              <a class=\"right carousel-control\" href=\"#myCarousel\" data-slide=\"next\">›</a> \n"
                            + "			  -->\n"
                            + "              </div>"
                            + "<div class=\"btn-toolbar\">\n"
                            + "			  <div class=\"btn-group\">\n"
                            //                        + "				<span class=\"btn\"><i class=\"icon-envelope\"></i></span>\n"
                            //                        + "				<span class=\"btn\" ><i class=\"icon-print\"></i></span>\n"
                            //                        + "				<span class=\"btn\" ><i class=\"icon-zoom-in\"></i></span>\n"
                            //                        + "				<span class=\"btn\" ><i class=\"icon-star\"></i></span>\n"
                            //                        + "				<span class=\"btn\" ><i class=\" icon-thumbs-up\"></i></span>\n"
                            //                        + "				<span class=\"btn\" ><i class=\"icon-thumbs-down\"></i></span>\n"
                            + "			  </div>\n"
                            + "			</div>\n"
                            + "			</div>\n"
                            + "			<div class=\"span6\">\n"
                            + "				<h3>" + rs.getString("name") + "</h3>\n"
                            + "				<hr class=\"soft\"/>\n"
                            + "				<form class=\"form-horizontal qtyFrm\">\n"
                            + "				  <div class=\"control-group\">\n"
                            + "					<label class=\"control-label\"><span>$" + rs.getString("price") + "</span></label>\n"
                            + "					<div class=\"controls\">\n"
                            + "					<input value='1' id='Qty" + id + "' type=\"number\" class=\"span1\" placeholder=\"Qty.\"/>\n"
                            + "					  <button data-price=" + rs.getString("price") + " type=\"button\" class=\"btn btn-large btn-primary pull-right\" id='" + id + "' onclick=\"changeQuantityOfProduct(this.id,this.getAttribute('data-price'))\"> Add to cart <i class=\" icon-shopping-cart\"></i></button>\n"
                            + "					</div>\n"
                            + "				  </div>\n"
                            + "				</form>\n"
                            + "				\n"
                            + "				<hr class=\"soft\"/>\n"
                            + "				<h4>" + rs.getString("qunatity") + " items in stock</h4>\n"
                            + "				<hr class=\"soft clr\"/>\n"
                            + "				<p>" + desc[0] + "</p>\n"
                            //                        + "				<a class=\"btn btn-small pull-right\" href=\"#detail\">More Details</a>\n"
                            + "				<br class=\"clr\"/>\n"
                            + "			<a href=\"#\" name=\"detail\"></a>\n"
                            + "			<hr class=\"soft\"/>\n"
                            + "			</div>\n"
                            + "			\n"
                            + "			<div class=\"span9\">\n"
                            + "            <ul id=\"productDetail\" class=\"nav nav-tabs\">\n"
                            + "              <li class=\"active\"><a href=\"#home\" data-toggle=\"tab\">Product Details</a></li>\n"
                            + "               <li><a href=\"#profile\" data-toggle=\"tab\">Reviews</a></li>\n"
                            + "            </ul>\n"
                            + "            <div id=\"myTabContent\" class=\"tab-content\">\n"
                            + "              <div class=\"tab-pane fade active in\" id=\"home\">\n"
                            + "			  <h4>Product Information</h4>\n"
                            + "                <table class=\"table table-bordered\">\n"
                            + "				<tbody>\n"
                            + "				<tr class=\"techSpecRow\"><th colspan=\"2\">Product Details</th></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Brand: </td><td class=\"techSpecTD2\">" + desc[1] + "</td></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Released on:</td><td class=\"techSpecTD2\">" + desc[2] + "</td></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Package:</td><td class=\"techSpecTD2\">" + desc[3] + "</td></tr>\n"
                            + "				<tr class=\"techSpecRow\"><td class=\"techSpecTD1\">Display:</td><td class=\"techSpecTD2\">" + desc[4] + "</td></tr>\n"
                            + "				</tbody>\n"
                            + "				</table>\n"
                            + "				\n"
                            + "				<h5>Features</h5>\n"
                            + "				<p>" + desc[5] + "</p>\n"
                            + "              </div>");

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProductDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.println("<div class=\"tab-pane fade\" id=\"profile\">\n"
                + "		<div id=\"myTab\" class=\"pull-right\">\n"
                + "		 <a href=\"#listView\" data-toggle=\"tab\"><span class=\"btn btn-large\"><i class=\"icon-list\"></i></span></a>\n"
                + "		 <a href=\"#blockView\" data-toggle=\"tab\"><span class=\"btn btn-large btn-primary\"><i class=\"icon-th-large\"></i></span></a>\n"
                + "		</div>\n"
                + "		<br class=\"clr\"/>\n"
                + "		<hr class=\"soft\"/>\n"
                + "		<div class=\"tab-content\">\n"
                + "			<div class=\"tab-pane\" id=\"listView\">\n"
                + "				<div class=\"row\">	  \n"
                + "					<div class=\"span2\">\n"
                + "						<img src=\"themes/images/products/4.jpg\" alt=\"\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span4\">\n"
                + "						<h3>New | Available</h3>				\n"
                + "						<hr class=\"soft\"/>\n"
                + "						<h5>Product Name </h5>\n"
                + "						<p>\n"
                + "						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - \n"
                + "						that is why our goods are so popular..\n"
                + "						</p>\n"
                + "						<a class=\"btn btn-small pull-right\" href=\"product_details.html\">View Details</a>\n"
                + "						<br class=\"clr\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span3 alignR\">\n"
                + "					<form class=\"form-horizontal qtyFrm\">\n"
                + "					<h3> $222.00</h3>\n"
                + "					<label class=\"checkbox\">\n"
                + "						<input type=\"checkbox\">  Adds product to compair\n"
                + "					</label><br/>\n"
                + "					<div class=\"btn-group\">\n"
                + "					  <a href=\"product_details.html\" class=\"btn btn-large btn-primary\"> Add to <i class=\" icon-shopping-cart\"></i></a>\n"
                + "					  <a href=\"product_details.html\" class=\"btn btn-large\"><i class=\"icon-zoom-in\"></i></a>\n"
                + "					 </div>\n"
                + "						</form>\n"
                + "					</div>\n"
                + "			</div>\n"
                + "			<hr class=\"soft\"/>\n"
                + "			<div class=\"row\">	  \n"
                + "					<div class=\"span2\">\n"
                + "						<img src=\"themes/images/products/5.jpg\" alt=\"\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span4\">\n"
                + "						<h3>New | Available</h3>				\n"
                + "						<hr class=\"soft\"/>\n"
                + "						<h5>Product Name </h5>\n"
                + "						<p>\n"
                + "						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - \n"
                + "						that is why our goods are so popular..\n"
                + "						</p>\n"
                + "						<a class=\"btn btn-small pull-right\" href=\"product_details.html\">View Details</a>\n"
                + "						<br class=\"clr\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span3 alignR\">\n"
                + "					<form class=\"form-horizontal qtyFrm\">\n"
                + "						<h3> $222.00</h3>\n"
                + "						<label class=\"checkbox\">\n"
                + "						<input type=\"checkbox\">  Adds product to compair\n"
                + "						</label><br/>\n"
                + "						<div class=\"btn-group\">\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large btn-primary\"> Add to <i class=\" icon-shopping-cart\"></i></a>\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large\"><i class=\"icon-zoom-in\"></i></a>\n"
                + "						</div>\n"
                + "					</form>\n"
                + "					</div>\n"
                + "			</div>\n"
                + "			<hr class=\"soft\"/>\n"
                + "			<div class=\"row\">	  \n"
                + "					<div class=\"span2\">\n"
                + "					<img src=\"themes/images/products/6.jpg\" alt=\"\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span4\">\n"
                + "						<h3>New | Available</h3>				\n"
                + "						<hr class=\"soft\"/>\n"
                + "						<h5>Product Name </h5>\n"
                + "						<p>\n"
                + "						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - \n"
                + "						that is why our goods are so popular..\n"
                + "						</p>\n"
                + "						<a class=\"btn btn-small pull-right\" href=\"product_details.html\">View Details</a>\n"
                + "						<br class=\"clr\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span3 alignR\">\n"
                + "					<form class=\"form-horizontal qtyFrm\">\n"
                + "					<h3> $222.00</h3>\n"
                + "					<label class=\"checkbox\">\n"
                + "						<input type=\"checkbox\">  Adds product to compair\n"
                + "					</label><br/>\n"
                + "				<div class=\"btn-group\">\n"
                + "				  <a href=\"product_details.html\" class=\"btn btn-large btn-primary\"> Add to <i class=\" icon-shopping-cart\"></i></a>\n"
                + "				  <a href=\"product_details.html\" class=\"btn btn-large\"><i class=\"icon-zoom-in\"></i></a>\n"
                + "				 </div>\n"
                + "						</form>\n"
                + "					</div>\n"
                + "			</div>\n"
                + "			<hr class=\"soft\"/>\n"
                + "			<div class=\"row\">	  \n"
                + "					<div class=\"span2\">\n"
                + "					<img src=\"themes/images/products/7.jpg\" alt=\"\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span4\">\n"
                + "						<h3>New | Available</h3>				\n"
                + "						<hr class=\"soft\"/>\n"
                + "						<h5>Product Name </h5>\n"
                + "						<p>\n"
                + "						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - \n"
                + "						that is why our goods are so popular..\n"
                + "						</p>\n"
                + "						<a class=\"btn btn-small pull-right\" href=\"product_details.html\">View Details</a>\n"
                + "						<br class=\"clr\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span3 alignR\">\n"
                + "						<form class=\"form-horizontal qtyFrm\">\n"
                + "						<h3> $222.00</h3>\n"
                + "						<label class=\"checkbox\">\n"
                + "						<input type=\"checkbox\">  Adds product to compair\n"
                + "						</label><br/>\n"
                + "						<div class=\"btn-group\">\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large btn-primary\"> Add to <i class=\" icon-shopping-cart\"></i></a>\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large\"><i class=\"icon-zoom-in\"></i></a>\n"
                + "						</div>\n"
                + "						</form>\n"
                + "					</div>\n"
                + "			</div>\n"
                + "			\n"
                + "			<hr class=\"soft\"/>\n"
                + "			<div class=\"row\">	  \n"
                + "					<div class=\"span2\">\n"
                + "					<img src=\"themes/images/products/8.jpg\" alt=\"\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span4\">\n"
                + "						<h3>New | Available</h3>				\n"
                + "						<hr class=\"soft\"/>\n"
                + "						<h5>Product Name </h5>\n"
                + "						<p>\n"
                + "						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - \n"
                + "						that is why our goods are so popular..\n"
                + "						</p>\n"
                + "						<a class=\"btn btn-small pull-right\" href=\"product_details.html\">View Details</a>\n"
                + "						<br class=\"clr\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span3 alignR\">\n"
                + "						<form class=\"form-horizontal qtyFrm\">\n"
                + "						<h3> $222.00</h3>\n"
                + "						<label class=\"checkbox\">\n"
                + "						<input type=\"checkbox\">  Adds product to compair\n"
                + "						</label><br/>\n"
                + "						<div class=\"btn-group\">\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large btn-primary\"> Add to <i class=\" icon-shopping-cart\"></i></a>\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large\"><i class=\"icon-zoom-in\"></i></a>\n"
                + "						</div>\n"
                + "						</form>\n"
                + "					</div>\n"
                + "			</div>\n"
                + "			<hr class=\"soft\"/>\n"
                + "				<div class=\"row\">	  \n"
                + "					<div class=\"span2\">\n"
                + "					<img src=\"themes/images/products/9.jpg\" alt=\"\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span4\">\n"
                + "						<h3>New | Available</h3>				\n"
                + "						<hr class=\"soft\"/>\n"
                + "						<h5>Product Name </h5>\n"
                + "						<p>\n"
                + "						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - \n"
                + "						that is why our goods are so popular..\n"
                + "						</p>\n"
                + "						<a class=\"btn btn-small pull-right\" href=\"product_details.html\">View Details</a>\n"
                + "						<br class=\"clr\"/>\n"
                + "					</div>\n"
                + "					<div class=\"span3 alignR\">\n"
                + "						<form class=\"form-horizontal qtyFrm\">\n"
                + "						<h3> $222.00</h3>\n"
                + "						<label class=\"checkbox\">\n"
                + "						<input type=\"checkbox\">  Adds product to compair\n"
                + "						</label><br/>\n"
                + "						<div class=\"btn-group\">\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large btn-primary\"> Add to <i class=\" icon-shopping-cart\"></i></a>\n"
                + "						<a href=\"product_details.html\" class=\"btn btn-large\"><i class=\"icon-zoom-in\"></i></a>\n"
                + "						</div>\n"
                + "						</form>\n"
                + "					</div>\n"
                + "			</div>\n"
                + "			<hr class=\"soft\"/>\n"
                + "		</div>\n"
                + "	<div class=\"tab-pane active\" id=\"blockView\">\n");
        /**
         * *************************Reviews***********************************
         */

        MongoCRUD.connect();
        BasicDBObject query = new BasicDBObject("productId", Integer.parseInt(productId));
        int userId;
        MongoCursor<ProductComment> cursor;
        cursor = MongoCRUD.collection.find(query).iterator();
        int i = 0;
        while (cursor.hasNext()) {
            cursor.next();
            i++;
        }
        System.out.println("Number of comments = " + i);
        cursor = MongoCRUD.collection.find(query).iterator();
        if (!cursor.hasNext()) {
            out.println("<div><span style='font-size:18px;'>There aren't any reviews for this product</span></div><br><hr>\n");
        } else {

            userId = cursor.next().getUserId();
            System.out.println("I have a comment");
            db.connect();
            ResultSet rs = db.select("select * from customer where customer_id =" + userId);

            while (rs.next()) {
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                System.out.println("One line ahead");

                System.out.println("i = " + i);
                for (int j = 0; j < i; j++) {
                    out.println("<div><span style='font-size:18px;'>By " + fname + " " + lname + " <b></b>" + MongoCRUD.retreiveOnePoductComments(query).get(j).getTimer() + "</span>\n" + "<br><br>" + MongoCRUD.retreiveOnePoductComments(query).get(j).getComment() + "</div><br><hr>\n");
                }
                MongoCRUD.comments.clear();
//                }
                System.out.println(MongoCRUD.comments);

            }

            db.disconnect();
        }
        /**
         * ********************************************************************
         */
        out.println("<form action='ReviewController' method='post'>"
                + "<div id='result'></div><br><hr><textarea style='width:700px;height:70px' id=\"comment\" name=\"comment\" placeholder='\n Enter Your Comment'></textarea>\n"
                + "<button style='margin:12px 0px' type=\"button\" id='commentBtn' class=\"btn btn-large btn-primary pull-right\"> Comment </button></form>\n"
                + "			<hr class=\"soft\"/>\n"
                + "			</div>\n"
                + "		</div>\n"
                + "				<br class=\"clr\">\n"
                + "					 </div>\n"
                + "		</div>\n"
                + "          </div>\n"
                + "\n"
                + "	</div>\n"
                + "</div>\n"
                + "</div> </div>\n"
                + "</div>");

    }
}
