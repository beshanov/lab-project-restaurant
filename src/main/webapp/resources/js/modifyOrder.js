function modifyOrderStatus(orderId, statusId) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "orderModifyStatus",
        type: "POST",
        data: {
            orderId: orderId,
            statusId: statusId
        },
        //success: window.location.replace(window.location)
    });
}