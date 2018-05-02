<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/modal_registration.css">


<form id="formReg" class="form-horizontal" action="/user/userPage/savePersonal" method="post">

    <input type="hidden" name="urId" value="${loggedUser.id}">

    <%-- Company Data --%>
    <c:if test="${loggedUser.isCompany == true}">
    <div class="form-group">

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCOwnership">Форма власності:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCOwnership" type="text" name="urOwnership"
                       placeholder="Приватне підприємство" value="${loggedUser.companyData.ownership}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCFullName">Назва:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCFullName" type="text" name="urFullName"
                       placeholder="Ваша назва" value="${loggedUser.companyData.fullName}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCShortName">Скорочена назва:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCShortName" type="text" name="urShortName"
                       placeholder="ПП ВашаНаз" value='${loggedUser.companyData.shortName}' required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCCode">Код ЄДРПОУ:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCCode" type="text" name="urCode"
                       placeholder="01234567" value="${loggedUser.companyData.code}" required>
            </div>
        </div>

    </div>
    </c:if>
    <%--Main Data--%>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urIName">Ім'я:</label>
        <div class="col-sm-7">
            <input class="form-control uData" id="urIName" type="text" name="urName"
                   placeholder="Петро" value="${loggedUser.name}" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urISurname">Прізвище:</label>
        <div class="col-sm-7">
            <input class="form-control uData" id="urISurname" type="text" name="urSurname"
                   placeholder="Петрів" value="${loggedUser.surname}" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urIPhoneNumber">Номер телефону:</label>
        <div class="col-sm-7">
            <input class="form-control uData" id="urIPhoneNumber" type="text" name="urPhoneNumber"
                   placeholder="+3800011122233" value="${loggedUser.phoneNumber}" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urEmail">Email:</label>
        <div class="col-sm-7">
            <input type="text" class="form-control uData" id="urEmail" name="urEmail"
                   placeholder="Введіть Ваш Email" value="${loggedUser.email}" required>
        </div>
    </div>

    <div id="errorRegDiv" class="alert alert-danger" style="display: none">
        <h4 id="errorRegMsg"></h4>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-7">
                <button id="submitReg" type="submit" class="btn btn-success btn-block">Зберегти зміни</button>
        </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

</form>