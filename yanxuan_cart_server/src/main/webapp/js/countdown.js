// 限时福利倒计时
$(function(){
    var $hour = $(".welfare .con .count .time .hour");
    var $minite = $(".welfare .con .count .time .minite");
    var $second = $(".welfare .con .count .time .second");
    // 秒
    var nowSecond = parseInt($second.html());
    var nowMinite = parseInt($minite.html());
    var nowHour = parseInt($hour.html());
    var timer = setInterval(function () {
        // 判断定时器停止
        if(nowHour == 0 && nowMinite == 0 && nowSecond == 0){
            clearInterval(timer);
            return;
        }
        nowSecond--;
        nowSecond = nowSecond < 0 ? 59 : nowSecond;
        if (nowSecond < 10) {
            $second.html("0" + nowSecond);
        } else {
            $second.html(nowSecond);
        }
        // 判断分，当秒变为59时让分减1
        if(nowSecond == 59){
            nowMinite --;
            nowMinite = nowMinite < 0 ? 59 : nowMinite;
            if (nowMinite < 10) {
                $minite.html("0" + nowMinite);
            } else {
                $minite.html(nowMinite);
            }
            // 每次分变化后，都去验证是否要减小时
            // 判断小时，当分变为59时让小时减1
            if (nowMinite == 59) {
                nowHour--;
                nowHour = nowHour < 0 ? 59 : nowHour;
                if (nowHour < 10) {
                    $hour.html("0" + nowHour);
                } else {
                    $hour.html(nowHour);
                }
                
            }
        }  
              
    }, 1000);

    //点击登录 弹出登录页面
    $('.login').click(function(){
        $('.log_on').show();
    });

    $('.enroll').click(function(){
        $(this).parents('.register').hide().siblings('.log_on').show();
    })

    //点击注册 弹出注册页面
    $('.regist').click(function(){
        $('.register').show();
    })

    $('.reg_now').click(function(){
        $(this).parents('.log_on').hide().siblings('.register').show();
    })
    //关闭登录，注册窗口
    $('.log_close').click(function(){
        $(this).parents('.mask').hide();
    })

});
