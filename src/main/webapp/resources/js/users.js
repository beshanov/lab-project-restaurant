function saveUserRole(userId) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "user/" + userId,
        type: "POST",
        data: {
            roleId: $("#select_" + userId).val()
        },
        success: function () {
            hideButton(userId)
        }
    });
}


function showButton(id) {
    $('#UserButton_' + id).attr("hidden", false);
}

function hideButton(id) {
    $('#UserButton_' + id).attr("hidden", true);
}