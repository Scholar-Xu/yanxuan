angular.module("cart").controller("cartController", function ($scope, cartService) {

    // 获取购物车中的信息
    $scope.queryCart = function(){
        cartService.get().then(
            function (res) {
                $scope.cartList = res.data;
            }
        );
    };

    // 修改商品的数量
    $scope.addCart = function(skuId, count){
        var entity = {
            skuId : skuId,
            count: count
        };
        cartService.post(entity).then(
            function(res){
                $scope.queryCart();
            }
        );
    };

    // 已选商品的初始化
    $scope.selectedGoods = [];
    /**
     * [{sellerId, sellerName, goodsList:[]},{sellerId, sellerName, goodsList:[]}]
     * 1. 第一次勾选： 直接创建 {sellerId, sellerName, goodsList:[]}
     * 2. 选择是已添加的商家的商品信息，取到{sellerId, sellerName, goodsList:[]}，并且存放值
     * 3. 添加新的商家的商品，直接创建 {sellerId, sellerName, goodsList:[]}
     * @param event
     * @param orderGoods
     * @param cartInfo
     */
    $scope.selectGoods = function(event, orderGoods, cartInfo){
        var targetCartInfo = queryObject(cartInfo.sellerId);
        if(event.target.checked){
            if(targetCartInfo === null){
                // 直接创建对象，并且存放到$scope.selectedGoods
                targetCartInfo = {
                    sellerId: cartInfo.sellerId,
                    sellerName: cartInfo.sellerName,
                    orderGoodsList: []
                };
                targetCartInfo.orderGoodsList.push(orderGoods);

                $scope.selectedGoods.push(targetCartInfo);
            }else{
                // 直接向targetCartInfo的goodslist中添加当前选择的orderGoods
                targetCartInfo.orderGoodsList.push(orderGoods);
            }
        }else{
            // 移除商品
            var index = targetCartInfo.orderGoodsList.indexOf(orderGoods);
            targetCartInfo.orderGoodsList.splice(index, 1);
            // 判断是否还存在其他的商品
            if(targetCartInfo.orderGoodsList.length <=0){
                // 移除整个商家的信息
                var cartInfoIndex = $scope.selectedGoods.indexOf(targetCartInfo);
                $scope.selectedGoods.splice(cartInfoIndex, 1);
            }
        }


        console.log($scope.selectedGoods);

        // 计算总数量和总价格
        getSum();
    };

    // 初始化
    $scope.totalCount = 0;
    $scope.totalFee = 0;

    var getSum = function(){
        $scope.totalCount = 0;
        $scope.totalFee = 0;
        $scope.selectedGoods.forEach(
            function(element){ // CartInfo
                element.orderGoodsList.forEach(
                    function (orderGoods) {
                        $scope.totalCount++;
                        $scope.totalFee += orderGoods.totalFee;
                    }
                )
            }
        )
    };

    /**
     * 就是判断已选择的商品列表中是否存在与当前选择的商品的店铺的id一致
     * @param sellerId
     * @returns {*}
     */
    var queryObject = function(sellerId){
        for(var i=0; i<$scope.selectedGoods.length ;i++){
            if(sellerId === $scope.selectedGoods[i].sellerId){
                return $scope.selectedGoods[i];
            }
        }
        return null;
    };

    $scope.submitCart = function(){
        if($scope.selectedGoods.length<=0){
            alert("请您至少选择一件商品");
        }
        // 保存已选择的商品到本地
        localStorage.setItem("selectedGoods", JSON.stringify($scope.selectedGoods) );

        location.href = "./goods_cart_submit.html";
    };

    // 发送请求获取购物车
    $scope.queryCart();
});