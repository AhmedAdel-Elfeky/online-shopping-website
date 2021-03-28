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
public class Navbar extends HttpServlet {

    Connection c = null;
    PreparedStatement ps;
    String URL = "jdbc:postgresql://localhost:5432/e_commerce";
    String uname;
    String password;
    ResultSet result = null;
    int flag;
    HttpSession session = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        PrintWriter out = response.getWriter();
        try {

            Class.forName("org.postgresql.Driver");

            session = request.getSession(false);
            c = DriverManager.getConnection(URL, "postgres", "ayaabdelkader");

            ps = c.prepareStatement("select uname , password from customer where uname=? and password=?  ",
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, uname);
            ps.setString(2, password);
            result = ps.executeQuery();

            if (result.next()) {
                if (result.getString("uname").equals(uname) && result.getString("password").equals(password)) {
                    session = request.getSession(true);
                    session.setAttribute("loginState", "true");
                    session.setAttribute("name", uname);

                    RequestDispatcher disp = request.getRequestDispatcher("guestHeader.jsp");
                    disp.include(request, response);

                    RequestDispatcher side = request.getRequestDispatcher("SideBar.jsp");
                    side.include(request, response);
                }
            } else {
                session.setAttribute("loginState", "false");
                System.out.println("no");

            }

            ps.close();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed");
        }

    }

}
