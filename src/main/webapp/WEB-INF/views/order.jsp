<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Orders</title>
</head>
<body>
<div id="contents">
    <h1 align="center">Order #${order.id}</h1>
    <div id="order" align="center">
        <div align="center" border-style="solid" border-radius="5px">
            <p>User: ${order.user.firstname + " " + order.user.lastname}</p>
            <p>Date: ${order.orderdate}</p>
            <p>Status: ${order.status.name}</p>
        </div>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <th align="center" width="70%">Dish</th>
                <th align="center" width="10%">Amount</th>
                <th align="center" width="10%">Price</th>
            </tr>
            <c:set var="totalPrice" value="${0}"/>
            <c:forEach items="${dishMap}" var="entry">
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
                </tr>
            </c:forEach>
        </table>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <td>
                    <div>
                        <h3 align="left">Total:</h3>
                        <h3 align="right">${totalPrice}</h3>
                    </div>
                </td>
            </tr>
        </table>
        <div align="center">
            <a href="${pageContext.request.contextPath}/order">Back</a>
        </div>
    </div>
</div>
</body>
</html>
