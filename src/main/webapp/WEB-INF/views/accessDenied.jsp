<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.accessDenied"/></title>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 51px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid" style="margin-top: 51px">
    <div class="container col-lg-6 col-md-10 col-sm-12 h-100 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6);">
        <div class="container col-lg-4 col-md-6 col-sm-8"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px;">
            <p class="my-3 text-white text-center"><spring:message code="label.accessDenied"/></p>
            <form action="${pageContext.request.contextPath}/dish">
                <div class="form-row my-3">
                    <button class="btn btn-primary col" type="submit">
                        <spring:message code="label.menu"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
