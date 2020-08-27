angular.module("goods").controller("goodsManageController", function ( $scope, goodsService, $controller) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        $scope.pageQuery();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});

    // 商品的状态数组
    $scope.statusArray = ["待审核","审核通过","审核退回","删除"];

    // 请求分页数据
    $scope.pageQuery = function () {
        // 页面初始化时$scope.name == undefined
        if($scope.name === undefined){
            $scope.name ="";
        }
        // 定义查询参数
        var queryParams = {
            currentPage: $scope.pageOption.currentPage,
            pageSize: $scope.pageOption.pageSize,
            name : $scope.name,
            status: $scope.status
        };
        // 发送请求
        goodsService.get(queryParams).then(
            function (res) {
                $scope.goodsList = res.data.result;
                $scope.pageOption.total = res.data.total;
            }
        );
    }
});