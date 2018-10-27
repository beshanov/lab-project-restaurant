function deleteFromCart(dishId) {
    $.ajax({
        url: "cart?id=" + dishId,
        type: "DELETE",
        success: window.location.replace(window.location)
    });
}