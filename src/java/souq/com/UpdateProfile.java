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
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aya
 */
public class UpdateProfile extends HttpServlet {

    Connection c = null;
    Statement stmt;
    String URL = "jdbc:postgresql://localhost:5432/e_commerce";
    ResultSet result = null;
    String dob;
    String job;
    String fname;
    String lname;
    String uname;
    String rolee;
    String mail;
    int creditlimit;
    String creditno;
    String address;

    String pass;
    String intersts;
    Integer id;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        dob = request.getParameter("Date Of Birth");
        job = request.getParameter("job");
        fname = request.getParameter("Firstname");
        lname = request.getParameter("Lastname");
        uname = request.getParameter("Username");
        rolee = request.getParameter("role");
        mail = request.getParameter("mail");
        creditno = request.getParameter("creditno");
        address = request.getParameter("address");

        pass = request.getParameter("Password");
        intersts = request.getParameter("intersts");
        HttpSession httpSession = request.getSession(false);
//        if (httpSession != null) {
            int id = (int) httpSession.getAttribute("customer_id");

//        }
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, "postgres", "postgres");
            stmt = c.createStatement();

            String sql = ("update customer SET dob= '" + dob + "' , job = '" + job + "', fname = '" + fname + 
                    "' , lname = '" + lname + "' , uname = '" + uname + "' , mail = '" + mail + "', credit_num = '" 
                    + creditno + "',  address = '" + address + "'  , password = '" + pass + "'  ,interests = '" 
                    + intersts + "'  where customer_id="+id+";");

            stmt.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
            out.println("failed");
            
        }
            response.sendRedirect("Profile?customer_id="+id);
    }
}
