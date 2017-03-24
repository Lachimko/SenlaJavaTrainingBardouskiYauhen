angular.module('app', ['ui.router','ctrls']).config(function ($stateProvider) {

    $stateProvider.state({
        name: 'mechanic',
        url: '/mechanic',
        templateUrl: '_mech.tpl.html'  
    }).state({
        name: 'workplace',
        url: '/workplace',
        templateUrl: '_workpl.tpl.html'  
    }).state({
        name: 'order',
        url: '/order',
        templateUrl: '_ord.tpl.html'  
    })

})
.factory('cons', function() {

   return function () {
        return {
            mechCaption : "Mechanic Control Module",
            workCaption : "Workplace Control Module",
            ordCaption : "Order Control Module",
            defaultradioValue : "",
            domain : "/servlet-layer"
        }
   }
})
.directive('sortingDiv', function () {

    // html: <sorting-div column-name="id" object-property="order.id"></sorting-div>
    return {
        restrict: 'E',
        template: '<th><a href=""></a></th>',
        replace: true,

        link: function($scope, element, attrs) {
            attrs.$observe('columnName', function(value) {
                element.find('a').text(value)
            })

            attrs.$observe('objectProperty', function(value) {
                element.find('a').attr('ng-click', "sortType="+value+"; sortReverse = !sortReverse");

            })
        }
    }
});
