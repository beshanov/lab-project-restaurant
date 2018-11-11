function changePassword() {
    var pass = $("#newPassword").val();
    var valid = pass == $("#matchPassword").val();
    if (!valid) {
        $("#error").show();
        return;
    } else {
        $("#error").hide();
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
            success: function (data) {
                if (data != "") {
                    $("#wrong").show();
                    return;
                }
                $("#myModal").modal('hide');
                $("#success").show();
            }
        });
    }
}