<%-- 
    Document   : checkSession
    Created on : Mar 20, 2021, 9:26:17 PM
    Author     : elfeky
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    session = request.getSession(false);

    String loginState = (String) session.getAttribute("loginState");
    if (loginState == null) {
        //first time he entered web site
        session.setAttribute("loginState", "false");
        session.setAttribute("name", "");

    } else {
        if (loginState.equals("true")) {

        } else {

        }

    }


%>