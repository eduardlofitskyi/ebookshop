<%@ page import="Entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="Entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 09.11.2014
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><%=(String)request.getAttribute("title")%><%!
      private User user;
    %> - Search BOOKLE</title>
</head>
<body>
<jsp:include page="querybook.jsp" />
<form method="get" action="http://localhost:80/cart">
<input type='hidden' name='todo' value='add' />
<table cellpadding="5">
  <tr>
    <td><b></b></td>
    <td><b>Author</b></td>
    <td><b>Title</b></td>
    <td><b>Price</b></td>
    <td><b>QTY</b></td>
  </tr>
  <% List<Book> data= (List<Book>)request.getAttribute("books");
    for (int i=0; i<data.size(); i++) {
      %>
  <tr>
    <td><input type="checkbox" name="id" value="<%=data.get(i).getId()%>"></td>
    <td><%=data.get(i).getAuthor()%></td>
    <td><%=data.get(i).getTitle()%></td>
    <td><%=data.get(i).getPrice()%></td>
    <td><input type="text" name="id<%=data.get(i).getId()%>" pattern="[1-9]" value="1" size="1"></td>
  </tr>
  <%}%>
</table>
  <%if ((user = (User)session.getAttribute("user"))!=null && user.getName()!=null){%>
  <input type="submit" value="Add to Shopping Cart" />
  <%}%>
</form>
</body>
</html>
