/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ELROWAD
 */
public class ReviewController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();  
        String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December" };
        String comment = req.getParameter("comment");
        String [] date = java.time.LocalDate.now().toString().split("\\-");
        String month = monthName[Integer.parseInt(date[1])];
        PrintWriter out = resp.getWriter();
        out.println("<span style='font-size:18px;'>By <b>"+session.getAttribute("fname")+" "+session.getAttribute("lname")+"</b>  on "+date[2]+" "+month+" "+date[0]+"</span>\n"
                    + "  <br><br>"+comment);
    }

   
}
