let loginRegister = angular.module('loginRegister', ['ngRoute']);

loginRegister.config(function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'loginContent.html',
        controller: 'LoginController'
    })
    .when('/login', {
        templateUrl: 'loginContent.html',
        controller: 'LoginController'
    })
    .when('/register', {
        templateUrl: 'registerContent.html',
        controller: 'RegisterController'
    });
});


loginRegister.controller('LoginController', function($scope) {
});

loginRegister.controller('RegisterController', function($scope) {
});