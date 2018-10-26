<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cart contents</title>
    <spring:url value="/resources/js/cart.js" var="cartJS"/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="${cartJS}"></script>
</head>
<body>
<div id="contents">
    <h1 align="center">Composing new order</h1>
    <div id="orderSettings">
        <c:set var="totalPrice" value="${0}"/>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <th align="center" width="70%">Dish</th>
                <th align="center" width="10%">Amount</th>
                <th align="center" width="10%">Price</th>
                <th align="center" width="10%">Action</th>
            </tr>
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
                        <input type="button" onclick="deleteFromCart('${entry.key.id}')" value="Remove">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
            <tr>
                <td>
                    <span><p align="left">Total: </p><p align="right">${totalPrice}</p></span>
                </td>
            </tr>
        </table>
        <br/>
        <div align="center">
            <form action="order" method="post">
                <input type="submit" value="Submit"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
