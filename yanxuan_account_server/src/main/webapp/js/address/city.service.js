angular.module("address").service("cityService", function (restService) {

    // 定义请求的资源路径
    var baseUrl = "../../city";
    // get请求
    this.get = function(options){
        return restService.get(baseUrl, options);
    };
});