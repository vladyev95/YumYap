
let app = angular.module('app', ["ui.router"]);

app.config(function($stateProvider, $urlRouterProvider){
	console.log("init app..");

	$stateProvider
	.state("login",{
		url:"/login",
		templateUrl: "templates/login.html",// html 
		controller: "LoginCtrl as login"// ng controller			
	})
	.state("register",{
		url: "/register",
		templateUrl: "templates/register.html",
		controller: "RegisterCtrl as register"
	});	
});

app.service("UserService", function($http, $q){
	console.log("in userService");

	var service = this;

	service.user={
			username : "",
			password : "",
			authenticated : false
	};

	service.getUser= function(){
		return service.user;
	};

	service.setUser = function(data){
		service.user.username = data.username;
		service.user.password = data.password;
		service.user.authenticated = data.authenticated;
	};

	service.registerUser = function(){
		var promise;

		promise = $http.post(
				'rest/user/register',service.user
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

app.controller("LoginCtrl", function($state){
	console.log("in loginctrl");
});

app.controller("RegisterCtrl", function(UserService, $state){
	console.log("in registerctrl");
	
	var register = this;
	register.user = UserService.getUser();
	
	register.doRegister = function(){
		var promise = UserService.registerUser();
		
		promise.then(
			function(response){
				console.log(response);
				UserService.setUser(response.data);
				$state.go('login');
			}, function(error){
				console.log(error);
			}	
		);
	}
});

app.controller("NavCtrl", function($state){
	console.log("in navctrl");
})

/*
 * controller for the overall application holds whether we are loggedIn or not
 */
app.controller('AppController', function() {
	this.loggedIn = false;

});

/*
 * controller for the Login/Register part of the app holds whether we are on the
 * login page or the register page
 */
app.controller('LoginController', function() {
	this.onLogin = true;
});

/*
 * controller for the stuff once we've logged in
 */
app.controller('ContentController', function() {
	this.tab = 'Home';
});
