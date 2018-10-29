<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dish ${dish.name}</title>
</head>
<body>
<jsp:include page="navigate.jsp"></jsp:include>
<div class="container-fluid">
<form:form id="dishForm" modelAttribute="dish" action="/dish/${dish.id}" method="post">
    <div class="form-group">
                <form:label path="id">Id </form:label>

                <form:input class="form-control" path="id" disabled="true"/>
    </div>
    <div class="form-group">
                <form:label path="name">Name</form:label>

                <form:input class="form-control" path="name"/>
    </div>
    <div class="form-group">
                <form:label path="description">Description</form:label>

                <form:textarea class="form-control" path="description"/>
    </div>
    <div class="form-group">
                <form:label path="price">price</form:label>

                <form:input class="form-control" path="price"/>
    </div>
    <div class="form-group">

                <form:button  class="btn btn-dark" id="dish_${dish.id}" name="dish_${dish.id}">Update</form:button>
    </div>
</form:form>

</div>
</body>
</html>