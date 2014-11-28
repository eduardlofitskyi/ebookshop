<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 08.11.2014
  Time: 21:27
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>EBOOKSHOP</title>
  </head>
  <body>
  <jsp:include page="login.jsp" />
    <h2>EBOOKSHOP</h2>
    <form method="get" action="http://localhost:80/query">
      What do you want :
      <input type="text" name="author"/>
      <input type="submit" value="Search" />
    </form>
  </body>
</html>
