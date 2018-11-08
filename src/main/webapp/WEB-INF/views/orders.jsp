<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.orders"/></title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div id="contents">
    <h1 align="center"><spring:message code="label.orders"/></h1>
    <div id="orders" align="center" class="container-fluid col-4">
        <div class="list-group">
            <c:forEach items="${orderList}" var="entry">
                <a href="${pageContext.request.contextPath}/order/${entry.id}"
                   class="list-group-item list-group-item-action flex-column align-items-start">
                    <div class="d-flex w-100 justify-content-between">
                        <h5 class="mb-1"><spring:message code="label.orderNumber"/>: ${entry.id}</h5>
                        <small>${entry.orderDate}</small>
                    </div>
                    <div class="d-flex w-100 justify-content-between">
                        <div class="mb-1"><spring:message
                                code="label.user"/>: ${entry.user.firstname} ${entry.user.lastname} (${entry.user.login})
                        </div>
                        <div class="mb-1"><spring:message code="label.status"/>: ${entry.status.name}</div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
