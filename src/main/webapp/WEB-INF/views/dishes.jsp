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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/deleteDish.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addToCart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/showDish.js"></script>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<sec:authorize access="hasAuthority('ADMINISTRATOR')" var="isAdmin"/>
<div class="container-fluid col-11">
    <div class="row">
        <c:forEach var="dish" items="${dishesList}" varStatus="counter">
            <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                <div class="dish card h-100" id="dish_${dish.id}">
                    <div class="dish_name card-header" class="btn btn-primary" data-toggle="modal"
                         data-target="#modalPage" data-dish-id="${dish.id}">${dish.name}</div>
                    <div class="card-body">
                        <div class="dish_desc card-text">${dish.description}</div>
                        <div class="dish_price card-text"><spring:message code="label.price"/>: ${dish.price}</div>

                    </div>
                    <div class="card-footer">
                        <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
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
                        </sec:authorize>
                        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                            <c:if test="${dish.deleted == false}">
                                <button class="btn btn-dark" onclick="deleteDish('${dish.id}')"><spring:message
                                        code="button.delete"/></button>
                            </c:if>
                        </sec:authorize>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
        <div class="card">
            <div class="card-header">
                <a href="${pageContext.request.contextPath}/dish/create" type="button"><spring:message
                        code="button.addNew"/></a>
            </div>
        </div>
    </sec:authorize>
</div>

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
                    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                        <div class="form-group">
                            <label>Id </label>
                            <input class="input_dishId form-control" disabled="true"/>
                        </div>
                    </sec:authorize>
                    <div class="form-group">
                        <label><spring:message code="label.description"/></label>
                        <textarea class="input_dishDesc form-control"></textarea>
                    </div>
                    <div class="form-group">
                        <label><spring:message code="label.price"/></label>
                        <input class="input_dishPrice form-control"/>
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
            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                <div class="modal-footer">
                    <button type="button" class="btn btn-dark" onclick="updateDish()">
                        <spring:message code="button.update"/>
                    </button>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>


</body>
</html>