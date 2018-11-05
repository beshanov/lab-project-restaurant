function deleteDish(dishId) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "dish/" + dishId,
        type: "DELETE",
        success: $("#delete_" + dishId).remove()
    });
}
