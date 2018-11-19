<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.login"/></title>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 51px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid">
    <div class="container col-lg-6 col-md-10 col-sm-12 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6); min-height: 100%">
        <div class="container col-lg-4 col-md-6 col-sm-8"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px">
            <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-row my-3">
                    <div class="input-group">
                        <spring:message code="label.login" var="loginLabel"/>
                        <div class="input-group-prepend">
                            <span class="input-group-text oi oi-person" style="font-size: 20px"></span>
                        </div>
                        <input type="text" class="form-control" name="login" pattern="[\w\d]{3,8}"
                               placeholder="${loginLabel}" required="required"/>
                    </div>
                </div>
                <div class="form-row my-3">
                    <div class="input-group">
                        <spring:message code="label.password" var="passwordLabel"/>
                        <div class="input-group-prepend">
                            <span class="input-group-text oi oi-key " style="font-size: 20px"></span>
                        </div>
                        <input class="form-control" type="password" name="password" pattern="[\w\d]{3,8}"
                               placeholder="${passwordLabel}" required="required"/>
                    </div>
                </div>
                <div class="form-row my-3">
                    <button class="btn btn-primary col" id="loginProcess" name="loginProcess">
                        <spring:message code="button.login"/>
                    </button>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/register">
                <div class="form-row my-3">
                    <button class="btn btn-dark col" type="submit">
                        <spring:message code="button.register"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>