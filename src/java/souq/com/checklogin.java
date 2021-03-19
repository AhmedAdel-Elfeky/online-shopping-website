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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aya
 */
public class checklogin extends HttpServlet {

     Connection c = null;
    Statement ps;
    String URL = "jdbc:postgresql://localhost:5432/e_commerce"; 
    String uname;
    String password;
    ResultSet result = null;
    int flag;

@Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String  uname = request.getParameter("username");
      String  password = request.getParameter("password");
             PrintWriter out = response.getWriter();
        try{
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL,"postgres","ayaabdelkader");
            ps = c.createStatement();
            result= ps.executeQuery("select * from customer");
            
            while(result.next())
            {
                if(result.getString(6).equals(uname) && result.getString(12).equals(password))
            {
               out.println("exit");
                flag=0;
                break;
            }
            else
                flag=1;
            }
            if(flag==1){
               out.println("not exit");
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
