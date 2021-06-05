<%-- 
    Document   : Register
    Created on : Mar 19, 2021, 7:04:33 PM
    Author     : m_elieba
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.*,java.util.*" %>
<%@page import="javax.servlet.jsp.JspWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="souq.com.DataBase"%>
<%@page import="souq.com.Register"%>

<%
    RequestDispatcher header = request.getRequestDispatcher("guestHeader.jsp");
    header.include(request, response);
%>

<%@ include file="sideBar.jsp" %> 
<%    Cookie cooky = new Cookie("userExist", "true");
%>
<%!
    boolean sucessFlag = true;
%>
<div class="span9">
    <ul class="breadcrumb">
        <li><a href="index.jsp">Home</a> <span class="divider">/</span></li>
        <li class="active">Registration</li>
    </ul>
    <h3> Registration</h3>	
    <div class="well">
        <form class="form-horizontal" method="post" action="Register" id="registerationForm">
            <h4>Your personal information</h4>

            <div class="control-group">
                <label class="control-label" for="inputFname1">First name <sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="inputFname1" placeholder="First Name" name="fname" required>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputLnam">Last name <sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="inputLnam" placeholder="Last Name" name="lname" required>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="inputLnam">User name <sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="inputLnam" placeholder="user name" name="uname" required>
                </div>
            </div>
            <%                    DataBase db = new DataBase();
                db.connect();

                ResultSet unameRS = db.select("select uname from customer where uname like '" + request.getParameter("uname") + "'");
                if (unameRS.next()) {
                    out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                            + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                            + "                <strong>Error!</strong> This User name is already taken \n"
                            + "         </div> ");
                    sucessFlag = false;

                }

            %>
            <div class="control-group">
                <label class="control-label" for="input_email">Email <sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="email" id="input_email" placeholder="Email" name="email" required>
                </div>
            </div>	  
            <%                ResultSet emailRS = db.select("select mail from customer where mail like '" + request.getParameter("email") + "'");
                if (emailRS.next()) {
                    out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                            + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                            + "                <strong>Error!</strong> This Email belongs to someone else\n"
                            + "         </div> ");
                    sucessFlag = false;
                }

            %>

            <%                  if (request.getParameter("email") != null && !Register.isEmailValid(request.getParameter("email"))) {
                    out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                            + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                            + "                <strong>Error!</strong>It's not a valid Email address\n"
                            + "         </div> ");
                }


            %>
            <div class="control-group">
                <label class="control-label" for="inputPassword1">Password <sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="password" id="inputPassword1" placeholder="Password" name="password" required>
                </div>
            </div>	  
            <div class="control-group">
                <label class="control-label">Date of Birth <sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="date" name="age" max="2000-01-01" required>
                </div>
            </div>  

            <div class="control-group">
                <label class="control-label" for="company">Job</label>
                <div class="controls">
                    <input type="text" id="company" placeholder="Enter your job" name="job"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="company">Credit Number<sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="company" placeholder="Enter your credit number" name="creditnumber"/>
                </div>
            </div>
            <%                ResultSet creditNumberRS = db.select("select credit_num from customer where credit_num like '" + request.getParameter("creditnumber") + "'");
                if (creditNumberRS.next()) {
                    out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                            + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                            + "                <strong>Error!</strong> it doesn't seem to be your credit card \n"
                            + "         </div> ");
                    sucessFlag = false;
                }
                db.disconnect();
            %>
            <%
////                String creditNumber = request.getParameter("creditnumber");
                    if (request.getParameter("creditnumber") != null) {
                        try {
                            Long.parseLong(request.getParameter("creditnumber"));
                            if (!Register.isCreditCardValid(Long.parseLong(request.getParameter("creditnumber")))) {
                                out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                                        + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                                        + "                <strong>Error!</strong> it's not a valid Credit Number\n"
                                        + "         </div> ");
                            }
                        } catch (NumberFormatException ex) {
                            out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                                    + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                                    + "                <strong>Error!</strong>It's not a valid Credit Number\n"
                                    + "         </div> ");
                        }

                    }

            %>




            <div class="control-group">
                <label class="control-label" for="address">Address<sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="address" placeholder="Adress" name="addl1" required/> <span>Street address, P.O. box, company name, c/o</span>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="address2">Address (Line 2)<sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="address2" placeholder="Adress line 2" name="addl2" required/> <span>Apartment, suite, unit, building, floor, etc.</span>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="city">City<sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="city" placeholder="city" name="city" required/> 
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="postcode">Zip / Postal Code<sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text" id="postcode" placeholder="Zip / Postal Code" name="zip" required/> 
                </div>
            </div>

            <%    if (request.getParameter ( 
                    "zip") != null) {
                    try {
                        Long.parseLong(request.getParameter("zip"));
                    } catch (NumberFormatException ex) {
                        out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                                + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                                + "                <strong>Error!</strong> it's not a valid ZIP\n"
                                + "         </div> ");
                        sucessFlag = false;
                    }
                }
            %>

            <div class="control-group">
                <label class="control-label" for="phone"> Phone <sup sup style="color: red">*</sup></label>
                <div class="controls">
                    <input type="text"  name="phone" id="phone" placeholder="phone" required/>
                </div>
            </div>
            <%
                if (request.getParameter ( 
                    "phone") != null) {
                    try {
                        Long.parseLong(request.getParameter("zip"));
                    } catch (NumberFormatException ex) {
                        out.println("<div class=\"alert alert-block alert-error fade in\">\n"
                                + "                <button type=\"button\" class=\"close\" data-dismiss=\"alert\">×</button>\n"
                                + "                <strong>Error!</strong> it's not a valid phone number\n"
                                + "         </div> ");
                        sucessFlag = false;
                    }
                }
            %>
            <div class="control-group">
                <label class="control-label" for="aditionalInfo">Interests</label>
                <div class="controls">
                    <br>
                    <input type="checkbox" name="interests" value="Technology"/>
                    <label for="technology">Technology</label>
                    <input type="checkbox" name="interests" value="Sports" />
                    <label for="sports">Sports</label>
                    <input type="checkbox" name="interests" value="Fashion"/>
                    <label for="fashion">Fashion</label>
                    <input type="checkbox" name=interests" value="Entertainment" />
                    <label for="entertainment">Entertainment</label>


                </div>
            </div>

            <p sup style="color: red">Fields marked with * are required</p>

            <div class="control-group">
                <div class="controls">
                    <input type="hidden" name="email_create" value="1">
                    <input type="hidden" name="is_new_customer" value="1">
                    <input class="btn btn-large btn-success" type="submit" value="Register" />
                </div>
            </div>		
        </form>
    </div>

</div>
</div>
</div>
</div>

<script src="themes/js/bootshop.js"></script>
<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
<%@ include file="Footer.html" %> 