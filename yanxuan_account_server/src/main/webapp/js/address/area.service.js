angular.module("address").service("areaService", function (restService) {

    // 定义请求的资源路径
    var baseUrl = "../../area";
    // get请求
    this.get = function(options){
        return restService.get(baseUrl, options);
    };
});