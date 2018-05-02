<%@include file="head.jsp" %>

<div class="container">
    <h1>Welcome to Admin Page</h1>
    <form action="/user/logout" method="post" id="logoutForm">
        <a href="#"><button type="submit" class="btn btn-link">Close admin session</button></a>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}" />
    </form>
</div>

<div class="container">
    <div class="dropdown fleft">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Insert into table
            <span class="caret"></span></button>
        <ul class="dropdown-menu">
            <%--<li><a href="/admin/newImage">Images</a></li>--%>
            <li><a href="/admin/newProduct">Product</a></li>
            <li><a href="/admin/newProductGroup">Product Group</a></li>
        </ul>
    </div>
    <div class="dropdown fleft mrg-l10">
        <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Modify table
            <span class="caret"></span></button>
        <ul class="dropdown-menu">
            <li><a href="/admin/listUser">User</a></li>
            <li><a href="/admin/listInvoice">Invoice</a></li>
            <li><a href="/admin/listProduct">Product</a></li>
            <li><a href="/admin/listProductGroup">Product Group</a></li>
        </ul>
    </div>
</div>

<c:if test="${not empty errorAdminPage}">
<div class="container">
    <br>
    <div class="alert alert-danger"><h4>${errorAdminPage}</h4></div>
</div>
</c:if>