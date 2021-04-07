package souq.com;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;

import javax.mail.internet.*;

import javax.activation.*;

public class SendEmail extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DataBase db = new DataBase();
        String from = "mohamed.eliba712@gmail.com";
        String host = "smtp.gmail.com";
        String port = "465";
        String to = request.getParameter("email");

        Properties props = new Properties();
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, "ydmrcxtzixedmrla");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        db.connect();
        HttpSession s = request.getSession(false);

        try {
            
            ResultSet rs = db.select("select * from customer where mail like '" + to + "'");
            if (rs.next()) {

                String uname = rs.getString("uname");
                String password = rs.getString("password");
//                ResultSet passwordRS = db.select("select password from customer where mail like '" + to + "'");
                // Set From: header field of the header.
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("This is the Subject Line!");

                // Now set the actual message
                message.setText("your user name is: " + uname + "\n" + "and, your password is: " + password);

                System.out.println("sending...");
                
                // Send message
                Transport.send(message);
                s.setAttribute("emailState","ok");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/forgetpass.jsp");
                requestDispatcher.forward(request,response);
            } else {
                s.setAttribute("emailState","wrong");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/forgetpass.jsp");
                requestDispatcher.forward(request,response);
                
                
            }
        } catch (SQLException | MessagingException ex) {
            ex.printStackTrace();
        }
        db.disconnect();

    }

}