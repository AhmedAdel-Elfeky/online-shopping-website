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
<%! String numOfProduct="0";
    String totalPrice="0";%>
        <%
             Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                    for (Cookie c : cookies) {
                        if (c.getName().startsWith("productInCart")) {
                            numOfProduct = c.getValue();
                        }
                        else if (c.getName().startsWith("totalPrice")) {
                            totalPrice = c.getValue();
                        }
                    }
            }
        %>
	<div id="sidebar" class="span3">
            <div class="well well-small"><a   id="myCart" href="Cart.jsp"><img src="themes/images/ico-cart.png" alt="cart"><span id="add-tocart"><%=numOfProduct%></span> Items in your cart  <span class="badge badge-warning pull-right" >$<span id="total-price"> <%=totalPrice%> </span></span></a></div>
		<ul id="sideManu" class="nav nav-tabs nav-stacked">
			 <%
                                int numOfFeatured=0;
                                DataBase d = new DataBase();
                                numOfFeatured = d.getNumOfDev(out,request,session);
                                numOfFeatured--;
                                if(session.getAttribute("orderId") != null)
                                   d.updateOrder(cookies,(Integer)session.getAttribute("orderId"),(Integer)session.getAttribute("customer_id"),"unconfirmed");
                                
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
