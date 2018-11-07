<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.order"/></title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/modifyOrder.js"></script>
    <sec:csrfMetaTags/>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div id="contents">
    <div id="order" class="container-fluid col-7">
        <div class="card mb-1" style="width:25rem">
            <div class="card-header"><spring:message code="label.orderNumber"/>: ${order.id}</div>
            <div class="card-body">
                <p><spring:message code="label.user"/>: ${order.user.firstname} ${order.user.lastname}</p>
                <p><spring:message code="label.login"/>: ${order.user.login}</p>
                <p><spring:message code="label.date"/>: ${order.orderDate}</p>
                <p><spring:message code="label.status"/>: ${order.status.name}</p>
                <p><spring:message code="label.admin"/>: ${order.admin.firstname} ${order.user.lastname}</p>
            </div>
        </div>
        <div class="jumbotron">
            <ul class="list-group">
                <c:forEach items="${dishMap}" var="entry">
                    <li class="list-group-item">
                        <h5 class="mb-1">${entry.key.name}
                            <span class="badge badge-dark badge-pill">${entry.value}</span></h5>
                        <p class="mb-1">${entry.key.description}</p>
                        <p class="text-right"><spring:message code="label.price"/>: ${entry.key.price * entry.value}</p>
                    </li>
                </c:forEach>
                <li class="list-group-item text-right list-group-item-dark">
                    <h4><spring:message code="label.total"/>: ${order.amount}</h4>
                </li>
            </ul>
        </div>
        <div id="adminButton" align="center">
            <form action="${pageContext.request.contextPath}/order.setStatus" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="orderId" value="${order.id}">

                <c:choose>
                    <c:when test="${order.status.id == 1}">
                        <input type="hidden" name="statusId" value="2">
                        <button type="submit"><spring:message code="button.accept"/></button>
                    </c:when>
                    <c:when test="${order.status.id == 2}">
                        <input type="hidden" name="statusId" value="3">
                        <button type="submit">Pew</button>
                    </c:when>
                    <c:when test="${order.status.id == 3}">
                        <input type="hidden" name="statusId" value="4">
                        <button type="submit"><spring:message code="button.pay"/></button>
                    </c:when>
                    <c:when test="${order.status.id == 4}">
                        <p align="center"><spring:message code="label.orderClosed"/></p>
                    </c:when>
                </c:choose>
            </form>
        </div>
        <div align="center">
            <a href="${pageContext.request.contextPath}/order"><spring:message code="label.back"/></a>
        </div>
    </div>
</div>
</body>
</html>
