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
    <link rel="stylesheet" href="bootstrap/css/font-awesome.min.css">
<!--    <link rel="stylesheet" href="bootstrap/css/imageProfile.css">-->
	<style type="text/css" id="enject"></style>
  </head>
<body onpageshow="reloadPage()">
<div id="header">
<div class="container">
<div id="welcomeLine" class="row">
    <%
        String loginstate = (String) session.getAttribute("loginState");
        if(loginstate.equals("false"))
        {
            out.print("<div class=\"span6\">Welcome!<strong> User</strong></div>	");
        }
        else
        {
            String role = (String) session.getAttribute("role");
            if(role.equals("a"))
            {
                out.print("<div class=\"span6\">Welcome!<strong> admin  "+(String) session.getAttribute("fname")+" "+(String) session.getAttribute("lname")+"</strong></div>");
            }
            else
            {
                out.print("<div class=\"span6\">Welcome!<strong> "+(String) session.getAttribute("fname")+" "+(String) session.getAttribute("lname")+"</strong></div>	");
            }
        }
    %>
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
    <%
        if( ((String) session.getAttribute("role")).equals("a"))
        {
            out.print("<form class=\"form-inline navbar-search\" method=\"get\" action=\"AdminSearchProducts\" >");
        }
        else 
        {
            out.print("<form class=\"form-inline navbar-search\" method=\"get\" action=\"SearchOnProduct\" >");
        }
        %>
                    <input style="width: 130px;" id="srchFld" class="srchTxt" type="text" name="search" />
		  <select style="width:90px" class="srchTxt" name="category">
                      <option value="All">All</option>
                      <option value="mobile">Mobiles</option>
                      <option value="labtop">Labtops</option>
		</select>
                 <select style="width:140px" class="price" name="price">
                      <option value="">All</option>
                      <option value="1000 and 5000">1000 - 5000</option>
                      <option value="5001 and 10000">5000 - 10000</option>
                      <option value="10001 and 20000">10000 - 20000</option>
                      <option value="20001 and 30000">20000 - 30000</option>
		</select> 
		  <button type="submit" id="submitButton" class="btn btn-primary">Go</button>
    </form>
    <ul id="topMenu" class="nav pull-right">
       <%
            if( ((String)session.getAttribute("loginState")) == null)
            {
                 out.print("<li class=\"\"><a href=\"index.jsp\">Home</a></li>");
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
                     out.print("<li class=\"\"><a href=\"index.jsp\">Home</a></li>");
                    out.print("<li class=\"\"><a href=\"register.jsp\">Sign up</a></li>");
                    out.print("<li class=\"\"><a href=\"contact.html\">Contact</a></li>");
                    out.print("<li class=\"\">");
                    out.print("<a href=\"login.jsp\" role=\"button\" style=\"padding-right:0\"><span class=\"btn btn-large btn-success\">Login</span></a>");
                    out.print("</li>");
                }
                else{
                    String role = (String) session.getAttribute("role");
                    if(role.equals("a"))
                    {
                        out.print("<li class=\"\"><a href=\"AddProduct.jsp\">Add Product</a></li>");
                        out.print("<li class=\"\"><a href=\"Users.jsp\">Customers</a></li>");
                        out.print("<li class=\"\"><a href=\"Profile?customer_id="+(int)session.getAttribute("customer_id")+"\">Profile</a></li>");
                        out.print("<li class=\"\" ><a href=\"AdminSearchProducts\" >Products</a></li>");
                        out.print("<li class=\"\">");
                        out.print("<a href=\"login.jsp?logout=false\"  role=\"button\" style=\"padding-right:0\"><span class=\"btn btn-large btn-success\">Logout</span></a>");
                        out.print("</li>");
                        
                    }
                    else
                    {
                         out.print("<li class=\"\"><a href=\"index.jsp\">Home</a></li>");
                        out.print("<li class=\"\"><a href=\"ListOrders.jsp?id="+(int)session.getAttribute("customer_id")+"\">Orders</a></li>");
                        out.print("<li class=\"\"><a href=\"Profile?customer_id="+(int)session.getAttribute("customer_id")+"\">Profile</a></li>");
                        out.print("<li class=\"\" ><a href=\"SearchOnProduct\" >Products</a></li>");
                        out.print("<li class=\"\">");
                        out.print("<a href=\"login.jsp?logout=false\" role=\"button\" style=\"padding-right:0\"><span class=\"btn btn-large btn-success\">Logout</span></a>");
                        out.print("</li>");
                    }
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

