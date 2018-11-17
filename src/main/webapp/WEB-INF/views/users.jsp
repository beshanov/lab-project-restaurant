<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <sec:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.users"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/users.js"></script>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid" style="margin-top: 51px">
    <div class="container col-lg-6 col-md-8 col-sm-10 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6); min-height: 100%">
        <div class="container col-lg-8 col-md-8 col-sm-10"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px">
            <spring:message code="button.save" var="saveLabel"/>
            <c:forEach var="user" items="${usersList}">
                <div class="user card my-3 bg-dark text-white" id="user_${user.id}">
                    <div class="card-header">${user.lastname} ${user.firstname} (${user.login})</div>
                    <div class="card-body">
                        <form id="userForm${user.id}">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <input class="btn btn-primary btn-outline-light" type="button"
                                           value="${saveLabel}" id="UserButton_${user.id}"
                                           onclick="saveUserRole('${user.id}')"
                                           hidden>
                                </div>
                                <select class="custom-select bg-secondary text-white" id="select_${user.id}"
                                        onchange="showButton(${user.id})">
                                    <c:forEach var="role" items="${rolesList}">
                                        <option class="bg-secondary" value="${role.id}"
                                                <c:if test="${user.role.id == role.id}">selected</c:if>>
                                                ${role.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </form>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>