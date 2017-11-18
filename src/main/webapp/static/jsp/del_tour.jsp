<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE>
<html lang="en-us">
<head>
    <title>Удаление тура</title>
	<meta charset="UTF-8">
	<meta name="description" content="Удаление туров. Доступ для админа.">
	<link rel="stylesheet" href="static/styles/del_tour_style.css">
</head>
<body>

<div class="central sideform">

    <h4>Выберите параметры для удаления тура </h4>

    <c:if test="${requestScope.invalidParams}">
       <b style="color: darkred;">Что-то пошло не так. <br/> Попробуйте ввести данные для удаления тура снова.<br/></b>
    </c:if>

    <form class="filter_form" action="/tour/delTour" method="get">
        <label for="country">Страна</label>
                <select id="country" name="country"  required>
                    <option></option>
                    <% for (Map.Entry<Integer, String> param : ((Map<Integer, String>)(request.getAttribute("country"))).entrySet()) { %>
                        <option >
                            <%= param.getValue()%>
                        </option>
                    <% } %>
                </select>

                <label for="tour_type">Тип тура</label>
                <select id="tour_type" name="tour_type"  required>
                    <option></option>
                    <% for (Map.Entry<Integer, String> param : ((Map<Integer, String>)(request.getAttribute("tour_type"))).entrySet()) { %>
                        <option>
                            <%= param.getValue()%>
                        </option>
                    <% } %>
                </select>

        <label for="start_date">Дата начала тура</label>
        <input class="filter_input" type="date" id="start_date" name="start_date"/>

        <label for="adults">Количество взрослых</label>
        <input class="filter_input" type="number" name="adults" id="adults" min="0"  />

        <label for="children">Количество детей</label>
        <input class="filter_input" type="number" name="children" id="children" min="0"  />

        <label for="nights">Количество ночей</label>
        <input class="filter_input" type="number" name="nights" id="nights" min="0" />

        <label for="hotel">Отель</label>
        <select id="hotel" name="hotel">
            <option></option>
            <% for (Map.Entry<Integer, String> param : ((Map<Integer, String>)(request.getAttribute("hotel"))).entrySet()) { %>
              <option>
                  <%= param.getValue()%>
              </option>
            <% } %>
        </select>

       <label for="meal_type">Тип питания</label>
               <select id="meal_type" name="meal_type"  required>
                   <option></option>
                  <% for (Map.Entry<Integer, String> param : ((Map<Integer, String>)(request.getAttribute("meal_type"))).entrySet()) { %>
                      <option>
                          <%= param.getValue()%>
                      </option>
                  <% } %>
               </select>

        <label for="price">Цена, BYN</label>
        <input class="filter_input" type="number" step="any" name="price" id="price" min="0" />

        <input class="filter_input" type="submit" name="submit_button" value="Выбрать параметры"/>
    </form>
</div>

<div class="central content">
    <c:if test="${requestScope.tours != null}">
          <my:tour-table-delete tours="${requestScope['tours']}"/>
    </c:if>

    <c:if test="${requestScope.deleted != null}">
           <b style="color: darkred;"> Количество удаленных туров:  <c:out value="${requestScope.deleted}" /><br/></b>
    </c:if>
</div>
</body>
</html>
