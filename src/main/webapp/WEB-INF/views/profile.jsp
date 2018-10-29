<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Settings</title>
    </head>
    <body>
        <h1 align="center">Settings</h1>
        <form:form id="settingsForm" modelAttribute="user" action="profile" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <table align="center">
                <tr>
                    <td>
                        <form:label path="login">Login: </form:label>
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
                        <form:label path="password">Password</form:label>
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
                        <form:label path="firstname">FirstName</form:label>
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
                        <form:label path="lastname">LastName</form:label>
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
                        <form:label path="role">Role: </form:label>
                    </td>
                    <td>
                        <form:label path="role">${user.role.name}</form:label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:button id="apply">Apply</form:button>
                    </td>
                </tr>
                <tr>
                    <td><a href="/dish">Home</a></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>