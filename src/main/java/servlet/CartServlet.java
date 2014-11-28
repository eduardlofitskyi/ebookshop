package servlet;

import Entity.Cart;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by Eduard on 12.11.2014.
 */


@WebServlet ("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession(true);
        Cart cart;
        synchronized (session) {
            cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        }

        String connectionURL = "jdbc:mysql://localhost:3306/ebookshop";

        Connection connection = null;

        ResultSet rset;

        String sqlStr = null;

        Statement stmt = null;

        resp.setContentType("text/html");

        try {
            Class.forName("com.mysql.jdbc.Driver");


            connection = DriverManager.getConnection(connectionURL, "root",
                    "root");

            stmt = connection.createStatement();

            String todo = req.getParameter("todo");
            if (todo==null){
                todo = "view";
            }

            String[] ids = req.getParameterValues("id");
            if (ids == null) todo = "view";

            if (todo.equals("add") || todo.equals("update")){

                for (String id:ids){
                    sqlStr = "SELECT * FROM books WHERE id = '" + id+"'";
                    rset = stmt.executeQuery(sqlStr);
                    rset.next();
                    String title = rset.getString("title");
                    String author = rset.getString("author");
                    float price = rset.getFloat("price");
                    String tmp = req.getParameter("id"+id);
                    int qtyOrdered = Integer.parseInt(tmp);
                    int idInt = Integer.parseInt(id);
                    if (todo.equals("add")) {
                        cart.add(idInt, title, author, price, qtyOrdered);
                    } else if (todo.equals("update")) {
                        cart.update(idInt, qtyOrdered);
                    }
                }

            }

            if (todo.equals("remove")){
                int id = Integer.parseInt(req.getParameter("id"));
                cart.remove(id);
            }



        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection!=null)connection.close();
                if (stmt!=null)stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("cart.jsp");

        if (dispatcher != null) {
            dispatcher.forward(req, resp);

        }

    }
}
