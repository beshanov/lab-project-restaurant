<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
    </head>
    <body>
        <form:form id="regForm" modelAttribute="user" action="register" method="post">
            <table align="center">
                <tr>
                    <td>
                        <form:label path="login">Login: </form:label>
                    </td>
                    <td>
                        <form:input path="login" name="login" id="username"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="password">Password</form:label>
                    </td>
                    <td>
                        <form:password path="password" name="password" id="password"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="firstname">FirstName</form:label>
                    </td>
                    <td>
                        <form:input path="firstname" name="firstname" id="firstname"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="lastname">LastName</form:label>
                    </td>
                    <td>
                        <form:input path="lastname" name="lastname" id="lastname"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <form:button id="register" name="register">Register</form:button>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td></td>
                    <td><a href="/index">Home</a>
                    </td>
                </tr>
            </table>
        </form:form>
    <p>${message}</p>
    </body>
</html>