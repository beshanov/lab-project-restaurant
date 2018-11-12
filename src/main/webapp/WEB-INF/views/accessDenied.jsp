<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<h2>Sorry, you do not have permission to view this page.</h2>
Click <a href="<c:url value="/dish"/> ">here</a>
to go back to the Menu.
</body>
</html>
