// 定义brandController
angular.module("adInfo").controller("adInfoController", function ( $scope, $controller, adInfoService) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        $scope.pageQuery();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});

    // 状态数组
    $scope.adStatusArray = ["启用","停用"];

    // 发送分页的请求
    $scope.pageQuery = function(){
        // 页面初始化时$scope.name == undefined
        if($scope.name === undefined){
            $scope.name ="";
        }
        // 定义查询参数
        var queryParams = {
                currentPage: $scope.pageOption.currentPage,
                pageSize: $scope.pageOption.pageSize,
                name : $scope.name
            };
        adInfoService.get(queryParams)
            .then(
                function (value) {
                    // 总记录数
                    $scope.pageOption.total = value.data.total;
                    // 当前页显示的数据
                    $scope.dataList = value.data.result;
                }
            );
    };


    $scope.delete = function (id) {
        // 发送请求删除
        adInfoService.delete(id)
            .then(
            function (value) {
                // 刷新数据
                $scope.pageQuery();
            }
        )
    }
});