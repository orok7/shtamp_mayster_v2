<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/header.css">

<div class="myheader border headerbg">

    <div class="col-sm-offset-2 col-sm-7 tableHeader">
        <div class="row">
            <div class="col-sm-3">м.Львів, вул.Дорошенка, 14</div>
            <div class="col-sm-3">тел.: <a href="tel:+380676957889">(067) 695 7889</a></div>
            <div class="col-sm-2"><a href="tel:+380633741895">(063) 374 1895</a></div>
            <div class="col-sm-4"><a href="mailto:info@pechatka.lviv.ua?subject=Запитання відвідувача сайту">
                e-mail: info@pechatka.lviv.ua</a></div>
        </div>
        <div class="row">
            <div class="col-sm-3">м.Львів, вул.Куліша, 34</div>
            <div class="col-sm-3">тел.: <a href="tel:+380980439141">(098) 043 9141</a></div>
            <div class="col-sm-2"><a href="tel:+380635068283">(063) 506 8283</a></div>
            <div class="col-sm-4"><a href="mailto:info2@pechatka.lviv.ua?subject=Запитання відвідувача сайту">
                e-mail: info2@pechatka.lviv.ua</a></div>
        </div>
        <form class="search_form" action="/main/index" title="Пошук" method="get">
            <div class="form-group col-sm-9">
                <input class="form-control btn-block" type="search" name="searchThis" placeholder="Пошук...">
            </div>
            <div class="form-group col-sm-2">
                <button class="btn btn-success btn-block mrg-l10" type="submit">Пошук</button>
            </div>
        </form>

    </div>

    <div class="mycart col-sm-1">
        <a href="/order/cart" style="display: block; width: 100%; height: 100%" title="Корзина"></a>
    </div>

    <div class="col-sm-2">

        <div class="myuser col-sm-4 fleft">
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/admin/adminPage" style="display: block; width: 100%; height: 100%" title="Сторінка адміністратора"></a>
            </sec:authorize>
            <sec:authorize access="hasRole('USER')">
                <a href="/user/userPage/main" style="display: block; width: 100%; height: 100%" title="Особистий кабінет"></a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a href="/user/loginPage" style="display: block; width: 100%; height: 100%" title="Вхід"></a>
            </sec:authorize>
        </div>

        <div class="col-sm-7 fright mrg-t10"
             style="height: 103px;">

            <sec:authorize access="isAnonymous()">

            <div style="width: 96%;height: 46%;/*margin: 2%*/">
                <a href="/user/loginPage" style="text-decoration: none">
                    <button class="btn btn-success btn-block">Вхід</button>
                </a>
            </div>

            <div style="width: 96%;height: 46%;/*margin: 2%*/">
                <a href="/user/registrationPage" style="text-decoration: none">
                    <button class="btn btn-success btn-block">Реєстрація</button>
                </a>
            </div>

            </sec:authorize>

            <sec:authorize access="isAuthenticated()">

                <div style="width: 96%;height: 46%;/*margin: 2%*/">
                    <span>${pageContext.request.userPrincipal.name}</span>
                </div>


                <div style="width: 96%;height: 46%;/*margin: 2%*/">
                    <form action="/user/logout" method="post" id="logoutForm">
                        <input type="submit" class="btn btn-success btn-block" value="Вихід">
                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}" />
                    </form>
                </div>

            </sec:authorize>

        </div>

    </div>

</div>

<div class="mylogo">
    <a href="/" style="display: block; width: 100%; height: 100%" title="На головну"></a>
</div>
