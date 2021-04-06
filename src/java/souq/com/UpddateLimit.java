///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package souq.com;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author m_elieba
// */
//public class UpddateLimit extends HttpServlet {
//
//    String creditLimitHolder;
//    DataBase db = new DataBase();
//    ResultSet rs;
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String creditLimit = req.getParameter("creditLimit");
//        //        super.doPost(req, resp); //To change body of generated methods, choose Tools | Templates.
//        Cookie[] cookiess = req.getCookies();
//        for (Cookie cooky : cookiess) {
//            if (cooky.getName().contains("updateUserIdLimit")) {
//                creditLimitHolder = cooky.getValue();
//                System.out.println(creditLimitHolder);
//            }
//        }
//        try {
//            db.connect();
////            rs = db.select("select * from customer");
////            while (rs.next()) {
//                if (creditLimit != null && !"".equals(creditLimit)) {
//                    db.DML("UPDATE customer SET credit_limit = " + Long.parseLong(creditLimit) + "where customer_id =" + creditLimitHolder);
//                }
//                resp.sendRedirect("Users.jsp");
//                db.disconnect();
////            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UpddateLimit.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//}
