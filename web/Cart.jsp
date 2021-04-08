<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    RequestDispatcher header = request.getRequestDispatcher("guestHeader.jsp");
    header.include(request, response);
%>

<!-- Sidebar =============================================== -->
<%@ include file="sideBar.jsp" %>
<!-- Sidebar end=============================================== -->
<div class="span9">
    <ul class="breadcrumb">
        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
        <li class="active"> SHOPPING CART</li>
    </ul>
    <h3> SHOPPING CART</h3>	
    <hr class="soft"/>



    <%!        String ids = "";
        ResultSet rs = null;
        int totalOrderPrice;
        String quant = null;
        int price;
    %>
    <%        totalOrderPrice = 0;
        if (session.getAttribute("loginState").equals("false")) {
            session.setAttribute("requestedPage", "Cart.jsp");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        } else {
            if ((boolean)session.getAttribute("reload")) {
                session.setAttribute("reload",false);
    %>
    <script>
        window.location.reload();
    </script>
    <%
        }
        ids = "";
        Cookie[] cookie = request.getCookies();
        DataBase db = new DataBase();
        db.connect();
        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().startsWith("id") && !(c.getValue().equals("0"))) {
                    ids += c.getName().substring(2) + ",";
                }
            }
            if (!ids.equals("")) {
                if (session.getAttribute("orderId") == null) {
                    session.setAttribute("orderId", db.createOrder(cookie, (Integer) session.getAttribute("customer_id")));
                }
                StringBuffer sb = new StringBuffer(ids);
                sb.deleteCharAt(sb.length() - 1);
                rs = db.select("select product_id,price,name,img_url,qunatity from product where product_id in (" + sb + ");");
    %>
    <%if (session.getAttribute("exceedLimit") != null && session.getAttribute("exceedLimit").equals("true")) {
    %>
    <div style="background-color:#E8C7C7;padding:25px 35px;">Total price is greater than your balance pleas recharge your wallet then try again.</div> <br><br>
    <%}%>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Product</th>
                <th>Description</th>
                <th>Quantity/Update</th>
                <th>Price</th>
                <th style="text-align: center">Discount</th>
                <th style="text-align: center">Tax</th>
                <th>Total</th>
            </tr>
        </thead>
        <tbody>
            <%while (rs.next()) {%>
            <%for (Cookie c : cookie) {
                    if (c.getName().startsWith("id") && c.getName().substring(2).equals(rs.getString("product_id"))) {
                        quant = c.getValue();
                        price = Integer.parseInt(quant) * Integer.parseInt(rs.getString("price"));
                        totalOrderPrice += price;
                        break;
                    }
                }
                System.out.println(quant);
            %>
            <tr id=<%="row" + rs.getString("product_id")%>>
                <td> <img width="60" src= <%=rs.getString("img_url")%>   alt=""/></td>
                <td style="text-align: center"><%=rs.getString("name")%></td>

                <td>
                    <div class="input-append"><input onChange="changeQuantity(this.id)" class="span1"  style="max-width:34px" size="16" type="text" data-max-qunatity=<%=rs.getString("qunatity")%> value= <%=quant%> id=<%="quantity" + rs.getString("product_id")%>>
                        <button class="btn" type="button" onclick="decrementQuantity(this.id)" id=<%="dec" + rs.getString("product_id")%> >
                            <i class="icon-minus" ></i>
                        </button>
                        <button class="btn" type="button" onclick="incrementQuantity(this.id)" id=<%="incr" + rs.getString("product_id")%>>
                            <i class="icon-plus" ></i>      
                        </button><button  class="btn btn-danger" type="button" onclick="deleteProduct(this.id)" id=<%="remove" + rs.getString("product_id")%> ><i  class="icon-remove icon-white" ></i></button>				</div>
                    <p style="color:rgb(236, 88, 64);font-weight: bold">only <%=rs.getString("qunatity")%> left in stock!</p>
                </td>
                <td style="text-align: center" id=<%="price" + rs.getString("product_id")%>><%=rs.getString("price")%></td>
                <td style="text-align: center">0.00</td>
                <td style="text-align: center">0.00</td>
                <td class='product-total' id="<%="quantity-total" + rs.getString("product_id")%>"><%=price%></td>
            </tr>


            <%}
                db.disconnect();%>
            <tr>
                <td colspan="6" style="text-align:right">Total Price:	</td>
                <td id="total-order-price1"><%=totalOrderPrice%></td>
            </tr>
            <tr>
                <td colspan="6" style="text-align:right">Total Discount:	</td>
                <td style="text-align:center">0.00</td>
            </tr>
            <tr>
                <td colspan="6" style="text-align:right">Total Tax:	</td>
                <td style="text-align:center">0.00</td>
            </tr>
            <tr>
                <td colspan="6" style="text-align:right"><strong>TOTAL = </strong></td>
                <td class="label label-important" style="display:block"> <strong id="total-order-price2"> <%=totalOrderPrice%> </strong></td>
            </tr>
        </tbody>
    </table>
    <a href="SearchOnProduct"  class="btn btn-large"><i class="icon-arrow-left"></i> Continue Shopping </a>
    <a href="ConfirmOrder.jsp" class="btn btn-large pull-right">Proceed to Checkout <i class="icon-arrow-right"></i></a>

    <%} else {%>
    <div style="background-color:#F8F1A2;padding:25px 35px;">Shopping cart is currently empty
        Add items to your cart and view them here before you checkout. </div> <br><br>
    <a href="SearchOnProduct" class="btn btn-large" type="submit"><i class="icon-arrow-left"></i> Continue Shopping </a>

    <%}
            }
        };%>

</div>    

</div></div>
</div>

<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
<script src="bootstrap/js/cart.js"></script>
<%@ include file="Footer.html" %>
