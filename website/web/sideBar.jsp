<%@page import="java.util.ArrayList"%>

<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%-- 
    Document   : sideBar
    Created on : Mar 19, 2021, 12:26:42 AM
    Author     : elfek
--%>

<%@page import="souq.com.DataBase"%>
<div id="mainBody">
    <div class="container">
        <div class="row">
            <!-- Sidebar ================================================== -->
            <%! int numOfProduct = 0;
                int totalPrice = 0;
                DataBase d = new DataBase();
                int orderPrice;
                int productNum;
                Cookie[] cookies;
                ArrayList<Cookie> myCookie = new ArrayList<Cookie>();

            %>
            <%
                if (session.getAttribute("reloadUnconfirmedOrder").equals("false") && session.getAttribute("loginState").equals("true")) {
                    System.out.println("From SideBar:" + session.getAttribute("customer_id"));
                    d.connect();
                    ResultSet rs = d.select("select cop.order_id,cop.quantity,p.img_url,cop.price,cop.product_id,p.qunatity,p.name from product p"
                            + " inner join  customer_order_product cop on p.product_id=cop.product_id inner join orders o on "
                            + "cop.order_id=o.order_id where cop.customer_id =" + session.getAttribute("customer_id") + " and o.status='unconfirmed';");
                    while (rs.next()) {
                        numOfProduct += Integer.parseInt(rs.getString("quantity"));
                        totalPrice += Integer.parseInt(rs.getString("price")) * Integer.parseInt(rs.getString("quantity"));
//
                        myCookie.add(new Cookie("productInCart", Integer.toString(numOfProduct)));
                        myCookie.add(new Cookie("id" + rs.getString("product_id"), rs.getString("quantity")));
                        myCookie.add(new Cookie("totalPrice", Integer.toString(totalPrice)));
                        session.setAttribute("orderId", rs.getInt("order_id"));
                    }
//                    
                    for (Cookie c : myCookie) {
                        response.addCookie(c);
                    }
                    System.out.println(session.getAttribute("orderId"));
                    session.setAttribute("reloadUnconfirmedOrder", "true");
                    myCookie.clear();
                }

                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie c : cookies) {
                        if (c.getName().startsWith("productInCart") && c.getValue() != "") {
                            numOfProduct = Integer.parseInt(c.getValue());
                        } else if (c.getName().startsWith("totalPrice") && c.getValue() != "") {
                            totalPrice = Integer.parseInt(c.getValue());
                        }
                    }
                }
            %>
             <div id="sidebar" class="span3">
               <%
                String r = (String) session.getAttribute("role");
                 if(r.equals("c"))
                 {
                   out.print("<div class=\"well well-small\"><a   id=\"myCart\" href=\"Cart.jsp\"><img src=\"themes/images/ico-cart.png\" alt=\"cart\"><span id=\"add-tocart\">"+numOfProduct+"</span> Items in your cart  <span class=\"badge badge-warning pull-right\" >$<span id=\"total-price\"> "+totalPrice+" </span></span></a></div>");
                 }
                 else
                 {
                     out.print("<div class=\"well well-small\"><a   id=\"myCart\" ><img src=\"themes/images/ico-cart.png\" alt=\"cart\"><span id=\"add-tocart\"></span> No Cart for you  <span class=\"badge badge-warning pull-right\" >$<span id=\"total-price\"> </span></span></a></div>");
                 }
            %>
                <ul id="sideManu" class="nav nav-tabs nav-stacked">
                    <%
                        int numOfFeatured = 0;
                        numOfFeatured = d.getNumOfDev(out, request, session);
                        numOfFeatured--;
                        if (session.getAttribute("orderId") != null) {
                            d.updateOrder(cookies, (Integer) session.getAttribute("orderId"), (Integer) session.getAttribute("customer_id"), "unconfirmed");
                        }
                        numOfProduct = 0;
                        totalPrice = 0;
                    %>
                </ul>
                <br/>

                <div class="thumbnail">
                    <img src="themes/images/payment_methods.png" title="Bootshop Payment Methods" alt="Payments Methods">
                    <div class="caption">
                        <h5>Payment Methods</h5>
                    </div>
                </div>
            </div>
            <!-- Sidebar end=============================================== -->
