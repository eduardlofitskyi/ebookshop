<%--
  Created by IntelliJ IDEA.
  User: Eduard
  Date: 27.11.2014
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="http://localhost:80/registration">
    Name*
    <input type="text" name="name" pattern="[A-Za-z0-9]{6,}" title="Must be more than 6 symbols"/></br></br>
    E-mail*
    <input type="email" name="e-mail"/></br></br>
    Password* (6 and more characters)
    <input type="password" name="pass" pattern="[A-Za-z0-9]{6,}" title="Must be more than 6 symbols"/></br></br>
    Confirm password*
    <input type="password" name="passAddition" pattern="[A-Za-z0-9]{6,}" title="Must be more than 6 symbols"/></br></br>
    Phone +380
    <input type="text" name="phone" pattern="[0-9]{9}" title="Incorrect number"/></br></br>
    <input type="submit" value="REGISTRATION">
</form>
<a href="http://localhost:80/querybook.jsp">Home</a>
</body>
</html>
