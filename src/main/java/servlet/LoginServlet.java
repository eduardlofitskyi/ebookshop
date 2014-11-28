package servlet;

import Entity.Cart;
import Entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

/**
 * Created by Eduard on 13.11.2014.
 */

@WebServlet ("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String connectionURL = "jdbc:mysql://localhost:3306/ebookshop";

        Connection connection = null;

        ResultSet rset;

        String username= req.getParameter("username");

        String pass= req.getParameter("pass");

        String sqlStr = "";

        Statement stmt = null;

        resp.setContentType("text/html");

        try{
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(connectionURL, "root",
                    "root");

            stmt = connection.createStatement();

            sqlStr += "SELECT * FROM user WHERE name = '" + username+"' AND pass ='"+pass+"'";

            rset = stmt.executeQuery(sqlStr);

            if (rset.next()){
                HttpSession session = req.getSession(true);
                Cart cart;
                User user;
                synchronized (session) {
                    cart = (Cart) session.getAttribute("cart");
                    user = (User) session.getAttribute("user");
                    if (cart == null) {
                        cart = new Cart();
                        session.setAttribute("cart", cart);
                    }
                    if (user == null) {
                        user = new User();
                        session.setAttribute("user", user);
                    }
                }
                user.setId(rset.getInt("id_user"));
                user.setName(rset.getString("name"));
                user.setPass(rset.getString("pass"));
                user.setEmail(rset.getString("email"));


            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        resp.sendRedirect(req.getHeader("referer"));



    }
}
