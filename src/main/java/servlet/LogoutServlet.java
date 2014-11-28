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
import java.io.PrintWriter;

/**
 * Created by Eduard on 13.11.2014.
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();

        User user = (User)session.getAttribute("user");
        Cart cart = (Cart)session.getAttribute("cart");

        user.setName(null);
        cart=null;

        out.println("<a href='http://localhost:80/querybook.jsp'>---> Go to home page</a>");
    }
}
