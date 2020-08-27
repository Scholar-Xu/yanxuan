// 定义brandController
angular.module("yanxuan").controller("adInfoController", function ( $scope, adInfoService) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        $scope.pageQuery();
    });

    // 发送分页的请求
    $scope.pageQuery = function(){
        // 定义查询参数
        var queryParams = {
            typeId : 1,
            status: "0"
        };
        adInfoService.get(queryParams)
            .then(
                function (value) {
                    // 当前页显示的数据
                    $scope.dataList = value.data.result;
                }
            );
    };

    // 设置门户首页的搜索功能
    $scope.search = function(){
        location.href = "http://yanxuan.com:9904/goods_search.html#?keywords="+$scope.keywords;
    }

});