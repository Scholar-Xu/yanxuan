angular.module("seller").controller("sellerController", function ($scope, $http) {

    // 收集 数据并且发送至后台服务器 $scope.entity
    $scope.save = function () {
        $http.post("/seller", $scope.entity).then(
            function (res) {
                console.log(res)
            }
        )
    }
});