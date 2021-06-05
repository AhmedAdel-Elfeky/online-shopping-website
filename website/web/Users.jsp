<%-- 
    Document   : Users.jsp
    Created on : Mar 20, 2021, 7:55:19 PM
    Author     : m_elieba
--%>
<%@page import="java.sql.*"%>
<%@page import="souq.com.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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

                    db.disconnect();
                    for (Cookie cooky : cookiess) {
                        if (cooky.getName().contains("userId")) {
                            db.connect();
                            db.DML("Delete from customer where customer_id = " + cooky.getValue() );
                            db.disconnect();
                        } else if (cooky.getName().contains("updateUserIdLimit")) {
                            System.out.println(cooky.getValue());
                            db.connect();
                            db.DML("UPDATE customer SET credit_limit = " + cooky.getValue() + "where customer_id =" + cooky.getName().substring(17));
                            db.disconnect();
                        }
                    }

                %>
                <%                        db.connect();
                    String query = "select * from customer where role='c';";
                    
                    rs = db.select(query);
                    out.println("<table class=\"table table-bordered\">\n"
                            + "<tr>\n"
                            + "<th>Users</th>\n"
                            + "<th>Action</th>\n"
                            + "</tr></form>\n");
                    while (rs.next()) {
                         int c_id = rs.getInt(1);
                        out.println(" <tr id =\"row" + rs.getString("customer_id") + "\">\n"
                                + "<td>" + rs.getString("fname") + " " + rs.getString("lname") + "<br/>" + rs.getString("mail") + "</td>\n"
                                + "<td>\n"
                                + "<div class=\"input-append\">\n"
                                + "<a  href=\"#login\" onclick=\"updateUserLimit(" + rs.getString("customer_id") + ")\" role=\"button\" data-toggle=\"modal\"><i class=\"fa fa-edit\" style=\"font-size:42px;color:red\"></i></a><button id =" + rs.getString("customer_id") + " onclick=\"deleteUser(this.id)\" class=\"btn btn-danger\" type=\"button\"><i class=\"icon-remove icon-white\"></i></button><button style=\"margin-right:700px;\"  class=\"btn btn-large pull-right\"><a href=\"ListOrders.jsp?id="+c_id+"\">Orders</a></button></div>\n"
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
                                <input type="text" id="inputEmail" class="creditlimit" name="creditLimit" placeholder="update limit">
                            </div>

                            <button onclick="Update()" type="submit" class="btn btn-success">Update</button>
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

<script src="bootstrap/js/user.js"></script>


<%@ include file="Footer.html" %>