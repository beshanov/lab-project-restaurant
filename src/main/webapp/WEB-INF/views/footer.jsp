<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<footer class="text-light" style="background-color: rgba(0, 0, 0, 0.75);">
    <div class="row mr-0 ml-0">
        <div class="col-md-3 mx-auto">
            <h5 class="font-weight-bold text-uppercase mt-3 mb-4">
                <spring:message code="title.location"/></h5>
            <div id="mymap" class="mt-3 mb-4"></div>
        </div>
        <div class="col-md-3 mx-auto">
            <div class="mx-auto">
            <h5 class="font-weight-bold text-uppercase mt-3 mb-4 mx-auto">
                <spring:message code="title.navigate"/></h5>
            <ul class="list-unstyled mx-auto text-light">
                <sec:authorize access="!hasAuthority('ADMINISTRATOR')">
                    <li>
                        <a class="text-light" href="${pageContext.request.contextPath}/cart">
                            <spring:message code="title.cart"/></a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li>
                        <a class="text-light" href="${pageContext.request.contextPath}/profile">
                            <spring:message code="title.profile"/>
                        </a>
                    </li>
                    <li>
                        <a class="text-light" href="${pageContext.request.contextPath}/order">
                            <spring:message code="title.orders"/>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasAuthority('ADMINISTRATOR')">
                    <li>
                        <a class="text-light" href="${pageContext.request.contextPath}/user">
                            <spring:message code="title.users"/>
                        </a>
                    </li>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <li>
                        <a class="text-light" href="${pageContext.request.contextPath}/register">
                            <spring:message code="button.register"/>
                        </a>
                    </li>
                    <li>
                        <a class="text-light" href="${pageContext.request.contextPath}/login">
                            <spring:message code="button.login"/>
                        </a>
                    </li>
                </sec:authorize>
                <li>
                    <a class="text-light" href="#"><spring:message code="title.top"/></a>
                </li>
            </ul>
            </div>
        </div>
        <div class="col-md-3 mx-auto">
                <h5 class="font-weight-bold text-uppercase mt-3 mb-4">
                    <spring:message code="title.about"/></h5>
                <small><spring:message code="about.text"/></small>
        </div>
    </div>
    <div class="row mr-0 ml-0 text-center">
        <div class="col-md-3 mx-auto">
            <p>&copy; 2018 Copyright</p>
        </div>
    </div>
</footer>
<script type="text/javascript" charset="utf-8" src="https://api-maps.yandex.ru/services/constructor/1.0/js/?um=constructor%3A307c5e1683bce0a8432b7a7ef0ba81ee6676ffd026eb6f444e2e0eb654c86058&source=constructorLink&amp;height=200&amp;id=mymap"></script>