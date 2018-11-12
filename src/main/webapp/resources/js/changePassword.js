function changePassword() {
    $("#error").hide();
    $("#mismatch").hide();
    $("#wrongFormat").hide();
    var pass = $("#newPassword").val();
    var valid = pass == $("#matchPassword").val();
    if (!valid) {
        $("#mismatch").show();
        return;
    }
    if(!/^[\w\d]{3,10}$/.test(pass)){
        $("#wrongFormat").show();
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
            $("#oldPassword").val('');
            $("#newPassword").val('');
            $("#matchPassword").val('');
        },
        error: function () {
            $("#error").show();
        }
    });
}