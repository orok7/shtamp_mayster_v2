<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form class="form-horizontal" action="/user/saveNewPass" method="post">

    <div class="form-group">

        <label class="control-label col-sm-2" for="snpPass">Вкажіть новий пароль:</label>

        <div class="col-sm-10">
            <input type="password" class="form-control" id="snpPass"
                   name="newPass" placeholder="new password"/>
        </div>

    </div>


    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

            <button type="submit" class="btn btn-success btn-block">
                Зберегти
            </button>

            <hr style="margin-bottom: 0">

            <button id="regFPR_btn" type="button" class="btn btn-link fright">Зареєструватися</button>

        </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

    <input type="hidden" name="tempLink" value="${tempLink}">
    <input type="hidden" name="un" value="${un}">

</form>