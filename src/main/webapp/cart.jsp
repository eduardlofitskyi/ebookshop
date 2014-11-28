<%@ page import="Entity.Cart" %>
<%@ page import="Entity.Book" %>
<%@ page import="Entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 12.11.2014
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<jsp:include page="querybook.jsp" />
  <%
    HttpSession s = request.getSession(false);
    Cart cart = null;
    User user = null;
    if (s != null) {
      synchronized (s) {

        cart = (Cart) s.getAttribute("cart");
      }
    } {
      if (cart.isEmpty()){
      %>
    <h1>Your Shopping Cart is empty :(</h1><%
    }else{
        float totalPrice = 0.0f;%>
  <form method="post" action="http://localhost:80/checkout">
  <table cellpadding="5" border="1">
    <tr>
      <td><b>Author</b></td>
      <td><b>Title</b></td>
      <td><b>Price</b></td>
      <td><b>QTY</b></td>
      <td><b>Remove</b></td>
    </tr>

    <%
      for (Book book: cart.getItems()){
        totalPrice+=book.getPrice()*book.getAmountOrdered();
        %>
          <tr>
           <td> <%= book.getAuthor() %></td>
           <td> <%= book.getTitle() %></td>
           <td> <%= book.getPrice() %></td>
           <td>
              <form method="get">
                <input type="hidden" name='todo' value='update' />
                <input type="hidden" name='id' value='<%=book.getId()%>' />
                <input type="text" size="1" pattern="[1-999]" name='id<%=book.getId()%>' value='<%=book.getAmountOrdered()%>' />
                <input type="submit" value="Update">
              </form>
           </td>
           <td>
              <form method="get">
                <input type="hidden" name='todo' value='remove'>
                <input type="hidden" name='id' value='<%=book.getId()%>'>
                <input type="submit" value="Remove">
              </form>
           </td>
          </tr>
    <%
      }
    %>
    <tr><td colspan='5' align='right'>Total Price: <%=totalPrice%></td></tr>
  </table>
      <%
          if((user = (User)session.getAttribute("user"))!=null && user.getName()!=null){
      %>
      <input type="submit" value="Buy NOW!">
      <%}%>
  </form>
<%
        }}
  %>
</body>
</html>
