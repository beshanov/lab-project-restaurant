<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.register"/></title>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 51px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid" style="margin-top: 51px">
    <div class="container col-lg-6 col-md-10 col-sm-12 h-100 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6);">
        <div class="container col-lg-4 col-md-6 col-sm-8"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px;">
            <form:form id="regForm" modelAttribute="user" action="register" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-row my-3">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text oi oi-person" style="font-size: 20px"></span>
                        </div>
                        <spring:message code="label.login" var="loginLabel"/>
                        <form:input class="form-control" path="login" pattern="[\w\d]{3,8}" required="required"
                                    placeholder="${loginLabel}"/>
                    </div>
                </div>
                <div class="form-row my-1">
                    <div class="bg-danger text-white rounded col" style="min-height: 0">
                        <form:errors path="login"/>
                    </div>
                </div>
                <div class="form-row my-3">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text oi oi-key" style="font-size: 20px"></span>
                        </div>
                        <spring:message code="label.password" var="passwordLabel"/>
                        <form:password class="form-control" path="password" pattern="[\w\d]{3,8}" required="required"
                                       placeholder="${passwordLabel}"/>
                    </div>
                </div>
                <div class="form-row my-1">
                    <div class="bg-danger text-white rounded col" style="min-height: 0">
                        <form:errors path="password"/>
                    </div>
                </div>
                <div class="form-row my-3">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text oi oi-text" style="font-size: 20px"></span>
                        </div>
                        <spring:message code="label.firstName" var="firstnameLabel"/>
                        <form:input class="form-control" path="firstname" pattern="[\w\d]{2,10}" required="required"
                                    placeholder="${firstnameLabel}"/>
                    </div>
                </div>
                <div class="form-row my-1">
                    <div class="bg-danger text-white rounded col" style="min-height: 0">
                        <form:errors path="firstname"/>
                    </div>
                </div>
                <div class="form-row my-3">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text oi oi-text" style="font-size: 20px"></span>
                        </div>
                        <spring:message code="label.lastName" var="lastnameLabel"/>
                        <form:input class="form-control" path="lastname" pattern="[\w\d]{3,20}" required="required"
                                    placeholder="${lastnameLabel}"/>
                    </div>
                </div>
                <div class="form-row my-1">
                    <div class="bg-danger text-white rounded col" style="min-height: 0">
                        <form:errors path="lastname"/>
                    </div>
                </div>
                <div class="form-row my-3">
                    <form:button class="btn btn-primary col" id="register" name="register">
                        <spring:message code="button.register"/>
                    </form:button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
