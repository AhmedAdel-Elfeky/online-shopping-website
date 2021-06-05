
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author m_elieba
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    DataBase db = new DataBase();
     HttpSession session=null;

    public static boolean isCreditCardValid(long number) {
        return (getSize(number) >= 13
                && getSize(number) <= 16)
                && (prefixMatched(number, 4)
                || prefixMatched(number, 5)
                || prefixMatched(number, 37)
                || prefixMatched(number, 6))
                && ((sumOfDoubleEvenPlace(number)
                + sumOfOddPlace(number)) % 10 == 0);
    }

    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 2; i >= 0; i -= 2) {
            sum += getDigit(Integer.parseInt(num.charAt(i) + "") * 2);
        }

        return sum;
    }

    public static int getDigit(int number) {
        if (number < 9) {
            return number;
        }
        return number / 10 + number % 10;
    }

    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String num = number + "";
        for (int i = getSize(number) - 1; i >= 0; i -= 2) {
            sum += Integer.parseInt(num.charAt(i) + "");
        }
        return sum;
    }

    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }

  
    public static int getSize(long d) {
        String num = d + "";
        return num.length();
    }

    public static long getPrefix(long number, int k) {
        if (getSize(number) > k) {
            String num = number + "";
            return Long.parseLong(num.substring(0, k));
        }
        return number;
    }

    public static boolean isEmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        String fname = req.getParameter("fname");
        String lname = req.getParameter("lname");
        String uname = req.getParameter("uname");
        String email = req.getParameter("email");
        String addLine1 = req.getParameter("addl1");
        String addLine2 = req.getParameter("addl2");
        String city = req.getParameter("city");
        String DOB = req.getParameter("age");
        String password = req.getParameter("password");
        String zip = req.getParameter("zip");
        String phone = req.getParameter("phone");

        String creditNumber = req.getParameter("creditnumber");

        String full_address = addLine1 + " " + addLine2 + " " + city + "/" + zip;
        String job = req.getParameter("job");
        String interestsString = "";
        String[] interests = req.getParameterValues("interests");
        if (interests != null) {
            for (String interest : interests) {
                interestsString += interest + ",";

            }
        } else {
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

            if (unameRS.next() || emailRS.next() || creditNumberRS.next()) {
                req.getRequestDispatcher("register.jsp").forward(req, resp);

            } else if (creditNumber != null && phone != null && zip != null) {
                try {
                    Long.parseLong(creditNumber);
                    Long.parseLong(phone);
                    Long.parseLong(zip);
                    if (isEmailValid(email) && isCreditCardValid(Long.parseLong(creditNumber))) {
                        db.DML("INSERT INTO customer (fname,lname,uname,password,mail,job,credit_num,address,interests,dob,role) VALUES ('" + fname + "','" + lname + "','" + uname + "','" + password + "','" + email + "','" + job + "','" + creditNumber + "','" + full_address + "','" + interestsString + "','" + DOB + "','c')");
                       
//                    session = req.getSession(true);
//                    session.setAttribute("loginState", "true");
//                    session.setAttribute("role", 'c');
//                    session.setAttribute("name",uname);
//                    session.setAttribute("fname",fname);
//                    session.setAttribute("lname",lname);
//                    ResultSet getCusId = db.select("select customer_id from customer where uname="+uname+";");
//                        while (getCusId.next()){
//                            session.setAttribute("customer_id",getCusId.getInt(1));
//                        }
                            
                    
                     resp.sendRedirect("login.jsp");
                    } else {
                        req.getRequestDispatcher("register.jsp").forward(req, resp);
                    }

                } catch (NumberFormatException ex) {
                    req.getRequestDispatcher("login.jsp").forward(req, resp);

                }

            } else {

                db.DML("INSERT INTO customer (fname,lname,uname,password,mail,job,credit_num,address,interests,dob,role) VALUES ('" + fname + "','" + lname + "','" + uname + "','" + password + "','" + email + "','" + job + "','" + creditNumber + "','" + full_address + "','" + interestsString + "','" + DOB + "','c')");
//                 session = req.getSession(true);
//                    session.setAttribute("loginState", "true");
//                    session.setAttribute("role", 'c');
//                    session.setAttribute("name",uname);
//                    session.setAttribute("fname",fname);
//                    session.setAttribute("lname",lname);
//                    ResultSet getCusId2 = db.select("select customer_id from customer where uname="+uname+";");
//                        while (getCusId2.next()){
//                            session.setAttribute("customer_id",getCusId2.getInt(1));
//                        }
                resp.sendRedirect("login.jsp");
            }

            db.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

}
