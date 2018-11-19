<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.orders"/></title>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 48px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid d-flex">
    <div class="container col-lg-8 col-md-10 col-sm-12 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6); min-height: 100%">
        <c:if test="${fn:length(orderList) == 0}">
            <div class="container col-lg-4 col-md-10 col-sm-12"
                 style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px;">
                <p class="my-3 text-white text-center"><spring:message code="label.orderListEmpty"/></p>
                <sec:authorize access="hasAuthority('CUSTOMER')">
                    <form action="${pageContext.request.contextPath}/dish">
                        <div class="form-row my-3">
                            <button class="btn btn-primary col" type="submit">
                                <spring:message code="label.menu"/>
                            </button>
                        </div>
                    </form>
                </sec:authorize>
            </div>
        </c:if>
        <c:if test="${fn:length(orderList) > 0}">
            <div class="container col-lg-6 col-md-10 col-sm-12 d-flex flex-column"
                 style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px;">
                <h2 class="text-white text-center my-4"><spring:message code="label.orders"/></h2>
                <div class="list-group w-100 my-3">
                    <c:forEach items="${orderList}" var="entry">
                        <a href="${pageContext.request.contextPath}/order/${entry.id}"
                           class="list-group-item list-group-item-action flex-column align-items-start bg-dark text-white">
                            <div class="d-flex w-100 justify-content-between">
                                <h5 class="mb-1">#${entry.id}</h5>
                                <small><fmt:formatDate type="both" value="${entry.orderDate}"/></small>
                            </div>
                            <div class="d-flex w-100 justify-content-between">
                                <div class="mb-1">
                                    <spring:message code="label.user"/>: ${entry.user.login}
                                    (${entry.user.firstname} ${entry.user.lastname})
                                </div>
                                <div class="mb-1"><spring:message code="label.status"/>: ${entry.status.name}</div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
