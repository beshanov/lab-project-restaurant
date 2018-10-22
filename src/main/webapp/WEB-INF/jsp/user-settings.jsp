<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Settings</title>
</head>
<body>
    <h1 align="center">Settings</h1>
    <form:form id="settingsForm" modelAttribute="user" action="user-settings" method="post">
        <table align="center">
            <tr>
                <td>
                    <form:label path="login">Login: </form:label>
                </td>
                <td>
                    <form:input path="login" value="${user.login}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="password">Password</form:label>
                </td>
                <td>
                    <form:password path="password" value="${user.password}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="firstname">FirstName</form:label>
                </td>
                <td>
                    <form:input path="firstname" value="${user.firstname}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <form:label path="lastname">LastName</form:label>
                </td>
                <td>
                    <form:input path="lastname" value="${user.lastname}"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <form:button id="apply">Apply</form:button>
                </td>
            </tr>
            <tr></tr>
            <tr>
                <td><a href="/register">Home</a></td>
            </tr>
        </table>
    </form:form>
</body>
</html>