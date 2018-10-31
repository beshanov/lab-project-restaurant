function addToCart(dishId) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "cart",
        type: "POST",
        data: {
            id: dishId,
            count: $('[name = pieces_' + dishId + ']').val()
        }
    });
}