// 统一维护http请求的逻辑代码
angular.module("yanxuan").service("searchService", function ($http) {

    /**
     * get请求
     * @param url
     * @param options
     * @returns {HttpPromise}
     */
    this.get = function( options){
        var url ="/search";
        // 如果是根据主键ID进行查询，那么需要修改资源路径
        if(options !== undefined && typeof options !== "object"){
            url = url + "/"+ options;
        }
        // 完成的是分页条件查询
        return $http.get(url, {params: options});
    };

});