<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<jsp:include page="navigate.jsp"></jsp:include>
<div class="container-fluid">
    <form:form id="loginForm" modelAttribute="user" action="login" method="post">
        <div class="form-group">
            <form:label path="login">Login: </form:label>
            <form:input path="login" class="form-control" name="login" id="login" pattern="[\w\d]{3,8}"
                        required="required"/>
        </div>
        <div class="form-group">
            <form:label path="password">Password:</form:label>
            <form:password path="password" class="form-control" pattern="[\w\d]{3,8}"
                           required="required"/>
        </div>
        <div class="form-group">
            <form:button id="loginProcess" class="btn btn-dark" name="loginProcess">Login</form:button>
        </div>
    </form:form>
</div>
<div class="container-fluid" style="font-style: italic; color: red;">${message}</div>
</body>
</html>