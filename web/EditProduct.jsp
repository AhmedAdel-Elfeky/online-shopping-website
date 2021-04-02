<%-- 
    Document   : EditProduct
    Created on : Mar 27, 2021, 2:16:47 AM
    Author     : elfek
--%>

<%@page import="souq.com.ProductInfo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="AdminHeader.jsp" %>
<%@ include file="AdminSideBar.jsp" %>

<div class="span9">
    <ul class="breadcrumb">
        <li><a href="AddProduct.jsp">Add Product</a> <span class="divider">-</span></li>
        <li><a href="#">Customers</a> </li>

    </ul>
     <%
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        ProductInfo p = new ProductInfo();
        p = d.getProductInfo(response, request, product_id);
        String[] desc = p.description.split("\\;");
                for (String s : desc) {
                    System.out.println(s);
                }
    %>
    
    <hr class="soft"/>
    <h5 style="display: inline;" > Edit Product 
        
        <%
          out.print("<h3 style=\"display: inline;text-decoration: underline\">"+ p.name+ " </h3>")    ;
        %>
        </h5>
    <hr class="soft"/>
    
   

    <form class="form-horizontal" id="addproductForm" onsubmit="return validateMyForm();" method="post" action="EditProduct">
        <h4>Product information</h4>
        <div class="control-group">
            <label class="control-label">Category <sup>*</sup></label>
            <div class="controls">
                <!--                <span>-->
                <label style="display:inline">
                    
                    <%
                        out.print("<input type=\"hidden\" name=\"product_id\" value=\""+product_id+"\"/>");
                        if(p.categoryId == 1)
                        {
                            out.print(" <input id=\"mobil_radiabtn\" type=\"radio\" name=\"category\" value=\"1\" checked > Mobil");
                            out.print(" </label>"); 
                            out.print(" <label style=\"display:inline\">"); 
                            out.print(" </label>"); 
                            out.print(" <input id=\"laptop_radiabtn\" type=\"radio\" name=\"category\" value=\"2\"  style=\"margin-left: 100px \" > Laptop");
                        }
                        else
                        {
                            out.print(" <input id=\"mobil_radiabtn\" type=\"radio\" name=\"category\" value=\"1\"  > Mobil");
                            out.print(" </label>"); 
                            out.print(" <label style=\"display:inline\">"); 
                            out.print(" </label>"); 
                            out.print(" <input id=\"laptop_radiabtn\" type=\"radio\" name=\"category\" value=\"2\" checked style=\"margin-left: 100px \" > Laptop");
                        }
                        %>              
                </label>
            </div>
            
          
        </div>
        <div class="control-group">
            <label class="control-label" for="input_productname">Product Name <sup>*</sup></label>
            <div class="controls">
                <%
                    out.print(" <input type=\"text\" name=\"pname\" id=\"input_productname\" placeholder=\"Name\" value=\""+p.name+"\">");
                %>
               
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the name</p>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="input_pdate">Date <sup>*</sup></label>
            <div class="controls">
                <%
                    out.print("<input type=\"date\" name=\"pdate\" id=\"input_pdate\" placeholder=\"Date\" value="+p.date+">");
                    %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must select the date</p>
            </div>
            
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_pprice">Price <sup>*</sup></label>
            <div class="controls">
                 <%
                    out.print("<input type=\"number\" min=\"0\" name=\"pprice\" id=\"input_pprice\" placeholder=\"Price\" value="+p.price+">");
                    %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the price</p>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_pquantity">Quantity <sup>*</sup></label>
            <div class="controls">
                 <%
                    out.print("<input type=\"number\" min=\"0\" name=\"pquantity\" id=\"input_pquantity\" placeholder=\"Quantity\" value="+p.qunatity+">");
                    %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the quantity</p>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_purl">Image URL <sup>*</sup></label>
            <div class="controls">
                 <%
                    out.print("<input type=\"text\" name=\"purl\" id=\"input_purl\" placeholder=\"URL\" value="+p.imgUrl+">");
                    %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the URL</p>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_featured">Featured <sup>*</sup></label>
            <div class="controls">
                <label style="display:inline">
                     <%
                        if(p.featured.equals("f"))
                        {
                            out.print(" <input id=\"input_pfeatured\" type=\"radio\" name=\"featured\" value=\"f\" checked > Yes");
                            out.print(" </label>"); 
                            out.print(" <label style=\"display:inline\">"); 
                            out.print(" <input id=\"input_pufeatured\" type=\"radio\" name=\"featured\" value=\"uf\"  style=\"margin-left: 100px \" > No");
                        }
                        else
                        {
                            out.print(" <input id=\"input_pfeatured\" type=\"radio\" name=\"featured\" value=\"f\"  > Yes");
                            out.print(" </label>"); 
                            out.print(" <label style=\"display:inline\">"); 
                            out.print(" <input id=\"input_pufeatured\" type=\"radio\" name=\"featured\" value=\"uf\" checked style=\"margin-left: 100px \" > No");
                        }
                        %>     
                </label>
            </div>
        </div>	 

        <div class="control-group">
            <label class="control-label" for="input_pdesc">Description <sup>*</sup></label>
            <div class="controls">
                 <%
                    out.print("<textarea rows=\"6\" cols=\"790\"  name=\"description\" id=\"input_pdesc\" placeholder=\"Description\" >"+desc[0]+"</textarea>");
                 %>
                 <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter the description</p>

            </div>
        </div>	 
        
        <div class="control-group">
            <label class="control-label" for="input_pbrand">Brand <sup>*</sup></label>
            <div class="controls">
                <%
                    out.print("<textarea  name=\"pBrand\" id=\"input_pbrand\" placeholder=\"Brand\" >"+desc[1]+"</textarea>");
                %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product brand</p>

            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_preleased">Released <sup>*</sup></label>
            <div class="controls">
                <%
                    out.print("<textarea id=\"input_preleased\" name=\"pReleased\"  placeholder=\"Released\" >"+desc[2]+"</textarea>");
                %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product released</p>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_ppackage">Package <sup>*</sup></label>
            <div class="controls">
                <%
                    out.print("<textarea id=\"input_ppackage\" name=\"pPackage\"  placeholder=\"Package\" >"+desc[3]+"</textarea>");
                %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product package</p>
            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_pdisplay">Display <sup>*</sup></label>
            <div class="controls">
                <%
                    out.print("<textarea id=\"input_pdisplay\" name=\"pDisplay\"  placeholder=\"Display\" >"+desc[4]+"</textarea>");
                %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product display</p>

            </div>
        </div>
        
        <div class="control-group">
            <label class="control-label" for="input_pFeatures">Features <sup>*</sup></label>
            <div class="controls">
                <%
                    out.print("<textarea id=\"input_pFeatures\" name=\"pFeatures\"  placeholder=\"Features\" >"+desc[5]+"</textarea>");
                %>
                <p class="addproduct_errors" style="display:inline;color: red;visibility: hidden">  ERROR: you must enter product features</p>
            </div>
        </div>




        <p><sup>*</sup>Required field	</p>        
         <%
            String edditsuccessfull = (String) session.getAttribute("Edditproduct");
            if(edditsuccessfull.equals("yes"))
            {
                out.print("<p style=\"margin-left: 18vw;color: green;margin-top: 2vh;position:absolute\" >you edited a product successfully<p>");
            }
         %>

        <div class="control-group">
            <div class="controls">
               
                <input id="product_save" class="btn btn-large btn-success" type="submit" value="Save" style="margin-left:  20vw;display:inline" />
            </div>
        </div>		
    </form>

    <br class="clr"/>
</div>



</div>
</div>

<script src="themes/switch/add_product.js" type="text/javascript" charset="utf-8"></script>

<%@ include file="Footer.html" %>
