<%@ page import="Entity.User" %>
<%@ page import="Entity.Cart" %>
<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 11.11.2014
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
  <%User user;
    if ( (user = (User)session.getAttribute("user"))==null || user.getName()==null){%>
  <form method="post" action="http://localhost:80/login">
    Username <input type="text" name="username">
    Password <input type="password" name="pass">
    <input type="submit" value="Sign in">
    </br><a href="http://localhost:80/registration.jsp">Register NOW</a>
      </br>  <a href="http://localhost:80/querybook.jsp">Home</a>
      </br>  <a href="http://localhost:80/recovery.jsp">Forgot password?</a>
  </form>
  <%}else {%>
  <form method="post" action="http://localhost:80/logout">
    <h2>Hello, <%=user.getName()%>
    <input type="submit" value="Log out">
    </h2>
      <%Cart cart;
      if ((cart  = (Cart) session.getAttribute("cart"))!=null){%>
    <a href="http://localhost:80/cart">SHOW CART: <%=cart.size()%></a>
    <%}else {%>
    <a href="http://localhost:80/cart">SHOW CART: 0</a>
    <%}%>

  </form>
  <a href="http://localhost:80/show">SHOW ORDERS</a></br>
  <a href="http://localhost:80/querybook.jsp">Home</a>
  <%}%>
</body>
</html>
