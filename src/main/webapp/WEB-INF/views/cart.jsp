<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.cart"/></title>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cart.js"></script>
</head>
<body>
<div id="contents">
    <h1 align="center"><spring:message code="label.newOrder"/></h1>
    <div id="orderSettings">
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <th align="center" width="70%"><spring:message code="label.dish"/></th>
                <th align="center" width="10%"><spring:message code="label.amount"/></th>
                <th align="center" width="10%"><spring:message code="label.price"/></th>
                <th align="center" width="10%"><spring:message code="label.action"/></th>
            </tr>
            <c:set var="totalPrice" value="${0}"/>
            <spring:message code="button.remove" var="removeLabel"/>
            <spring:message code="button.submit" var="submitLabel"/>
            <c:forEach items="${sessionScope.dishMap}" var="entry">
                <c:set var="totalPrice" value="${totalPrice + entry.key.price * entry.value}"/>
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
                    <td>
                        <input type="button" onclick="deleteFromCart('${entry.key.id}')" value="${removeLabel}">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <td>
                    <div><p align="left"><spring:message code="label.total"/>: </p>
                        <p align="right">${totalPrice}</p></div>
                </td>
            </tr>
        </table>
        <br/>
        <div align="center">
            <form action="order" method="post">
                <input type="submit" value="${submitLabel}"/>
            </form>
        </div>
        <br/>
        <br/>
        <div align="center">
            <a href="${pageContext.request.contextPath}/dish"><spring:message code="label.menu"/></a>
        </div>
    </div>
</div>
</body>
</html>
