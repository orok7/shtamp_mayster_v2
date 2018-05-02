<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th></th>
            <th>Ціна</th>
            <th>Сума</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="indexPG" value="1" scope="page" />
        <c:forEach items="${invoiceProducts}" var="product">
            <tr>
                <td>${indexPG}</td>
                <td><img src="${product.product.mainPicture}" alt="" style="width: 100px"></td>
                <td>${product.product.article}</td>
                <td>${product.product.name}</td>
                <td>${product.number}</td>
                <td>${product.product.measurementUnits}</td>
                <td>${product.product.price}</td>
                <td>${product.product.price*product.number}</td>
            </tr>
            <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="/admin/listInvoice">Повернутися до списку</a></td>
            <td></td>
            <td></td>
            <td><strong>Всього:</strong></td>
            <td><strong>${invoiceSum} грн.</strong></td>
        </tr>
        </tbody>
    </table>
</div>

</body>
</html>
