<%-- 
    Document   : forgetpass
    Created on : Apr 7, 2021, 11:52:33 AM
    Author     : eliba 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="souq.com.DataBase"%>
 
 <%@ include file="guestHeader.jsp" %>

<!-- Header End====================================================================== -->
<div id="mainBody">
	<div class="container">
	<div class="row">
<!-- Sidebar ================================================== -->
	<%@ include file="sideBar.jsp" %>
<!-- Sidebar end=============================================== -->
	<div class="span9">
    <ul class="breadcrumb">
		<li><a href="index.html">Home</a> <span class="divider">/</span></li>
		<li class="active">Forget password?</li>
    </ul>
	<h3> FORGET YOUR PASSWORD?</h3>	
	<hr class="soft"/>
	
	<div class="row">
		<div class="span9" style="min-height:900px">
			<div class="well">
			<h5>Reset your password</h5><br/>
			Please enter the email address for your account. A verification code will be sent to you. Once you have received the verification code, you will be able to choose a new password for your account.<br/><br/><br/>
                        <form method="post" action="SendEmail">
			  <div class="control-group">
				<label class="control-label" for="inputEmail1">E-mail address</label>
				<div class="controls">
                                    <input class="span3"  type="text" id="inputEmail1" placeholder="Email" name="email">
                                    <%
                                        if( ((String) session.getAttribute("emailState")).equals("wrong") )
                                        {
                                             out.print("<p class=\"addproduct_errors\" style=\"display:inline;color: red;visibility: visible\">  This email doesn\'t exist</p>");
                                        }
                                        else if(((String) session.getAttribute("emailState")).equals("ok"))
                                        {
                                            out.print("<p class=\"addproduct_errors\" style=\"display:inline;color: green;visibility: visible\">  An email was sent to you</p>");
                                        }
                                        else if(((String) session.getAttribute("emailState")).equals("wating"))
                                        {
                                            out.print("<p class=\"addproduct_errors\" style=\"display:inline;color: black;visibility: visible\">  An email are being sent to you....</p>");
                                        }
                                        %>
				</div>
			  </div>
			  <div class="controls">
                              <input type="submit" class="btn block" value="Submit">
			  </div>
			</form>
		</div>
		</div>
	</div>	
	
</div>
</div>
</div>
</div>
</div>
</div>
</div>
<!-- MainBody End ============================= -->
<%@ include file="Footer.html" %>