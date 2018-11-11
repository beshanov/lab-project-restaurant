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
                <div class="container">
                    <div class="row">
                        <div class="col"><spring:message code="label.user"/>:</div>
                        <div class="col">${order.user.firstname} ${order.user.lastname}</div>
                    </div>
                    <div class="row">
                        <div class="col"><spring:message code="label.login"/>:</div>
                        <div class="col">${order.user.login}</div>
                    </div>
                    <div class="row">
                        <div class="col"><spring:message code="label.date"/>:</div>
                        <div class="col">${order.orderDate}</div>
                    </div>
                    <div class="row">
                        <div class="col"><spring:message code="label.status"/>:</div>
                        <div class="col">${order.status.name}</div>
                    </div>
                    <div class="row">
                        <div class="col"><spring:message code="label.admin"/>:</div>
                        <div class="col">${order.admin.firstname} ${order.admin.lastname}</div>
                    </div>
                </div>
            </div>
            <div align="center">
                <form action="${pageContext.request.contextPath}/order.setStatus" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="orderId" value="${order.id}">
                    <c:choose>
                        <c:when test="${order.status.id == 1}">
                            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                                <input type="hidden" name="statusId" value="2">
                                <button class="btn-dark btn" type="submit"><spring:message
                                        code="button.accept"/></button>
                            </sec:authorize>
                        </c:when>
                        <c:when test="${order.status.id == 2}">
                            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                                <input type="hidden" name="statusId" value="3">
                                <button class="btn-dark btn" type="submit">Pew</button>
                            </sec:authorize>
                        </c:when>

                        <c:when test="${order.status.id == 4}">
                            <p align="center"><spring:message code="label.orderClosed"/></p>
                        </c:when>
                    </c:choose>

                </form>
                <c:choose>
                <c:when test="${order.status.id == 3}">
                <sec:authorize access="hasAuthority('CUSTOMER')">
                <form action="${pageContext.request.contextPath}/order/${order.id}/pay" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" name="orderId" value="${order.id}">
                    <input type="hidden" name="statusId" value="4">
                    <button class="btn-dark btn" type="submit"><spring:message
                            code="button.pay"/></button>
                    </sec:authorize>
                    </c:when>
                    </c:choose>
                </form>
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
    </div>
</div>
</div>
</body>
</html>
