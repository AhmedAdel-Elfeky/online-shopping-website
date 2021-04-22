/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package souq.com;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
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

    int userId = 0;

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};
        String comment = req.getParameter("comment");
        userId = (int) session.getAttribute("customer_id");
        String[] date = java.time.LocalDate.now().toString().split("\\-");
        String month = monthName[Integer.parseInt(date[1])];
        PrintWriter out = resp.getWriter();
        ProductComment newComment = new ProductComment();
        newComment.setComment(comment);
        newComment.setUserId(userId);
        newComment.setProductId(Integer.parseInt(ProductDetails.productId));
        newComment.setTimer(MongoCRUD.strDate);
        MongoCRUD.connect();
        MongoCRUD.insert(newComment);

        if (session.getAttribute("loginState").toString().equals("true")) {
            out.println("<span style='font-size:18px;'>By <b>" + session.getAttribute("fname") + " " + session.getAttribute("lname") + "</b>  on " + MongoCRUD.strDate + "</span>\n"
                    + "  <br><br>" + comment);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}