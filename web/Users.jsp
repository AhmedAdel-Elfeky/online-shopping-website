<%-- 
    Document   : Users.jsp
    Created on : Mar 20, 2021, 7:55:19 PM
    Author     : m_elieba
--%>
<%@page import="java.sql.*"%>
<%@page import="souq.com.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Bootshop online Shopping cart</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--        Less styles 
                 Other Less css file //different less files has different color scheam
                     <link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
                     <link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
                     <link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
                
                <link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
                <script src="themes/js/less.js" type="text/javascript"></script> 
        
                 Bootstrap style  -->
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
        <style type="text/css" id="enject"></style>
    </head>
    <body>



        <%@ include file="guestHeader.jsp" %>
        <%--<%@ include file="sideBar.jsp" %>--%>
        <!-- Header End====================================================================== -->
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    <%@ include file="sideBar.jsp" %>
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                            <li class="active"> USERS LIST</li>
                        </ul>
                        <h3>  USERS LIST<a href="index.jsp" class="btn btn-large pull-right"><i class="icon-arrow-left"></i> Back To Home </a></h3>	
                        <hr class="soft"/>
                        <%! ResultSet rs;%>
                        <%! DataBase db = new DataBase();%>
                        <%Cookie[] cookiess = request.getCookies();
                            for (Cookie cooky : cookiess) {
                                if (cooky.getName().contains("userId")) {
                                    db.connect();
                                    db.DML("Delete from customer where customer_id = " + cooky.getValue());
                                    db.disconnect();
                                } 
                               else if (cooky.getName().contains("updateUserIdLimit")) {
                                    db.connect();
                                    db.DML("UPDATE customer SET credit_limit = " + Long.parseLong(request.getParameter("creditLimit")) + "where customer_id =" + cooky.getValue());
                                    db.disconnect();
                                }
                            }
                        %>
                        <%                        db.connect();
                            String query = "select * from customer;";

                            rs = db.select(query);
                            out.println("<table class=\"table table-bordered\">\n"
                                    + "<tr>\n"
                                    + "<th>Users</th>\n"
                                    + "<th>Action</th>\n"
                                    + "</tr></form>\n");
                            while (rs.next()) {

                                out.println(" <tr id =\"row" + rs.getString("customer_id") + "\">\n"
                                        + "<td>" + rs.getString("fname") + " " + rs.getString("lname") + "<br/>" + rs.getString("mail") + "</td>\n"
                                        + "<td>\n"
                                        + "<div class=\"input-append\">\n"
                                        + "<a  href=\"#login\" onclick=\"updateUserLimit(" + rs.getString("customer_id") + ")\" role=\"button\" data-toggle=\"modal\"><i class=\"fa fa-edit\" style=\"font-size:42px;color:red\"></i></a><button id =" + rs.getString("customer_id") + " onclick=\"deleteUser(this.id)\" class=\"btn btn-danger\" type=\"button\"><i class=\"icon-remove icon-white\"></i></button><button style=\"margin-right:700px;\"  class=\"btn btn-large pull-right\">Orders</button></div>\n"
                                        + "</td>\n ");
                            }
                            out.println("</tbody>\n"
                                    + "</table>");
                            db.disconnect();
                        %>
                        <!--<a href="#login" role="button" data-toggle="modal"><span class="btn btn-large btn-success">Login</span></a>-->
                        <div id="login" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h3>Update Limit</h3>
                            </div>
                            <div class="modal-body">
                                <form class="form-horizontal loginFrm" action="Users.jsp" method="post">
                                    <div class="control-group">								
                                        <input type="text" id="inputEmail" name="creditLimit" placeholder="update limit">
                                    </div>

                                    <button type="submit" class="btn btn-success">Update</button>
                                    <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                                </form>
                            </div>
                        </div>		

                        <a href="index.jsp" class="btn btn-large"><i class="icon-arrow-left"></i> Back To Home   </a>
                        <a href="login.html" class="btn btn-large pull-right">Next <i class="icon-arrow-right"></i></a>

                    </div>
                </div></div>
        </div>

        <!-- MainBody End ============================= -->
    </div><!-- Container End -->
