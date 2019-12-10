<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/head.jsp"%>
<%@include file="templates/main_parts/header.jsp" %>
<%@include file="templates/main_parts/menu.jsp" %>

<div class="container fleft">

    <div class="col-sm-offset-1">
        <h3 style="margin-top: 11px">Підтверження замовлення</h3>
    </div>

    <%@include file="templates/forms/f_cart.jsp" %>
    <td></td>
    </tr>
    </tbody>
    </table>
    </div>

<form id="formReg" class="form-horizontal" action="/order/addOrder" method="post">

    <sec:authorize access="isAnonymous()">

    <div class="form-group">
        <label class="control-label col-sm-4" for="odName">Ім'я:</label>
        <div class="col-sm-7">
            <input class="form-control" id="odName" type="text" name="odName" placeholder="Петро" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="odSurname">Прізвище:</label>
        <div class="col-sm-7">
            <input class="form-control" id="odSurname" type="text" name="odSurname" placeholder="Петрів" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="odPhoneNumber">Номер телефону:</label>
        <div class="col-sm-7">
            <input class="form-control" id="odPhoneNumber" type="text" name="odPhoneNumber" placeholder="+3800011122233" required>
        </div>
    </div>

    </sec:authorize>

    <div class="form-group">
        <label class="control-label col-sm-4" for="odInvoicePaymentTypes">Тип оплати:</label>
        <div class="col-sm-7">
            <select class="form-control" id="odInvoicePaymentTypes" name="odInvoicePaymentTypes">
                <c:forEach items="${paymentTypes}" var="paymentType">
                    <option value="${paymentType}">${paymentType.getDescription()}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="odNotes">Примітки:</label>
        <div class="col-sm-7">
            <textarea class="form-control" id="odNotes" type="text" name="odNotes"></textarea>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-4 col-sm-7">
            <button type="submit" class="btn btn-success btn-block">Купити</button>
        </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

</form>

</div>

<%@include file="templates/main_parts/footer.jsp" %>

</body>
</html>