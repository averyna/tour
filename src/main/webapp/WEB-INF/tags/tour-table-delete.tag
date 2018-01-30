<%@tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@attribute name="tours" type="java.util.Collection" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4>Выберите туры, которые хотите удалить </h4>
<form id="content_form" action="/tour/delTour" method="post" >
    <table>
        <tr>
            <th></th>
            <th>Страна</th>
            <th>Тип тура</th>
            <th>Дата начала тура</th>
            <th>Количество взрослых</th>
            <th>Количество детей</th>
            <th>Количество ночей</th>
            <th>Название отеля</th>
            <th>Тип питания</th>
            <th>Цена</th>
        </tr>
        <c:forEach var="tour" items="${tours}" >
          <tr>
              <td><input type="checkbox" name="id" value = "${tour['id']}" /></td>
              <td><c:out value="${tour['country']}" /></td>
              <td><c:out value="${tour['tourType']}" /></td>
              <td><c:out value="${tour['startDate']}" /></td>
              <td><c:out value="${tour['adults']}" /></td>
              <td><c:out value="${tour['children']}" /></td>
              <td><c:out value="${tour['nights']}" /></td>
              <td><c:out value="${tour['hotel']}" /></td>
              <td><c:out value="${tour['mealType']}" /></td>
              <td><c:out value="${tour['price']}" /></td>
          </tr>
        </c:forEach>
      </table>

    <input class="tag_submit_button" type="submit" name="submit_button" value="Удалить"/>
</form>
