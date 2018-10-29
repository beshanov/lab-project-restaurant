<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form id="loginForm" action="/login" method="post">
            <table align="center">
                <tr>
                    <td>
                        <label path="login">Login: </label>
                    </td>
                    <td>
                        <input path="login" pattern="[\w\d]{3,8}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label path="password">Password:</label>
                    </td>
                    <td>
                        <input type="password" path="password" pattern="[\w\d]{3,8}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <button id="loginProcess" name="loginProcess">Login</button>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td></td>
                    <td><a href="home.jsp">Home</a>
                    </td>
                </tr>
            </table>
        </form>
        <table align="center">
            <tr>
                <td style="font-style: italic; color: red;">${message}</td>
            </tr>
        </table>
    </body>
</html>