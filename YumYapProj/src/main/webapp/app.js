/**
 * 
 */

let app = angular.module('app', ['ui.router']);


app.config(function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/loginRegister');
    $stateProvider
    .state('loginRegister', {
        url: '/loginRegister',
        templateUrl: 'loginRegisterContent.html',
        controller: 'LoginRegisterController as logreg'
    })
    .state('app', {
        url: '/app',
        templateUrl: 'appContent.html',
        controller: 'AppController as cntrl'
    });
});

app.service("UserService", function($http, $q){
	console.log("in userservice");
	var service = this;
	service.user={
		id : "",
		following : "",
		firstname : "",
		lastname : "",
		password : "",
		email : "",
		active : "",
		loggedIn : "",
		favoriteRecipes :""
};

service.getUser = function(){
	console.log("in service.getUser");
	console.log(service.user);
	return service.user;
};

service.setUser = function(data){
	console.log("service.user.username "+service.user.username);
	console.log("data.username" + data.username);
	service.user.username = data.username;
	service.user.password = data.password;
	service.user.authenticated = data.authenticated;
};

service.registerUser = function(){
	console.log("In register user");
	var promise;
	console.log(service.user);
	
	promise = $http.post(
			'yum/user/register', service.user
			).then(
					function(response){
						console.log(response);
						return response;
					},
					function(error){
						console.log('register user promise failed');
						return $q.reject(error);
					}
					);
	return promise;
};


});


app.controller('LoginRegisterController', function(UserService, $scope) {
    $scope.onLogin = true;
    console.log("In loginRegisterController");
    
    var cntrl = this;
    cntrl.user = UserService.getUser();
    cntrl.doRegister = function(){
    		console.log("In do register")
    	
		var promise = UserService.registerUser();
		
		promise.then(
				function(response){
					console.log("in promise response")
					UserService.setUser(response.data);
//					$state.go('login');
					
				},function(error){
					console.log(error);
				})
	}
    
    
});

app.controller('AppController', function($scope) {
    $scope.tab = 'Home';
});