<%-- 
    Document   : loginform
    Created on : Mar 19, 2021, 10:31:19 PM
    Author     : aya  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
         <%
            session.setAttribute("loginState", "false");
            RequestDispatcher header = request.getRequestDispatcher("guestHeader.jsp");
            header.include(request, response);
        %>
        <!-- Header End====================================================================== -->
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    <!-- Sidebar ================================================== -->
                     <%@ include file="sideBar.jsp" %>
                    <!-- Sidebar end=============================================== -->
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="index.jsp">Home</a> <span class="divider">/</span></li>
                            <li class="active">Login</li>
                        </ul>
                        <h3> Login</h3>	

                        <div class="span1"> &nbsp;</div>
                        <div class="span4">
                            <div class="well">
                                <h5>ALREADY REGISTERED ?</h5>
                                <form method="post" action="checklogin">
                                    <div class="control-group">
                                        <label class="control-label" for="inputEmail1">User Name</label>
                                        <div class="controls">
                                            <input class="span3"  type="text" name="username"  id="inputEmail1" placeholder="User Name">
                                        </div>
                                    </div>
                                    <div class="control-group">
                                        <label class="control-label" for="inputPassword1">Password</label>
                                        <div class="controls">
                                            <input type="password" class="span3"  name="password" id="inputPassword1" placeholder="Password">

                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <div class="controls">
                                            <button type="submit" class="btn">Sign in</button> <a href="forgetpass.html">Forget password?</a>

                                        </div>
                                    </div>
                                
                                </form>
                                <form action ="register.jsp">
                                    <button type="submit" class="btn block" >Create Your Account</button>

                                </form>
                            </div>
                        </div>
                    </div>	

                </div>
            </div></div>
    </div>
</div>
</div>
</div>

    <!-- MainBody End ============================= -->
    <br/>
    <!-- Footer ================================================================== -->
     <%@ include file="Footer.html" %>