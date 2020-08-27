angular.module("order").controller("orderController", function ($scope, addressService, orderService) {

    // 读取数据，从localStorage
    var selectedGoodsStr = localStorage.getItem("selectedGoods");
    // 如果读取到数据，就进行转换JSON
    if(selectedGoodsStr!==null && selectedGoodsStr!== undefined){
        $scope.selectedGoods = JSON.parse(selectedGoodsStr);
        // 总数量、总价格的初始化
        $scope.totalCount = 0;
        $scope.totalFee = 0;
        // 遍历商品数据进行计算
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
    }

    // 页面初始化时加载收货地址信息
    $scope.queryAddress = function(){
        addressService.get().then(
            function(res){
                $scope.addressList = res.data;
                $scope.addressList.forEach(
                    function(address){
                        if(address.isDefault === "1"){
                            $scope.selectedAddressId = address.id;
                        }
                    }
                );
            }
        );
    };

    $scope.isSelected = function(address){
        if(address.id === $scope.selectedAddressId){
            return true;
        }else{
            return false;
        }
    };

    $scope.selectAddress = function(address){
        $scope.selectedAddressId = address.id;
    };

    $scope.payType = "0";
    $scope.isPayType = function(payType){
        if($scope.payType === payType){
            return true;
        }else{
            return false;
        }
    };

    $scope.selectPayType = function(payType){
        $scope.payType = payType;
    };

    // 保存
    $scope.saveOrder = function () {
        var order = {
            cartInfoList : $scope.selectedGoods,
            payType: $scope.payType,
            fareFee: 0,
            addressId: $scope.selectedAddressId,
            message: $scope.message,
            status: "1"
        };
        orderService.post(order).then(
            function (res){
                // 清空本地存储的商品信息 localStorage 中的信息
                localStorage.removeItem("selectedGoods");
                alert("订单信息已生成，请尽快完成支付！");
                location.href = "./pay?payOrderNo="+res.data.payOrderNo;
            }
        );
    }
});