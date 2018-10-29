<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <sec:csrfMetaTags />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dish ${dish.name}</title>
</head>
<body>
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
                <form:label path="name">Name</form:label>
            </td>
            <td>
                <form:input path="name"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">Description</form:label>
            </td>
            <td>
                <form:textarea  path="description"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="price">price</form:label>
            </td>
            <td>
                <form:input path="price"/>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <form:button id="dish_${dish.id}" name="dish_${dish.id}">Update</form:button>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td></td>
            <td><a href="/dish">Back</a>
            </td>
        </tr>
    </table>
</form:form>

</div>
</body>
</html>