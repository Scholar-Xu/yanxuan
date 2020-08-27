angular.module("address").service("provinceService", function (restService) {

    // 定义请求的资源路径
    var baseUrl = "../../province";
    // get请求
    this.get = function(options){
        return restService.get(baseUrl, options);
    };

});