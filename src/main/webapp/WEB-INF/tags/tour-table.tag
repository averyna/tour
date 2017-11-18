<%@tag import="edu.olya.tour.model.TourView" %>
<%@tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@attribute name="tours" type="java.util.Collection" required="true" rtexprvalue="true" %>
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
    <%
        for (Object o : tours) {
            TourView t = (TourView) o;
    %>
    <tr>
        <td><%=t.getCountry()%></td>
        <td><%=t.getTourType()%></td>
        <td><%=t.getStartDate()%></td>
        <td><%=t.getAdults()%></td>
        <td><%=t.getChildren()%></td>
        <td><%=t.getNights()%></td>
        <td><%=t.getHotel()%></td>
        <td><%=t.getMealType()%></td>
        <td><%=t.getPrice()%></td>
    </tr>
    <%
        }
    %></table>