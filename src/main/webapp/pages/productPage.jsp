<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="templates/main_parts/head.jsp" %>
<%@include file="templates/main_parts/header.jsp" %>
<%@include file="templates/main_parts/menu.jsp" %>


<div class="container fright">
    <br>
    <div class="media">
        <div class="media-left">
            <img src="${product.mainPicture}" class="media-object" style="width:200px">
        </div>
        <div class="media-body">
            <h3 class="media-heading">${product.name} (${product.article})</h3>
            <div class="rating-div-md" title="Оцінка ${ratingAvrgText}">
                <div style="width: ${product.rating*20}%"></div>
            </div>
            <h3 style="color: red"><strong>${product.price} грн.</strong></h3>
            <p>${product.description}</p>
            <form action="" onsubmit="return addProduct(${product.id})" class="form-inline">
                <input id="numToCart${product.id}" class="form-control" style="width: 62px;margin-left: 5px"
                       type="number" value="1">
                <button id="addToCart${product.id}" class="btn btn-success" style="width: 123px;" type="submit">Додати у
                    корзину
                </button>
            </form>
            <div>
                <button type="button" id="showReviewBtn" class="btn btn-link">Відгуки (${product.numberOfRatings})</button>
            </div>
        </div>
    </div>
    <br>
    <div id="reviewsDiv" class="" style="display: none;">
        <div class="">
            <c:forEach items="${reviews}" var="review">
                <div class="review-div">
                    <div><h4 style="margin-bottom: 0">${review.user.name} ${review.user.surname} <span style="font-size: 0.75em;color: grey">
                        ${review.date}
                    </span></h4>
                    <div class="rating-div-sm" title="Оцінка ${review.rating}">
                        <div style="width: ${review.rating*20}%"></div>
                    </div>
                    <p>${review.reviews}</p></div>
                </div>
            </c:forEach>
        </div>
    </div>
    <br>
    <div class="container-fluid">
        <form class="form-horizontal" onsubmit="return addReview()"
              action="/main/productPage/addReview" method="post">
            <div class="form-group">
                <textarea id="reviewText" class="form-control" name="reviewText"></textarea>
            </div>
            <div class="form-group">
                <div id="starRatingBG" style="cursor: hand" class="rating-div-lg">
                    <div id="starRating" style="width: 0%"></div>
                </div>
            </div>
            <div class="form-group">
                <input type="submit" class="btn btn-success" value="Додати відгук">
            </div>
            <input type="hidden" id="ratingVal" name="ratingVal">
            <input type="hidden" name="prodId" value="${product.id}">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </form>
    </div>
    <br>

    <div class="alert alert-info" id="addSuccess" style="display: none">
        <strong>Success added!!!</strong>
    </div>

</div>

<%@include file="templates/main_parts/footer.jsp" %>

<script>

    document.getElementById('showReviewBtn').onclick = function () {
        let $reviewsDiv = $('#reviewsDiv');
        $reviewsDiv.slideToggle();
    };

    document.getElementById('starRatingBG').onclick = function (e) {
        let left = this.getBoundingClientRect().left;
        let per = (e.pageX - left) / (this.getBoundingClientRect().right - left) * 100;
        if (per > 0 && per < 20.1) per = 20;
        else if (per > 20.1 && per < 40.1) per = 40;
        else if (per > 40.1 && per < 60.1) per = 60;
        else if (per > 60.1 && per < 80.1) per = 80;
        else per = 100;
        document.getElementById('starRating').style.width = per + "%";
        console.log(per);
    };

</script>

<script>

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    function addProduct(id) {

        console.log(1);
        let num = document.getElementById('numToCart' + id).value;
        console.log(2);
        $.ajax({
            url: "/order/cart/addProd",
            type: 'POST',
            data: {prod_id: id, prod_num: num},
            success: function (result) {
                console.log(3);
                var options = {
                    display: 'block',
                    position: 'fixed',
                    left: 10,
                    right: 10,
                    bottom: 10
                };

                $('#addSuccess').css(options);
                setTimeout(function () {
                    $('#addSuccess').css("display", "none")
                }, 1000);
            },
            error: function () {
                alert("error!!!");
            }
        });

        return false;
    }

    function addReview() {
        let rating = document.getElementById('starRating').style.width;
        document.getElementById('ratingVal').value = rating.split("%")[0] / 20;
        return true;
    }

</script>

</body>
</html>