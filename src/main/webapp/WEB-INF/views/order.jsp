<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.order"/></title>
    <sec:csrfMetaTags/>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 51px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid d-flex">
    <div class="container col-lg-10 col-md-12 col-sm-12 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6); min-height: 100%">
        <div class="container col-lg-10 col-md-12 col-sm-12"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px">
            <div class="row my-3">
                <div class="col-6">
                    <div class="jumbotron my-0">
                        <ul class="list-group">
                            <c:forEach items="${dishMap}" var="entry">
                                <li class="list-group-item">
                                    <span class="badge badge-dark badge-pill">${entry.value}</span>
                                    <h5 class="mb-1" style="overflow: hidden">${entry.key.name}</h5>
                                    <p class="mb-1">${entry.key.description}</p>
                                    <p class="text-right">
                                        <spring:message code="label.price"/>: ${entry.key.price * entry.value}$
                                    </p>
                                </li>
                            </c:forEach>
                            <li class="list-group-item text-right list-group-item-dark">
                                <h4><spring:message code="label.total"/>: ${order.amount}$</h4>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-6">
                    <div class="card">
                        <div class="card-header">
                            <h2 class="text-left"><spring:message code="label.orderNumber"/>: ${order.id}</h2>
                        </div>
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-6"><spring:message code="label.user"/>:</div>
                                    <div class="col-6">${order.user.firstname} ${order.user.lastname}</div>
                                </div>
                                <div class="row">
                                    <div class="col-6"><spring:message code="label.login"/>:</div>
                                    <div class="col-6">${order.user.login}</div>
                                </div>
                                <div class="row">
                                    <div class="col-6"><spring:message code="label.date"/>:</div>
                                    <c:set var="castedDate" value="${order.orderDate.time}"/>
                                    <div class="col-6"><fmt:formatDate type="both" value="${order.orderDate}"/></div>
                                </div>
                                <div class="row">
                                    <div class="col-6"><spring:message code="label.status"/>:</div>
                                    <div class="col-6">${order.status.name}</div>
                                </div>
                                <div class="row">
                                    <div class="col-6"><spring:message code="label.admin"/>:</div>
                                    <div class="col-6">${order.admin.firstname} ${order.admin.lastname}</div>
                                </div>
                            </div>
                        </div>
                        <div class="card-footer">
                            <form action="${pageContext.request.contextPath}/order.setStatus" method="post" class="row">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <input type="hidden" name="orderId" value="${order.id}">
                                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                                    <c:choose>
                                        <c:when test="${order.status.id == 1}">
                                            <input type="hidden" name="statusId" value="2">
                                            <div class="col-4"></div>
                                            <button class="btn-primary btn col-4" type="submit">
                                                <spring:message code="button.accept"/>
                                            </button>
                                            <div class="col-4"></div>
                                        </c:when>
                                        <c:when test="${order.status.id == 2}">
                                            <input type="hidden" name="statusId" value="3">
                                            <div class="col-4"></div>
                                            <button class="btn-primary btn col-4" type="submit">
                                                <spring:message code="button.ready"/>
                                            </button>
                                            <div class="col-4"></div>
                                        </c:when>
                                    </c:choose>
                                </sec:authorize>
                            </form>
                            <c:if test="${order.status.id == 4}">
                                <h5 class="text-center"><spring:message code="label.orderClosed"/></h5>
                            </c:if>
                            <c:if test="${order.status.id == 3}">
                                <sec:authorize access="hasAuthority('CUSTOMER')">
                                    <form class="row" action="${pageContext.request.contextPath}/order/${order.id}/pay"
                                          method="post">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <input type="hidden" name="orderId" value="${order.id}">
                                        <input type="hidden" name="statusId" value="4">
                                        <div class="col-4"></div>
                                        <button class="btn btn-primary col-4" type="submit">
                                            <spring:message code="button.pay"/>
                                        </button>
                                        <div class="col-4"></div>
                                    </form>
                                </sec:authorize>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
