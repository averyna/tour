<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html lang="ru-ru">
<head>
    <title>Отзывы</title>
  	<meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
  	<meta name="description" content="Отзывы о работе турагентства">
    <meta name="keywords" content="поиск туров, отдых, билеты">
  	<link rel="stylesheet" href="static/styles/comments_style.css">
    <script src="static/scripts/layout_script.js"></script>
</head>
<body>
    <div id="comments_list" class="comments_list">
      <my:comment id="comment_tag" comments="${requestScope['comments']}"/>
    </div>

      <div class="comment_box">
      <form action="/tour/comment" method="post" id="comment_form">
        <div class="author_date">
            <label class="inner">Введите Ваше имя</label>
            <input class="comment_input" type="text" name="author" pattern="^[А-Я а-я]+$" />
        </div>
        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate var="now" type="date" value="${now}" pattern="yyyy-MM-dd"/>
        <input type="hidden" name="date" value="${now}"/>
        <textarea class="comment" name="comment"  rows="3"
          placeholder="Введите Ваш отзыв..." required></textarea>
        <input class="comment_submit_button" type="submit" name="comment_submit_form" value="Добавить отзыв"/>

      </form>
    </div>
</body>
</html>
