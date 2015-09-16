var app = angular.module('RabirologistApp', ['ngMaterial']);

app.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('teal')
    .accentPalette('teal');
});

app.controller('AppCtrl', ['$scope', function($scope) {

    $scope.action = function() {
        // do smth
    };

}]);
