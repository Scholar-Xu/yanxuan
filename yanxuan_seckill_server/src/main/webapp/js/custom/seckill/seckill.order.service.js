angular.module("seckill").service("orderService", function ($http) {

    this.post = function(entity){
        return $http.post("/seckillOrder", entity);
    }
});