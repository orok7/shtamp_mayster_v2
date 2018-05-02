<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/adminHead.jsp" %>

<br>
<div class="container">
    <form id="formReg" class="form-horizontal" action="/admin/saveUser" method="post">

        <input type="hidden" name="urId" value="${user.id}">

        <%-- Company Data --%>
        <c:if test="${user.isCompany == true}">
            <div class="form-group">

                <div class="form-group">
                    <label class="control-label col-sm-4" for="urCOwnership">Форма власності:</label>
                    <div class="col-sm-7">
                        <input class="form-control uData" id="urCOwnership" type="text" name="urOwnership"
                               placeholder="Приватне підприємство" value="${user.companyData.ownership}" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4" for="urCFullName">Назва:</label>
                    <div class="col-sm-7">
                        <input class="form-control uData" id="urCFullName" type="text" name="urFullName"
                               placeholder="Ваша назва" value="${user.companyData.fullName}" required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4" for="urCShortName">Скорочена назва:</label>
                    <div class="col-sm-7">
                        <input class="form-control uData" id="urCShortName" type="text" name="urShortName"
                               placeholder="ПП ВашаНаз" value='${user.companyData.shortName}' required>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-4" for="urCCode">Код ЄДРПОУ:</label>
                    <div class="col-sm-7">
                        <input class="form-control uData" id="urCCode" type="text" name="urCode"
                               placeholder="01234567" value="${user.companyData.code}" required>
                    </div>
                </div>

            </div>
        </c:if>
        <%--Main Data--%>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urIName">Ім'я:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urIName" type="text" name="urName"
                       placeholder="Петро" value="${user.name}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urISurname">Прізвище:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urISurname" type="text" name="urSurname"
                       placeholder="Петрів" value="${user.surname}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urIPhoneNumber">Номер телефону:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urIPhoneNumber" type="text" name="urPhoneNumber"
                       placeholder="+3800011122233" value="${user.phoneNumber}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urEmail">Email:</label>
            <div class="col-sm-7">
                <input type="text" class="form-control uData" id="urEmail" name="urEmail"
                       placeholder="Введіть Ваш Email" value="${user.email}" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urDiscount">Знижка:</label>
            <div class="col-sm-7">
                <input type="number" class="form-control uData" id="urDiscount" name="urDiscount"
                       placeholder="Введіть Ваш Email" value="${user.discount}" required>
            </div>
        </div>

        <div class="col-sm-offset-4 checkbox">
            <label class="col-sm-5">
                <input type="checkbox" name="urAccountNonExpired" <c:if test="${user.accountNonExpired}">checked</c:if>>
                Account non expired
            </label>
            <label class="col-sm-5">
                <input type="checkbox" name="urAccountNonLocked" <c:if test="${user.accountNonLocked}">checked</c:if>>
                Account non locked
            </label>
        </div>

        <div class="col-sm-offset-4 checkbox">
            <label class="col-sm-5">
                <input type="checkbox" name="urCredentialsNonExpired" <c:if test="${user.credentialsNonExpired}">checked</c:if>>
                Credentials non expired
            </label>
            <label class="col-sm-5">
                <input type="checkbox" name="urEnabled" <c:if test="${user.enabled}">checked</c:if>>
                Enabled
            </label>
        </div><br>

        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-7">
                <button id="submitReg" type="submit" class="btn btn-success btn-block">Зберегти зміни</button>
            </div>
        </div>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

    </form>
</div>

</body>
</html>
