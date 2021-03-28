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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aya
 */
public class EditProfile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection c = null;
        Statement stmt;
        String URL = "jdbc:postgresql://localhost:5432/e_commerce";
        ResultSet result = null;
        Statement ps;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(URL, "postgres", "ayaabdelkader");
            ps = c.createStatement();
            result = ps.executeQuery("select * from customer ");

            RequestDispatcher disp = request.getRequestDispatcher("guestHeader.jsp");
            disp.include(request, response);
            RequestDispatcher side = request.getRequestDispatcher("SideBar.jsp");
            side.include(request, response);
            while (result.next()) {
                out.println("<h1 style=\"position: relative;left: 250px;top: 2px  \">Edit Profile</h1><br>\n"
                        + "        <form action=\"UpdateProfile\" method=\"Post\">\n"
                        + "        <div style=\"position: relative;left: 200px;top: 2px;font-size:20px  \">\n"
                        + "            Date Of Birth  &emsp; &emsp; <input type=\"text\" name=\"Date Of Birth\" value=" + result.getString(2) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 200px;top: 2px;font-size:20px  \">\n"
                        + "            Job &emsp; &emsp; &ensp; &emsp;  &emsp; &emsp;<input type=\"text\" name=\"job\" value=" + result.getString(3) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 200px;top: 2px;font-size:20px  \">\n"
                        + "            First Name &emsp;&emsp; &emsp;  <input type=\"text\" name=\"Firstname\" value=" + result.getString(4) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 500px;top: 2px ;font-size:20px \">\n"
                        + "            Last Name &emsp;&emsp; &emsp; <input type=\"text\" name=\"Lastname\" value=" + result.getString(5) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 500px;top: 2px ;font-size:20px \">\n"
                        + "            User Name &ensp; &emsp; &emsp; <input type=\"text\" name=\"Username\" value=" + result.getString(6) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 500px;top: 2px ;font-size:20px \">\n"
                        + "            Email  &emsp;&emsp;&emsp; &emsp; &emsp;  <input type=\"text\" name=\"mail\" value=" + result.getString(8) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 500px;top: 2px ;font-size:20px \">\n"
                        + "            Credit Number &ensp; &emsp;  <input type=\"text\" name=\"creditno\" value=" + result.getString(10) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 500px;top: 2px ;font-size:20px \">\n"
                        + "            Address &emsp; &emsp;&emsp; &emsp; <input type=\"text\" name=\"address\" value=" + result.getString(11) + " style=\"font-size: 17px \" >\n"
                        + "        </div> <br>\n"
                        + "        <div style=\"position: relative;left: 500px;top: 2px ;font-size:20px \">\n"
                        + "            Password &ensp;  &ensp;  &emsp; &emsp;  <input type=\"password\" name=\"Password\" value=" + result.getString(12) + " style=\"font-size: 17px \">\n"
                        + "        </div><br>\n"
                        + "        <div style=\"position: relative;left: 500px;top: 2px;font-size:20px  \">\n"
                        + "            Interests &emsp;  &ensp; &emsp; &emsp; <input type=\"text\" name=\"intersts\" value=" + result.getString(13) + " style=\"font-size: 17px \">\n"
                        + "        </div> <br>\n"
                        + "        <input type=\"submit\" value=\"Confirm Editing\" style=\"position: relative;left: 500px;top: 2px;font-size:20px  \" >\n"
                        + "        </form>"
                        + "");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
