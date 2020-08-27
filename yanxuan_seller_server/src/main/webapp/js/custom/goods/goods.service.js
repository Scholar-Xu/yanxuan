angular.module("goods").service("goodsService", function (restService) {

    // 定义请求的资源路径
    var baseUrl = "../../goods";
    // get请求
    this.get = function(options){
        return restService.get(baseUrl, options);
    };

    // post请求
    this.post = function (entity) {
        return restService.post(baseUrl, entity);
    };

    // put请求
    this.put = function (entity) {
        return restService.put(baseUrl, entity);
    };

    // Delete请求
    this.delete = function (id) {
        return restService.delete(baseUrl, id);
    };

    // Patch请求
    this.patch = function (entity) {
        return restService.patch(baseUrl, entity);
    }
});