angular.module("seckill").service("goodsService", function($http){

    // http://yanxuan.com:9907/seckillGoods/1
    this.get = function(id){
        var url = "/seckillGoods/"+id;
        return $http.get(url);
    }
});