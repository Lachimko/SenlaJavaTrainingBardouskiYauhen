angular.module('ctrls', [])
    
   .controller('MechanicCtrl', function ($scope, $http, cons) {

    $scope.caption = cons().mechCaption;
    $scope.radioValue = cons().defaultradioValue;

    $scope.findId = function () {
      
        $http({
            method: 'GET',
            params: {id: $scope.id},
            url: cons().domain + '/MechanicServlet'
        }).then(function (response) {
            $scope.mechanics=[];
            $scope.mechanics.push(response.data);
          })
    }

    $scope.viewList = function () {
      
        $http({
            method: 'GET',
            params: {sorting: $scope.radioValue},
            url: cons().domain + '/MechanicListServlet'
        }).then(function (response) {
            $scope.mechanics = response.data;
          })
    }

    $scope.createByFullname = function () {
      
       $http({
            method: 'POST',
            params: {fullname:  $scope.fullname},
            url: cons().domain + '/MechanicServlet'
        }).then(function (response) {
            $scope.resp = response.data;
            $scope.viewList();
          })
    }

    $scope.removeById = function (identifier) {
      
       $http({
            method: 'DELETE',
            params: {id: $scope.id},
            url: cons().domain + '/MechanicServlet'
        }).then(function (response) {
            $scope.resp = response.data;
            $scope.viewList();
          })
    }
   
})
.controller('WorkplaceCtrl', function ($scope, cons) {

    $scope.caption = cons().workCaption;
})
.controller('OrderCtrl', function ($scope, cons) {

    $scope.caption = cons().ordCaption;
});