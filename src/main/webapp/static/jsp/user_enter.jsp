<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Аверкина
  Date: 01.10.2017
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's enter page</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/styles.css">
</head>
<body>
<h1>Вход пользователя под указанным именем</h1>
<%--<c:if test="${!((sessionScope.user_verified) && sessionScope.user_verified != null)}">--%>
    <%--Пользователь с именем <b style="color: darkred"><c:out value="${param.name}"/></b>--%>
    <%--и указанным паролем не зарегистрирован! <br/> Введите корректные данные для входа либо перейдите на страницу регистрации.--%>
<%--</c:if>--%>

<c:choose>
    <c:when test="${sessionScope.user_verified == null}">

    </c:when>

    <c:when test="${!sessionScope.user_verified}">
        Пользователь с именем <b style="color: darkred"><c:out value="${param.name}"/></b>
        и указанным паролем не зарегистрирован! <br/> Введите корректные данные для входа либо перейдите на страницу регистрации.
    </c:when>
</c:choose>



<form method='post' action='/tour/user_enter'>
    <input type='text' name='name' placeholder="Enter your name..." required style="display: block; margin: 5px;"/>
    <input type='password' name='password' placeholder="Enter password..." required style="display: block;margin: 5px;"/>
    <input type="submit" style="display: block; margin: 5px;"/>
</form>
<p><a class = "loginHref" href="static/registration.jsp" >Регистрация</a></p>
</body>
</html>
