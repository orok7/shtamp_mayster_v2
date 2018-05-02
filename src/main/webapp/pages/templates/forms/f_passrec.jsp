<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form class="form-horizontal" action="/user/passrecovery" method="post">

    <div class="form-group">

        <label class="control-label col-sm-2" for="prEmail">Ваш E-mail:</label>

        <div class="col-sm-10">
            <input type="email" class="form-control" id="prEmail"
                   name="userEmail" placeholder="Введіть Ваш Email"/>
        </div>

    </div>


    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">

            <button type="submit" class="btn btn-success btn-block">
                Надіслати
            </button>

            <hr style="margin-bottom: 0">

            <button id="regFPR_btn" type="button" class="btn btn-link fright">Зареєструватися</button>

        </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

</form>