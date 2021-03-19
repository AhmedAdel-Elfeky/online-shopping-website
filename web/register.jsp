<%-- 
    Document   : Register
    Created on : Mar 19, 2021, 7:04:33 PM
    Author     : m_elieba
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <%
            RequestDispatcher header = request.getRequestDispatcher("guestHeader.jsp");
            header.include(request, response);
        %>
<!-- Header End====================================================================== -->
<%@ include file="sideBar.jsp" %> 

<!-- Sidebar end=============================================== -->
	<div class="span9">
    <ul class="breadcrumb">
		<li><a href="index.html">Home</a> <span class="divider">/</span></li>
		<li class="active">Registration</li>
    </ul>
	<h3> Registration</h3>	
	<div class="well">
	<!--
	<div class="alert alert-info fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>LorFfem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	<div class="alert fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	 <div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div> -->
        <form class="form-horizontal" method="post" action="Register">
		<h4>Your personal information</h4>
		
		<div class="control-group">
			<label class="control-label" for="inputFname1">First name <sup>*</sup></label>
			<div class="controls">
                            <input type="text" id="inputFname1" placeholder="First Name" name="fname" required>
			</div>
		 </div>
		 <div class="control-group">
			<label class="control-label" for="inputLnam">Last name <sup>*</sup></label>
			<div class="controls">
                            <input type="text" id="inputLnam" placeholder="Last Name" name="lname" required>
			</div>
		 </div>
                <div class="control-group">
			<label class="control-label" for="inputLnam">User name <sup>*</sup></label>
			<div class="controls">
                            <input type="text" id="inputLnam" placeholder="user name" name="uname" required>
			</div>
		 </div>
		<div class="control-group">
		<label class="control-label" for="input_email">Email <sup>*</sup></label>
		<div class="controls">
                    <input type="email" id="input_email" placeholder="Email" name="email" required>
		</div>
	  </div>	  
	<div class="control-group">
		<label class="control-label" for="inputPassword1">Password <sup>*</sup></label>
		<div class="controls">
                    <input type="password" id="inputPassword1" placeholder="Password" name="password" required>
		</div>
	  </div>	  
		<div class="control-group">
		<label class="control-label">Date of Birth <sup>*</sup></label>
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
			<label class="control-label" for="company">Credit Number</label>
			<div class="controls">
                            <input type="text" id="company" placeholder="Enter your credit number" name="creditnumber"/>
			</div>
		</div>

	<div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
                <strong>This Card belongs to someone else</strong>
	 </div>	
		
		
		
		<div class="control-group">
			<label class="control-label" for="address">Address<sup>*</sup></label>
			<div class="controls">
                            <input type="text" id="address" placeholder="Adress" name="addl1" required/> <span>Street address, P.O. box, company name, c/o</span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="address2">Address (Line 2)<sup>*</sup></label>
			<div class="controls">
                            <input type="text" id="address2" placeholder="Adress line 2" name="addl2" required/> <span>Apartment, suite, unit, building, floor, etc.</span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="city">City<sup>*</sup></label>
			<div class="controls">
                            <input type="text" id="city" placeholder="city" name="city" required/> 
			</div>
		</div>
				
		<div class="control-group">
			<label class="control-label" for="postcode">Zip / Postal Code<sup>*</sup></label>
			<div class="controls">
                            <input type="text" id="postcode" placeholder="Zip / Postal Code" name="zip" required/> 
			</div>
		</div>
		
		
		
		<div class="control-group">
			<label class="control-label" for="phone"> Phone <sup>*</sup></label>
			<div class="controls">
			  <input type="text"  name="phone" id="phone" placeholder="phone" required/>
			</div>
		</div>
		
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
		
	<p>Fields marked with * are required</p>
	
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
<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
	<%@ include file="Footer.html" %> 