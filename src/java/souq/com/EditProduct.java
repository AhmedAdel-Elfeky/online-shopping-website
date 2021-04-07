/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Delayed;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author elfek
 */
public class EditProduct extends HttpServlet {

    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
             int productId = Integer.parseInt(request.getParameter("product_id"));
             DataBase d = new DataBase();
             d.editProduct(response, request,productId);
            
            HttpSession session = request.getSession(false);
            session.setAttribute("Edditproduct","yes");
           
             RequestDispatcher requestDispatcher = request.getRequestDispatcher("/EditProduct.jsp");
             requestDispatcher.forward(request,response);
           
        
    }

   
}