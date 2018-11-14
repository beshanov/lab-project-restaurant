<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.login"/></title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div class="container-fluid col-sm-5">
    <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <label><spring:message code="label.login"/></label>
            <input class="form-control" name="login" pattern="[\w\d]{3,8}" required="required"/>
        </div>
        <div class="form-group">
            <label><spring:message code="label.password"/></label>
            <input class="form-control" type="password" name="password" pattern="[\w\d]{3,8}" required="required" />
        </div>
        <div class="form-group">
            <button class="btn btn-dark" id="loginProcess" name="loginProcess"><spring:message code="button.login"/>
        </div>

    </form>
</div>
</body>
</html>