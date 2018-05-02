function my_modal(blockId, closeId) {
    console.log('func');
    var block = document.getElementById(blockId);
    var cBtn = document.getElementById(closeId);
    $(block).css('display', 'block');
    $(cBtn).click(function () {
        $(block).css('display', 'none');
    });
    $(block).click(function (e) {
        if (e.target == block) {
            $(block).css('display', 'none');
        }
    });
};

function my_modal_old(blockId, closeBtnId) {
    var block1 = document.getElementById(blockId);
    var closeBtn = document.getElementById(closeBtnId);

    closeBtn.onclick = function () {
        block1.style.display = "none";
    }

    window.onclick = function (event) {
        console.log(event.target);
        console.log(block1);
        if (event.target == block1) {
            block1.style.display = "none";
        }
    }
}