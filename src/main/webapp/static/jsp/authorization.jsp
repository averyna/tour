<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница авторизации</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/styles/authorization_style.css">
</head>
<body>

<div class="authorization">

    <h2>Авторизация<br> Введите имя и пароль </h2>

    <c:if test="${requestScope.invalidParam}">
        Пользователь с указанным именем и паролем не зарегистрирован! <br/> Введите корректные данные для входа либо перейдите на страницу регистрации.
    </c:if>

<form class="authorization_form" method='post' action='authorization'>
    <input type='text' class="authorization_input" name='name' placeholder="Введите имя " pattern="^[0-9a-zA-Z]+$" required />
    <input type='password' class="authorization_input" name='password' placeholder="Введите пароль" required/>
    <input type="submit" class="authorization_submit"/>
</form>
<p><a href="register" >Регистрация</a></p>
</div>
</body>
</html>
