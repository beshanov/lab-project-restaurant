<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="title.dishes"/></title>
    <sec:csrfMetaTags/>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/deleteDish.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addToCart.js"></script>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<div class="container-fluid col-11">
    <div class="card-deck">
        <c:forEach var="dish" items="${dishesList}" varStatus="counter">
            <div class="dish card mb-3" id="dish_${dish.id}">
                <div class="dish_name card-header"><a href="dish/${dish.id}">${dish.name}</a></div>
                <div class="card-body">
                    <div class="dish_desc card-text">${dish.description}</div>
                    <div class="dish_price card-text"><spring:message code="label.price"/>: ${dish.price}</div>

                </div>
                <div class="card-footer">
                    <sec:authorize access="hasAuthority('CUSTOMER')">
                        <form class="input-group" id="dishForm_${dish.id}">
                            <input type="number" min="1" value="1" class="form-control" aria-describedby="button-addon"
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
            <div class="w-100 d-none d-sm-block d-md-none"></div>
            <c:if test="${counter.count % 2 == 0}">
                <div class="w-100 d-none d-md-block d-lg-none"></div>
            </c:if>
            <c:if test="${counter.count % 3 == 0}">
                <div class="w-100 d-none d-lg-block"></div>
            </c:if>

        </c:forEach>
        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
            <div class="card">
                <div class="card-header">
                    <a href="${pageContext.request.contextPath}/dish/create" type="button"><spring:message
                            code="button.addNew"/></a>
                </div>
            </div>
        </sec:authorize>
    </div>
</div>
</body>
</html>