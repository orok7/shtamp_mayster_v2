<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Change</th>
            <th>Remove</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="indexPG" value="1" scope="page" />
        <c:forEach items="${productGroupList}" var="productGroup">
                <tr>
                    <td>${indexPG}</td>
                    <td>${productGroup.name}</td>
                    <td><a href="/admin/modifyProductGroup${productGroup.id}">Modify</a></td>
                    <td><a href="/admin/removeProductGroup${productGroup.id}">Delete</a></td>
                </tr>
                <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
            </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>
