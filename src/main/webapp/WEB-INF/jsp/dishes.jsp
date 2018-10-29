<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<jsp:include page="navigate.jsp"></jsp:include>
<div class="container-fluid">
    <div class="card-deck">
        <c:forEach var="dish" items="${dishesList}">
            <div class="card" id="dish_${dish.id}">
                <div class="dish_name card-header"><a href="dish/${dish.id}">${dish.name}</a></div>
                <div class="card-body">
                    <div class="dish_price card-text">${dish.description}</div>
                    <div class="dish_price card-text">${dish.price}</div>
                </div>
                <div class="card-footer">
                    <div class="input-group mb-3">
                        <input type="number" min="1" value="1" class="form-control " aria-describedby="button-addon"
                               name="pieces_${dish.id}"/>
                        <div class="input-group-append">
                            <button class="btn btn-outline-secondary" id="button-addon"
                                    onclick="addToCart('${dish.id}')">Add to Cart
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cart.js"></script>
</body>
</html>