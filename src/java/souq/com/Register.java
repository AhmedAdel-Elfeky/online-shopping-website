
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author m_elieba
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    DataBase db = new DataBase();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.

        PrintWriter out = resp.getWriter();
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String uname = req.getParameter("uname");
        String email = req.getParameter("email");
        String addLine1 = req.getParameter("addl1");
        String addLine2 = req.getParameter("addl2");
        String city = req.getParameter("city");
        String zip = req.getParameter("zip");
        String DOB = req.getParameter("age");
        String password = req.getParameter("password");

        String full_address = addLine1 + " " + addLine2 + " " + city + "/" + zip;
        String job = req.getParameter("job");
        String interestsString = "";
        String creditNumber = req.getParameter("creditnumber");
        String[] interests = req.getParameterValues("interests");
         
        
        if (interestsString != null) {
            for (String interest : interests) {
                interestsString += interest + ",";

            }
        }
         else {
                interestsString = "";
                 
            }
            interestsString = interestsString.replaceAll(",$", "");

            try {
                db.connect();
                ResultSet unameRS = db.select("select uname from customer where uname like '" + uname + "'");
                ResultSet emailRS = db.select("select mail from customer where mail like '" + email + "'");
                ResultSet creditNumberRS = db.select("select credit_num from customer where credit_num like '" + creditNumber + "'");
                req.setAttribute(uname, unameRS);
                req.setAttribute(email, emailRS);
                req.setAttribute(creditNumber, creditNumberRS);
                req.getRequestDispatcher("register.jsp").forward(req, resp);

                if (unameRS.next()) {

                } else if (emailRS.next()) {

                } else if (creditNumberRS.next()) {
                
                } else {

                    db.DML("INSERT INTO customer (fname,lname,uname,password,mail,job,credit_num,address,interests,dob,role) VALUES ('" + fname + "','" + lname + "','" + uname + "','" + password + "','" + email + "','" + job + "','" + creditNumber + "','" + full_address + "','" + interestsString + "','" + DOB + "','c')");
                    resp.sendRedirect("index.jsp");
                      req.getRequestDispatcher("index.jsp").forward(req, resp);

                }

                db.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
