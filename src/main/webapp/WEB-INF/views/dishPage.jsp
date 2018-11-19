<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<div class="row card-columns my-3">
    <c:forEach var="dish" items="${dishesList}">
        <div class="card my-3 ml-4" id="dish_${dish.id}" style="width: 302px">
            <img class="card-img-top" src="${pageContext.request.contextPath}/resources/img/test.png"
                 data-toggle="modal" data-target="#modalPage" data-dish-id="${dish.id}"
                 style="width: 300px; height: 300px; cursor: pointer">
            <div class="card-footer">
                <h5 class="card-title text-dark" style="overflow: hidden">${dish.name}</h5>
                <div class="dish_price card-text"><spring:message code="label.price"/>: ${dish.price}$</div>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <div class="row">
                        <div class="col-3"></div>
                        <c:if test="${!dish.deleted}">
                            <input type="button" class="btn btn-primary col-6" onclick="updateIsDeleted('${dish.id}')"
                                   value="<spring:message code="button.delete"/>"/>
                        </c:if>
                        <c:if test="${dish.deleted}">
                            <input type="button" class="btn btn-primary col-6" onclick="updateIsDeleted('${dish.id}')"
                                   value="<spring:message code="button.restore"/>"/>
                        </c:if>
                        <div class="col-3"></div>
                    </div>
                </sec:authorize>
                <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                    <form class="input-group" id="dishForm_${dish.id}">
                        <input type="number" min="1" value="1" class="form-control bg-light"
                               aria-describedby="button-addon"
                               name="pieces_${dish.id}">
                        <div class="input-group-append">
                            <input type="button" class="btn btn-primary" id="button-addon"
                                   onclick="addToCart('${dish.id}', '${pageContext.request.contextPath}')"
                                   value="<spring:message code="button.addToCart"/>">
                        </div>
                    </form>
                </sec:authorize>
            </div>
        </div>
    </c:forEach>
</div>

<c:if test="${dishesList.size() == 0}">
    <div class="alert alert-dark" role="alert">
        <spring:message code="label.empty"/>
    </div>
</c:if>
<c:if test="${maxPage != 0}">
    <div class="col-12">
        <ul class="pagination mt-2">
            <li class="page-item <c:if test="${!(currentPage > 1)}">disabled</c:if>">
                <a class="page-link" onclick="changeCurrentPage(${currentPage - 1},${deleted})">
                    <spring:message code="label.previous"/>
                </a>
            </li>
            <li class="page-item">
                <a class="page-link">${currentPage} / ${maxPage}</a>
            </li>
            <li class="page-item <c:if test="${!(currentPage < maxPage)}">disabled</c:if>">
                <a class="page-link" onclick="changeCurrentPage(${currentPage + 1},${deleted})">
                    <spring:message code="label.next"/>
                </a>
            </li>
        </ul>
    </div>
    <script></script>
</c:if>
