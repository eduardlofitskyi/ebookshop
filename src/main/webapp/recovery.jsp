<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 28.11.2014
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Password recovery</title>
</head>
<body>
<jsp:include page="querybook.jsp" />
    <form method="post" action="http://localhost:80/recovery">
        E-MAIL:
        <input type="email" name="email">
        <input type="submit" value="RECOVERY">
    </form>
</body>
</html>
