<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE>
<html lang="en-us">
<head>
    <title>Добавление тура</title>
	<meta charset="UTF-8">
	<meta name="description" content="Добавление туров. Доступ только для админа.">
	<link rel="stylesheet" href="static/styles/add_tour_style.css">
</head>
<body>

<div class="add_tour">
    <h4>Добавление нового тура</h4>

    <c:if test="${requestScope.invalidParams}">
       <b style="color: darkred;">Кажется, что-то пошло не так. <br/> Попробуйте ввести данные для добавления тура снова.<br/></b>
    </c:if>

    <c:set var = "cache" scope = "page" value = "${applicationScope['edu.olya.tour.utils.cache.Cache']}"/>

    <form class="filter_form" action="/tour/addTour" method="get">

        <label for="countryId">Страна</label>
            <select id="countryId" name="countryId"  required>
                <option></option>
                <c:forEach var="country" items="${cache.getValue('getAllCountries')}" >
                    <option value="${country['id']}">
                        <c:out value="${country['name']}"/>
                    </option>
                </c:forEach>
            </select>

        <label for="tourTypeId">Тип тура</label>
            <select id="tourTypeId" name="tourTypeId"  required>
                <option></option>
                <c:forEach var="type" items="${cache.getValue('getAllTourTypes')}" >
                    <option value="${type['id']}">
                        ${type.name}
                    </option>
                </c:forEach>
            </select>

        <jsp:useBean id="now" class="java.util.Date"/>
        <fmt:formatDate var="now" type="date" value="${now}" pattern="yyyy-MM-dd"/>
        <label for="startDate">Дата начала тура</label>
        <input class="filter_input" type="date" id="startDate" name="startDate" value="${now}" min = "${now}" required/>

        <label for="adults">Количество взрослых</label>
        <input class="filter_input" type="number" name="adults" id="adults" min="1" value="1" required/>

        <label for="children">Количество детей</label>
        <input class="filter_input" type="number" name="children" id="children" min="0" value="0" required/>

        <label for="nights">Количество ночей</label>
        <input class="filter_input" type="number" name="nights" id="nights" min="2" value="7" required/>

        <label for="hotelId">Отель</label>
        <select id="hotelId" name="hotelId" required>
            <option></option>
                <c:forEach var="hotel" items="${cache.getValue('getAllHotels')}" >
                    <option value="${hotel['id']}">
                        ${hotel.name}
                    </option>
                </c:forEach>
        </select>

       <label for="mealTypeId">Тип питания</label>
       <select id="mealTypeId" name="mealTypeId"  required>
           <option></option>
                <c:forEach var="meal" items="${cache.getValue('getAllMealTypes')}" >
                    <option value="${meal['id']}">
                        ${meal.name}
                    </option>
                </c:forEach>
       </select>

        <label for="price">Цена, BYN</label>
        <input class="filter_input" type="number" step="any" name="price" id="price" min="0" value="0" placeholder="0"/>

        <input class="filter_input" type="submit" name="submit_button" value="Добавить тур"/>
    </form>
</div>
</body>
</html>
