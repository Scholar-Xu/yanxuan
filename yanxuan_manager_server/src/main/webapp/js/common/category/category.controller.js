// 定义brandController
angular.module("category").controller("categoryController", function ( $scope, $controller, categoryService, brandService, specService) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        $scope.pageQuery();
        // 查询所有的品牌信息
        $scope.queryBrand();
        // 查询所有的规格信息
        $scope.querySpec();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});
    // 定义面包屑菜单
    $scope.breadMenu = [];

    // 所有品牌信息
    $scope.queryBrand = function(){
        brandService.get().then(
            function (value) {
                $scope.brandList = [];
                // 只保留数组中对象的id、name属性
                value.data.result.forEach(function (element) {
                    $scope.brandList.push({
                        id: element.id,
                        name: element.name
                    })
                })
            }
        )
    };

    // 所有的规格信息
    $scope.querySpec = function(){
        specService.get().then(
            function (value) {
                $scope.specList = [];
                value.data.result.forEach(function (element) {
                    $scope.specList.push({
                        id: element.id,
                        name: element.name
                    })
                })
            }
        )
    };

    // 发送分页的请求
    $scope.pageQuery = function(entity){
        // 页面初始化时$scope.name == undefined
        if($scope.name === undefined){
            $scope.name ="";
        }
        if(entity === undefined){
            $scope.parentId = 0;
        }else{
            $scope.parentId = entity.id;
            $scope.pageOption.currentPage = 1;
        }
        // 定义查询参数
        var queryParams = {
            currentPage: $scope.pageOption.currentPage,
            pageSize: $scope.pageOption.pageSize,
            name : $scope.name,
            parentId : $scope.parentId
        };

        categoryService.get(queryParams).then(
            function (value) {
                // 总记录数
                $scope.pageOption.total = value.data.total;
                // 当前页显示的数据
                $scope.categoryList = value.data.result;
            }
        );

        // 设置面包屑菜单
        // 移除菜单，什么时候移除，如果$scope.breadMenu中存在相同的对象就移除，否则添加
        if(entity!==undefined){
            var index = $scope.breadMenu.indexOf(entity);
            if(index ===-1){
                // 添加菜单
                $scope.breadMenu.push(entity);
            }else{
                // 移除菜单
                $scope.breadMenu.splice(index+1, $scope.breadMenu.length-index-1);
            }

        }else{
            $scope.breadMenu = [];
        }

    };

    // 窗口数据的初始化
    $scope.initCategory = function(entity){
        if(entity === undefined){
            $scope.category = {
                structName : "-",
                level: 1,
                parentId: 0,
                relation: {
                    brandIds: [],
                    specIds: []
                }
            }
        }else{
            $scope.category = {
                structName : (entity.structName + ">"+ entity.name).replace("->","") ,
                level: entity.level+1,
                parentId: entity.id,
                relation: {
                    brandIds: [],
                    specIds: []
                }
            }
        }
    };

    // 根据主键进行查询
    $scope.getCategory = function(id){
        categoryService.get(id).then(function (value) {
            $scope.category = value.data;
            $scope.category.relation.brandIds = JSON.parse($scope.category.relation.brandIds);
            $scope.category.relation.specIds = JSON.parse($scope.category.relation.specIds);
        });
    };

    // 执行保存的方法
    $scope.save = function () {
        var response = null;
        // 删除多余的属性
        $scope.category.relation.brandIds.forEach(function (value) {
            delete value["$$hashKey"];
        });
        $scope.category.relation.specIds.forEach(function (value) {
            delete value["$$hashKey"];
        });
        // 把关联的品牌信息和规格信息转换成字符串
        $scope.category.relation.brandIds = JSON.stringify($scope.category.relation.brandIds);
        $scope.category.relation.specIds = JSON.stringify($scope.category.relation.specIds);
        if($scope.category.id === undefined){
            // 新增保存
            response = categoryService.post($scope.category);
        }else{
            response = categoryService.put($scope.category)
        }

        response.then(function (value) {
            // 关闭模态窗口
            $("#newModal").modal("hide");
            // 刷新规格列表
            $scope.pageQuery();
        })

    };


    $scope.delete = function (id) {
        categoryService.delete(id).then(function (value) {
            // 刷新规格列表
            $scope.pageQuery();
        })
    }
});