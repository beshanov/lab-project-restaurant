<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.cart"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cart.js"></script>
    <sec:csrfMetaTags/>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<spring:message code="button.remove" var="removeLabel"/>
<spring:message code="button.submit" var="submitLabel"/>
<div class="row container-fluid">
    <div class="col-lg-2"></div>
    <div class="d-inline-block col-lg-5">
        <c:forEach items="${sessionScope.dishMap}" var="entry">
            <c:set var="totalPrice" value="${totalPrice + entry.key.price * entry.value}"/>
            <div class="card mb-2">
                <div class="card-header">
                        ${entry.key.name}
                </div>
                <div class="card-body">
                    <p class="card-text">
                            ${entry.key.description}
                    </p>
                    <div class="text-right">
                        <input type="button" class="btn btn-dark" onclick="deleteFromCart('${entry.key.id}')"
                               value="${removeLabel}">
                    </div>
                </div>
                <div class="card-footer text-right">
                    <spring:message code="label.amount"/>: ${entry.value}
                    &nbsp;&nbsp;&nbsp;
                    <spring:message code="label.price"/>: ${entry.key.price * entry.value}
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="d-inline-block col-lg-3">
        <div class="card sticky-top">
            <div class="card-header">
                <h3><spring:message code="label.newOrder"/></h3>
            </div>
            <div class="card-body">
                <spring:message code="label.total"/>: ${totalPrice}
            </div>
            <div class="card-footer align-content-center">
                <form action="order" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="submit" class="btn-lg btn-dark" value="${submitLabel}"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
