<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title><spring:message code="title.profile"/></title>
    <sec:csrfMetaTags/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/changePassword.js"></script>
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
        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
            <div class="form-group row">
                <label class="col-4 col-form-label"><spring:message code="label.role"/></label>
                <input class="form-control col-8" readonly="true" value="${user.role.name}"/>
            </div>
        </sec:authorize>
        <div class="form-group">
            <form:button class="btn btn-primary" id="apply" type="submit"><spring:message
                    code="button.apply"/></form:button>
            <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#myModal">
                <spring:message code="button.change"/>
            </button>
        </div>
        <div class="form-group">
            <div id="success" class="alert alert-success col-12" style="display:none">
                <strong><spring:message code="alert.success"/></strong>
            </div>
        </div>
    </form:form>
</div>

<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
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
                    <div class="form-group">
                        <label class="col-form-label">
                            <spring:message code="label.oldpassword"/>
                        </label>
                        <input id="oldPassword" name="oldPassword" type="password" class="form-control"
                               required="required"/>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label"><spring:message code="label.newpassword"/></label>
                        <input id="newPassword" name="newPassword" type="password" class="form-control"
                               required="required"/>
                    </div>
                    <div class="alert alert-danger" id="wrongFormat" style="display:none">
                        <strong><spring:message code="error.wrongFormat"/></strong>
                    </div>
                    <div class="form-group">
                        <label class="col-form-label"><spring:message code="label.confirm"/></label>
                        <input id="matchPassword" type="password" class="form-control"
                               required="required"/>
                    </div>
                    <div class="alert alert-danger" id="mismatch" style="display:none">
                        <strong><spring:message code="error.mismatch"/></strong>
                    </div>
                    <div class="alert alert-danger" id="error" style="display:none">
                        <strong><spring:message code="error.wrong"/></strong>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" onclick="changePassword()">
                        <spring:message code="button.apply"/>
                    </button>
                </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>