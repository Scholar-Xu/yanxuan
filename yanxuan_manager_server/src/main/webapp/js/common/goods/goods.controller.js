// 定义brandController
angular.module("goods").controller("goodsController", function ( $scope, $controller, goodsService) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        // 商品信息分页数据的查询
        $scope.pageQuery();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});
    // 商品的状态数组
    $scope.statusArray = ["待审核","审核通过","审核退回","删除"];

    // 发送分页的请求
    $scope.pageQuery = function(entity){
        // 页面初始化时$scope.name == undefined
        if($scope.name === undefined){
            $scope.name ="";
        }
        // 定义查询参数
        var queryParams = {
            currentPage: $scope.pageOption.currentPage,
            pageSize: $scope.pageOption.pageSize,
            name : $scope.name,
            status : "0"
        };

        goodsService.get(queryParams).then(
            function (value) {
                // 总记录数
                $scope.pageOption.total = value.data.total;
                // 当前页显示的数据
                $scope.goodsList = value.data.result;
            }
        );
    };

    // 根据主键ID查询商品的详细信息
    $scope.getGoodsInfoById = function (id) {
        goodsService.get(id).then(
            function (res) {
                $scope.entity = res.data;
                // 设置显示商品的详细信息
                $("#detial").html($scope.entity.detail);
                // 把$scope.entity.picUrl 转换成JSON对象
                $scope.entity.picUrl = JSON.parse($scope.entity.picUrl);
                // 转换的是sku信息中的内容
                $scope.entity.skuList.forEach(function (sku) {
                    sku.picUrl = JSON.parse(sku.picUrl);
                    sku.specs = JSON.parse(sku.specs);
                });
                // 选中的规格项
                $scope.entity.specCheckedList = JSON.parse($scope.entity.specCheckedList);
            }
        );
    };

    // 审核通过的方式
    $scope.pass = function (id) {
        var entity = {
            id: id,
            status: "1"
        };
        // Patch请求进行状态的修改
        goodsService.patch(entity).then(
            function (res) {
                // 关闭模态窗口
                $("#newModal").modal("hide");
                // 重新加载数据
                $scope.pageQuery();
            }
        );
    };

    // 审核退回的方式
    $scope.back = function (id) {
        var entity = {
            id: id,
            status: "2"
        };
        // Patch请求进行状态的修改
        goodsService.patch(entity).then(
            function (res) {
                // 关闭模态窗口
                $("#newModal").modal("hide");
                // 重新加载数据
                $scope.pageQuery();
            }
        );
    };
});