<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainNavBar"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mainNavBar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/dish"> <spring:message
                        code="title.dishes"/></a>
            </li>
            <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/cart"> <spring:message
                            code="title.cart"/></a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/order"> <spring:message
                            code="title.orders"/></a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/user"> <spring:message
                            code="title.users"/></a>
                </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/register"> <spring:message
                            code="title.register"/></a>
                </li>
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/login"> <spring:message
                            code="title.login"/></a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/profile"> <spring:message
                            code="title.profile"/></a>
                </li>
            </sec:authorize>
        </ul>
            <ul class="navbar-nav ml-auto">
                <li class="dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        Language
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="?lang=en">English</a>
                        <a class="dropdown-item" href="?lang=ru">Русский</a>
                    </div>
                </li>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <form action="${pageContext.request.contextPath}/logout" method="post" id="logoutForm"
                              style="display: none">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                        <a class="nav-link" href="#" onclick="document.getElementById('logoutForm').submit()">
                            <spring:message code="label.logout"/>
                        </a>
                    </li>
                </sec:authorize>
            </ul>
    </div>
</nav>