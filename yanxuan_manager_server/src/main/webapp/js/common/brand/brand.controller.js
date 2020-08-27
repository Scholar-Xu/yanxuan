// 定义brandController
angular.module("brand").controller("brandController", function ( $scope, brandService, $controller) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        $scope.pageQuery();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});

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
        // $http.get("../../brand/pageQuery?currentPage="+$scope.pageOption.currentPage+"&pageSize="+$scope.pageOption.pageSize+"&name="+$scope.name)
        // $http.get("../../brand",{params: queryParams})
        brandService.get(queryParams)
            .then(
                function (value) {
                    // 总记录数
                    $scope.pageOption.total = value.data.total;
                    // 当前页显示的数据
                    $scope.brandList = value.data.result;
                }
            );
    };


    // 执行保存的方法
    $scope.save = function () {
        var response = null;
        // 判断操作的类型
        if($scope.brand.id === undefined){
            // 新增保存
            // 发送请求
            // response = $http.post("../../brand", $scope.brand);
            response = brandService.post($scope.brand);
        }else{
            // 修改保存
            // response = $http.put("../../brand", $scope.brand);
            response = brandService.put($scope.brand)
        }
        // 抽取优化
        response.then(
            function (value) {
                // 关闭模态窗口
                $("#newModal").modal("hide");
                // 刷新品牌列表
                $scope.pageQuery();
            },
            function (reason) {
                console.log(reason)
            }
        )

    };

    // 修改初始化
    $scope.initData = function (brand) {
        $scope.brand = brand;
    }

    $scope.delete = function (id) {
        // 发送请求删除
        // $http.delete("../../brand/"+id)
        brandService.delete(id)
            .then(
            function (value) {
                // 刷新数据
                $scope.pageQuery();
            }
        )
    }
});