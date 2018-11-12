function updateIsDeleted(dishId, dest) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "dish/" + dishId,
        type: "DELETE",
        success: function () {
            if($("#dish_" + dishId).parent().attr('id') == 'active'){
                $("#deleted").append($("#dish_" + dishId));
            }else{
                $("#active").append($("#dish_" + dishId));
            }
        }
    });
}
