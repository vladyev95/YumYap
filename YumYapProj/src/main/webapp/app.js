let app = angular.module('app', ['ngRoute']);

app.config(function($routeProvider) {
    $routeProvider
    .when('/', {
        url: '/',
        templateUrl: 'loginRegisterContent.html'
    })
    .when('loginRegister', {
        url: '/loginRegister',
        templateUrl: 'loginRegisterContent.html'
    })
    .when('app', {
        url: '/app',
        templateUrl: 'appContent.html'
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


/* LoginService */
app.service('LoginService', function($http, $q) {
    let service = this;
    
    service.user = {  
    			firstname: '',
            lastname: '',
            email: '',
            password: ''};
    
    service.getUser = function() {
        return service.user;
    }
 
    service.attemptLogin = function() {
        return $http.post('yum/user/login', service.user);
        
    };
});
/* LoginService */


/* RegisterService */
app.service('RegisterService', function($http, $q) {
    let service = this;
    
    service.user = {
        firstname: '',
        lastname: '',
        email: '',
        password: '',
        password2: ''
    };
    
    service.getUser = function() {
        return service.user;
    };
    
    service.attemptRegister = function() {
        return $http.post('yum/user/register', service.user);
    };
   
});
/* RegisterService */


/* LoginRegisterController */
app.controller('LoginRegisterController', function($scope) {
    $scope.onLogin = true;
});
/* LoginRegisterController */


/* LoginController*/
app.controller('LoginController', function($scope, LoginService) {
    
    $scope.user = LoginService.getUser();
    
    $scope.attemptLogin = function() {
        console.log($scope.user);
        LoginService.attemptLogin()
        .then(
            function(response) {
                console.log('attemptLogin() success response: ');
                console.log(response);
                
                /* reset all fields upon success */
                $scope.user = { email: '', password: '' };
            },
            function(error) {
                console.log('attemptLogin() error response: ');
                console.log(error);
            }
        );
    };
});
/* LoginController */


/* RegisterController */
app.controller('RegisterController', function($scope, RegisterService) {
    
    $scope.user = RegisterService.getUser();
    
    $scope.attemptRegister = function() {
        console.log($scope.user);
        RegisterService.attemptRegister()
        .then(
            function(response) {
                console.log('attemptRegister() success response: ');
                console.log(response);
                if(response.firstname){
                		$scope.registerMessage = "Success, weclome "+response.firstname;
                }
                else{
                	$scope.registerMessage = "unable to register";
                }
                /* reset all fields upon success */
                $scope.user = { firstName: '', lastName: '', email: '', password1: '', password2: '' };
            },
            function(error) {
                console.log('attemptRegister() error response: ');
                console.log(error);
            }
        );
    };
});
/* RegisterController */


/* ViewAuthorService */
app.service('ViewAuthorService', function() {
    let service = this;
    
    service.email = '';
    
    service.setEmail = function(email) {
        service.email = email;
    };
    
    service.getEmail = function(email) {
        return service.email;
    }
});
/* ViewAuthorService */


/* AppController */
app.controller('AppController', function($scope, ViewAuthorService) {
    $scope.tab = 'Home';
    
    
    $scope.homeTab = function() {
        $scope.tab = 'Home';
    };
    
    $scope.viewAuthor = function(email) {
        $scope.tab = 'ViewAuthor';
        ViewAuthorService.setEmail(email);
    };
});
/* AppController */


/* HomeTabController */
app.controller('HomeTabController', function($scope) {
    
});
/* HomeTabController */


/* ViewAuthorController */
app.controller('ViewAuthorController', function($scope, $http, $q, ViewAuthorService) {
    let email = ViewAuthorService.getEmail();
    
    $scope.user = null;
    
    $http('getUserInfoController', email)
    .then(
        function(response) {
        },
        function(error) {
            
        });
        
        
    $http('getRecipesByUserIdController', user.id)
    .then(
        function(response) {
        },
        function(error) {
        });
    
});
/* ViewAuthorController */



