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


app.config(function ($routeProvider) {
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

/* LoginRegisterController */
app.controller('LoginRegisterController', function ($scope) {
    $scope.onLogin = true;
});
/* LoginRegisterController */

app.service("UserService", function ($http, $q) {
    'use strict';
    console.log("in userservice");
    let service = this;
    let user = {};

    service.getUser = function () {
        console.log("in service.getUser");
        console.log(user);
        return user;
    };

    service.setUser = function (data) {
        user.id = data.id;
        user.email = data.email;
        user.firstName = data.firstName;
        user.lastName = data.lastName;
        user.following = data.following;
        user.favoriteRecipes = data.favoriteRecipes;
    };
});


/* LoginService */
app.service('LoginService', function ($http, $q) {
    let service = this;

    service.user = { email: '', password: '' };

    service.getUser = function () {
        return service.user;
    }

    service.attemptLogin = function () {
        return $http.post('yum/user/attemptLogin', service.user);

    };
});
/* LoginService */


/* RegisterService */
app.service('RegisterService', function ($http, $q) {
    let service = this;

    service.attemptRegister = function (user) {
        log('registering user '+user);
        return $http.post('yum/user/attemptRegister', user);
    };
});
/* RegisterService */

app.service('RecipeService', function ($http) {
    let service = this;

    service.createRecipe = function (recipe) {
        log('RecipeService create recipe');
        return $http.post('yum/recipe/create', recipe);
    };
});


/* LoggedInUserService */
app.service('LoggedInUserService', function () {
    let service = this;

    service.setUser = function (user) {
        service.user = user;
    }
});
/* LoggedInUserService */


/* LoginRegisterController */
app.controller('LoginRegisterController', function ($scope) {
    $scope.onLogin = true;
});
/* LoginRegisterController */


/* LoginController*/
app.controller('LoginController', function ($scope, $location, LoginService, UserService) {

    $scope.user = LoginService.getUser();

    $scope.attemptLogin = function () {
        console.log('attempting to log in: ');
        console.log($scope.user);
        LoginService.attemptLogin()
            .then(
            function (response) {
                if (response.data) {
                    console.log('attemptLogin() success response: ');
                    console.log(response);
                    console.log('data: ');
                    console.log(response.data);
                    UserService.setUser(response.data);
                    $location.path('/app');
                } else {
                    console.log('reponse.data is null');
                }

            },
            function (error) {
                console.log('attemptLogin() error response: ');
                console.log(error);
            }
            );
    };
});
/* LoginController */


/* RegisterController */
app.controller('RegisterController', function ($scope, RegisterService) {

    $scope.attemptRegister = function () {
        console.log('attempting to register: ');
        console.log($scope.user);
        RegisterService.attemptRegister($scope.user)
            .then(
            function (response) {
                console.log('attemptRegister() success response: ');
                console.log(response);
                console.log('data: ');
                console.log(response.data);
            },
            function (error) {
                console.log('attemptRegister() error response: ');
                console.log(error);
            }
            );
    };
});
/* RegisterController */


/* ViewAuthorService */
app.service('ViewAuthorService', function ($http) {
    let service = this;
    console.log("Inside ViewAuthorService")
    
    service.user = {
    		id: '',
    		email: '',
    		firstName: '',
    		lastName: '',
    		following: '',
    		favoriteRecipes: ''
    };

    service.setUser = function (data) {
       service.user.id =  data.id;
       service.user.email = data.email;
       service.user.firstName = data.firstName;
       service.user.lastName = data.lastName;
       service.user.following = data.following;
       service.user.favoriteRecipes = data.favoriteRecipe;
    };

    service.getUser = function (email) {
        return service.user;
    };
    
    service.follow = function () {
    	console.log("Adding a follower");
    	var follower = service.user;
    	var user = getUserInfoService();
    	
    	return $http.post('yum/user/addFollower', user, follower);
    };
});
/* ViewAuthorService */

app.controller('ViewAuthorController', function ($scope, ViewAuthorService, RecipeService) {
	console.log("Inside ViewAuthorController");
	var viewAuthor = ViewAuthorService;
	var recipeService = RecipeService;
	var author = this;
	author.user = viewAuthor.getUser();
	$scope.user = author.user;
	$scope.recipes = author.user.recipes;
	
	$scope.viewAuthor = function(recipe){
		recipeService.viewAuthor(recipe);
	}

	$scope.favoriteRecipe = function(recipe){
		recipeService.favoriteRecipe(recipe);
	}
});


/* AppController */
app.controller('AppController', function ($scope, ProfileService, ViewAuthorService) {
	log('in AppController');
    $scope.tab = 'Home';

    $scope.switchToHome = function () {
    	log('switching to \'Home\' tab');
        $scope.tab = 'Home';
    };
    
    $scope.switchToCreateRecipe = function () {
    	log('switching to \'Create Recipe\' tab');
    	$scope.tab = 'CreateRecipe';
    };

    $scope.viewAuthor = function (email) {
    	log('switching to \'View Author\' tab');
        $scope.tab = 'ViewAuthor';
        ViewAuthorService.setEmail(email);
    };

    var profile = ProfileService;
    var data = function () {
        console.log("start view");
        profile.viewDash()
            .then(
            function (response) {
                console.log(response);
                $scope.recipes = response.data.recipes;
                console.log(response.data.recipes);
                profile.setRecipes(response.data);
                console.log("The last");
                console.log(profile.getRecipes());
                return response;

            }, function (error) {
                console.log("error")
                console.log(error);
                //$scope.output = error;
            });
    }();

    var favoriteRecipe = function () {
        console.log("maybe some goats");
        // TODO: Use FavoriteRecipeService here
    }
});
/* AppController */

app.controller('RecipeCtrl', function ($scope, $http, RecipeService, UserService) {
    'use strict';
    const API_KEY = '1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE';
    const EXCLUDED_GROUPS = ['Baby Foods', 'Fast Foods', 'Restaurant Foods'];
    const TRACKED_NUTRIENTS = ['208', '204', '205', '203'];

    $scope.food = { 'name': '', 'nutrients': { 'calories': 0, 'fat': 0, 'carbs': 0, 'protein': 0 } };
    $scope.measures = [];
    $scope.nutrientsByMeasure = {};
    $scope.ingredients = [];
    $scope.ingredients2 = [];
    $scope.steps = [];

    $scope.publishRecipe = function () {
        log('RecipeCtrl create recipe');
        let recipe = {
            creator: UserService.getUser(),
            name: $scope.recipeName,
            description: $scope.recipeDescription,
            directions: $scope.steps,
            imageUrl: '',
            ingredients: $scope.ingredients2,
            calories: $scope.food.nutrients.calories,
            fat: $scope.food.nutrients.fat,
            carbs: $scope.food.nutrients.carbs,
            protein: $scope.food.nutrients.protein
        };

        RecipeService.createRecipe(recipe);
    };

    $scope.addIngredient = function (quantity, fraction, measure, name) {
        log('Creating ingredient with ' + quantity + ', ' + fraction + ', ' + measure + ', ' + name);
        let ingredient = { name: name, quantity: quantity, fraction: fraction, measure: measure };
        $scope.ingredients.push(ingredient);
        $scope.ingredients2.push({ name: name, amount: quantity + eval(fraction), measure: measure });
        $scope.search = null;
        $scope.selection = null;
    };

    $scope.addStep = function (step) {
        log('Adding step ' + step);
        $scope.steps.push(step);
        $scope.recipeStep = '';
    };

    $scope.getFoodReport = function (selection) {
        log('in getFoodReport');
        log('Selected food= ' + selection);

        if (selection) {
            $http.get('https://api.nal.usda.gov/ndb/reports/?ndbno=' + selection + '&format=xml&api_key=' + API_KEY)
                .then(function (responseXml) {

                    let foodReport = new DOMParser()
                        .parseFromString(responseXml.data, 'text/xml')
                        .firstChild.children.item(0);

                    if (foodReport.tagName === 'food') {
                        $scope.food.name = foodReport.getAttribute('name');
                        $scope.customName = foodReport.getAttribute('name');

                        let nutrients = foodReport.children.item(0).children;
                        let m = nutrients.item(0).children.item(0).children;

                        $scope.food.nutrients = { 'calories': 0, 'fat': 0, 'carbs': 0, 'protein': 0 };
                        $scope.measures = [];
                        $scope.nutrientsByMeasure = {};

                        for (var i = 0; i < m.length; i++) {
                            let label = m.item(i).getAttribute('label');
                            $scope.measures.push(label);
                            $scope.nutrientsByMeasure[label] = {};
                        }

                        log('measures= ' + $scope.measures);

                        for (let i = 0; i < nutrients.length; i++) {
                            let nutrient = nutrients.item(i);
                            let measures = nutrient.children.item(0).children;

                            let id = nutrient.getAttribute('nutrient_id');
                            if (TRACKED_NUTRIENTS.indexOf(id) !== -1) {

                                for (let i = 0; i < measures.length; i++) {

                                    let label = measures.item(i).getAttribute('label');
                                    let value = measures.item(i).getAttribute('value');
                                    $scope.nutrientsByMeasure[label][id] = value;
                                }
                            }
                        }
                    } else {

                        // Bad food ndbno
                    }
                },
                function (error) {
                    logError(error);
                });
        }
    };

    $scope.calculateNutrients = function (quantity, fraction, measure) {
        log('in calculate nutrients');
        if (!measure) return;
        if (!quantity) quantity = 0;
        if (!fraction) fraction = 0;
        let nutrients = $scope.food.nutrients;
        let nutrientsForMeasure = $scope.nutrientsByMeasure[measure];
        let i = 0;
        for (let attr in nutrients) {
            nutrients[attr] = (quantity + eval(fraction)) * nutrientsForMeasure[TRACKED_NUTRIENTS[i++]];
        }
    };

    $scope.searchFoods = function (searchTerm) {
        if (searchTerm) {
            $http.get('https://api.nal.usda.gov/ndb/search/?q=' + searchTerm + '&ds=Standard%20Reference&max=20&format=xml&api_key=' + API_KEY)
                .then(function (responseXml) {

                    // Make sure slow response doesn't affect empty search box
                    if (!$scope.search) return;

                    // Get elements from XML search results
                    let searchResults = new DOMParser()
                        .parseFromString(responseXml.data, 'text/xml')
                        .firstChild.children;
                    log('first element= ' + searchResults.item(0).tagName);
                    if (searchResults.item(0).tagName === 'item') {
                        $scope.foodItems = [];

                        // Go through each food item in result
                        for (let i = 0; i < searchResults.length; i++) {

                            let group = searchResults.item(i).getElementsByTagName('group')[0].innerHTML;
                            // Skip food if in excluded food group
                            if (EXCLUDED_GROUPS.indexOf(group) !== -1) continue;

                            let name = searchResults.item(i).getElementsByTagName('name')[0].innerHTML;
                            let ndbno = searchResults.item(i).getElementsByTagName('ndbno')[0].innerHTML;

                            // Create food item and add to list
                            let item = { 'name': name, 'ndbno': ndbno, 'group': group };
                            $scope.foodItems.push(item);
                        }
                    } else {

                        // Show previous results when no new results
                        // Or give no search results message here
                    }

                }, function (error) {
                    logError(error);
                });

        } else {
            // Clear search results when search box is empty
            $scope.foodItems = [];
        }
    };
});

app.service('ProfileService', function ($http, $q) {
    var service = this;
    service.user = {
        firstname: '',
        lastname: '',
        email: 'us@er.com',
        password: ''
    };

    service.viewProfile = function () {
        console.log("getting profile");
        console.log(service.user);
        return $http.post('yum/user/profile', service.user);
    };

    service.viewDash = function () {
        console.log("getting dash");
        console.log(service.user);
        return $http.post('yum/user/dash', service.user);
    };

    service.recipes = {

    };

    service.setRecipes = function (data) {
        service.recipes = data.recipes;
    }
    service.getRecipes = function () {
        return service.recipes;
    }

    service.viewUser = {
        firstname: '',
        lastname: '',
        email: 'us@er.com'
    };

    service.setViewUser = function (data) {
        service.viewUser.firstname = data.user.firstname;
        service.viewUser.firstname = data.user.lastname;
        service.viewUser.firstname = data.user.email;
    }

    service.getViewUser = function () {
        return service.viewUser;
    }
});
app.controller('ProfileController', function ($scope, ProfileService, $http, $q) {


    var profile = ProfileService;
    var data = function () {
        console.log("start view");
        profile.viewProfile()
            .then(
            function (response) {
                console.log(response);
                console.log(response.data.recipes);
                profile.setRecipes(response.data);
                profile.setViewUser(resonse.data);
                $scope.recipes = profile.getRecipes();
                $scope.user = profile.getViewUser();
                return response;

            }, function (error) {
                console.log("error")
                console.log(error);
                //$scope.output = error;
            });
    }();

    var favoriteRecipe = function () {
        console.log("maybe some goats");
        // TODO: Use FavoriteRecipeService here
    }


});

app.controller('DashboardController', function ($scope, ProfileService, $http, $q) {


    var profile = ProfileService;
    var data = function () {
        console.log("start view");
        profile.viewDash()
            .then(
            function (response) {
                console.log(response);
                $scope.recipes = response.data.recipes;
                console.log(response.data.recipes);
                profile.setRecipes(response.data);
                console.log("The last");
                console.log(profile.getRecipes());
                return response;

            }, function (error) {
                console.log("error")
                console.log(error);
                //$scope.output = error;
            });
    }();

    var favoriteRecipe = function () {
        console.log("maybe some goats");
        // TODO: Use FavoriteRecipeService here
    }


});

function logError(error) {
    log(error.status + ' error, ' + error.statusText);
}

function log(message) {
    console.log('apitest.js -- ' + message);
}
