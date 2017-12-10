<%@ tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@ attribute name="tagRole" type="java.lang.String" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${tagRole eq sessionScope['edu.olya.tour.model.User'].role}">
    <jsp:doBody/>
</c:if>
