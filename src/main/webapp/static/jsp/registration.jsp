<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Аверкина
  Date: 29.09.2017
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Регистрация нового пользователя</h1>
<c:if test="${requestScope.user_exists}">
   Пользователь с именем <b style="color: darkred"><c:out value="${param.name}"/></b>
    и адресом электронной почты <b style="color: darkred"><c:out value="${param.email}"/></b>
    уже существует! <br/> Введите другие данные для регистрации.
</c:if>

<form method='post' action='/tour/register'>
    <input type='text' name='name' placeholder="Enter your name..." required style="display: block; margin: 5px;"/>
    <input type='password' name='password' placeholder="Enter password..." required style="display: block;margin: 5px;"/>
    <input type="submit" style="display: block; margin: 5px;"/>
</form>
</body>
</html>
