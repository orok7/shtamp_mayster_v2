<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<div class="col-sm-offset-1 col-sm-4">
    <form id="saveProductForm" action="/admin/saveProduct" class="form-horizontal" method="post" enctype="multipart/form-data">

        <input class="form-control" type="hidden" name="idProduct" value="${productId}">

        <h4>Група продуктів</h4>
        <select class="form-control" name="productProductGroup">
            <c:forEach items="${productGroups}" var="productGroup">
                <option
                    <c:if test="${productGroup.id == productProductGroupId}">selected</c:if>
                    value="${productGroup.id}">
                        ${productGroup.name}
                </option>
            </c:forEach>
        </select>

        <h4>Артикул</h4>
        <input class="form-control" type="text" name="articleProduct" value="${productArticle}" required>

        <h4>Назва продукту</h4>
        <input class="form-control" type="text" name="nameProduct" value="${productName}" required>

        <h4>Одиниці виміру</h4>
        <select class="form-control" name="productMeasurementUnits">
            <c:forEach items="${measurementUnits}" var="measurementUnit">
                <option
                        <c:if test="${measurementUnit == measurementUnitSel}">selected</c:if>
                        value="${measurementUnit}">
                        ${measurementUnit}
                </option>
            </c:forEach>
        </select>

        <h4>Ціна продукту</h4>
        <input class="form-control" type="number" name="priceProduct" value="${productPrice}" required>

        <h4>Опис продукту</h4>
        <%--<input type="text" name="descriptionProduct" value="${productDescription}">--%>
        <textarea class="form-control" name="descriptionProduct" cols="30" rows="10">${productDescription}</textarea>

        <h4>Виберіть фото</h4>
        <input class="form-select-button" type="file" name="picture" <%--required--%>>
        <br>

        <input class="btn btn-success btn-block" type="submit" value="Save">

        <c:if test="${not empty productId}">
            <button class="btn btn-warning btn-block" type="button" id="saveAsNew">Save as new</button>
        </c:if>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>

<script>
    document.getElementById('saveAsNew').onclick = function () { 
        let spf = document.getElementById('saveProductForm');
        document.getElementsByName('idProduct')[0].value = 0;
        spf.submit();
    }
</script>

</body>
</html>
