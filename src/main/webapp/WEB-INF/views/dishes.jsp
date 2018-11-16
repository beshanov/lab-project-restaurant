<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="auth" content="<sec:authentication property="authorities[0]"/>"/>
    <title><spring:message code="title.dishes"/></title>
    <sec:csrfMetaTags/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addToCart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dishes.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/showDish.js"></script>
</head>
<body>
<jsp:include page="navigate.jsp"/>
<sec:authorize access="!hasAuthority('ADMINISTRATOR')">
    <div class="container">
        <div id="forUser" class="row">

        </div>
    </div>
    <script>
            getDishes(false,6);
    </script>
</sec:authorize>

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
                        <a class="nav-link active" href="#active" data-toggle="tab"
                           onclick="getDishes(false)"><spring:message
                                code="label.activeDishes"/></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#deleted" data-toggle="tab" onclick="getDishes(true)"><spring:message
                                code="label.deletedDishes"/></a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div id="active" class="tab-pane fade active show">

                    </div>
                    <div id="deleted" class="tab-pane fade">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        loadBothTabs();
    </script>
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