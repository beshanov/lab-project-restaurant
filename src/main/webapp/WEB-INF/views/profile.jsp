<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
    <head>
        <meta charset="utf-8">
        <title><spring:message code="title.profile"/></title>
    </head>
    <body>
    <a href="profile?lang=en">English</a> | <a href="profile?lang=ru">Russian</a>
        <h1 align="center">Settings</h1>
        <form:form id="settingsForm" modelAttribute="user" action="profile" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <table align="center">
                <tr>
                    <td>
                        <form:label path="login"><spring:message code="label.login"/></form:label>
                    </td>
                    <td>
                        <form:input path="login" pattern="[\w\d]{3,10}" required = "required" value="${user.login}"/>
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
                        <form:password path="password" pattern="[\w\d]{3,10}" required = "required" value="${user.password}"/>
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
                        <form:input path="firstname" pattern="[\w\d]{3,20}" required = "required" value="${user.firstname}"/>
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
                        <form:input path="lastname" pattern="[\w\d]{3,20}" required = "required" value="${user.lastname}"/>
                    </td>
                    <td>
                        <form:errors path="lastname"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="role"><spring:message code="label.role"/></form:label>
                    </td>
                    <td>
                        <form:label path="role">${user.role.name}</form:label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:button id="apply"><spring:message code="button.apply"/></form:button>
                    </td>
                </tr>
                <tr>
                    <td><a href="/dish"><spring:message code="label.home"/></a></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>