angular.module("seckill").controller("seckillController", function ($scope, goodsService, $interval, orderService) {

    var id = getQueryVariable("id");

    console.log("seckillId="+id);
    $scope.skuInfo = "";
    $scope.queryById = function () {
        goodsService.get(id).then(
            function (res) {
                // 接收秒杀商品的详情信息
                $scope.entity = res.data;
                // 处理商品的图片信息
                $scope.entity.goodsSku.picUrl = JSON.parse($scope.entity.goodsSku.picUrl);
                // 处理规格信息
                $scope.entity.goodsSku.specs = JSON.parse($scope.entity.goodsSku.specs);
                for(var key in $scope.entity.goodsSku.specs){
                    $scope.skuInfo += $scope.entity.goodsSku.specs[key];
                }

                // 处理倒计时
                var tmpSecond = $scope.entity.seconds /1000;
                // 开启定时任务
                var tmpInterval = $interval(function(){
                    // 动态的计算时间
                    tmpSecond -- ;
                    $scope.countDownTimeStr = convertSecondToTime(tmpSecond);
                    if(tmpSecond<=0){
                        // 取消定时器
                        $interval.cancel(tmpInterval);
                    }
                }, 1000);
                // 时间转换
                $scope.countDownTimeStr = convertSecondToTime(tmpSecond);
                $scope.countDownMsg = "";
                if($scope.entity.secondsFlag ==="before"){
                    $scope.countDownMsg = "距离秒杀开始还有";
                    $scope.btnMessage = "尚未开始";
                }else if($scope.entity.secondsFlag ==="middle" && $scope.entity.stockCount>0){
                    $scope.countDownMsg = "距离秒杀结束还有";
                    $scope.btnMessage = "立即抢购";
                }else{
                    $interval.cancel(tmpInterval);
                    $scope.countDownMsg = "秒杀活动已经结束，谢谢参与";
                    $scope.countDownTimeStr = "";
                    $scope.btnMessage = "已经结束";
                }

            }
        );
    };
    /**
     * 根据秒值 计算出 时、分、秒  123.321
     * @param second 秒数
     */
    var convertSecondToTime = function(second){
        var ss = Math.floor(second%60);
        var mm = Math.floor(second/60%60);
        var hh = Math.floor(second/60/60%24);
        var day = Math.floor(second/60/60/24);

        var timeStr = hh+" : "+mm+" : "+ss ;

        return timeStr;
    };

    $scope.submitOrder = function(){
        if($scope.entity.secondsFlag ==="before"){
            alert("活动尚未开始，请稍后再试");
        }else if($scope.entity.secondsFlag ==="middle" && $scope.entity.stockCount>0){
            // 发送请求
            orderService.post({id: $scope.entity.id}).then(
                function(res){
                    if(res.status === 201){
                        console.log("秒杀成功，请尽快完成支付");
                        location.href = "./goods_cart_submit.html?orderNo="+res.data.orderNo;
                    }else{
                        alert("活动已经结束，谢谢关注");
                    }
                },
                function (season) {
                    alert("活动已经结束，谢谢关注");
                }
            );
        }else{
            alert("活动已经结束，谢谢关注");
        }
    }
});
