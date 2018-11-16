<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="utf-8" %>

<c:forEach var="dish" items="${dishesList}">
    <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
        <div class="col-xs-12 col-sm-6 col-md-4">
    </sec:authorize>
    <div class="card" id="dish_${dish.id}">
        <div class="card-body">
            <div class="card-title" class="btn btn-primary" data-toggle="modal"
                 data-target="#modalPage" data-dish-id="${dish.id}"
                 style='cursor: pointer;'>${dish.name}</div>
            <div class="dish_price card-text"><spring:message code="label.price"/>: ${dish.price} $</div>

            <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                <div class="card-text">
                        ${dish.description}
                </div>
                <c:if test="${!dish.deleted}">
                    <input type="button" class="btn btn-dark" onclick="updateIsDeleted('${dish.id}')"
                           value="<spring:message code="button.delete"/>"/>
                </c:if>
                <c:if test="${dish.deleted}">
                    <input type="button" class="btn btn-dark" onclick="updateIsDeleted('${dish.id}')"
                           value="<spring:message code="button.restore"/>"/>
                </c:if>
            </sec:authorize>


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
        </div>
        </sec:authorize>


    </div>
    </div>

</c:forEach>

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
            <li class="page-item bg-dark">
                <a class="page-link bg-dark text-white">${currentPage} ... ${maxPage}</a>
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
