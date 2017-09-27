// Copy - Pastable JavaScript Objects based on Dtos
//  UserDto:
//  user = {id: <int>,
//	favoriteRecipes: {<array of recipeDtos>},
//	following: {<array of users>},
//	firstname: "",
//	lastname: "",
//	password: "",
//	email: "",
//	active: "", //(this is a number)
//	loggedIn: "" } //this is a boolean
// RecipeDto:
//  id: "",
//	created: "" //this is a time and will have to be formatted
//	creator: {User object}
//	name: "",
//	description: "",
//	directions: { list of strings},
//	image: image url,
//	ingredients: {list of strings}

let app = angular.module('app', ['ngRoute']);


app.config(function($routeProvider) {
    $routeProvider
    .when('/', {
        templateUrl: 'loginRegisterContent.html'
    })
    .when('/loginRegister', {
        templateUrl: 'loginRegisterContent.html'
    })
    .when('/app', {
        templateUrl: 'appContent.html'
    });
});

/* LoginService */
app.service('LoginService', function($http, $q) {
    let service = this;
    
    service.user = { email: '', password: '' };
    
    service.getUser = function() {
        return service.user;
    }
 
    service.attemptLogin = function() {
        return $http.post('yum/user/attemptLogin', service.user);
        
    };
});
/* LoginService */


/* RegisterService */
app.service('RegisterService', function($http, $q) {
    let service = this;
    
    service.user = {
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    };
    
    service.getUser = function() {
        return service.user;
    };
    
    service.attemptRegister = function() {
        return $http.post('yum/user/attemptRegister', service.user);
    };
   
});
/* RegisterService */


/* LoggedInUserService */
app.service('LoggedInUserService', function() {
	let service = this;
	
	service.setUser = function(user) {
		service.user = user;
	}
});
/* LoggedInUserService */


/* LoginRegisterController */
app.controller('LoginRegisterController', function($scope) {
    $scope.onLogin = true;
});
/* LoginRegisterController */


/* LoginController*/
app.controller('LoginController', function($scope, $location, LoginService, LoggedInUserService) {
    
    $scope.user = LoginService.getUser();
    
    $scope.attemptLogin = function() {
    	console.log('attempting to log in: ');
        console.log($scope.user);
        LoginService.attemptLogin()
        .then(
            function(response) {
                if (response.data) {
                	console.log('attemptLogin() success response: ');
                	console.log(response);
                	console.log('data: ');
                	console.log(response.data);
                	LoggedInUserService.setUser(response.data);
                	$location.path('/app');
                } else {
                	console.log('reponse.data is null');
                }
                
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
        console.log('attempting to register: ');
        console.log($scope.user);
        RegisterService.attemptRegister()
        .then(
            function(response) {
        		console.log('attemptRegister() success response: ');
        		console.log(response);
        		console.log('data: ');
        		console.log(response.data);             
            },
            function(error) {
                console.log('attemptRegister() error response: ');
                console.log(error);
            }
        );
    };
});
/* RegisterController */

/* AppController */
app.controller('AppController', function($scope, ViewAuthorService, LoggedInUserService) {
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