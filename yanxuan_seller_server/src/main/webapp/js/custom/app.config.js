// angular路由设置
app.config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/",
        {
            templateUrl: "home.html"
        })
        .when("/goods/edit",
            {
                templateUrl: "pages/goods/edit.html",
                controller: "goodsController"
            })
        .when("/goods/edit/:id",
            {
                templateUrl: "pages/goods/edit.html",
                controller: "goodsController"
            })
        .when("/goods/manage",
            {
                templateUrl: "pages/goods/manage.html",
                controller: "goodsManageController"
            })
        .when("/account/detail",
            {
                templateUrl: "pages/account/detail.html"
            })
        .when("/account/security",
            {
                templateUrl: "pages/account/security.html"
            })
        .otherwise({redirectTo: '/'});
}])