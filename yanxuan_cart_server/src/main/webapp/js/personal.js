$(function(){
    $("#city").click(function (e) {
        SelCity(this,e);
    });
    $("s").click(function (e) {
        SelCity(document.getElementById("city"),e);
    });


    // 设置左侧菜单的点击样式
    $(".menu_personal").click(function(){
    	// 移除所有class="menu_personal"的current class
    	$(".menu_personal").removeClass("current");
    	// 为当前节点添加class=current
    	$(this).addClass("current");
    });

    // 设置账户安全按钮点击样式
    $(".menu_account").click(function(){
    	var element = $(this).attr("data-target");
    	// 移除所有class="menu_personal"的current class
    	$(".menu_account").removeClass("current");
    	// 为当前节点添加class=current
    	$(this).addClass("current");
    	// 移除内容元素的class
    	$(".alter_num li").removeClass("current");
    	// 设置内容的class=current
    	$("#"+element).addClass("current");
    })
});