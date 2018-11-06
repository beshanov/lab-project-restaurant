<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainNavBar" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mainNavBar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/dish">Dishes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cart">Cart</a>
            </li>
            <li>
                <a class="nav-link" href="${pageContext.request.contextPath}/order">Orders</a>
            </li>
            <li>
                <a class="nav-link" href="${pageContext.request.contextPath}/register">Register</a>
            </li>
            <li>
                <a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
            </li>
            <li>
                <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
        </ul>
    </div>
</nav>
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>