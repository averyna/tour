<%@ page import="java.sql.*" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- содержимое для дырки - поиск тура -->
<div class="column sidemenu">
	<!--define action-->
	<form id="filter_form" action="/tour/tourSearch" method="get">
		<label for="country">Страна</label>
		<%
			try{
				Class.forName("org.postgresql.Driver");
				Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tour", "olga", "olga");
				Statement statement = connection.createStatement() ;
				ResultSet resultSet = statement.executeQuery(
						"SELECT DISTINCT (country) FROM countries ORDER BY country ");
		%>

		<select id="country" name="country"  required>
			<%  while(resultSet.next()){ %>
			<option><%= resultSet.getString(1)%></option>
			<% } %>
		</select>


		<label for="tour_type">Тип тура</label>
		<%
			resultSet = statement.executeQuery(
					"SELECT DISTINCT (tour_type)FROM tour_types");
		%>
		<select id="tour_type" name="tour_type" required>
			<% while(resultSet.next()){ %>
			<option><%= resultSet.getString(1)%></option>
			<% } %>
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

		<label for="stars">Звездность отеля</label>
		<select id="stars" name="stars" required>
			<option value="5">5</option>
			<option value="4">4</option>
			<option value="3">3</option>
		</select>

		<label for="meal_type">Тип питания</label>
		<%
			resultSet = statement.executeQuery(
					"SELECT DISTINCT (meal_type)FROM meal_types ORDER BY meal_type ");
		%>
		<select id="meal_type" name="meal_type">
			<option selected="selected"></option>
			<% while(resultSet.next()){ %>
			<option><%= resultSet.getString(1)%></option>
			<% } %>
		</select>
		<%
				connection.close();
				statement.close();
				resultSet.close();
			}
			catch(SQLException | ClassNotFoundException e)
			{
				System.out.println("Exception from hot_tours.jsp");
				e.printStackTrace();
			}
		%>
		<label for="price_from">Цена, BYN</label>
		<input class="filter_input" type="number" step="any" name="price_from" id="price_from" min="0" placeholder="От"/>
		<input class="filter_input" type="number" step="any" name="price_to"  min="0" placeholder="До"/>

		<input class="filter_input" type="submit" name="submit_button" value="Поиск"/>
	</form>
</div>

<div class="column content">
	<my:tour-table tours="${requestScope['tours']}"/>
</div>