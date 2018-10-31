<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <sec:csrfMetaTags/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users</title>
</head>
<body>
<div>
    <c:forEach var="user" items="${usersList}">
        <div class="user" id="user_${user.id}">
            <div>${user.lastname}
                    ${user.firstname}
                <form id="userForm${user.id}">
                    <select id="select_${user.id}" onchange="showButton(${user.id})">
                        <c:forEach var="role" items="${rolesList}">
                            <option value="${role.id}" <c:if test="${user.role.id == role.id}">selected</c:if>>
                                    ${role.name}
                            </option>
                        </c:forEach>
                    </select>
                    <input type="button" value="Save" id="UserButton_${user.id}" onclick="saveUserRole('${user.id}')"
                           hidden>
                </form>

            </div>
        </div>
        <hr>
    </c:forEach>
</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/users.js"></script>

</body>
</html>