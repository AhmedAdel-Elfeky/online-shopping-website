<%-- 
    Document   : AdminSideBar
    Created on : Mar 24, 2021, 11:51:36 PM
    Author     : elfek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="souq.com.DataBase"%>
<div id="mainBody">
    <div class="container">
        <div class="row">
            <!-- Sidebar ================================================== -->
            <div id="sidebar" class="span3">


                <ul id="sideManu" class="nav nav-tabs nav-stacked">
                    <%
                        int numOfFeatured = 0;
                        DataBase d = new DataBase();
                        numOfFeatured = d.getNumOfDev(out,request,session);
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