<%-- 
    Document   : guestHeader
    Created on : Mar 19, 2021, 12:06:31 AM
    Author     : elfek
--%>

<%@page import="souq.com.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>TAD online Shopping cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

<!-- Bootstrap style --> 
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
    <link href="themes/css/base.css" rel="stylesheet" media="screen"/>
<!-- Bootstrap style responsive -->	
	<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
	<link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
	<link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
<!-- fav and touch icons -->
    <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
	<style type="text/css" id="enject"></style>
  </head>
<body>
<div id="header">
<div class="container">
<div id="welcomeLine" class="row">
	<div class="span6">Welcome!<strong> User</strong></div>	
        <!--session.getAttribute("name")-->
</div>
<!-- Navbar ================================================== -->
<div id="logoArea" class="navbar">
<a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
	<span class="icon-bar"></span>
</a>
  <div class="navbar-inner">
    <a class="brand" href="index.jsp"><img src="themes/images/logo2.png" alt="Bootsshop"/></a>
		<form class="form-inline navbar-search" method="get" action="SearchOnProduct" >
		<input id="srchFld" class="srchTxt" type="text" name="search" />
		  <select class="srchTxt" name="category">
                      <option value="All">All</option>
                      <option value="mobile">Mobiles</option>
                      <option value="labtop">Labtops</option>
		</select> 
		  <button type="submit" id="submitButton" class="btn btn-primary">Go</button>
    </form>
    <ul id="topMenu" class="nav pull-right">
        <li class=""><a href="index.jsp">Home</a></li>
        <%
            if( ((String)session.getAttribute("loginState")) == null)
            {
                out.print("<li class=\"\"><a href=\"register.jsp\">Sign up</a></li>");
                out.print("<li class=\"\"><a href=\"contact.html\">Contact</a></li>");
                out.print("<li class=\"\">");
                out.print("<a href=\"login.jsp\" role=\"button\" style=\"padding-right:0\"><span class=\"btn btn-large btn-success\">Login</span></a>");
                out.print("</li>");
            }
            else
            {
                if(((String)session.getAttribute("loginState")).equals("false"))
                {
                    out.print("<li class=\"\"><a href=\"register.jsp\">Sign up</a></li>");
                    out.print("<li class=\"\"><a href=\"contact.html\">Contact</a></li>");
                    out.print("<li class=\"\">");
                    out.print("<a href=\"login.jsp\" role=\"button\" style=\"padding-right:0\"><span class=\"btn btn-large btn-success\">Login</span></a>");
                    out.print("</li>");
                }
                else{
                    out.print("<li class=\"\"><a href=\"register.jsp\">Profile</a></li>");
                    out.print("<li class=\"\" ><a href=\"SearchOnProduct\" >Products</a></li>");
                    out.print("<li class=\"\">");
                    out.print("<a href=\"login.jsp\" role=\"button\" style=\"padding-right:0\"><span class=\"btn btn-large btn-success\">Log Out</span></a>");
                    out.print("</li>");
                    session.setAttribute("loginState", "true");
            }
            }
            
        %>
    </ul>
  </div>
</div>
</div>
</div>
<!-- Header End====================================================================== -->

