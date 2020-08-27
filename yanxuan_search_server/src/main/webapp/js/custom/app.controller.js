// 定义brandController
angular.module("yanxuan").controller("searchController", function ( $scope, searchService, $location) {

    // 分页参数初始化
    $scope.queryParams = {
        pageNum : 1, // 当前页码
        pageSize: 20 // 每页展示的记录数
    };

    /**
     * 获取门户首页传递过来的关键字，并查询
     */
    $scope.getKeywords = function(){
        var keywords = $location.search()['keywords'];

        $scope.queryParams.keywords = keywords;

        $scope.search();
    };

    // 发送分页的请求
    $scope.search = function(){
        searchService.get($scope.queryParams)
            .then(
                function (value) {
                    // 当前页显示的商品数据
                    $scope.dataList = value.data.result;
                    $scope.dataList.forEach(
                        function(element){
                            element.picUrl = JSON.parse(element.picUrl);
                        }
                    );
                    // 类目信息
                    $scope.categoryList = value.data.category;
                    // 品牌信息
                    $scope.brandList = value.data.brandList;
                    // 规格信息
                    $scope.specList = value.data.specList;
                    // 分页的结果
                    $scope.total = value.data.total;// 总的记录数
                    $scope.totalPage = value.data.totalPage;// 总页码数

                    $scope.createPage();
                }
            );
    };

    /**
     * 判断输入的关键字是否是品牌名称
     * @returns {boolean}
     */
    $scope.isBrand = function(){
        if($scope.brandList === undefined){
            return false;
        }

        var result = false;
        // 遍历所有的品牌信息判断是否存在于关键字一致的品牌
        $scope.brandList.forEach(
            function(element){
                if(element.name === $scope.queryParams.keywords){
                    // 设置品牌作为查询条件
                    $scope.queryParams.brand = $scope.queryParams.keywords;
                    result = true;
                }
            }
        );

        return result;
    };

    // 排序的方法
    $scope.addSort = function(field){
        // 默认第一次为降序
        $scope.queryParams.sortField = field;
        if($scope.queryParams.sortType === "DESC"){
            $scope.queryParams.sortType = "ASC";
        }else{
            $scope.queryParams.sortType = "DESC";
        }

        $scope.search();

    };

    /**
     * 根据指定的页码进行查询
     * @param pageNum
     */
    $scope.queryByPageNum = function(pageNum){
        if(pageNum<1 || pageNum>$scope.totalPage){
            return ;
        }
        $scope.queryParams.pageNum = pageNum;

        $scope.search();
    };

    /**
     *  1 2 3 4 5
     *      start = 1
     *      end = 5
     *  1 2 3 4 5 6 7 8 9 10
     *  1. 当页码值< 3 1-5
     *  2. 总页码值-当前页码值<3
     *  3. 其他情况都是 当前页的前两和后两个
     */
    $scope.createPage = function(){
        // 用来存放显示的页码值， 最多显示5个页码值
        $scope.pageLabel = [];
        // 页码开始值、结束值
        var start = 1;
        var end = $scope.totalPage;

        if($scope.totalPage > 5){
            if($scope.queryParams.pageNum <= 3){
                end = 5;
            }else if($scope.totalPage-$scope.queryParams.pageNum < 3){
                start = $scope.totalPage - 4;
            }else{
                start = $scope.queryParams.pageNum - 2;
                end = $scope.queryParams.pageNum +2;
            }
        }

        // 开始之前、结束之后是否显示 ...的标识符
        $scope.startFlag = false;
        $scope.endFlag = false;
        if(start > 1){
            $scope.startFlag = true;
        }
        if(end < $scope.totalPage){
            $scope.endFlag = true;
        }

        for(var index=start; index<=end; index++){
            $scope.pageLabel.push(index);
        }
    };

    /**
     * {
     *      keywords: '',
     *      category:'',
     *      brand:'',
     *      price: '',
     *      spec: {
     *          '机身内存':'256GB',
     *          '运行内存':'8GB'
     *      }
     * }
     * @param key
     * @param value
     */
    // 添加查询条件
    $scope.addSearchOption = function (key, value) {
        if($scope.queryParams.spec === undefined){
            $scope.queryParams.spec = {};
        }

        console.log(key)
        // 向查询条件中添加类目、品牌的信息
        if(key ==='category' || key ==="brand" || key==="price"){
            $scope.queryParams[key] = value;
        }else{
            $scope.queryParams.spec[key] = value;
        }

        // 发送请求
        $scope.search();
    };

    // 移除查询条件
    $scope.removeSearchOption = function (key) {
        if(key ==='category' || key ==='brand' || key==="price"){
            delete $scope.queryParams[key];
        }else{
            delete $scope.queryParams.spec[key];
        }

        // 发送请求
        $scope.search();
    }
});