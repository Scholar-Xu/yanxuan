angular.module("yanxuan").controller("accountController", function ($scope, smsService, accountService) {

    // 发送请求-向指定的手机号发送短信验证码
    $scope.sendCheckCode = function(){
        // 检查手机号是否合理
        // 正则表达式
        var reg=/^[1][3,4,5,7,8][0-9]{9}$/ ;
        if(!reg.test($scope.entity.phone)){
            alert("手机格式不正确，请重新确认");

            return false;
        }
        smsService.get($scope.entity.phone).then(
            function (res) {
                console.log("短信验证码发送成功");
            }
        );
    };


    // 发送注册的请求
    $scope.saveAccountInfo = function () {
        if($scope.entity.password !== $scope.entity.confirmPwd){
            alert("两次输入的密码不一致，请重新输入");
            return ;
        }
        accountService.post($scope.entity).then(
            function (res) {
                console.log("恭喜您成功注册为极光严选的用户，您可以继续浏览商品");
            },function (res) {
                alert(res.data);
            }
        );
    }
});