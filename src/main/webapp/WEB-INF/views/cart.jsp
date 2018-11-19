<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.cart"/></title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cart.js"></script>
    <sec:csrfMetaTags/>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 51px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid">
    <div class="container col-lg-6 col-md-10 col-sm-12 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6); min-height: 100%">
        <div class="container col-lg-8 col-md-8 col-sm-10"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px">
            <c:if test="${fn:length(sessionScope.dishMap) == 0}">
                <div class="container-fluid">
                    <div class="row my-5">
                        <div class="col text-center text-white"><spring:message code="label.cartEmpty"/></div>
                    </div>
                    <div class="row my-5">
                        <div class="col-3"></div>
                        <a class="col-6 btn btn-primary" href="${pageContext.request.contextPath}/dish">
                            <spring:message code="label.menu"/>
                        </a>
                        <div class="col-3"></div>
                    </div>
                </div>
            </c:if>
            <c:if test="${fn:length(sessionScope.dishMap) > 0}">
                <h2 class="text-center text-white my-3"><spring:message code="label.newOrder"/></h2>
                <div class="accordion" id="cartAccordion">
                    <spring:message code="button.remove" var="removeLabel"/>
                    <c:forEach items="${sessionScope.dishMap}" var="entry">
                        <c:set var="orderTotal" value="${orderTotal + entry.key.price * entry.value}"/>
                        <div class="card my-3 bg-secondary text-white">
                            <div class="card-header row align-items-end" id="heading${entry.key.id}">
                                <button class="btn btn-secondary col text-left" type="button" data-toggle="collapse"
                                        data-target="#collapse${entry.key.id}" aria-expanded="true"
                                        aria-controls="collapse${entry.key.id}"
                                        style="overflow: hidden">
                                    <span class="badge badge-dark badge-pill">${entry.value}</span>
                                        ${entry.key.name}
                                </button>
                                <input type="button" class="btn btn-dark col-lg-3 col-md-4 col-sm-6"
                                       onclick="deleteFromCart('${entry.key.id}')"
                                       value="${removeLabel}">
                            </div>
                            <div id="collapse${entry.key.id}" class="collapse" aria-labelledby="heading${entry.key.id}"
                                 data-parent="#cartAccordion">
                                <div class="card-body">
                                    <a>${entry.key.description}</a>
                                    <h5 class="text-right">
                                        <spring:message code="label.total"/>: ${entry.key.price * entry.value}$
                                    </h5>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <div class="container-fluid my-3 bg-transparent">
                    <h2 class="text-center text-white">
                        <spring:message code="label.total"/>: ${orderTotal}$
                    </h2>
                    <form class="form-row" action="order" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <spring:message code="button.submit" var="submitLabel"/>
                        <div class="col-3"></div>
                        <input type="submit" class="btn btn-primary col-6" value="${submitLabel}"/>
                        <div class="col-3"></div>
                    </form>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
