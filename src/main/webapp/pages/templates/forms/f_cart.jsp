<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="indexPG" value="1" scope="page" />
        <c:set var="prodSum" value="0" scope="page" />
        <c:forEach items="${listProd}" var="product">
            <tr>
                <td>${indexPG}</td>
                <td><img src="${product.product.mainPicture}" alt="" style="width: 100px"></td>
                <td>${product.product.article}</td>
                <td>${product.product.name}</td>
                <td><input id="numProd${product.product.id}" type="number" class="numProd"
                           value="${product.number}" style="width: 50px;"></td>
                <td>${product.product.measurementUnits}</td>
                <td id="prodPrice${product.product.id}">${product.product.price}</td>
                <td id="prodSum${product.product.id}">${product.product.price*product.number}</td>
                    <%--<td><a href="/admin/modifyProduct${product.id}">Modify</a></td>--%>
                <td><a href="/order/cart/remProd${product.product.id}">Delete</a></td>
            </tr>
            <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
            <c:set var="prodSum" value="${prodSum + product.product.price*product.number}" scope="page"/>
        </c:forEach>
        <tr>
            <td></td>
            <td><a href="/order/cart/remAllProd" class="btn btn-success">Clear All</a></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><strong>Всього:</strong></td>
            <td id="prodSum"><strong>${prodSum} грн.</strong></td>

<script>
    $('.numProd').change(function () {
        let num = this.value;
        let name = "prodid_";
        let id = this.id.split('numProd')[1];
        document.cookie = name + id + "="+num+"; path=/; expires=-1";
        let price = document.getElementById('prodPrice' + id).innerText;
        let prevOneSum = document.getElementById('prodSum' + id).innerText;
        let sum = document.getElementById('prodSum').innerText;
        document.getElementById('prodSum' + id).innerText = num*price;
        document.getElementById('prodSum').innerText = (num*price-prevOneSum) + parseFloat(sum);
    });
</script>
