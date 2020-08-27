$(function(){
    // 获取元素
    var $carousel = $(".banner .carousel");
    var $ulLis = $(".banner .carousel ul li");
    var $olLis = $(".banner .carousel ol li");
    // 信号量
    var idx = 0;
    // 小圆点点击事件
    $olLis.click(function(){
        // 获取下一张下标
        var number = $(this).index();
        // 调用函数
        change(number);
    });
    // 自动执行切换
    var carousel_timer = setInterval(set, 2000);
    // 鼠标移上停止定时器
    $carousel.mouseover(function(){
        clearInterval(carousel_timer);
    });
    // 鼠标离开开启定时器
    $carousel.mouseleave(function(){
        carousel_timer = setInterval(set, 2000);
    });
 
    // 定时器函数
    function set(){
        // idx自加赋值给num
        var number = idx + 1;
        number = number > $ulLis.length - 1 ? 0 : number;
        // 执行函数
        change(number);
    }
    // 节流
    var lock = false;
    // 函数执行动画
    function change(num) {
        if(lock) return;
        lock = true;
        $ulLis.eq(idx).fadeOut(1000);
        $ulLis.eq(num).fadeIn(1000,function(){
            idx = num;
            $ulLis.eq(idx).addClass("current").siblings().removeClass("current");
            $olLis.eq(idx).addClass("current").siblings().removeClass("current");
            lock = false;
        });        
    }
});
