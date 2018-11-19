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
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/addToCart.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/dishes.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/showDish.js"></script>
</head>
<body style="background: url('${pageContext.request.contextPath}/resources/img/background.jpg') no-repeat center center fixed;
        background-size: cover; padding-top: 48px">
<jsp:include page="navigate.jsp"/>
<div class="container-fluid">
    <div class="container col-lg-10 col-md-10 col-sm-12 my-5 d-flex align-items-center"
         style="background-color: min-height: 100%">
        <div class="container col-lg-8 col-md-10 col-sm-12"
             style="background-color: rgba(0, 0, 0, 0.8); border-radius: 10px">
            <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                <div id="forUser"></div>
                <script>getDishes(false, 6);</script>
            </sec:authorize>
            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                <div class="row my-3">
                    <a class="col-3" href="${pageContext.request.contextPath}/dish/create">
                        <button class="btn btn-primary">
                            <spring:message code="button.addNew"/>
                        </button>
                    </a>
                </div>
                <div class="row my-3">
                    <div class="col">
                        <ul class="nav nav-tabs nav-fill bg-dark" role="tablist">
                            <li class="nav-item text-white">
                                <a class="nav-link active" href="#active" data-toggle="tab" onclick="getDishes(false)">
                                    <spring:message code="label.activeDishes"/>
                                </a>
                            </li>
                            <li class="nav-item text-white">
                                <a class="nav-link" href="#deleted" data-toggle="tab" onclick="getDishes(true)">
                                    <spring:message code="label.deletedDishes"/>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content bg-transparent">
                            <div id="active" class="tab-pane fade active show bg-transparent"></div>
                            <div id="deleted" class="tab-pane fade bg-transparent"></div>
                        </div>
                    </div>
                </div>
                <script>loadBothTabs();</script>
            </sec:authorize>
        </div>
    </div>
</div>

<div class="modal fade" id="modalPage" role="dialog">
    <div class="modal-dialog" style="max-width: 602px">
        <div class="modal-content bg-dark text-white" style="width: auto">
            <div class="modal-header">
                <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                    <input class="input_dishName form-control-plaintext bg-transparent pl-2 text-white"/>
                </sec:authorize>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <input class="input_dishName form-control-plaintext bg-light pl-2"/>
                </sec:authorize>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body p-0">
                <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                    <img src="${pageContext.request.contextPath}/resources/img/test600.png"
                         style="width: 600px; height: 600px">
                </sec:authorize>
                <div class="container-fluid mt-4">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <input type="hidden" class="input_dishId form-control" disabled="true"/>
                    <div class="row my-3 mx-3">
                        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                            <label><spring:message code="label.description"/></label>
                            <textarea class="input_dishDesc form-control" style="resize:none" rows="7"></textarea>
                        </sec:authorize>
                        <div class="div-description col text-left"></div>
                    </div>
                    <div class="row my-3 mx-3">
                        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                            <label><spring:message code="label.price"/></label>
                            <input class="input_dishPrice form-control"/>
                        </sec:authorize>
                        <div class="col-10"></div>
                        <div class="col-2 text-right"><h5 class="div-price"></h5></div>
                    </div>
                    <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                        <div class="form-check mx-3">
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
                <div class="container">
                    <div class="row">
                        <div class="col-3"></div>
                        <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                            <button type="button" class="btn btn-primary col-6" onclick="updateDish()">
                                <spring:message code="button.update"/>
                            </button>
                        </sec:authorize>
                        <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                            <button type="button" class="btn btn-primary col-6"
                                    onclick="addToCartFromModal('${pageContext.request.contextPath}')">
                                <spring:message code="button.addToCart"/>
                            </button>
                        </sec:authorize>
                        <div class="col-3"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>