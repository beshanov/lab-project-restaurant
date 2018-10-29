function deleteFromCart(dishId) {
    $.ajax({
        url: "cart?id=" + dishId,
        type: "DELETE",
        success: window.location.replace(window.location)
    });
}

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
