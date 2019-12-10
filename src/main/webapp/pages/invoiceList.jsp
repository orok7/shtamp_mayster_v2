<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Number</th>
            <th>Date</th>
            <th>Buyer</th>
            <th>Sum</th>
            <th>Payed</th>
            <th>Payment Type</th>
            <th>Status</th>
            <th></th>
            <th></th>
            <%--<th></th>--%>
        </tr>
        </thead>
        <tbody>
        <c:set var="indexPG" value="1" scope="page" />
        <c:forEach items="${listInvoice}" var="invoice">
            <tr>
                <td>${indexPG}</td>
                <td>${invoice.id}</td>
                <td>${invoice.date}</td>
                <td>${invoice.buyer.name} ${invoice.buyer.surname}</td>
                <td>${invoice.sum}</td>
                <form action="/admin/saveInvoice${invoice.id}" method="post">
                    <td><input class="form-control" type="number" name="odPayed"
                               style="width: 100px;" value="${invoice.payed}"></td>

                    <td><select class="form-control" id="odInvoicePaymentTypes" name="odInvoicePaymentTypes">
                            <c:forEach items="${paymentTypes}" var="paymentType">
                                <option <c:if test="${invoice.paymentType == paymentType}">
                                              selected
                                        </c:if>
                                        value="${paymentType}">
                                        ${paymentType.getDescription()}
                                </option>
                            </c:forEach>
                        </select></td>

                    <td><select class="form-control" id="odInvoiceStatus" name="odInvoiceStatus">
                        <c:forEach items="${invoiceStatus}" var="status">
                            <option <c:if test="${invoice.status == status}">
                                selected
                            </c:if>
                                    value="${status}">
                                    ${status.getDescription()}
                            </option>
                        </c:forEach>
                    </select></td>

                    <td><button type="submit" class="btn btn-link">Save</button></td>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                </form>

                <%--<td><a href="/admin/saveInvoice${invoice.id}">Save</a></td>--%>
                <td><a href="/admin/modifyInvoice${invoice.id}">Details</a></td>
                <%--<td><a href="/admin/removeInvoice${invoice.id}">Remove</a></td>--%>
            </tr>
            <c:set var="indexPG" value="${indexPG + 1}" scope="page"/>
        </c:forEach>
        </tbody>
    </table>

</div>

</body>
</html>
