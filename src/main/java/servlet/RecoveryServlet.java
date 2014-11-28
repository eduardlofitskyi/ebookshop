package servlet;

import util.Sendler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Eduard on 28.11.2014.
 */

@WebServlet ("/recovery")
public class RecoveryServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        Connection connection = null;
        String connectionURL = "jdbc:mysql://localhost:3306/ebookshop";
        ResultSet rset;
        resp.setContentType("text/html");
        Statement stmt = null;
        String email = req.getParameter("email");

        String pass;
        String username;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(connectionURL, "root",
                    "root");

            stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery("SELECT * FROM user WHERE email ='"+email+"'");
            if (resultSet.next()){
                pass=resultSet.getString("pass");
                username = resultSet.getString("name");
                new Sendler(username,pass,email).start();
                out.println("You username and pass was sent to your email");
                out.println("<a href='http://localhost:80/querybook.jsp'>Home</a>");

            }else {
                out.println("You input incorrect e-mail");
                out.println("<a href='http://localhost:80/querybook.jsp'>Home</a>");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
