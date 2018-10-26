function deleteFromCart(dishId) {
    $.ajax({
        url: "cart",
        type: "POST",
        data: {
            deleteId: dishId
        },
        success: window.location.replace(window.location)
    });
}