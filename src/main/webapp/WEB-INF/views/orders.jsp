<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <title><spring:message code="title.orders"/></title>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover;">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid" style="margin-top: 51px">
    <div class="container col-lg-8 col-md-10 col-sm-12 h-100 d-flex align-items-center"
         style="background-color: rgba(0, 0, 0, 0.6);">
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
            <div class="container col-lg-10 col-md-10 col-sm-12"
                 style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px;">
                <div class="row my-5 bg-transparent">
                    <div class="col-4 my-3">
                        <div class="list-group" id="list-tab" role="tablist">
                            <c:forEach items="${orderList}" var="entry">
                                <a class="list-group-item list-group-item-action" id="listEntry${entry.id}"
                                   data-toggle="list" href="#tabEntry" role="tab" aria-controls="home">
                                    <div class="row">
                                        <div class="text-left col-5"><h5>#${entry.id}</h5></div>
                                        <div class="text-right col-7">${entry.status.name}</div>
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-8 my-3">
                        <div class="tab-content bg-light " id="nav-tabContent">
                            <div class="tab-pane" id="tabEntry" role="tabpanel">
                                <div class="container-fluid">
                                    //order content here
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
