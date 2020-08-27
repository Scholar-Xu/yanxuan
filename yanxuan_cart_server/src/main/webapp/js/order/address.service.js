angular.module("order").service("addressService", function ($http) {

    this.get = function(options){
        return $http.get("/address", options);
    }
});