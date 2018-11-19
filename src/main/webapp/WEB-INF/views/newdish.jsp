<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.newDish"/></title>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 51px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid">
    <div class="container col-lg-6 col-md-10 col-sm-12 h-100 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6);">
        <div class="container col-lg-6 col-md-8 col-sm-10 text-white"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px;">
            <h1 class="text-center my-4"><spring:message code="label.newDish"/></h1>
            <form:form modelAttribute="dish" action="${pageContext.request.contextPath}/dish" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <spring:message code="label.name" var="nameLabel"/>
                <spring:message code="label.description" var="descriptionLabel"/>
                <spring:message code="label.price" var="priceLabel"/>
                <div class="form-row my-3">
                    <input class="form-control" name="name" pattern="[\w\d\s-']{2,45}" required="required"
                           placeholder="${nameLabel}"/>
                </div>
                <div class="form-row my-3">
                    <textarea class="form-control" name="description" required="required"
                              placeholder="${descriptionLabel}" rows="10"></textarea>
                </div>
                <div class="form-row my-3">
                    <input class="form-control" name="price" pattern="\d{1,10}(\.\d{1,2})?" required="required"
                           placeholder="${priceLabel}"/>
                </div>
                <div class="form-row my-3">
                    <button class="btn btn-primary col" type="submit">
                        <spring:message code="button.add"/>
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>