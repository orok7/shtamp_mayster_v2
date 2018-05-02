<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/modal_registration.css">


<form id="formReg" class="form-horizontal" onsubmit="return submitRegForm()" action="/user/regIndividualUser" method="post">

    <div class="col-sm-offset-2 checkbox">
        <label><input type="checkbox" id="urIsCompany">
            Обліковий запис для юридичної особи
        </label>
    </div>
    <br>

    <%-- Company Data --%>

    <div class="form-group" id="urCompanyData">

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCOwnership">Форма власності:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCOwnership" type="text" name="urOwnership" placeholder="Приватне підприємство">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCFullName">Назва:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCFullName" type="text" name="urFullName" placeholder="Ваша назва">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCShortName">Скорочена назва:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCShortName" type="text" name="urShortName" placeholder="ПП ВашаНаз">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-4" for="urCCode">Код ЄДРПОУ:</label>
            <div class="col-sm-7">
                <input class="form-control uData" id="urCCode" type="text" name="urCode" placeholder="01234567">
            </div>
        </div>

    </div>

    <%--Main Data--%>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urIName">Ім'я:</label>
        <div class="col-sm-7">
            <input class="form-control uData" id="urIName" type="text" name="urName" placeholder="Петро" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urISurname">Прізвище:</label>
        <div class="col-sm-7">
            <input class="form-control uData" id="urISurname" type="text" name="urSurname" placeholder="Петрів" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urIPhoneNumber">Номер телефону:</label>
        <div class="col-sm-7">
            <input class="form-control uData" id="urIPhoneNumber" type="text" name="urPhoneNumber" placeholder="+3800011122233" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urUsername">Логін:</label>
        <div class="col-sm-7">
            <input type="text" class="form-control uData" id="urUsername" name="urUsername" placeholder="Введіть Ваш Логін" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urEmail">Email:</label>
        <div class="col-sm-7">
            <input type="text" class="form-control uData" id="urEmail" name="urEmail" placeholder="Введіть Ваш Email" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urPassword">Пароль:</label>
        <div class="col-sm-7">
            <input type="password" class="form-control uData" id="urPassword" name="urPassword" placeholder="password" required>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-4" for="urPasswordAg">Підтвердіть пароль:</label>
        <div class="col-sm-7">
            <input type="password" class="form-control uData" id="urPasswordAg" name="urPasswordAg" placeholder="password" required>
        </div>
    </div>

    <div class="checkbox col-sm-offset-2">
        <label><input type="checkbox" name="urIsCompany" id="isAccepted">
            Я приймаю <a href="#">умови</a> реєстрації
        </label>
    </div>
    <br>

    <div id="errorRegDiv" class="alert alert-danger" style="display: none">
        <h4 id="errorRegMsg"></h4>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-7">
                <button id="submitReg" type="submit" class="btn btn-success btn-block">Зареєструвати</button>
        </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

</form>



<script>
    (function reloadAccepted() {
        console.log("reload");
        isAccepted.checked = false;
        submitReg.disabled = true;
    }());
</script>

<script>
    var regIsCompany = document.getElementById('urIsCompany');
    var regCompanyData = document.getElementById("urCompanyData");
    regIsCompany.onclick = function () {
        if (regIsCompany.checked) {
            regCompanyData.style.display = "block";
            document.getElementById("formReg").action = "/user/regCompanyUser";
        } else {
            regCompanyData.style.display = "none";
            document.getElementById("formReg").action = "/user/regIndividualUser";
        }
        var uData = document.getElementsByClassName("uData");
        for (var i = 0; i < uData.length; i++){
            uData[i].required = !uData[i].required;
        }
    }
</script>

<script>
    var isAccepted = document.getElementById('isAccepted');
    var submitReg = document.getElementById('submitReg');

    isAccepted.onclick = function () {
        console.log("isAccepted.onclick");
        submitReg.disabled = !isAccepted.checked;
    }

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    function submitRegForm() {
        var login = $('#urUsername').val();
        var res = false;
        console.log("login ", login);
        console.log(login_validator(login));
        if (login_validator(login)){
            $.ajax({
                url: "loginValidate",
                type: 'POST',
                data: {userLogin: login},
                success: function (result) {
                    if (result) {
                        if (email_validator($('#urEmail').val())) {
                            let pass = document.getElementById('urPassword').value;
                            let passAg = document.getElementById('urPasswordAg').value;
                            if (pass != null && pass === passAg) {
                                res = true;
                                document.forms["formReg"].submit();
                            } else {
                                document.getElementById('errorRegMsg').innerText = "Невірний пароль підтвердження";
                                document.getElementById('errorRegDiv').style.display = 'block';
                                res = false;
                            }
                        } else {
                            document.getElementById('errorRegMsg').innerText = "Невірно вказаний Email";
                            document.getElementById('errorRegDiv').style.display = 'block';
                            res = false;
                        }
                    } else {
                        document.getElementById('errorRegMsg').innerText = "Користувач з таким логіном вже існує";
                        document.getElementById('errorRegDiv').style.display = 'block';
                        res = false;
                    }
                },
                error: function () {
                    alert("error!!!");
                }
            });
        } else {
            document.getElementById('errorRegMsg').innerText = "Логін має починатися з букви і може містити" +
                " тільки букви латинського алфавіту та цифри";
            document.getElementById('errorRegDiv').style.display = 'block';
            res = false;
        }
        return res;
    }

</script>