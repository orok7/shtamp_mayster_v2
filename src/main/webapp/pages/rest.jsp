<button id="btn">show all</button>

<p id="convert"></p>

<hr>

<input type="text" id="text">
<input type="text" id="title">
<button id="savePostRest">save this</button>

<script>
    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });
    $('#savePostRest').click(function () {
        var text = $('#text').val();
        var title = $('#title').val();
        var post = {postText: text, postTitle: title};
        post = JSON.stringify(post);
        $.ajax({
            url: "/savePostRest",
            type: "POST",
            contentType: "application/json",
            data: post,
            success: function () {
                alert("ok!");
            },
            error: function () {
                alert("error!!!");
            }
        })
    })
    console.log("hello rest");
    $('#btn').click(function () {
        $('#convert')
        $.ajax({
            url: "showAll",
            type: 'GET',
            success: function (result) {
                console.log(result);
                $(result).each(function () {
                    $('#convert').append($('<p/>', {text: this.postTitle + " " + this.postText}))
                });
            }
        });
    });
</script>

</body>
</html>