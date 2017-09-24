let loginRegister = angular.module('loginRegister', ['ui.router']);

/**
loginRegister.config(['$locationProvider', function($locationProvider) {
    $locationProvider.hashPrefix('');
}]);*/


loginRegister.config(function($stateProvider, $urlRouterProvider) {
    
    $stateProvider
    .state('home', {
        url: '',
        templateUrl: 'loginContent.html',
        controller: 'LoginController as login'
    })
    .state('login', {
        url: '/login',
        templateUrl: 'loginContent.html',
        controller: 'LoginController as login'
    })
    .state('register', {
        url: '/register',
        templateUrl: 'registerContent.html',
        controller: 'RegisterController as register'
    });
});


loginRegister.controller('LoginController', function($scope) {
    
});

loginRegister.controller('RegisterController', function($scope) {
});