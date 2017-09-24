let app = angular.module('app', ['ui.router']);


app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/loginRegister');
    $stateProvider
    .state('loginRegister', {
        url: '/loginRegister',
        templateUrl: 'loginRegisterContent.html',
        controller: 'LoginRegisterController as cntrl'
    })
    .state('app', {
        url: '/app',
        templateUrl: 'appContent.html',
        controller: 'AppController as cntrl'
    });
});


app.controller('LoginRegisterController', function($scope) {
    $scope.onLogin = true;
    
});

app.controller('AppController', function($scope) {
    $scope.tab = 'Home';
});