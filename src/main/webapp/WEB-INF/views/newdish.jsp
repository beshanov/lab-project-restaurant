<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
    <head>
        <meta charset="utf-8">
        <title><spring:message code="title.newDish"/></title>
    </head>
    <body>
    <h1 align="center"><spring:message code="label.newDish"/></h1>
        <form:form id="dishForm" modelAttribute="dish" action="/dish" method="post">
            <table align="center">
                <tr>
                    <td>
                        <form:label path="name"><spring:message code="label.name"/></form:label>
                    </td>
                    <td>
                        <form:input path="name" pattern="[\w\d\s-\"]{2,45}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="description"><spring:message code="label.description"/></form:label>
                    </td>
                    <td>
                        <form:textarea  path="description" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="price"><spring:message code="label.price"/></form:label>
                    </td>
                    <td>
                        <form:input path="price" pattern="\d+(\.\d{1,2})?" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td/>
                    <td>
                        <form:button id="Add"><spring:message code="button.add"/></form:button>
                    </td>
                </tr>
                <tr>
                    <td/>
                    <td><a href="${pageContext.request.contextPath}/dish"><spring:message code="label.back"/></a></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>