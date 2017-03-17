angular.module('senla', ['ngRoute', 'ctrls']).config(function ($routeProvider) {
	 
	 $routeProvider.when('/mechanic',
        {
            templateUrl:'_mech.tpl.html',
            controller:'MechanicCtrl'
        })
     .when('/workplace',
        {
            templateUrl:'_workpl.tpl.html',
            controller:'WorkplaceCtrl'
        })
     .when('/order',
        {
            templateUrl:'_ord.tpl.html',
            controller:'OrderCtrl'
        });

})

angular.module('ctrls', [])
    
   .controller('MechanicCtrl', function ($scope, $http) {

    $scope.caption = 'Mechanic Control Module';
    $scope.radioValue="all";
    $scope.mechanics = [];

    $scope.findId = function () {
      
        $http({
            method: 'GET',
            params: {id: $scope.id},
            url: '/servlet-layer/MechanicServlet'
        }).then(function (response) {
            $scope.mechanics = response.data;
          })
    }

    $scope.viewList = function () {
      
        $http({
            method: 'GET',
            params: {sorting: $scope.radioValue},
            url: '/servlet-layer/MechanicListServlet'
        }).then(function (response) {
            $scope.mechanics = response.data;
          })
    }

    $scope.createByFullname = function () {
      
       $http({
            method: 'POST',
            params: {fullname:  $scope.fullname},
            url: '/servlet-layer/MechanicServlet'
        }).then(function (response) {
            $scope.resp = response.data;
          })
    }

    $scope.removeById = function () {
      
       $http({
            method: 'DELETE',
            params: {id: $scope.id},
            url: '/servlet-layer/MechanicServlet'
        }).then(function (response) {
            $scope.resp = response.data;
          })
    }
   
})
.controller('WorkplaceCtrl', function ($scope) {

    $scope.caption = 'Workplace Control Module';
})
.controller('OrderCtrl', function ($scope) {

    $scope.caption = 'Order Control Module';
});