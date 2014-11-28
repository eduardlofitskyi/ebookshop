package servlet;

/**
 * Created by Eduard on 08.11.2014.
 */

import Entity.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/query")
public class QueryJSPServlet extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)

            throws ServletException, IOException {

        // Устанавливаем соединение с БД

        String connectionURL = "jdbc:mysql://localhost:3306/ebookshop";

        Connection connection = null;

        ResultSet rs;

        String author = null;

        response.setContentType("text/html");

        List<Book> dataList = new ArrayList<>();

        try {

            // Загружаем драйвер БД

            Class.forName("com.mysql.jdbc.Driver");

            // Подключаемся к БД

            connection = DriverManager.getConnection(connectionURL, "root",
                    "root");

            // Выбираем данные из БД
            author = request.getParameter("author");

            String sqlStr = "SELECT * FROM books WHERE author LIKE (";
            sqlStr += "'%" + author + "%'";
            sqlStr += ") OR title LIKE ('%"+author+"%')";
            sqlStr += "  AND amount > 0 ORDER BY author ASC, title ASC";

            Statement s = connection.createStatement();

            s.executeQuery(sqlStr);

            rs = s.getResultSet();

            while (rs.next()) {

                // Сохраняем всё в список

                dataList.add(new Book(rs.getInt("id"),rs.getString("author"),rs.getString("title"),rs.getFloat("price"),rs.getInt("amount")));

            }

            rs.close();

            s.close();

        } catch (Exception e) {

            System.out.println("Exception is ;" + e);

        }

        request.setAttribute("books", dataList);
        request.setAttribute("title", author);
        // Переходим на JSP страницу

        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");

        if (dispatcher != null) {

            dispatcher.forward(request, response);

        }

    }
}