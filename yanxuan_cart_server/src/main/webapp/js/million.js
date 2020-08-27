$(function(){
    // 获取元素
    var $leftbtn = $(".million .list .leftbtn");
    var $rightbtn = $(".million .list .rightbtn");
    var $ul = $(".million .list ul");

    // 右按钮
    $rightbtn.click(function(){
        // 让ul向左走动1152px
        $ul.animate({"left":"-1152px"},2000);
    });
    // 左按钮
    $leftbtn.click(function () {
        // 让ul向右走动0
        $ul.animate({ "left": "0" }, 2000);
    });
});