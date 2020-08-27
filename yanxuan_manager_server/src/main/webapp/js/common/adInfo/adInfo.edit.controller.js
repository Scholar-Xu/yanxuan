// 定义brandController
angular.module("adInfo").controller("adInfoEditController", function ( $scope, $controller, adInfoService, adTypeService, $routeParams) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        var id = $routeParams.id;
        if(id !== undefined){
            $scope.queryById(id);
        }

        $scope.queryType();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});

    //完成广告类型数据的查询
    $scope.queryType = function (){
        adTypeService.get().then(
            function (res) {
                $scope.typeList = res.data.result;
            }
        );
    };

    // 执行保存的方法
    $scope.save = function () {
        // 获取广告的信息内容
        $scope.entity.content = editor.txt.html();
        var response = null;
        // 判断操作的类型
        if($scope.entity.id === undefined){
            // 新增保存
            response = adInfoService.post($scope.entity);
        }else{
            // 修改保存
            response = adInfoService.put($scope.entity);
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
    $scope.queryById = function (id) {
        adInfoService.get(id).then(
            function (res) {
                $scope.entity = res.data;
                editor.txt.html($scope.entity.content);
            }
        );
    };

});