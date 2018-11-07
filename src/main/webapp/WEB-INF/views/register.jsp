<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.register"/></title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div class="container-fluid col-sm-5">
    <form:form id="regForm" modelAttribute="user" action="register" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <form:label path="login"><spring:message code="label.login"/> </form:label>
            <form:input class="form-control"  path="login" pattern="[\w\d]{3,8}" required="required"/>
            <form:errors path="login"/>
        </div>
        <div class="form-group">
            <form:label path="password"><spring:message code="label.password"/></form:label>
            <form:password class="form-control"  path="password" pattern="[\w\d]{3,8}" required="required"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <form:label path="firstname"><spring:message code="label.firstName"/></form:label>
            <form:input class="form-control" path="firstname" pattern="[\w\d]{2,10}" required="required"/>
            <form:errors path="firstname"/>
        </div>
        <div class="form-group">
            <form:label path="lastname"><spring:message code="label.lastName"/></form:label>
            <form:input class="form-control"  path="lastname" pattern="[\w\d]{3,20}" required="required"/>
            <form:errors path="lastname"/>
        </div>
        <div class="form-group">
            <form:button class="btn btn-dark" id="register" name="register"><spring:message code="button.register"/></form:button>
        </div>
    </form:form>
</div>
</body>
</html>
