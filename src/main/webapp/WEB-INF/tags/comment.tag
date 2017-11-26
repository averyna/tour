<%@tag import="edu.olya.tour.model.Comment" %>
<%@tag body-content="scriptless" pageEncoding="UTF-8" %>
<%@attribute name="comments" type="java.util.Collection" required="true" rtexprvalue="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${comments != null}">
    <c:forEach var="comment" items="${comments}" >
        <div class="comment_box">

            <div class="author_date">
              <div class="inner">
                <c:out value="${comment['author']}" />
              </div>
              <div class="inner">
                <c:out value="${comment['date']}" />
              </div>
            </div>

            <div class="comment">
              <c:out value="${comment['comment']}" />
            </div>

        </div>
    </c:forEach>
</c:if>