</div>
<!-- Placed at the end of the document so the pages load faster ============================================= -->
<script src="themes/js/jquery.js" type="text/javascript"></script>
<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
<script src="themes/js/google-code-prettify/prettify.js"></script>
<script src="bootstrap/js/user.js"></script>
<script src="themes/js/bootshop.js"></script>
<script src="themes/js/jquery.lightbox-0.5.js"></script>

<!-- Themes switcher section ============================================================================================= -->
<div id="secectionBox">
    <link rel="stylesheet" href="themes/switch/themeswitch.css" type="text/css" media="screen" />
    <script src="themes/switch/theamswitcher.js" type="text/javascript" charset="utf-8"></script>
    <div id="themeContainer">
        <div id="hideme" class="themeTitle">Style Selector</div>
        <div class="themeName">Oregional Skin</div>
        <div class="images style">
            <a href="themes/css/#" name="bootshop"><img src="themes/switch/images/clr/bootshop.png" alt="bootstrap business templates" class="active"></a>
            <a href="themes/css/#" name="businessltd"><img src="themes/switch/images/clr/businessltd.png" alt="bootstrap business templates" class="active"></a>
        </div>
        <div class="themeName">Bootswatch Skins (11)</div>
        <div class="images style">
            <a href="themes/css/#" name="amelia" title="Amelia"><img src="themes/switch/images/clr/amelia.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="spruce" title="Spruce"><img src="themes/switch/images/clr/spruce.png" alt="bootstrap business templates" ></a>
            <a href="themes/css/#" name="superhero" title="Superhero"><img src="themes/switch/images/clr/superhero.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="cyborg"><img src="themes/switch/images/clr/cyborg.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="cerulean"><img src="themes/switch/images/clr/cerulean.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="journal"><img src="themes/switch/images/clr/journal.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="readable"><img src="themes/switch/images/clr/readable.png" alt="bootstrap business templates"></a>	
            <a href="themes/css/#" name="simplex"><img src="themes/switch/images/clr/simplex.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="slate"><img src="themes/switch/images/clr/slate.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="spacelab"><img src="themes/switch/images/clr/spacelab.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="united"><img src="themes/switch/images/clr/united.png" alt="bootstrap business templates"></a>
            <p style="margin:0;line-height:normal;margin-left:-10px;display:none;"><small>These are just examples and you can build your own color scheme in the backend.</small></p>
        </div>
        <div class="themeName">Background Patterns </div>
        <div class="images patterns">
            <a href="themes/css/#" name="pattern1"><img src="themes/switch/images/pattern/pattern1.png" alt="bootstrap business templates" class="active"></a>
            <a href="themes/css/#" name="pattern2"><img src="themes/switch/images/pattern/pattern2.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern3"><img src="themes/switch/images/pattern/pattern3.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern4"><img src="themes/switch/images/pattern/pattern4.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern5"><img src="themes/switch/images/pattern/pattern5.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern6"><img src="themes/switch/images/pattern/pattern6.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern7"><img src="themes/switch/images/pattern/pattern7.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern8"><img src="themes/switch/images/pattern/pattern8.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern9"><img src="themes/switch/images/pattern/pattern9.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern10"><img src="themes/switch/images/pattern/pattern10.png" alt="bootstrap business templates"></a>

            <a href="themes/css/#" name="pattern11"><img src="themes/switch/images/pattern/pattern11.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern12"><img src="themes/switch/images/pattern/pattern12.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern13"><img src="themes/switch/images/pattern/pattern13.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern14"><img src="themes/switch/images/pattern/pattern14.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern15"><img src="themes/switch/images/pattern/pattern15.png" alt="bootstrap business templates"></a>

            <a href="themes/css/#" name="pattern16"><img src="themes/switch/images/pattern/pattern16.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern17"><img src="themes/switch/images/pattern/pattern17.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern18"><img src="themes/switch/images/pattern/pattern18.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern19"><img src="themes/switch/images/pattern/pattern19.png" alt="bootstrap business templates"></a>
            <a href="themes/css/#" name="pattern20"><img src="themes/switch/images/pattern/pattern20.png" alt="bootstrap business templates"></a>

        </div>
    </div>
</div>

<span id="themesBtn"></span>
<%@ include file="Footer.html" %>
