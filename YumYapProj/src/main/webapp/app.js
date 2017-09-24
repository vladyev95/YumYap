var yumApp = angular.module("yumApp", ["ui.router"]);

yumApp.config(function($stateProvider, $urlRouterProvider){
	console.log("init yumyap app..");

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
	})
	.state("home",{
		url:"/home",
		templateUrl: "templates/home.html",
		controller: "HomeCtrl as home"
	})
	.state("logout",{
		url: "/logout",
		templateUrl: "templates/home.html",
		controller: "LogoutCtrl as logout"
	});	
});


/*
 * In AngularJS, a service is a function or object that is 
 * available for, and limited to, your AngularJS application.
 * Angular has about 30 built in services like $http
 * https://docs.angularjs.org/api/ng/service/$q
 */
yumApp.service("UserService", function($http, $q){
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

	service.authenticateUser = function(){
		var promise = $http.post(
				"rest/user/login", service.user)
				.then(
						function(response){
							console.log("login: " + response);
							return response;
						},
						function(error){
							console.log("login promise fail");
						}
				);
		return promise;
	};

	service.registerUser = function(){

		var promise;

		promise = $http.post(
				"rest/user/register", service.user
		).then(// can pass in and register up to three callback functions [success, error, notified(which is sort of like a finally)]
				function(response){
					console.log("register: " + response);
					return response;
				},
				function(error){
					console.log("register user promise failed");
					return $q.reject(error);
				}
		);

		return promise;
	};

});

yumApp.controller("HomeCtrl", function(UserService,$state){
	console.log("in homeCtrl");
	var home = this;
	home.user = UserService.getUser();
});

yumApp.controller("LoginCtrl", function(UserService, $state){
	console.log("in loginctrl");

	var login = this;
	login.user = UserService.getUser();

	login.doLogin = function(){
		console.log("about to authenticate user");
		var promise = UserService.authenticateUser();
	
		promise.then(
				function(response){
					if(login.user!= null){
						login.user.authenticated = true;
						UserService.setUser(response.data);
						console.log("setting user in login ctrl")
						console.log(UserService.getUser());
						$state.go("home");
					} else{
						alert("Invalid login!");
					}
				},function(error){
					console.log(error);
				});
	
	};
});

yumApp.controller("LogoutCtrl", function(UserService, $state){
	console.log("in logoutctrl");

	var login = this;
	login.user = UserService.getUser();

	login.doLogout = function(){
		console.log("Logging out user");
		var promise = UserService.logoutUser();
	
		promise.then(
				function(response){
					if(login.user!= null){
						login.user.authenticated = false;
						UserService.setUser(undefined);
						$state.go("home");
					} else{
						alert("No user is currently logged in");
					}
				},function(error){
					console.log(error);
				});
	
	};
});

yumApp.controller("RegisterCtrl", function(UserService, $state){
	console.log("in registerctrl");

	var register = this;

	register.user = UserService.getUser();

	register.doRegister = function(){

		var promise = UserService.registerUser();

		promise.then(
				function(response){
					console.log("setting data");
					console.log(response.data);
					UserService.setUser(response.data);
					$state.go("login");

				}, function(error){
					console.log(error);
				}	
		)
	}
});

yumApp.controller("NavCtrl", function($state){
	console.log("in navctrl");
})