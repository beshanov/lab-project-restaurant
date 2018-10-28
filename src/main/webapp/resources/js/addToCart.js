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