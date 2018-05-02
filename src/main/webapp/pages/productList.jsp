<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Image</th>
            <th>Article</th>
            <th>Name</th>
            <th>ProductGroup</th>
            <th>MeasUnits</th>
            <th>Price</th>
            <th>Description</th>
            <th>Change</th>
            <th>Remove</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="indexPG" value="1" scope="page" />
        <c:forEach items="${productList}" var="product">
                <tr>
                    <td>${indexPG}</td>
                    <td><img src="${product.mainPicture}" alt="" style="width: 100px"></td>
                    <td>${product.article}</td>
                    <td>${product.name}</td>
                    <td>${product.group.name}</td>
                    <td>${product.measurementUnits}</td>
                    <td>${product.price}</td>
                    <td>${product.description}</td>
                    <td><a href="/admin/modifyProduct${product.id}">Modify</a></td>
                    <td><a href="/admin/removeProduct${product.id}">Delete</a></td>
                </tr>
                <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
            </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>
