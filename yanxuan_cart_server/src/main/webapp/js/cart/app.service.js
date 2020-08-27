// 统一维护http请求的逻辑代码
angular.module("cart").service("restService", function ($http) {

    /**
     * get请求
     * @param url
     * @param options
     * @returns {HttpPromise}
     */
    this.get = function(url, options){
        // 如果是根据主键ID进行查询，那么需要修改资源路径
        if(options !== undefined && typeof options !== "object"){
            url = url + "/"+ options;
        }
        // 完成的是分页条件查询
        return $http.get(url, {params: options});
    };

    /**
     * post请求
     * @param url
     * @param entity
     * @returns {HttpPromise}
     */
    this.post = function (url, entity) {
        return $http.post(url, entity);
    };

    /**
     * put请求
     * @param url
     * @param entity
     * @returns {HttpPromise}
     */
    this.put = function (url , entity) {
        return $http.put(url, entity);
    };

    /**
     * delete请求
     * @param url
     * @param id
     * @returns {HttpPromise}
     */
    this.delete = function (url, id) {
        if(id !== undefined){
            url = url + "/"+id;
        }
        return $http.delete(url);
    };

    /**
     * patch请求
     * @param url
     * @param entity
     * @returns {HttpPromise}
     */
    this.patch = function (url, entity) {
        return $http.patch(url, entity);
    }
});