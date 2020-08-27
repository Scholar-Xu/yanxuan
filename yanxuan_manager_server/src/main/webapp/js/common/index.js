var app = angular.module("yanxuan", ["ngRoute","pagination"]);
// angular路由设置
app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "home.html"
    }).when("/goods/brand", {
        templateUrl: "pages/goods/brand.html",
        controller: "brandController"
    }).when("/goods/spec", {
        templateUrl: "pages/goods/spec.html"
    }).when("/goods/category/:pId", {
        templateUrl: "pages/goods/category.html"
    }).when("/goods/audit", {
        templateUrl: "pages/goods/audit.html"
    }).when("/seller/audit", {
        templateUrl: "pages/seller/audit.html"
    }).when("/seller/manage", {
        templateUrl: "pages/seller/manage.html"
    }).when("/ad/type", {
        templateUrl: "pages/ad/type.html"
    }).when("/ad/content", {
        templateUrl: "pages/ad/content.html"
    }).when("/ad/edit/", {
        templateUrl: "pages/ad/edit.html"
    }).when("/ad/edit/:id", {
        templateUrl: "pages/ad/edit.html"
    }).otherwise({redirectTo: '/'});
}]);

// 定义brandController
app.controller("brandController", function ($http, $scope) {
    // 发送请求，获取所有的商品品牌信息
    $scope.queryAll = function (){
        $http.get("../../brand/queryAll")
            .then(
                function (value) {
                    console.log(value);
                    $scope.brandList = value.data;
                },
                function (reason) {
                    console.log(reason)
                }
            );
    };

    // 定义分页参数
    $scope.pageOption  = {
        total : 0 , // 总记录数
        currentPage : 1 , // 当前页码值，初始值为1
        pageSize : 10, // 每页显示的记录数，初始值为10
        pageSizeArr : [10, 20 ,30, 40, 50] , // 每页显示记录的选择数组
        onChange : function () {
            // 页码和每页显示记录数发生变化执行触发的业务逻辑，，可以用来请求数据查询的操作
            $scope.pageQuery();
        }
    };

    // 状态数组
    $scope.statusArray = ["正常","停用"];

    // 发送分页的请求
    $scope.pageQuery = function(){
        // 页面初始化时$scope.name == undefined
        if($scope.name === undefined){
            $scope.name ="";
        }
        $http.get("../../brand/pageQuery?currentPage="+$scope.pageOption.currentPage+"&pageSize="+$scope.pageOption.pageSize+"&name="+$scope.name)
            .then(
                function (value) {
                    // 总记录数
                    $scope.pageOption.total = value.data.total;
                    // 当前页显示的数据
                    $scope.brandList = value.data.result;
                }
            );
    };

    // 在页面加载时进行第一次请求
    $scope.pageQuery();
    
    // 执行保存的方法
    $scope.save = function () {
        var response = null;
        // 判断操作的类型
        if($scope.brand.id === undefined){
            // 新增保存
            // 发送请求
            response = $http.post("../../brand/save", $scope.brand);

        }else{
            // 修改保存
            response = $http.post("../../brand/update", $scope.brand);

        }
        // 抽取优化
        response.then(
            function (value) {
                // 保存成功
                if(value.data.code){
                    alert(value.data.message);
                    // 关闭模态窗口
                    $("#newModal").modal("hide");
                    // 刷新品牌列表
                    $scope.pageQuery();
                }
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
        $http.get("../../brand/delete?id="+id).then(
            function (value) {
                if(value.data.code){
                    alert(value.data.message);
                    // 刷新数据
                    $scope.pageQuery();
                }
            }
        )
    }
});