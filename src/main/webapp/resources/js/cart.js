function deleteFromCart(dishId) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "cart/" + dishId,
        type: "DELETE",
        success: window.location.replace(window.location)
    });
}