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
        <div class="dish" id="${dish.id}">
            <div class="dish_name">${dish.name}</div>
            <div class="dish_desc">${dish.description}</div>
            <div class="dish_price">${dish.price}</div>
            <input type="number" min="1" value="1" style="width: 50px;"> pieces</input>
            <button>Add to Cart</button>
        </div>
    </c:forEach>

</div>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script>
    $('button').click(function () {
        $.ajax({
            url: "dishes",
            type: "POST",
            data: {
                id: $(this).parent().attr("id"),
                count: $(this).parent().children('input[type="number"]').val()
            }
        });

    });

</script>
</body>
</html>