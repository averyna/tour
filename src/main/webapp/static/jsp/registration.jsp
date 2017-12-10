<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Страница регистрации</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/styles/authorization_style.css">
</head>
<body>

    <div class="authorization">

    <h2>Регистрация нового пользователя</h2>
<c:if test="${requestScope.invalidParam != null}">
   Пользователь с именем <b style="color: darkred"><c:out value="${requestScope.invalidParam}"/></b>
    уже существует! <br/> Введите другие данные для регистрации.
</c:if>

        <form class="authorization_form" method='post' action='register'>
            <input type='text' class="authorization_input" name='name' placeholder="Введите имя..." required />
            <input type='password' class="authorization_input" name='password' placeholder="Введите пароль" required/>
            <input type="submit" class="authorization_submit"/>
        </form>
        <p><a href="authorization" >Авторизация</a></p>
        </div>
</body>
</html>
