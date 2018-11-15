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
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover;">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid" style="margin-top: 51px">
    <div class="container col-lg-8 col-md-10 col-sm-12 h-100 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6);">
        <div class="container col-lg-4 col-md-6 col-sm-8 text-white"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px;">
            <h2 class="text-center my-3"><spring:message code="title.profile"/></h2>
            <form:form id="settingsForm" modelAttribute="user" action="profile" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-row my-3">
                    <div class="text-left col py-2"><spring:message code="label.login"/></div>
                    <input class="form-control col" name="login" pattern="[\w\d]{3,10}" required="required"
                           value="${user.login}"/>
                </div>
                <div class="form-row my-1">
                    <form:errors path="login" class="alert alert-danger col"/>
                </div>
                <div class="form-row my-3">
                    <div class="text-left col py-2"><spring:message code="label.firstName"/></div>
                    <input class="form-control col" name="firstname" pattern="[\w\d]{3,10}" required="required"
                           value="${user.firstname}"/>
                </div>
                <div class="form-row my-1">
                    <form:errors path="firstname" class="alert alert-danger"/>
                </div>
                <div class="form-row my-3">
                    <span class="text-left col py-2"><spring:message code="label.lastName"/></span>
                    <input class="form-control col" name="lastname" pattern="[\w\d]{3,10}" required="required"
                           value="${user.lastname}"/>
                </div>
                <div class="form-row my-1">
                    <form:errors path="lastname" class="alert alert-danger"/>
                </div>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <div class="form-row my-3">
                        <span class="text-left col py-2"><spring:message code="label.role"/></span>
                        <input class="form-control col bg-secondary text-dark btn-outline-dark" readonly="true"
                               value="${user.role.name}"/>
                    </div>
                </sec:authorize>
                <div class="form-row my-3">
                    <button class="btn btn-primary col" id="apply" type="submit">
                        <spring:message code="button.apply"/>
                    </button>
                </div>
                <div class="form-row my-3">
                    <button type="button" class="btn btn-secondary col" data-toggle="modal" data-target="#myModal">
                        <spring:message code="button.change"/>
                    </button>
                </div>
                <div class="form-row my-3">
                    <div id="success" class="alert alert-success col" style="display:none">
                        <strong><spring:message code="alert.success"/></strong>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog col-2">
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
                <div class="col-4"></div>
                <button type="button" class="btn btn-primary col-4" onclick="changePassword()">
                    <spring:message code="button.apply"/>
                </button>
                <div class="col-4"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>