<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Orders</title>
</head>
<body>
<div id="contents">
    <h1 align="center">Orders</h1>
    <div id="orders" align="center">
        <c:forEach items="${orderMap}" var="orderMapEntry">
            <details>
                <summary>User: ${orderMapEntry.key.user.firstname} ${orderMapEntry.key.user.lastname}
                    /// Order number: ${orderMapEntry.key.id}
                    /// Date: ${orderMapEntry.key.orderDate}
                </summary>
                <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
                    <tr>
                        <th align="center" width="70%">Dish</th>
                        <th align="center" width="10%">Amount</th>
                        <th align="center" width="10%">Price</th>
                    </tr>
                    <c:set var="totalPrice" value="${0}"/>
                    <c:forEach items="${orderMapEntry.value}" var="dishMapEntry">
                        <c:set var="totalPrice" value="${totalPrice + dishMapEntry.key.price * dishMapEntry.value}"/>
                        <tr>
                            <td>
                                <h3 align="left">${dishMapEntry.key.name}</h3>
                                <p align="left">${dishMapEntry.key.description}</p>
                            </td>
                            <td>
                                <p align="center">${dishMapEntry.value}</p>
                            </td>
                            <td>
                                <p align="center">${dishMapEntry.key.price * dishMapEntry.value}</p>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                <table align="center" width="40%" cellpadding="1" bgcolor="#DDDDDD" border="1">
                    <tr>
                        <td>
                            <div>
                                <p align="left">Total:</p>
                                <p align="right">${totalPrice}</p>
                            </div>
                        </td>
                    </tr>
                </table>
            </details>
            <br/>
            <br/>
        </c:forEach>
        <div align="center">
            <form action="order" method="get">
                <input type="submit" value="Refresh"/>
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
