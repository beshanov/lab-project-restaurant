$(document).ready(function () {
    $(document).on('shown.bs.modal', '#modalPage', function (e) {
        var dishId = $(e.relatedTarget).data('dish-id');
        $.ajax({
            beforeSend: function (request) {
                var token = $("meta[name='_csrf']").attr("content");
                var header = $("meta[name='_csrf_header']").attr("content");
                request.setRequestHeader(header, token);
            },
            url: "dish/" + dishId,
            type: "GET",
            contentType: "application/json",
            success: function (data) {
                var auth = $('meta[name="auth"]').attr("content");
                if (!(auth.toString() === 'ADMINISTRATOR')) {
                    $('.input_dishName').attr('readonly', 'true');
                    $('.div-description').html(data.description);
                    $('.div-price').html(data.price + ' &#8381;');
                }
                $('.input_dishId').val(data.id);
                $('.input_dishName').val(data.name);
                $('.input_dishDesc').val(data.description);
                $('.input_dishPrice').val(data.price);
                $('#img').attr("src", "resources/img/dish_" + data.id + ".png");
                var checked = data.deleted;
                $('.input_dishDeleted').prop('checked', checked);
            }
        });
    })

});

function updateDish() {
    var dishId = $('.input_dishId').val();
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "dish/" + dishId,
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            id: $('.input_dishId').val(),
            name: $('.input_dishName').val(),
            description: $('.input_dishDesc').val(),
            price: $('.input_dishPrice').val(),
            deleted: $('.input_dishDeleted').is(':checked')
        }),
        success: function () {
            $("#modalPage").modal('hide');
            location.reload();
        }
    });
}

function addToCartFromModal(contextPath) {
    var dishId = $('.input_dishId').val();
    addToCart(parseInt(dishId, 10), contextPath);
}
