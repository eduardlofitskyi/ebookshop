package servlet;

import Entity.Book;
import Entity.Cart;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Eduard on 10.11.2014.
 */

@WebServlet("/checkout")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String connectionURL = "jdbc:mysql://localhost:3306/ebookshop";

        Connection connection = null;


        Statement stmt = null;

        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");



        User user = (User)req.getSession().getAttribute("user");
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        int id_user = user.getId();
        List<Book> list = cart.getItems();

        DateTimeFormatter  dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("YYYY-MM-d hh:mm:ss");

        try  {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(connectionURL, "root", "root");

            stmt = connection.createStatement();

            stmt.execute("INSERT INTO orders (id_user, date_order) VALUES ('" + id_user + "', '" + dateTimeFormatter.format(LocalDateTime.now()) + "');");

            ResultSet resultSet = stmt.executeQuery("SELECT id_order FROM orders ORDER BY id_order DESC LIMIT 1");
            resultSet.next();
            int max_id = resultSet.getInt("id_order");
            resultSet.close();

            for (Book book:list){
                stmt.execute("INSERT INTO ordered_book (id_order, id_book, amount) VALUES ('"+max_id+"','"+book.getId()+"','"+book.getAmountOrdered()+"')");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (connection!=null)connection.close();
                if (stmt!=null)stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        req.setAttribute("books",list);


        RequestDispatcher dispatcher = req.getRequestDispatcher("checkout.jsp");

        if (dispatcher != null) {
            dispatcher.forward(req, resp);

        }

    }
}
