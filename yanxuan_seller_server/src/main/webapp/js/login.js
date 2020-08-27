$(function(){
    

    // 绑定点击事件
    $(".account").click(function() {
        $("#account").removeClass("login_account");
        $("#scanCode").addClass("login_scanCode");
    });

    // 绑定点击事件
    $(".scanCode").click(function() {
        $("#scanCode").removeClass("login_scanCode");
        $("#account").addClass("login_account");
    });
});