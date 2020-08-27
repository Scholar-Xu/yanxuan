// angular的过滤器，该过滤器的作用是把信息作为html代码在页面上进行展示
angular.module("seckill").filter(
    'to_trusted', ['$sce', function ($sce) {
        return function (text) {
            return $sce.trustAsHtml(text);
        }
    }]
);