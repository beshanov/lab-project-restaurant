<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.dishes"/></title>
    <sec:csrfMetaTags/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/deleteDish.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addToCart.js"></script>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<sec:authorize access="hasAuthority('ADMINISTRATOR')">
    <div class="container">
        <div class="row">
            <c:forEach var="dish" items="${dishesList}" varStatus="counter">
                <div class="col-xs-12 col-sm-6 col-md-4">
                    <div class="card" id="dish_${dish.id}">
                        <div class="card-body">
                            <a class="card-title" href="dish/${dish.id}">${dish.name}</a>
                            <div class="dish_price card-text"><spring:message code="label.price"/>: ${dish.price}</div>
                            <form class="input-group" id="dishForm_${dish.id}">
                                <input type="number" min="1" value="1" class="form-control"
                                       aria-describedby="button-addon"
                                       name="pieces_${dish.id}">
                                <div class="input-group-append">
                                    <input type="button" class="btn btn-outline-dark" id="button-addon"
                                           onclick="addToCart('${dish.id}')"
                                           value="<spring:message code="button.addToCart"/>">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</sec:authorize>

<sec:authorize access="!hasAuthority('ADMINISTRATOR')">
    <div class="container">
        <ul class="nav nav-tabs" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" href="#active" data-toggle="tab"><spring:message code="label.activeDishes"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#deleted" data-toggle="tab"><spring:message code="label.deletedDishes"/></a>
            </li>
        </ul>

        <div class="tab-content">
            <div id="active" class="tab-pane fade active show">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-4 col-sm-6 col-md-3">
                            <c:forEach var="dish" items="${dishesList}" varStatus="counter">
                                <c:if test="${!dish.deleted}">
                                    <div class="card" id="dish_${dish.id}">
                                        <div class="card-body">
                                            <a class="card-title" href="dish/${dish.id}">${dish.name}</a>
                                            <div class="card-text"><spring:message code="label.price"/>
                                                : ${dish.price}
                                            </div>
                                            <div class="card-text">
                                                ${dish.description}
                                            </div>
                                            <form class="input-group">
                                                <div class="input-group-append">
                                                    <button class="btn btn-dark" onclick="deleteDish('${dish.id}')"><spring:message
                                                            code="button.delete"/>
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>
                            <div class="card">
                                <a href="${pageContext.request.contextPath}/dish/create" type="button"><spring:message
                                        code="button.addNew"/></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="deleted" class="tab-pane fade">
                <h1>euuuuuuuuuuuuuuuuu</h1>
                <c:forEach var="dish" items="${dishesList}">
                    <c:if test="${dish.deleted}">
                        <div class="card" id="dish_${dish.id}">
                            <div class="card-body">
                                <a class="card-title" href="dish/${dish.id}">${dish.name}</a>
                                <div class="card-text"><spring:message code="label.price"/>
                                    : ${dish.price}
                                </div>
                                <div class="card-text">
                                        ${dish.description}
                                </div>
                                <form class="input-group">
                                    <div class="input-group-append">
                                        <button class="btn btn-dark" onclick="deleteDish('${dish.id}')"><spring:message
                                                code="button.return"/>
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>