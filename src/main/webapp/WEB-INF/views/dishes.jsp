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
<div class="container-fluid col-11">
    <div class="row">
        <c:forEach var="dish" items="${dishesList}" varStatus="counter">
            <div class="col-lg-3 col-md-4 col-sm-6 mb-4">
                <div class="dish card h-100" id="dish_${dish.id}">
                    <div class="dish_name card-header"><a href="dish/${dish.id}">${dish.name}</a></div>
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
</body>
</html>