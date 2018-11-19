var currentPageActive = 1;
var currentPageDeleted = 1;

function loadBothTabs(){
    getDishes(true, 6);
    getDishes(false, 6);
}

function getDishes(deleted,countPerPage) {
    if (deleted) {
        page = currentPageDeleted;
    } else {
        page = currentPageActive;
    }
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "dish/page/" + page,
        data: {'deleted': deleted, 'countPerPage':countPerPage},
        type: "GET",
        success: function (data) {
            if (deleted) {
                $("#deleted").html(data);
            }
            else {
                $("#active").html(data);
                $("#forUser").html(data);
            }
        }
    })
}

function changeCurrentPage(page, deleted) {
    if (deleted) {
        currentPageDeleted = page;
    } else {
        currentPageActive = page;
    }
    getDishes(deleted, 6);
}

function updateIsDeleted(dishId) {
    $.ajax({
        beforeSend: function (request) {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            request.setRequestHeader(header, token);
        },
        url: "dish/" + dishId,
        type: "DELETE",
        success: function () {
            loadBothTabs();
        }
    });
}





