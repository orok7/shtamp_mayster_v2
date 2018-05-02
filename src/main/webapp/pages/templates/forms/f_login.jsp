<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<form name='loginForm' class="form-horizontal" action="/user/login" method="post">

    <div class="form-group">
        <label class="control-label col-sm-2" for="uliEmail">Логін:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="uliEmail"
                   name="username" placeholder="Введіть Ваш логін (ім'я користувача)"/>
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="uliPassword">Пароль:</label>
        <div class="col-sm-10">
            <input type="password" class="form-control" id="uliPassword"
                   name="password" placeholder="Введіть пароль"/>
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-success btn-block">Увійти</button>
            <div style="margin-top: 15px">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger"><h4>${error}</h4></div>
                </c:if>
                <c:if test="${not empty msg}">
                    <div class="alert alert-info"><h4>${msg}</h4></div>
                </c:if>
            </div>
            <hr style="margin-bottom: 0">
            <a href="/user/passrecPage">
                <button id="pasreg_btn" type="button" class="btn btn-link fleft" style="margin-right: 10px">Забули пароль?
                </button>
            </a>
            <a href="/user/registrationPage">
                <button id="regFL_btn" type="button" class="btn btn-link fright">Зареєструватися</button>
            </a>
        </div>
    </div>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

</form>
