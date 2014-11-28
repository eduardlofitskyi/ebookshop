<%@ page import="java.util.List" %>
<%@ page import="Entity.Book" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 15.11.2014
  Time: 11:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful purchase</title>
</head>
<body>
  <jsp:include page="querybook.jsp" />
  <h1>Your order is successful!</h1>
  <%List<Book> list = (List < Book >)request.getAttribute("books");
  float totalPrice = 0.0f;%>
  <table cellpadding="12" border="2">
    <tr>
      <td><b>Author</b></td>
      <td><b>Title</b></td>
      <td><b>Price</b></td>
      <td><b>QTY</b></td>
    </tr>
    <%for (Book book:list){%>
    <tr>
      <td><%=book.getAuthor()%></td>
      <td><%=book.getTitle()%></td>
      <td><%=book.getPrice()%></td>
      <td><%=book.getAmountOrdered()%></td>
      <%totalPrice+=book.getPrice()*book.getAmountOrdered();%>
    </tr>
    <%}%>
    <% DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm:ss");%>
    <tr><td colspan='5' align='right'>Time: <%=dateTimeFormatter.format(LocalDateTime.now())%></td></tr>
    <tr><td colspan='5' align='right'>Total Price: <%=totalPrice%></td></tr>
  </table>
</body>
</html>
