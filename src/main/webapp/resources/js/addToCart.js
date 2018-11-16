function addToCart(dishId, contextPath) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: contextPath + "/cart",
        type: "POST",
        data: {
            id: dishId,
            count: $('[name = pieces_' + dishId + ']').val()
        },
        success: function() {
            setCartSize(contextPath);
            $("#modalPage").modal('hide');
        }
    });
}