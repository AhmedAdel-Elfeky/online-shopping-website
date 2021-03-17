/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELROWAD
 */
@WebServlet(name = "SearchOnProduct", urlPatterns = {"/SearchOnProduct"})
public class SearchOnProduct extends HttpServlet {

   
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                String name=request.getParameter("search");  
             response.sendRedirect("https://www.bing.com?q="+name);  
    }
}
