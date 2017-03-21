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
 });


