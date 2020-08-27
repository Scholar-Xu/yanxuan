// 完成请求的发送，也就是对Http服务进行在封装
/**
 * GET http://localhost:9901/brand/11
 * GET http://localhost:9901/brand?pageNum=1&pageSize=10&name=极光
 *
 * POST http://localhost:9901/brand
 * PUT http://localhost:9901/brand
 * PATCH http://localhost:9901/brand
 *
 * DELETE http://localhost:9901/brand/11
 * @return
 */
/**
 * 第一个参数：service的名称
 * 第二个参数：回调函数
 */
angular.module("loginUser").service("loginUserService", function (restService) {

    // 定义请求的资源路径
    var baseUrl = "../../loginUser";
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
    }
});