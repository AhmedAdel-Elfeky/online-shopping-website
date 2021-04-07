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
        session.setAttribute("fname", "");
        session.setAttribute("lname", "");
        session.setAttribute("role", "c");
        session.setAttribute("customer_id",-1);
        session.setAttribute("emailState","");

    } else {
        if (loginState.equals("true")) {

        } else {

        }

    }


%>