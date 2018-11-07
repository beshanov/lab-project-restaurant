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
<div id="contents">
    <h1 align="center"><spring:message code="label.orderNumber"/>: ${order.id}</h1>
    <div id="order" align="center">
        <table align="center" width="40%">
            <tr align="left">
                <td width="20%"><spring:message code="label.user"/>:</td>
                <td>${order.user.firstname} ${order.user.lastname}</td>
            </tr>
            <tr align="left">
                <td width="20%"><spring:message code="label.login"/>:</td>
                <td>${order.user.login}</td>
            </tr>
            <tr align="left">
                <td width="20%"><spring:message code="label.date"/>:</td>
                <td>${order.orderDate}</td>
            </tr>
            <tr align="left">
                <td width="20%"><spring:message code="label.status"/>:</td>
                <td>${order.status.name}</td>
            </tr>
            <tr align="left">
                <td width="20%"><spring:message code="label.admin"/>:</td>
                <td>${order.admin.firstname} ${order.admin.lastname}</td>
            </tr>
        </table>
        <br/>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <th align="center" width="70%"><spring:message code="label.dish"/></th>
                <th align="center" width="10%"><spring:message code="label.amount"/></th>
                <th align="center" width="10%"><spring:message code="label.price"/></th>
            </tr>
            <c:forEach items="${dishMap}" var="entry">
                <tr>
                    <td>
                        <h3 align="left">${entry.key.name}</h3>
                        <p align="left">${entry.key.description}</p>
                    </td>
                    <td>
                        <p align="center">${entry.value}</p>
                    </td>
                    <td>
                        <p align="center">${entry.key.price * entry.value}</p>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <td>
                    <div>
                        <h3 align="left"><spring:message code="label.total"/>:</h3>
                        <h3 align="right">${order.amount}</h3>
                    </div>
                </td>
            </tr>
        </table>
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
