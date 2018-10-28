function deleteDish(dishId) {
    $.ajax({
        url: "dish/" + dishId,
        type: "DELETE",
        complete: setTimeout(function () {
            window.location.replace("/dish")
        }, 200)
    });
}