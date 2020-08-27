angular.module("order").service("orderService", function ($http) {

    // post请求
    this.post = function (entity) {
        return $http.post("/order", entity);
    };
});