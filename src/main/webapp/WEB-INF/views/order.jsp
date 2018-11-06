<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.order"/></title>
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
            </div>
        </div>
        <div class="jumbotron">
            <ul class="list-group">
                <c:set var="totalPrice" value="${0}"/>
                <c:forEach items="${dishMap}" var="entry">
                    <c:set var="totalPrice" value="${totalPrice + entry.key.price * entry.value}"/>
                    <li class="list-group-item">
                        <h5 class="mb-1">${entry.key.name}
                            <span class="badge badge-dark badge-pill">${entry.value}</span></h5>
                        <p class="mb-1">${entry.key.description}</p>
                        <p class="text-right"><spring:message code="label.price"/>: ${entry.key.price * entry.value}</p>
                    </li>
                </c:forEach>
                <li class="list-group-item text-right list-group-item-dark">
                    <h4><spring:message code="label.total"/>: ${totalPrice}</h4>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
