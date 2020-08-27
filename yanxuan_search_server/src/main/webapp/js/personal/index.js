var app = angular.module("personal",["ngRoute"]);
// angular路由设置
app.config(["$routeProvider",function($routeProvider){
  $routeProvider.when("/",{
    templateUrl: "personal_info.html"
  }).when("/order",{
    templateUrl: "personal_order.html"
  }).when("/info",{
    templateUrl: "personal_info.html"
  }).when("/addr",{
    templateUrl: "personal_addr.html"
  }).when("/advise",{
    templateUrl: "personal_advise.html"
  }).when("/focus",{
    templateUrl: "personal_focus.html"
  }).when("/account",{
    templateUrl: "personal_account.html"
  }).otherwise({redirectTo:'/'});
}])