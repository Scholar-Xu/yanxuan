// 定义brandController
angular.module("seller").controller("sellerController", function ( $scope, $controller, sellerService) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        $scope.pageQuery();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});
    
    $scope.pageQuery = function () {
        if($scope.name === undefined){
            $scope.name ="";
        }
        // 定义查询参数
        var queryParams = {
            currentPage: $scope.pageOption.currentPage,
            pageSize: $scope.pageOption.pageSize,
            name : $scope.name,
            status: "0"
        };
        
        sellerService.get(queryParams).then(
            function (res) {
                // 总记录数
                $scope.pageOption.total = res.data.total;
                // 当前页显示的数据
                $scope.sellerList = res.data.result;
            }
        )
    };

    // 初始化
    $scope.initEntity = function (seller) {
        $scope.entity = seller;
    };
    
    // 状态修改
    $scope.updateStatus = function (id, status) {
        // 请求参数
        var entity = {
            id: id,
            status : status
        };
        sellerService.put(entity).then(
            function (res) {
                alert("状态修改成功");
                // 关闭模态窗口
                $("#newModal").modal("hide");
                // 刷新规格列表
                $scope.pageQuery();
            }
        )
    }

});