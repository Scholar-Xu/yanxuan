// 定义brandController
angular.module("goods").controller("goodsController", function ( $scope, goodsService, $controller, categoryService, $routeParams) {

    // 监听视图内容是否加载完毕，加载完毕后触发回调函数
    $scope.$on("$viewContentLoaded", function (event) {
        // 获取传递的id值
        var id = $routeParams.id;
        // 如果id是undufied，则不进行查询
        if(id !== undefined){
            $scope.queryGoodsInfoById(id);
        }
        $scope.querycategory1();
    });

    // 继承其他的controller， baseController
    $controller("baseController", {$scope : $scope});

    // 初始化数据
    $scope.entity = {
        specCheckedList : []
    };

    // 根据主键ID进行查询商品的信息
    $scope.queryGoodsInfoById = function(id){
        // 根据主键ID
        goodsService.get(id).then(
            function (res) {
                $scope.entity = res.data;
                // 设置在富文本编辑器中显示详细信息
                editor.txt.html($scope.entity.detail);
                // 把$scope.entity.picUrl 转换成JSON对象
                $scope.entity.picUrl = JSON.parse($scope.entity.picUrl);
                // 转换的是sku信息中的内容
                $scope.entity.skuList.forEach(function (sku) {
                    sku.picUrl = JSON.parse(sku.picUrl);
                    sku.specs = JSON.parse(sku.specs);
                });
                // 选中的规格项
                $scope.entity.specCheckedList = JSON.parse($scope.entity.specCheckedList);
                if($scope.entity.specCheckedList===null){
                    $scope.entity.specCheckedList = [];
                }
            }
        );
    };

    // 检查规格项是否设置为选中的状态
    // 查看specCheckedList中是否有传递进来的规格项
    $scope.checkSpecOption = function(specName, optionValue){
        // 根据规格名称进行查询
        var obj = queryObject($scope.entity.specCheckedList, specName);
        if(obj === null){
            return false;
        }else{
            if(obj.optionValue.indexOf(optionValue)<0){
                return false;
            }else {
                return true;
            }
        }
    };

    // 加载一级类目
    $scope.querycategory1 = function () {
        categoryService.get({parentId: 0}).then(
            function (res) {
                $scope.category1List = res.data.result;
            }
        );
    };

    // 监听一级类目的选择
    $scope.$watch("entity.category1Id", function (newVal) {
        if(newVal === undefined){
            $scope.category2List = [];
            return null;
        }
        // 查询二级类目
        categoryService.get({parentId: newVal}).then(
            function (res) {
                $scope.category2List = res.data.result;
            }
        );
    });

    // 监听二级类目的选择
    $scope.$watch("entity.category2Id", function (newVal) {
        if(newVal === undefined){
            $scope.category3List = [];
            return null;
        }
        // 查询三级类目
        categoryService.get({parentId: newVal}).then(
            function (res) {
                $scope.category3List = res.data.result;
            }
        );
    });

    // 监听三级类目选择，并且加载关联的品牌信息
    $scope.$watch("entity.category3Id", function (newVal) {
        if(newVal === undefined){
            return null;
        }
        // 查询三级类目的所有的信息
        categoryService.get(newVal).then(
            function (res) {
                // 所有的品牌信息
                $scope.brandList = JSON.parse(res.data.relation.brandIds);
                // 获取规格和规格项的信息
                $scope.specList = res.data.specList;
                console.log($scope.specList);
            }
        );
    });

    $scope.save = function () {
        // 设置富文本编辑器中的内容到entity中
        $scope.entity.detail = editor.txt.html();
        // 把$scope.entity.picUrl 转换成字符串进行传递
        $scope.entity.picUrl = JSON.stringify($scope.entity.picUrl);
        // 转换的是sku信息中的内容
        $scope.entity.skuList.forEach(function (sku) {
            sku.picUrl = JSON.stringify(sku.picUrl);
            sku.specs = JSON.stringify(sku.specs);
        });
        // 选中的规格项
        $scope.entity.specCheckedList = JSON.stringify($scope.entity.specCheckedList);

        if($scope.entity.id ===undefined){
            // 发送请求，传递entity
            goodsService.post($scope.entity).then(
                function (res) {
                    alert("商品增加成功");
                    // 跳转到商品列表页面
                    window.location="#/goods/manage";
                }
            );
        }else{
            goodsService.put($scope.entity).then(
                function (res) {
                    alert("商品修改成功");
                    // 跳转到商品列表页面
                    window.location="#/goods/manage";
                }
            );
        }

    };
    
    $scope.uploadSpuPic = function () {
        $.Tupload.init({
			url: "/upload",
            title	  : "商品的主图片",
            fileNum: 5, // 上传文件数量
            divId: "goodsPic", // div  id
            accept: "image/jpeg,image/x-png", // 上传文件的类型
            preViewData: $scope.entity.picUrl,
            onSuccess: function(data, i) {
                console.log(data);
                $scope.entity.picUrl = data.data;
            },
            onDelete: function(i) {

            }
        });
    };

    // SKU图片的上传插件初始化
    $scope.uploadSkuPic = function (index) {
        $.Tupload.init({
            url: "/upload",
            title	  : "商品的主图片",
            fileNum: 5, // 上传文件数量
            divId: "skuPic", // div  id
            accept: "image/jpeg,image/x-png", // 上传文件的类型
            preViewData: $scope.entity.skuList[index].picUrl,
            onSuccess: function(data, i) {
                console.log(data);
                $scope.entity.skuList[index].picUrl = data.data;
                console.log($scope.entity.skuList)
            },
            onDelete: function(i) {

            }
        });
    };

    // 销毁图片上传的插件
    $scope.destoryUpload = function(id){
        $.Tupload.destroy({
            divId: id
        });
    };

    /**
     *  机身内存， 32GB
     *$scope.entity.specCheckedList = [{specName: "机身内存", optionValue: ["32GB"]}]
     * 机身内存，64GB
     * @param specName
     * @param optionValue
     */
    $scope.getSpecCheckedList = function (event, specName, optionValue) {
        // 根据规格名称进行查询
        var obj = queryObject($scope.entity.specCheckedList, specName);
        // 判断obj是否存在
        if(obj === null){
            $scope.entity.specCheckedList.push({
                specName: specName,
                optionValue : [
                    optionValue
                ]
            })
        }else{
            // 判断是否选中
            if(event.target.checked){ //选中
                obj.optionValue.push(optionValue);
            }else{ // 去除选中
                obj.optionValue.splice(obj.optionValue.indexOf(optionValue),1);
                if(obj.optionValue.length===0){
                    $scope.entity.specCheckedList.splice($scope.entity.specCheckedList.indexOf(obj), 1);
                }
            }
        }

        // 调用生成SKU信息的方法
        createSkuList();

/*
        // 第一次向数组中进行存放
        if($scope.entity.specCheckedList.length===0){
            $scope.entity.specCheckedList.push({
                specName: specName,
                optionValue : [
                    optionValue
                ]
            })
        }else{
            var flag = true;
            // 判断集合中是否存在相应的值
            $scope.entity.specCheckedList.forEach(function (element, index) {
                if(element["specName"]=== specName){
                    if(event.target.checked){ //选中
                        element.optionValue.push(optionValue);
                    }else{ // 去除选中
                        element.optionValue.splice(element.optionValue.indexOf(optionValue),1);
                        if(element.optionValue.length===0){
                            $scope.entity.specCheckedList.splice(index, 1);
                        }
                    }
                    flag = false;
                }
            });

            if(flag){
                $scope.entity.specCheckedList.push({
                    specName: specName,
                    optionValue : [
                        optionValue
                    ]
                })
            }
        }*/
    };

    // 从集合中查找对应的对象
    var queryObject = function(list, specName){
        var result = null;
        list.forEach(function (element) {
            if(element.specName === specName){
                result = element;
            }
        });
        return result;
    };

    /**
     * 1. 只选择了一个规格项 specCheckedList = [{specName: "机身内存", optionValue: ["32GB"]}]
     *
     * 2. 选择了同一个规格下的多个规格项 specCheckedList = [{specName: "机身内存", optionValue: ["32GB","64GB"]}]
     *
     * 3. 选择不同的规格下的规格项 specCheckedList = [{"specName":"机身内存","optionValue":["32GB","64GB"]},{"specName":"运行内存","optionValue":["2GB"]}]
     */
    // 根据选择的规格项生成SKU的信息
    var createSkuList = function () {
        // 初始化SkuList
        $scope.entity.skuList = [{specs:{},price:0, stockCount:999, picUrl:[]}];

        $scope.entity.specCheckedList.forEach(function (checkedSpec) {
            // 获取规格的名称
            var specName = checkedSpec.specName;
            // 定义一个临时的集合
            var tmpSkuList = [];
            // 遍历选中的规格的规格项
            checkedSpec.optionValue.forEach(function (optionValue) {
                // 遍历SkuList
                $scope.entity.skuList.forEach(function (sku) {
                    // 每次设置的值必须是一个新的对象
                    var newSku = JSON.parse(JSON.stringify(sku));
                    newSku.specs[specName] = optionValue;
                    // 新的对象放到临时的集合中
                    tmpSkuList.push(newSku);
                });
            });
            // 把临时集合设置给skuList
            $scope.entity.skuList = tmpSkuList;
        });
    }

});