angular.module("personal").controller("personalController", function ($scope, personalService) {

    // 发送请求获取登录用户名
    personalService.get().then(
        function(res){
            $scope.loginUser = res.data.loginUser;
        }
    );
});