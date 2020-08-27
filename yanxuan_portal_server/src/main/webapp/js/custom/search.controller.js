angular.module("yanxuan").controller("searchController", function ($scope) {

    // 设置门户首页的搜索功能
    $scope.search = function(){
        location.href = "http://yanxuan.com:9904/goods_search.html#?keywords="+$scope.keywords;
    }
});