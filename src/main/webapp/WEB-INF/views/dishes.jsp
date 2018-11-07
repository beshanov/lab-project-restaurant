<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.dishes"/></title>
    <style>
        .dish {
            margin: 10px 0px;
            width: 300px;
            float: left;
        }
    </style>
    <sec:csrfMetaTags />
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/deleteDish.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addToCart.js"></script>
</head>
<body>
<div style="width: 900px; margin:auto;">
    <c:forEach var="dish" items="${dishesList}">
        <div class="dish" id="dish_${dish.id}">
            <div class="dish_name"><a href="dish/${dish.id}">${dish.name}</a></div>
                <div class="dish_price">${dish.price}</div>
            <form id="dishForm_${dish.id}">
                <input type="number" min="1" value="1" style="width: 50px;" name="pieces_${dish.id}">
                <spring:message code="label.pieces"/>
            </form>
            <sec:authorize access="hasAuthority('CUSTOMER')">
                <button onclick="addToCart('${dish.id}')"><spring:message code="button.addToCart"/></button>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                <button onclick="deleteDish('${dish.id}')"><spring:message code="button.delete"/></button>
            </sec:authorize>
        </div>
    </c:forEach>
    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
        <a href="${pageContext.request.contextPath}/dish/create" type="button"><spring:message code="button.addNew"/></a>
    </sec:authorize>
</div>
</body>
</html>