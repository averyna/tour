<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE>
<html lang="ru-ru">
<head>
    <title>Горящие туры</title>
	<meta charset="UTF-8">
	<meta name="description" content="Горящие туры">
    <meta name="keywords" content="поиск туров, отдых, горящие туры">
	<link rel="stylesheet" href="static/styles/tour_search_style.css">
</head>
<body>

<div class="central sideform">

    <c:set var = "cache" scope = "page" value = "${applicationScope['edu.olya.tour.utils.cache.Cache']}"/>

    <form class="filter_form" action="/tour/tourSearch" method="get">

        <label for="country">Страна</label>
        <select id="country" name="country"  required>
            <option></option>
            <c:forEach var="country" items="${cache.getValue('getAllCountries')}" >
                <option >
                    <c:out value="${country['name']}"/>
                </option>
            </c:forEach>
        </select>

        <label for="tour_type">Тип тура</label>
        <select id="tour_type" name="tour_type"  required>
            <option></option>
            <c:forEach var="type" items="${cache.getValue('getAllTourTypes')}" >
                <option >
                    ${type.name}
                </option>
            </c:forEach>
        </select>


        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate var="now" type="date" value="${now}" pattern="yyyy-MM-dd"/>
        <label for="start_date">Дата начала тура</label>
        <input class="filter_input" type="date" id="start_date" name="start_date" value="${now}" min = "${now}" required/>

        <label for="adults">Количество взрослых</label>
        <input class="filter_input" type="number" name="adults" id="adults" min="1" value="2" required/>

        <label for="children">Количество детей</label>
        <input class="filter_input" type="number" name="children" id="children" min="0" value="0"/>

        <label for="nights">Количество ночей</label>
        <input class="filter_input" type="number" name="nights" id="nights" min="2" value="7"/>

        <label for="meal_type">Тип питания</label>
        <select id="meal_type" name="meal_type"  required>
            <option></option>
            <c:forEach var="meal" items="${cache.getValue('getAllMealTypes')}" >
                <option >
                    ${meal.name}
                </option>
            </c:forEach>
        </select>

        <label for="price_from">Цена, BYN</label>
        <input class="filter_input" type="number" step="any" name="price_from" id="price_from" min="0" placeholder="От"/>
        <input class="filter_input" type="number" step="any" name="price_to"  min="0" placeholder="До"/>

        <input class="filter_input" type="submit" name="submit_button" value="Поиск"/>

    </form>
</div>

<div class="central content">
    <my:tour-table tours="${requestScope['tours']}"/>
</div>
</body>
</html>
