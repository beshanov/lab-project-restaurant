<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <sec:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.users"/></title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div class="container-fluid col-5">
    <spring:message code="button.save" var="saveLabel"/>
    <c:forEach var="user" items="${usersList}">
        <div class="user card mb-2" id="user_${user.id}">
            <div class="card-header">
                    ${user.lastname} ${user.firstname} (${user.login})
            </div>
            <div class="card-body">
                <form id="userForm${user.id}">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <input class="btn btn-outline-dark" type="button"
                                   value="${saveLabel}" id="UserButton_${user.id}"
                                   onclick="saveUserRole('${user.id}')"
                                   hidden>
                        </div>
                        <select class="custom-select" id="select_${user.id}" onchange="showButton(${user.id})">
                            <c:forEach var="role" items="${rolesList}">
                                <option value="${role.id}" <c:if test="${user.role.id == role.id}">selected</c:if>>
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
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/users.js"></script>

</body>
</html>