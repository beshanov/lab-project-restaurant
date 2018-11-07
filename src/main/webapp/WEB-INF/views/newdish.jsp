<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta charset="utf-8">
    <title><spring:message code="title.newDish"/></title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div class="container-fluid col-sm-7">
    <h1 align="center"><spring:message code="label.newDish"/></h1>
    <form:form id="dishForm" modelAttribute="dish" action="/dish" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <div class="form-group">
        <form:label path="name"><spring:message code="label.name"/></form:label>
        <form:input class="form-control" path="name" pattern="[\w\d\s-\"]{2,45}" required="required"/>
    </div>
    <div class="form-group">
        <form:label path="description"><spring:message code="label.description"/></form:label>
        <form:textarea class="form-control" path="description" required="required"/>
    </div>
    <div class="form-group">
        <form:label path="price"><spring:message code="label.price"/></form:label>
        <form:input class="form-control" path="price"  pattern="\d{1,10}(\.\d{1,2})?" required="required"/>
    </div>
    <div class="form-group">
        <form:button class="btn btn-dark" id="Add"><spring:message code="button.add"/></form:button>
    </div>
    </form:form>
</body>
</html>