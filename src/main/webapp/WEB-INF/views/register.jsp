<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.register"/></title>
</head>
<body>
<form:form id="regForm" modelAttribute="user" action="register" method="post">
    <table align="center">
        <tr>
            <td>
                <form:label path="login"><spring:message code="label.login"/> </form:label>
            </td>
            <td>
                <form:input path="login" pattern="[\w\d]{3,8}" required="required"/>
            </td>
            <td>
                <form:errors path="login"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="password"><spring:message code="label.password"/></form:label>
            </td>
            <td>
                <form:password path="password" pattern="[\w\d]{3,8}" required="required"/>
            </td>
            <td>
                <form:errors path="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="firstname"><spring:message code="label.firstName"/></form:label>
            </td>
            <td>
                <form:input path="firstname" pattern="[\w\d]{2,10}" required="required"/>
            </td>
            <td>
                <form:errors path="firstname"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="lastname"><spring:message code="label.lastName"/></form:label>
            </td>
            <td>
                <form:input path="lastname" pattern="[\w\d]{3,20}" required="required"/>
            </td>
            <td>
                <form:errors path="lastname"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:button id="register" name="register"><spring:message code="button.register"/></form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="/dish"><spring:message code="label.home"/></a>
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
