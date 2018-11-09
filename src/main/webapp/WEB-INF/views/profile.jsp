<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title><spring:message code="title.profile"/></title>
    <sec:csrfMetaTags/>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<h1 align="center"><spring:message code="label.settings"/></h1>
<div class="container-fluid col-sm-3">
    <form:form id="settingsForm" modelAttribute="user" action="profile" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group row">
            <form:label class="col-form-label col-4" path="login"><spring:message code="label.login"/></form:label>
            <form:input class="form-control col-8" path="login" pattern="[\w\d]{3,10}" required="required"
                        value="${user.login}"/>
        </div>
        <div class="form-group row">
            <form:errors path="login" class="alert alert-danger col-12"/>
        </div>
        <div class="form-group row">
            <form:label class="col-4 col-form-label" path="firstname"><spring:message
                    code="label.firstName"/></form:label>
            <form:input class="form-control col-8" path="firstname" pattern="[\w\d]{3,20}" required="required"
                        value="${user.firstname}"/>
        </div>
        <div class="form-group row">
            <form:errors path="firstname" class="alert alert-danger"/>
        </div>
        <div class="form-group row">
            <form:label class="col-4 col-form-label" path="lastname"><spring:message
                    code="label.lastName"/></form:label>
            <form:input class="form-control col-8" path="lastname" pattern="[\w\d]{3,20}" required="required"
                        value="${user.lastname}"/>
        </div>
        <div class="form-group row">
            <form:errors path="lastname" class="alert alert-danger"/>
        </div>
        <div class="form-group row">
            <label class="col-4 col-form-label"><spring:message code="label.role"/></label>
            <input class="form-control col-8" readonly="true" value="${user.role.name}"/>
        </div>
        <div class="form-group">
            <form:button class="btn btn-primary" id="apply" type="submit"><spring:message
                    code="button.apply"/></form:button>
            <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#myModal">
                <spring:message code="button.change"/>
            </button>
        </div>
    </form:form>
</div>

<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="profile/updatePassword" method="post">
                <div class="modal-header">
                    <h4 class="modal-title">
                        <spring:message code="button.change"/>
                    </h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div id="errormsg" class="alert alert-danger" style="display:none"></div>
                    <div class="form-group">
                        <label class="col-form-label">
                            <spring:message code="label.oldpassword"/>
                            </label>
                            <input type="password" class="form-control" name="oldpassword" pattern="[\w\d]{3,10}" required="required"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label"><spring:message code="label.newpassword"/></label>
                            <input type="password" class="form-control" name="password" pattern="[\w\d]{3,10}" required="required"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label"><spring:message code="label.confirm"/></label>
                            <input type="password" class="form-control" id="confirm" pattern="[\w\d]{3,10}" required="required"/>
                    </div>
                    <div class="alert alert-danger" id="error" style="display:none">
                        <strong>Password mismatch!</strong>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-dark" onclick="changePassword()">
                        <spring:message code="button.apply"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>