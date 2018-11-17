<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="utf-8" %>

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/open-iconic-bootstrap.css" rel="stylesheet"
      type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/restaurant.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/restaurant.js" type="text/javascript"></script>
<sec:authorize access="!hasAuthority('ADMINISTRATOR')">
    <script>$(window).on("load", setCartSize('${pageContext.request.contextPath}'));</script>
</sec:authorize>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainNavBar"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="mainNavBar">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="oi oi-clipboard nav-link" href="${pageContext.request.contextPath}/dish">
                    <spring:message code="title.dishes"/>
                </a>
            </li>
            <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                <li>
                    <a class="oi oi-cart nav-link" href="${pageContext.request.contextPath}/cart">
                        <spring:message code="title.cart"/>
                        <span class="badge badge-light badge-pill" id="navBarCartBadgeCounter">
                        </span>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                <li>
                    <a class="nav-link" href="${pageContext.request.contextPath}/user">
                        <spring:message code="title.users"/>
                    </a>
                </li>
            </sec:authorize>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="dropdown">
                <a class="oi oi-globe nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Language
                </a>
                <div class="dropdown-menu bg-dark text-white-50" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="?lang=en">English</a>
                    <a class="dropdown-item" href="?lang=ru">Русский</a>
                </div>
            </li>
            <sec:authorize access="!isAuthenticated()">
                <li>
                    <a class="oi oi-account-login nav-link" href="${pageContext.request.contextPath}/login">
                        <spring:message code="button.login"/>
                    </a>
                </li>
                <li>
                    <a class="oi oi-plus nav-link" href="${pageContext.request.contextPath}/register">
                        <spring:message code="button.register"/>
                    </a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="dropdown bg-dark text-white-50 mr-5">
                    <a class="oi oi-cog nav-link dropdown-toggle" href="#" id="settingsDropdown" role="button"
                       data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <sec:authentication property="principal.username"/>
                    </a>
                    <div class="dropdown-menu bg-dark text-white-50" aria-labelledby="settingsDropdown">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/order">
                            <spring:message code="title.orders"/>
                        </a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/profile">
                            <spring:message code="title.profile"/>
                        </a>
                        <a class="dropdown-item" href="#" onclick="document.getElementById('logoutForm').submit()">
                            <form action="${pageContext.request.contextPath}/logout" method="post" id="logoutForm"
                                  style="display: none">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                            <spring:message code="label.logout"/>
                        </a>
                    </div>
                </li>
            </sec:authorize>
            <li></li>
        </ul>
    </div>
</nav>