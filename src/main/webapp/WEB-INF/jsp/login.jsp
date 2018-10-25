<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form:form id="loginForm" modelAttribute="user" action="login" method="post">
            <table align="center">
                <tr>
                    <td>
                        <form:label path="login">Login: </form:label>
                    </td>
                    <td>
                        <form:input path="login" name="login" id="login" pattern="[\w\d]{3,8}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="password">Password:</form:label>
                    </td>
                    <td>
                        <form:password path="password" name="password" id="password" pattern="[\w\d]{3,8}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <form:button id="loginProcess" name="loginProcess">Login</form:button>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td></td>
                    <td><a href="home.jsp">Home</a>
                    </td>
                </tr>
            </table>
        </form:form>
        <table align="center">
            <tr>
                <td style="font-style: italic; color: red;">${message}</td>
            </tr>
        </table>
    </body>
</html>