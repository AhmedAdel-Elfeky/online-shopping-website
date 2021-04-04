<<<<<<< HEAD
<%-- 
    Document   : AddProduct
    Created on : Mar 25, 2021, 5:31:41 PM
    Author     : elfek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="AdminHeader.jsp" %>
<%@ include file="AdminSideBar.jsp" %>

<div class="span9">
    <ul class="breadcrumb">
        <li><a href="AddProduct.jsp">Add Product</a> <span class="divider">-</span></li>
        <li><a href="#">Customers</a> </li>

    </ul>

    <hr class="soft"/>
        <h3> Add Product</h3>
    <hr class="soft"/>

    <form class="form-horizontal" id="addproductForm" onsubmit="return validateMyForm();" method="post" action="AddProduct">
        <h4>Product information</h4>
        <div class="control-group">
            <label class="control-label">Category <sup>*</sup></label>
            <div class="controls">
                <!--                <span>-->
                <label style="display:inline">
                    <input id="mobil_radiabtn" type="radio" name="category" value="1" checked="true" > Mobil
                </label>
                <!--                </span>-->
                <label style="display:inline">
                    <input id="laptop_radiabtn" type="radio" name="category" value="2" style="margin-left: 100px "> Laptop
                </label>
            </div>
            
          
        </div>
        <div class="control-group">
            <label class="control-label" for="input_productname">Product Name <sup>*</sup></label>
            <div class="controls">
                <input type="text" name="pname" id="input_productname" placeholder="Name" value="">
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the name</p>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="input_pdate">Date <sup>*</sup></label>
            <div class="controls">
                <input type="date" name="pdate" id="input_pdate" placeholder="Date" value="">
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must select the date</p>
            </div>
            
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_pprice">Price <sup>*</sup></label>
            <div class="controls">
                <input type="number" min="0" name="pprice" id="input_pprice" placeholder="Price" value="">
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the price</p>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_pquantity">Quantity <sup>*</sup></label>
            <div class="controls">
                <input type="number" min="0" name="pquantity" id="input_pquantity" placeholder="Quantity" value="">
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the quantity</p>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_purl">Image URL <sup>*</sup></label>
            <div class="controls">
                <input type="text" id="input_purl" name="purl" placeholder="URL" value="">
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the URL</p>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_featured">Featured <sup>*</sup></label>
            <div class="controls">
                <label style="display:inline">
                    <input type="radio" id="input_pfeatured" name="featured" value="f" checked="true" > Yes
                </label>  
                <label style="display:inline">
                    <input type="radio" id="input_pufeatured" name="featured" value="uf"  style="margin-left: 100px"> No
                </label>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_pdesc">Description <sup>*</sup></label>
            <div class="controls">
                <textarea  id="input_pdesc" name="description"   rows="6" placeholder="Description"></textarea>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the description</p>

            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_pbrand">Brand <sup>*</sup></label>
            <div class="controls">
                <textarea  id="input_pbrand" name="pBrand"    placeholder="Brand"></textarea>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product brand</p>

            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_preleased">Released <sup>*</sup></label>
            <div class="controls">
                <textarea  id="input_preleased" name="pReleased"    placeholder="Released"></textarea>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product released</p>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_ppackage">Package <sup>*</sup></label>
            <div class="controls">
                <textarea  id="input_ppackage" name="pPackage"   placeholder="Package"></textarea>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product package</p>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_pdisplay">Display <sup>*</sup></label>
            <div class="controls">
                <textarea  id="input_pdisplay" name="pDisplay"    placeholder="Display"></textarea>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product display</p>

            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_pFeatures">Features <sup>*</sup></label>
            <div class="controls">
                <textarea  id="input_pFeatures" name="pFeatures"   placeholder="Features"></textarea>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product features</p>
            </div>
        </div>

        <p><sup>*</sup>Required field	</p>
            
         <%
            String addsucssfull = (String) session.getAttribute("addproduct");
            if(addsucssfull.equals("yes"))
            {
                out.print("<p style=\"margin-left: 18vw;color: green;margin-top: 2vh;position:absolute\" >you added a product successfully<p>");
            }
         %>
        
        <div class="control-group">
            <div class="controls">
                <input id="product_save" class="btn btn-large btn-success" type="submit" value="Add" style="margin-left:  20vw" />
            </div>
        </div>		
    </form>


    <!--    <div id="myTab" class="pull-right">
            <a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>
            <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
        </div>-->
    <br class="clr"/>
</div>



</div>
</div>

<script src="themes/switch/add_product.js" type="text/javascript" charset="utf-8"></script>

<%@ include file="Footer.html" %>