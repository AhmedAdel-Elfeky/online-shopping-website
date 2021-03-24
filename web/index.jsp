<%-- 
    Document   : index
    Created on : Mar 18, 2021, 4:59:36 PM
    Author     : elfek
--%>

<%@page import="souq.com.DataBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <%@ include file="checkSession.jsp" %>

        
           <%@ include file="guestHeader.jsp" %>
        
        <!-- Header End====================================================================== -->
        <div id="carouselBlk">
            <div id="myCarousel" class="carousel slide">
                <div class="carousel-inner">
                    <div class="item active">
                        <div class="container">
                            <a href="register.html"><img style="width:100%" src="themes/images/carousel/1.jpg" alt="special offers"/></a>
                            <div class="carousel-caption">
                                <h4>Second Thumbnail label</h4>
                                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="container">
                            <a href="register.html"><img style="width:100%" src="themes/images/carousel/2.jpg" alt=""/></a>
                            <div class="carousel-caption">
                                <h4>Second Thumbnail label</h4>
                                <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                            </div>
                        </div>
                    </div>

                </div>
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
            </div> 
        </div>        
        
            <!-- Sidebar end=============================================== -->
              <%@ include file="sideBar.jsp" %>
                    
 <!-- ##################################################################################### --> 
                    <div class="span9">		
                        <div class="well well-small">
                            <%
                                out.print("<h4>Featured Products <small class=\"pull-right\">" +numOfFeatured+ "+ featured products</small></h4>");
                            %>
                           
                            <div class="row-fluid">
                                <div id="featured" class="carousel slide">
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <ul class="thumbnails">
                                                <%
                                                    d.getFeaturedDev(out,1);
                                                %>
                                            </ul>
                                        </div>
                                        <div class="item">
                                            <ul class="thumbnails">
                                                <%
                                                    d.getFeaturedDev(out,2);
                                                %>
                                            </ul>
                                        </div>
                                    </div>
                                    <a class="left carousel-control" href="#featured" data-slide="prev">‹</a>
                                    <a class="right carousel-control" href="#featured" data-slide="next">›</a>
                                </div>
                            </div>
                        </div>
    <!-- ##################################################################################### -->                    
                        <h4>Latest Products </h4>
                        <ul class="thumbnails">
                             <%
                                    d.getLatestProducts(out);
                             %>
                        </ul>	

                    </div>
                </div>
            </div>
        </div>
        <!-- Footer ================================================================== -->
          <%@ include file="Footer.html" %>