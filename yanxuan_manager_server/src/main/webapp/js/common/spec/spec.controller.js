// 定义brandController
angular.module("spec").controller("specController", function ( $scope, $controller, specService) {

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

        specService.get(queryParams).then(
            function (value) {
                // 总记录数
                $scope.pageOption.total = value.data.total;
                // 当前页显示的数据
                $scope.specList = value.data.result;
            }
        )
    };

    // 初始化规格信息
    $scope.initSpec = function(id){
        if(id !== undefined){
            // 根据主键进行查询
            specService.get(id).then(function (value) {
                $scope.spec = value.data;
            })
        }else{
            $scope.spec = {
                name : '',
                optionList : []
            }
        }
    };

    // 新增规格选择项
    $scope.addSpecOption = function(){
        // 初始化时设置排序序号
        $scope.spec.optionList.push({
            name : '',
            sortNo: $scope.spec.optionList.length+1
        });
    };

    // 根据索引值进行移除
    $scope.removeSpecOption = function(index){
        $scope.spec.optionList.splice(index, 1);
        // 更新序号
        $scope.spec.optionList.forEach(function (value, index1) {
            value.sortNo = index1+1;
        })
    };


    // 执行保存的方法
    $scope.save = function () {
        var response = null;
        if($scope.spec.id === undefined){
            // 新增保存
            response = specService.post($scope.spec);
        }else{
            response = specService.put($scope.spec)
        }

        response.then(function (value) {
            // 关闭模态窗口
            $("#newModal").modal("hide");
            // 刷新规格列表
            $scope.pageQuery();
        })

    };


    $scope.delete = function (id) {
        specService.delete(id).then(function (value) {
            // 刷新规格列表
            $scope.pageQuery();
        })
    }
});