<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
    <title>Order details</title>
</head>
<body>
<jsp:include page="navigate.jsp"></jsp:include>
<div id="contents">
    <h1 align="center">Order № ${order.id}</h1>
    <div id="orderSettings">
        <p>Ordered dishes: </p>
        <table width="60%" cellpadding="2" bgcolor="#DDDDDD" border="1">
            <tr>
                <th align="center" width="50%">Dish</th>
                <th align="center" width="10%">Amount</th>
                <th align="center" width="20%">Price</th>
                <th align="center" width="20%">Action</th>
            </tr>
            <c:forEach items="${dishList}" var="entry">
                <tr>
                    <td>
                        <h3 align="left">${entry.name}</h3>
                        <p align="left">${entry.description}</p>
                    </td>
                    <td>
                        <form:input path=""></form:input>
                    </td>
                    <td>

                    </td>
                    <td>

                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>Total:</td>
                <td></td>
                <td>${totalPrice}</td>
            </tr>
        </table>
        <form:form>

        </form:form>
    </div>
</div>
</body>
</html>
