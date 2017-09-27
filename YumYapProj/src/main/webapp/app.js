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
	
	service.getUser = function() {
		return service.user;
	}
	
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
app.controller('AppController', function($scope, LoggedInUserService) {
    $scope.tab = 'Home';
    
    $scope.homeTab = function() {
        $scope.tab = 'Home';
    };
    
});
/* AppController */

app.service('HomeTabRecipesService', function(LoggedInUserService) {
	//$http('/getFollowingRecipes', LoggedInUserService.getUser());
});

/* HomeTabController */
app.controller('HomeTabController', function($scope, LoggedInUserService) {
	//let following = LoggedInUserService.getUser().following;
	
	
	
    $scope.recipes = [{
    	name: 'Baked Spaghetti',
    	description: 'Delicious baked spaghetti',
    	image: 'http://del.h-cdn.co/assets/17/37/980x490/landscape-1505340657-baked-spaghetti-delish-1.jpg',
    	creator: {
    		firstName: 'Rian',
    		lastName: 'Handler'
    	},
    	ingredients: [ 
    	               { name: 'spaghetty', measure: 'oz', amount: 16 }, 
    	               { name: 'extra virgin olive oil', measure: 'tbsp', amount: 2},
    	               { name: 'medium yellow onion, finely chopped', measure: 'cup', amount: 1},
    	               { name: 'minced garlec', measure: 'cloves', amount: 2},
    	               { name: 'ground beef', measure: 'lb', amount: 1}
    					],
    	directions: [ 
    	              'Preheat over to 350 degrees', 
    	              'Cook spaghetti' ,
    	              'Garnish with parsley'
    	              ]
    	
    }, {
    	name: 'Chicken Curry',
    	description: 'Some fire chicken curry',
    	image: 'http://del.h-cdn.co/assets/17/31/980x490/landscape-1501791674-delish-chicken-curry-horizontal.jpg',
    	creator: {
    		firstName: 'Jack',
    		lastName: 'Diamond'
    	},
    	ingredients: [
    	              { name: 'basmati rice', measure: 'cups', amount: '4' },
    	              { name: 'paprika', measure: 'tsp', amount: 1},
    	              { name: 'chicken breast', measure: 'lb', amount: 2 }
    		 ],
    	directions: [
    	             'In large pot, heat oil, add onion and cook',
    	             'season with salt and pepper',
    	             'garnish with cilantro'
    	             ]	
    } ];
});
/* HomeTabController */