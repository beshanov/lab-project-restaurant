<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title>Cart contents</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cart.js"></script>
</head>
<body>
<jsp:include page="navigate.jsp"></jsp:include>
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
                <input type="submit" value="Submit order"/>
            </form>
        </div>
        <br/>
        <br/>
        <div align="center">
            <a href="${pageContext.request.contextPath}/dish">Menu</a>
        </div>
    </div>
</div>
</body>
</html>
