<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <sec:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.dish"/></title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<sec:authorize access="hasAuthority('ADMINISTRATOR')" var="isAdmin"/>
<h1 align="center">${dish.name} <spring:message code="label.details"/></h1>
<div class="container-fluid col-sm-7">
    <form:form id="dishForm" modelAttribute="dish" action="/dish/${dish.id}" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="form-group">
            <form:label path="id">Id </form:label>
            <form:input class="form-control" path="id" disabled="true"/>
        </div>
        <div class="form-group">
            <form:label path="name"><spring:message code="label.name"/></form:label>

            <form:input class="form-control" path="name" disabled="${!isAdmin}"/>
        </div>
        <div class="form-group">
            <form:label path="description"><spring:message code="label.description"/></form:label>

            <form:textarea class="form-control" path="description" disabled="${!isAdmin}"/>
        </div>
        <div class="form-group">
            <form:label path="price"><spring:message code="label.price"/></form:label>

            <form:input class="form-control" path="price" disabled="${!isAdmin}"/>
        </div>
        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
            <div class="form-group">
                <form:label path="deleted"><spring:message code="label.deleted"/></form:label>
                <form:checkbox class="form-control" path="deleted"/>
            </div>
            <div class="form-group">
                <form:button class="btn btn-dark" id="dish_${dish.id}" name="dish_${dish.id}">
                    <spring:message code="button.update"/>
                </form:button>
            </div>
        </sec:authorize>
    </form:form>
</div>

</body>
</html>