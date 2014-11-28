package servlet;

import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Eduard on 27.11.2014.
 */

@WebServlet ("/registration")
public class RegistrationServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        String pass = req.getParameter("pass");
        String passTmp = req.getParameter("passAddition");

        if (!pass.equals(passTmp)){
            out.println("Your PASS doesn't equals CONFIRM_PASS");
            out.println("<a href='http://localhost:80/registration.jsp'>Input the same passwords</a>");
            return;
        }
        User user = new User(req.getParameter("name"), req.getParameter("e-mail"),pass,req.getParameter("phone"));

        String connectionURL = "jdbc:mysql://localhost:3306/ebookshop";

        Connection connection = null;

        Statement stmt = null;

        resp.setContentType("text/html");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, "root",
                    "root");
            stmt = connection.createStatement();

            ResultSet set = stmt.executeQuery("SELECT * FROM user WHERE name ='"+user.getName()+"'");
            if (set.next()){
                    out.println("Your USERNAME already uses");
                    out.println("<a href='http://localhost:80/registration.jsp'>Try another USERNAME</a>");
                    return;
            }
            set = stmt.executeQuery("SELECT * FROM user WHERE email ='"+user.getEmail()+"'");
            if (set.next()){
                out.println("Your E-MAIL already uses");
                out.println("<a href='http://localhost:80/registration.jsp'>Try another E-MAIL</a>");
                return;
            }

            stmt.execute("INSERT INTO user (name, email, pass, phone) VALUES ('"+user.getName()+"', '"+user.getEmail()+"', '"+user.getPass()+"', '"+user.getPhone()+"')");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


            out.println("Successful registration!");
            out.println("<a href='http://localhost:80/querybook.jsp'>Home</a>");

    }
}
