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
	<div id="sidebar" class="span3">
		<div class="well well-small"><a id="myCart" href="product_summary.html"><img src="themes/images/ico-cart.png" alt="cart">0 Items in your cart  <span class="badge badge-warning pull-right">$0</span></a></div>
		<ul id="sideManu" class="nav nav-tabs nav-stacked">
			 <%
                                int numOfFeatured=0;
                                DataBase d = new DataBase();
                                numOfFeatured = d.getNumOfDev(out);
                                numOfFeatured--;
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