<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Orders</title>
</head>
<body>
<div id="contents">
    <h1 align="center">Orders</h1>
    <div id="orders" align="center">
        <table>
            <tr>
                <th>Order number</th>
                <th>User</th>
                <th>Date</th>
                <th>Status</th>
                <th>Details</th>
            </tr>
            <c:forEach items="${orderList}" var="entry">
                <tr>
                    <td>${entry.id}</td>
                    <td>${entry.user.firstname + " " + entry.user.lastname}</td>
                    <td>${entry.orderdate}</td>
                    <td>${entry.status.name}</td>
                    <td><a href="/order/${entry.id}">View details</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>
        <div align="center">
            <a href="${pageContext.request.contextPath}/order">Menu</a>
        </div>
    </div>
</div>
</body>
</html>
