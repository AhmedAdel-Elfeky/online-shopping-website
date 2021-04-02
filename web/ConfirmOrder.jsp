<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    RequestDispatcher header = request.getRequestDispatcher("guestHeader.jsp");
    header.include(request, response);
%>

<!-- Sidebar =============================================== -->
<%@ include file="sideBar.jsp" %>
<!-- Sidebar end=============================================== -->
<div clasSs="span9">
    <ul class="breadcrumb">
        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
        <li class="active">Confirm Order</li>
    </ul>
    <h3>Confirm Order</h3>	
    <hr class="soft"/>

    <%!
        String state = "";
    %>
    <%    Cookie[] cookie = request.getCookies();
        state = d.confirmOrder((Integer)session.getAttribute("customer_id"), (Integer) session.getAttribute("orderId"), cookie);
        if (state.equals("unconfirmed")) {
            session.setAttribute("exceedLimit", "true");
            RequestDispatcher rd = request.getRequestDispatcher("Cart.jsp");
            rd.forward(request, response);
        } else if (state.equals("confirmed")) {
            session.setAttribute("exceedLimit", "false");
            for (Cookie c : cookie) {
                c.setValue("");
                c.setMaxAge(0);
                response.addCookie(c);
            }
    %>
   
    <div style="text-align: center"><img src="themes/images/successfully.png" ></div><br>
    <a href="SearchOnProduct" class="btn btn-large" type="submit"><i class="icon-arrow-left"></i> Continue Shopping </a>
    <%}%>

</div></div>
</div>

<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->

<%@ include file="Footer.html" %>