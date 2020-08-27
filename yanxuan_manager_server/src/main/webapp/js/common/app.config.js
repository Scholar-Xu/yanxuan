// angular路由设置
angular.module("yanxuan").config(["$routeProvider", function ($routeProvider) {
    $routeProvider.when("/", {
        templateUrl: "home.html"
    }).when("/goods/brand", {
        templateUrl: "pages/goods/brand.html",
        controller: "brandController"
    }).when("/goods/spec", {
        templateUrl: "pages/goods/spec.html",
        controller: "specController"
    }).when("/goods/category/:pId", {
        templateUrl: "pages/goods/category.html",
        controller: "categoryController"
    }).when("/goods/audit", {
        templateUrl: "pages/goods/audit.html",
        controller: "goodsController"
    }).when("/seller/audit", {
        templateUrl: "pages/seller/audit.html",
        controller: "sellerController"
    }).when("/seller/manage", {
        templateUrl: "pages/seller/manage.html"
    }).when("/ad/type", {
        templateUrl: "pages/ad/type.html",
        controller: "adTypeController"
    }).when("/ad/content", {
        templateUrl: "pages/ad/content.html",
        controller: "adInfoController"
    }).when("/ad/edit/", {
        templateUrl: "pages/ad/edit.html",
        controller:"adInfoEditController"
    }).when("/ad/edit/:id", {
        templateUrl: "pages/ad/edit.html",
        controller:"adInfoEditController"
    }).otherwise({redirectTo: '/'});
}]);