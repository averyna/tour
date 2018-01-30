<%@tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@attribute name="tours" type="java.util.Collection" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table>
    <tr>
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
