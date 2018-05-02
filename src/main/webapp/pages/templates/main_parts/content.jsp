<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="/css/content.css">
<link rel="stylesheet" href="/css/block.css">

<div class="content contentbg mrg-b15 fright border mrg-l10">

    <p class="content-title">${contentTitle}</p>
    <div class="table-div">
        <c:forEach items="${productList}" var="elem">

            <div class="block">
                <a href="/main/productPage${elem.id}" style="text-decoration: none">
                    <div>
                        <img src="${elem.mainPicture}" class="img-thumbnail" alt="">
                        <p>${elem.name}</p>
                        <div class="rating-div-sm" style="margin-left: 5px">
                            <div style="width: ${elem.rating*20}%"></div>
                        </div>
                        <h4 style="color: red">Ціна: ${elem.price} грн.</h4>
                    </div>
                </a>
                <form action="" onsubmit="return add(${elem.id})" class="form-inline">
                    <input id="numToCart${elem.id}" class="form-control" style="width: 62px;margin-left: 5px" type="number" value="1">
                    <button id="addToCart${elem.id}" class="btn btn-success" style="width: 123px;" type="submit">Додати у корзину</button>
                </form>
            </div>

        </c:forEach>
    </div>

    <div class="alert alert-info" id="addSuccess" style="display: none">
        <strong>Success added!!!</strong>
    </div>

</div>

<script>

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    function add(id) {

        let num = document.getElementById('numToCart'+id).value;

        $.ajax({
            url: "/order/cart/addProd",
            type: 'POST',
            data: {prod_id: id, prod_num: num},
            success: function (result) {

                var options = {
                    display: 'block',
                    position: 'fixed',
                    left: 10,
                    right: 10,
                    bottom: 10
                };

                $('#addSuccess').css(options);
                setTimeout(function() { $('#addSuccess').css("display", "none") }, 1000);
            },
            error: function () {
                alert("error!!!");
            }
        });

//        let num = document.getElementById('numToCart'+id).value;
//        let name = "prodid_";
//        document.cookie = name + id + "="+num+"; path=/; expires=0";//+ date.toUTCString();

        return false;
    }

    function getAllCart() {
        const regex = /(?:^|; )prodid_([^;]*)/g;
        let cookie = document.cookie;
        let m;
        console.log(cookie);
        while ((m = regex.exec(cookie)) !== null) {
            if (m.index === regex.lastIndex) {
                regex.lastIndex++;
            }
            console.log(m[1]);
        }
    }
</script>