<%@ page import="java.util.List" %>
<%@ page import="Entity.Order" %>
<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 20.11.2014
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Orders</title>
</head>
<body>
<jsp:include page="querybook.jsp" />
<%List<Order> list = (List<Order>) request.getAttribute("list");%>
    <%if (list.isEmpty()){%>
        <p>You have not orders</p>
    <%}else{ %>
        <table cellpadding="5" border="1">
            <tr>
                <td><b>Date</b></td>
                <td><b>Author</b></td>
                <td><b>Title</b></td>
                <td><b>QTY</b></td>
                <td><b>Total price</b></td>
            </tr>
            <%for (Order order:list){%>
            <tr>
                <td><%=order.getDate()%></td>
                <td><%=order.getAuthor()%></td>
                <td><%=order.getTitle()%></td>
                <td><%=order.getAmount()%></td>
                <td><%=order.getTotalPrice()%></td>
            </tr>
            <%}%>
        </table>
    <%}%>
</body>
</html>
