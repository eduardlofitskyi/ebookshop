package servlet;

import Entity.Order;
import Entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduard on 16.11.2014.
 */

@WebServlet ("/show")
public class ShowOrdersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String connectionURL = "jdbc:mysql://localhost:3306/ebookshop";

        Connection connection = null;

        Statement stmt = null;

        List<Order> orderList = new ArrayList<>();

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");

        int id_user = ((User) req.getSession().getAttribute("user")).getId();

        String strSql = "SELECT * FROM orders LEFT JOIN ordered_book on orders.id_order" +
                " = ordered_book.id_order LEFT JOIN books on ordered_book.id_book" +
                " = books.id WHERE id_user = "+id_user;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(connectionURL, "root", "root");

            stmt = connection.createStatement();

            //todo: execute for bought items!
            ResultSet resultSet = stmt.executeQuery(strSql);
            while (resultSet.next()){
                String strDate = resultSet.getString("orders.date_order");
                String author = resultSet.getString("books.author");
                String title = resultSet.getString("books.title");
                int amount = resultSet.getInt("ordered_book.amount");
                float price = resultSet.getFloat("books.price");
                orderList.add(new Order(strDate,author,title,amount, price*amount));
            }

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
        req.setAttribute("list",orderList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("showorders.jsp");

        if (dispatcher != null) {
            dispatcher.forward(req, resp);

        }
    }
}
