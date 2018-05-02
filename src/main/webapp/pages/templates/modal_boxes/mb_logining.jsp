<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="mb_logining" class="my-modal" style="display: ${loginingModDisplay}">

    <div class="my-modal-content">

        <div class="my-modal-header">
            <span class="my-close" id="closeUL">&times;</span>
            <h3 style="margin-top: 11px">Вхід в обліковий запис</h3>
        </div>

        <div class="my-modal-body">
            <%--<%@include file="../../loginPage.jsp" %>--%>
            <%@include file="../forms/f_login.jsp" %>
        </div>

    </div>

</div>