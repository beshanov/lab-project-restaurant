<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Cart contents</title>
</head>
<body>
<div id="contents">
    <h1 align="center">Composing new order</h1>
    <div id="orderSettings">
        <p align="center">Ordered dishes: </p>
        <table align="center" width="60%" cellpadding="2" bgcolor="#DDDDDD" border="1">
            <tr>
                <th align="center" width="50%">Dish</th>
                <th align="center" width="10%">Amount</th>
                <th align="center" width="20%">Price</th>
                <th align="center" width="20%">Action</th>
            </tr>
            <c:forEach items="${sessionScope.dishMap}" var="entry">
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
                        <input/>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>Total:</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </table>
        <form align="center" action="/order" method="post">
            <input type="submit" value="Submit"/>
        </form>
    </div>
</div>
</body>
</html>
