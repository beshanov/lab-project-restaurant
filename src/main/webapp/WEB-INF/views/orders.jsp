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
    <div id="orders" align="center">
        <table>
            <tr>
                <th><spring:message code="label.orderNumber"/></th>
                <th><spring:message code="label.user"/></th>
                <th><spring:message code="label.date"/></th>
                <th><spring:message code="label.status"/></th>
                <th><spring:message code="label.details"/></th>
            </tr>
            <c:forEach items="${orderList}" var="entry">
                <tr>
                    <td>${entry.id}</td>
                    <td>${entry.user.firstname} ${entry.user.lastname} (${entry.user.login})</td>
                    <td>${entry.orderDate}</td>
                    <td>${entry.status.name}</td>
                    <td><a href="order/${entry.id}"><spring:message code="label.viewDetails"/></a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>
        <div align="center">
            <a href="${pageContext.request.contextPath}/dish"><spring:message code="label.menu"/></a>
        </div>
    </div>
</div>
</body>
</html>
