function changePassword() {
    var pass = $("#newPassword").val();
    var valid = pass == $("#matchPassword").val();
    $("#error").hide();
    $("#mismatch").hide();
    if (!valid) {
        $("#mismatch").show();
        return;
    }
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "profile/updatePassword",
        type: "POST",
        data: {
            oldPassword: $("#oldPassword").val(),
            newPassword: $("#newPassword").val()
        },
        success: function () {
            $("#myModal").modal('hide');
            $("#success").show();
        },
        error: $("#error").show()
    });
}