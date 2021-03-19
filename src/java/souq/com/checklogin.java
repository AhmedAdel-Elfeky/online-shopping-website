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

    static final String url = "jdbc:postgresql://localhost:5432/e_commerce";
    static final String userName = "postgres";
    static final String Password = "ayaabdelkader";
    int flag;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, userName, Password);
            stmt = conn.createStatement();
            String sql = "select * from customer where uname= '" + username + "' ;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                if (rs.getString(6).equals(username) && rs.getString(12).equals(password)) {
                    resp.getWriter().println("the user exit in Database");
                    flag = 0;
                    break;
                } else {
                    flag = 1;
                }
            }
            if (flag == 1) {

                resp.getWriter().println("No exit");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(checklogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(checklogin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
