<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dishes</title>
    <style>
        .dish {
            margin: 10px 0px;
            width: 300px;
            float: left;
        }
    </style>
</head>
<body>
<div style="width: 900px; margin:auto;">
    <c:forEach var="dish" items="${dishesList}">
        <div class="dish" id="dish_${dish.id}">
            <div class="dish_name"><a href="dish/${dish.id}">${dish.name}</a></div>
                <div class="dish_price">${dish.price}</div>
            <form id="dishForm_${dish.id}">
                <input type="number" min="1" value="1" style="width: 50px;" name="pieces_${dish.id}"> pieces</input>
            </form>
            <button onclick="addToCart('${dish.id}')">Add to Cart</button>
        </div>
    </c:forEach>

</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
    function addToCart(dishId) {
        $.ajax({
            url: "cart",
            type: "POST",
            data: {
                id: dishId,
                count: $('[name = pieces_' + dishId + ']').val()
            }
        });

    }
</script>
</body>
</html>