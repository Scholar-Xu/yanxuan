angular.module("address").controller("addressController", function ($scope, provinceService, cityService, areaService, addressService) {

    // 页面初始化时加载省的信息
    $scope.$on("$viewContentLoaded", function (event) {
        // 请求省的信息
        provinceService.get().then(
            function (res) {
                $scope.provinceList = res.data;
            }
        );
    });

    $scope.$watch("entity.areaProvinceId", function (newVal) {
        if(newVal === undefined){
            $scope.cityList = [];
            return ;
        }
        // 请求市的信息
        cityService.get({provinceId: newVal}).then(
            function (res){
                $scope.cityList = res.data;
            }
        );
    });

    $scope.$watch("entity.areaCityId", function (newVal) {
        if(newVal === undefined){
            $scope.areaList = [];
            return ;
        }
        // 请求市的信息
        areaService.get({cityId: newVal}).then(
            function (res){
                $scope.areaList = res.data;
            }
        );
    });

    $scope.save = function(){
        if($scope.entity.isDefault !== "1"){
            $scope.entity.isDefault = "0";
        }
        addressService.post($scope.entity).then(
            function(res){

            }
        );
    }
});