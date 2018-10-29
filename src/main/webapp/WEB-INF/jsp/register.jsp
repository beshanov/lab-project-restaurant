<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>
<jsp:include page="navigate.jsp"></jsp:include>
<div class="container-fluid">
    <form:form id="regForm" modelAttribute="user" action="register" method="post">
        <div class="form-group">
            <form:label path="login">Login: </form:label>
            <form:input class="form-control" path="login" pattern="[\w\d]{3,8}" required="required"/>
            <div class="invalid-feedback">
                <form:errors path="login"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="password">Password</form:label>
            <form:password class="form-control" path="password" pattern="[\w\d]{3,8}" required="required"/>
            <div class="invalid-feedback">
                <form:errors path="password"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="firstname">FirstName</form:label>
            <form:input class="form-control" path="firstname" pattern="[\w\d]{2,10}" required="required"/>
            <div class="invalid-feedback">
                <form:errors path="firstname"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="lastname">LastName</form:label>
            <form:input class="form-control" path="lastname" pattern="[\w\d]{3,20}" required="required"/>
            <div class="invalid-feedback">
                <form:errors path="lastname"/>
            </div>
        </div>
        <div class="form-group">
            <form:button class="btn btn-dark" id="register" name="register">Register</form:button>
        </div>
    </form:form>
</div>
</body>
</html>
