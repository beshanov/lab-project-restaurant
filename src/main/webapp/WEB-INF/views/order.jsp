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
        <div align="center">
            <p><spring:message code="label.user"/>: ${order.user.firstname} ${order.user.lastname}</p>
            <p><spring:message code="label.login"/>: ${order.user.login}</p>
            <p><spring:message code="label.date"/>: ${order.orderDate}</p>
            <p><spring:message code="label.status"/>: ${order.status.name}</p>
        </div>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <th align="center" width="70%"><spring:message code="label.dish"/></th>
                <th align="center" width="10%"><spring:message code="label.amount"/></th>
                <th align="center" width="10%"><spring:message code="label.price"/></th>
            </tr>
            <%--<c:set var="totalPrice" value="${0}"/>--%>
            <c:forEach items="${dishMap}" var="entry">
                <%--<c:set var="totalPrice" value="${totalPrice + entry.key.price * entry.value}"/>--%>
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
            <c:choose>
                <c:when test="${order.status.id == 1}">
                    <button onclick="modifyOrderStatus('${order.id}', '2')">Accept</button>
                </c:when>
                <c:when test="${order.status.id == 2}">
                    <button onclick="modifyOrderStatus('${order.id}', '3')">Pay</button>
                </c:when>
                <c:when test="${order.status.id == 3}">
                    <button onclick="modifyOrderStatus('${order.id}', '4')">Close</button>
                </c:when>
                <c:when test="${order.status.id == 4}">
                    <p>Order closed</p>
                </c:when>
            </c:choose>
        </div>
        <div align="center">
            <a href="${pageContext.request.contextPath}/order"><spring:message code="label.back"/></a>
        </div>
    </div>
</div>
</body>
</html>
