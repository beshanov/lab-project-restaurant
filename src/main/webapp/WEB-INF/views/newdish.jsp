<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>New dish</title>
    </head>
    <body>
        <h1 align="center">New dish</h1>
        <form:form id="dishForm" modelAttribute="dish" action="/dish" method="post">
            <table align="center">
                <tr>
                    <td>
                        <form:label path="name">Name</form:label>
                    </td>
                    <td>
                        <form:input path="name" pattern="[\w\d\s-\"]{2,45}" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="description">Description</form:label>
                    </td>
                    <td>
                        <form:textarea  path="description" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form:label path="price">price</form:label>
                    </td>
                    <td>
                        <form:input path="price" pattern="\d+(\.\d{1,2})?" required = "required"/>
                    </td>
                </tr>
                <tr>
                    <td/>
                    <td>
                        <form:button id="Add">Add</form:button>
                    </td>
                </tr>
                <tr>
                    <td/>
                    <td><a href="/dish">Back</a></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>