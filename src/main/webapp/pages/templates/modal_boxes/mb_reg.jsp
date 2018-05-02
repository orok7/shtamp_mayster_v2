<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/modal_registration.css">

<div id="mb_reg" class="my-modal" style="display: ${regModDisplay}">

    <div class="my-modal-content">

        <div class="my-modal-header">
            <span class="my-close" id="closeReg">&times;</span>
            <h3 style="margin-top: 11px">Реєстрація</h3>
        </div>

        <div class="my-modal-body">
            <%@include file="../forms/f_reg.jsp" %>
        </div>
    </div>

</div>