<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><spring:message code="title.login"/></title>
    </head>
    <body>
    <a href="login?lang=en">English</a> | <a href="login?lang=ru">Russian</a>
    <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
            <table align="center">
                <tr>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <td>
                        <label path="login"><spring:message code="label.login"/></label>
                    </td>
                    <td>
                        <input path="login" name="login" pattern="[\w\d]{3,8}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label path="password"><spring:message code="label.password"/></label>
                    </td>
                    <td>
                        <input type="password" name="password" path="password" pattern="[\w\d]{3,8}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td align="left">
                        <button id="loginProcess" name="loginProcess"><spring:message code="button.login"/></button>
                    </td>
                </tr>
                <tr></tr>
                <tr>
                    <td></td>
                    <td><a href="${pageContext.request.contextPath}/dish"><spring:message code="label.home"/></a>
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