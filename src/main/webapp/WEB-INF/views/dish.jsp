<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <sec:csrfMetaTags />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.dish"/></title>
</head>
<body>
<h1>${dish.name} details</h1>
<form:form id="dishForm" modelAttribute="dish" action="/dish/${dish.id}" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <table align="center">
        <tr>
            <td>
                <form:label path="id" >Id </form:label>
            </td>
            <td>
                <form:input path="id" disabled = "true"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="name"><spring:message code="label.name"/></form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description"><spring:message code="label.description"/></form:label>
            </td>
            <td>
                <form:textarea  path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price"><spring:message code="label.price"/></form:label>
            </td>
            <td>
                <form:input path="price"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:button id="dish_${dish.id}" name="dish_${dish.id}">
                    <spring:message code="button.update"/>
                </form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="/dish"><spring:message code="label.back"/></a></td>
        </tr>
    </table>
</form:form>

</div>
</body>
</html>