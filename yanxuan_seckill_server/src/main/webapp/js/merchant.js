$(function(){
    var num = 0;
    var bg_width = 0;
    //点击同意按钮 
    $('.fin_btn').on('click',function(){
        if ($('#yuedu').prop('checked') == true) {
            num++;
            $(this).parent().hide();
            $(this).parent().parent().find('li').eq(num).show();
            $('.list_nav ul li').eq(num).addClass('finish');
            bg_width = 160 * (num + 1);
            $('.nav_bg').css('width',bg_width);
        }
    })
    // 点击上一步按钮
    $('.back_btn').on('click',function(){
        $('.list_nav ul li').eq(num).removeClass('finish');
        num--;
        $(this).parent().parent().hide();
        $(this).parent().parent().parent().find('li').eq(num).show();
        bg_width = 160 * (num + 1);
        $('.nav_bg').css('width',bg_width);        
    })
    // 点击下一步按钮 
    $('.next_btn').on('click', function(){
        num++;
        $(this).parent().parent().hide();
        $(this).parent().parent().parent().find('li').eq(num).show();
        $('.list_nav ul li').eq(num).addClass('finish');
        bg_width = 160 * (num + 1);
        $('.nav_bg').css('width',bg_width);
    })
})