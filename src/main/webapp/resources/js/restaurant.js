function setCartSize(contextPath) {
    $.ajax({
        url: contextPath + "/cart.size",
        type: "GET",
        success: function (response) {
            $('#navBarCartBadgeCounter').text(response);
        }
    });
}