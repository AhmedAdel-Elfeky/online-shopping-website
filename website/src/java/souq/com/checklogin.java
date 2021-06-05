/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.jasper.tagplugins.jstl.core.Catch;

/**
 *
 * @author Aya
 */
public class checklogin extends HttpServlet {

     Connection c = null;
    PreparedStatement ps;
    String URL = "jdbc:postgresql://localhost:5432/e_commerce"; 
    String uname;
    String password;
    ResultSet result = null;
    HttpSession session=null;
    
    

@Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      uname = request.getParameter("username");
      password = request.getParameter("password");
      
        try{
            session=request.getSession(false);
            c = DriverManager.getConnection(URL,"elieba","13058");
            
           ps = c.prepareStatement("select uname , password ,role,fname,lname,customer_id from customer where uname=? and password=?  ",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, uname);
            ps.setString(2, password);
            result = ps.executeQuery();
             if(result.next())
            {
                if(result.getString("uname").equals(uname) && result.getString("password").equals(password))
                {            
                    session = request.getSession(true);
                    session.setAttribute("loginState", "true");
                    session.setAttribute("role", result.getString(3));
                    session.setAttribute("name",uname);
                    session.setAttribute("fname",result.getString(4));
                    session.setAttribute("lname",result.getString(5));
                    session.setAttribute("customer_id",result.getInt(6));
                    if(result.getString(3).equals("c")){
                        if(session.getAttribute("requestedPage") == null){
                            session.setAttribute("reload",true);
                            response.sendRedirect("index.jsp");
                            
                        }
                        else{
                            session.setAttribute("reload",true);
                            response.sendRedirect("Cart.jsp");
                        }
                    }
                    else
                    {
                        response.sendRedirect("AdminSearchProducts");
                    }
                }
            }
            else
            {
                session.setAttribute("loginState", "false");
                response.sendRedirect("login.jsp");           
            }
                         
            ps.close();
            c.close();
            
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("failed");
        }
    
    }

}
