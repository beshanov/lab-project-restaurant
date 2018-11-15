<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="auth" content="<sec:authentication property="authorities[0]"/>"/>
    <title><spring:message code="title.dishes"/></title>
    <sec:csrfMetaTags/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/updateIsDeletd.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addToCart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/showDish.js"></script>
</head>
<body>
<jsp:include page="navigate.jsp"/>

<div class="container pt-5">
    <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
        <div class="row">
            <c:forEach var="dish" items="${dishesList}" varStatus="counter">
                <div class="col-xs-12 col-sm-6 col-md-4">
                    <div class="card" id="dish_${dish.id}">
                        <div class="card-body">
                            <div class="card-title" class="btn btn-primary" data-toggle="modal"
                                 data-target="#modalPage" data-dish-id="${dish.id}"
                                 style='cursor: pointer;'>${dish.name}</div>
                            <div class="dish_price card-text"><spring:message code="label.price"/>: ${dish.price} $</div>
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
    </sec:authorize>
</div>

<sec:authorize access="hasAuthority('ADMINISTRATOR')">
    <div class="container">
        <div class="row">
            <div class="col-2">
                <a href="/dish/create">
                    <button class="btn btn-dark"><spring:message code="button.addNew"/></button>
                </a>
            </div>
            <div class="col-10">
                <ul class="nav nav-tabs" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" href="#active" data-toggle="tab"><spring:message
                                code="label.activeDishes"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#deleted" data-toggle="tab"><spring:message
                                code="label.deletedDishes"/></a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div id="active" class="tab-pane fade active show">
                        <c:forEach var="dish" items="${dishesList}" varStatus="counter">
                            <c:if test="${!dish.deleted}">
                                <div class="card" id="dish_${dish.id}">
                                    <div class="card-body">
                                        <div class="card-title" class="btn btn-primary" data-toggle="modal"
                                             data-target="#modalPage" data-dish-id="${dish.id}"
                                             style='cursor: pointer;'>${dish.name}</div>
                                        <div class="card-text"><spring:message code="label.price"/>
                                            : ${dish.price}
                                        </div>
                                        <div class="card-text">
                                                ${dish.description}
                                        </div>
                                        <form class="input-group">
                                            <input type="button" class="btn btn-dark"
                                                   onclick="updateIsDeleted('${dish.id}',
                                                           'deleted')"
                                                   value="<spring:message code="button.deleteRestore"/>"/>
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div id="deleted" class="tab-pane fade">
                        <c:forEach var="dish" items="${dishesList}">
                            <c:if test="${dish.deleted}">
                                <div class="card" id="dish_${dish.id}">
                                    <div class="card-body">
                                        <div class="card-title" class="btn btn-primary" data-toggle="modal"
                                             data-target="#modalPage" data-dish-id="${dish.id}"
                                             style='cursor: pointer;'>${dish.name}</div>
                                        <div class="card-text"><spring:message code="label.price"/>
                                            : ${dish.price}
                                        </div>
                                        <div class="card-text">
                                                ${dish.description}
                                        </div>
                                        <form class="input-group">
                                            <input id="button_${dish.id}" type="button" class="btn btn-dark"
                                                   onclick="updateIsDeleted('${dish.id}', 'active')"
                                                   value="<spring:message code="button.deleteRestore"/>"/>
                                        </form>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</sec:authorize>

<div class="modal fade" id="modalPage" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <input class="input_dishName form-control-plaintext"/>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="container-fluid col-sm-7">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" class="input_dishId form-control" disabled="true"/>
                    <div class="form-group div-description">
                        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                        <label><spring:message code="label.description"/></label>
                        <textarea class="input_dishDesc form-control" style="resize:none" rows="7"></textarea>
                        </sec:authorize>
                    </div>
                    <div class="form-group div-price">
                        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                        <label><spring:message code="label.price"/></label>
                        <input class="input_dishPrice form-control"/>
                        </sec:authorize>
                    </div>
                    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                        <div class="form-check">
                            <label class="form-check-label">
                                <input type="checkbox"
                                       class="input_dishDeleted form-check-input" value=""/>
                                <spring:message code="label.deleted"/>
                            </label>
                        </div>
                    </sec:authorize>
                </div>
            </div>
            <div class="modal-footer">
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <button type="button" class="btn btn-dark" onclick="updateDish()">
                        <spring:message code="button.update"/>
                    </button>
                </sec:authorize>
                <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                    <button type="button" class="btn btn-dark" onclick="addToCartFromModal()">
                        <spring:message code="button.addToCart"/>
                    </button>
                </sec:authorize>
            </div>
        </div>
    </div>
</div>
</body>
</html>