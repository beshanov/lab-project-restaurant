<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Settings</title>
</head>
<body>
<jsp:include page="navigate.jsp"></jsp:include>
<h1 align="center">Settings</h1>
<div class="container-fluid">
    <form:form id="settingsForm" modelAttribute="user" action="profile" method="post">
        <div class="form-group">
            <form:label path="login">Login: </form:label>
            <form:input class="form-control" path="login" pattern="[\w\d]{3,10}" required="required" value="${user.login}"/>
            <div class="invalid-feedback">
                <form:errors path="login"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="password">Password</form:label>
            <form:password class="form-control" path="password" pattern="[\w\d]{3,10}" required="required" value="${user.password}"/>
            <div class="invalid-feedback">
                <form:errors path="password"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="firstname">FirstName</form:label>

            <form:input class="form-control" path="firstname" pattern="[\w\d]{3,20}" required="required" value="${user.firstname}"/>
            <div class="invalid-feedback">
                <form:errors path="firstname"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="lastname">LastName</form:label>
            <form:input class="form-control" path="lastname" pattern="[\w\d]{3,20}" required="required" value="${user.lastname}"/>
            <div class="invalid-feedback">
                <form:errors path="lastname"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="role">Role: </form:label>
            <form:label path="role">${user.role.name}</form:label>
        </div>
        <div class="form-group">
            <form:button  class="btn btn-dark" id="apply">Apply</form:button>
        </div>
    </form:form>
</div>
</body>
</html>