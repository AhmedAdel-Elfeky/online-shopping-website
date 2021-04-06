<%-- 
    Document   : ListOrders
    Created on : Apr 5, 2021, 9:34:09 PM
    Author     : elfek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="guestHeader.jsp" %>
<%@ include file="sideBar.jsp" %>

<div class="span9">
       <hr class="soft"/>
        <h3> Orders List</h3>
    <hr class="soft"/>
    
    <%
        int id = (int)session.getAttribute("customer_id");
        d.listOrders(out,id);
        %>
    


    <!--    <div id="myTab" class="pull-right">
            <a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>
            <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
        </div>-->
    <br class="clr"/>
</div>



</div>
</div>

<script src="themes/switch/add_product.js" type="text/javascript" charset="utf-8"></script>

<%@ include file="Footer.html" %>