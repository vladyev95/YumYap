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

app.service('RecipeService', function ($http) {
    let service = this;

    service.saveRecipe = function (recipe) {
        log('RecipeService save recipe');
        return $http.post('/YumYap/yum/recipe/create', recipe);
    };
});


/* LoginRegisterController */
app.controller('LoginRegisterController', function ($scope) {
    $scope.onLogin = true;
});
/* LoginRegisterController */

app.service("UserService", function ($http, $q) {
    console.log("in userservice");
    var service = this;
    service.user = {
        id: "",
        following: "",
        firstname: "",
        lastname: "",
        password: "",
        email: "",
        active: "",
        loggedIn: "",
        favoriteRecipes: ""
    };

    service.getUser = function () {
        console.log("in service.getUser");
        console.log(service.user);
        return service.user;
    };

    service.setUser = function (data) {
        console.log("service.user.username " + service.user.username);
        console.log("data.username" + data.username);
        service.user.username = data.username;
        service.user.password = data.password;
        service.user.authenticated = data.authenticated;
    };

    service.registerUser = function () {
        console.log("In register user");
        var promise;
        console.log(service.user);

        promise = $http.post(
            'yum/user/register', service.user
        ).then(
            function (response) {
                console.log(response);
                return response;
            },
            function (error) {
                console.log('register user promise failed');
                return $q.reject(error);
            }
            );
        return promise;
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

    service.user = {
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    };

    service.getUser = function () {
        return service.user;
    };

    service.attemptRegister = function () {
        return $http.post('yum/user/attemptRegister', service.user);
    };
});
/* RegisterService */

app.service('RecipeService', function ($http) {
    let service = this;

    service.saveRecipe = function (recipe) {
        log('RecipeService save recipe');
        return $http.post('/YumYap/yum/recipe/create', recipe);
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
app.controller('LoginController', function ($scope, $location, LoginService, LoggedInUserService) {

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
                    LoggedInUserService.setUser(response.data);
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

    $scope.user = RegisterService.getUser();

    $scope.attemptRegister = function () {
        console.log('attempting to register: ');
        console.log($scope.user);
        RegisterService.attemptRegister()
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
app.service('ViewAuthorService', function () {
    let service = this;

    service.email = '';

    service.setEmail = function (email) {
        service.email = email;
    };

    service.getEmail = function (email) {
        return service.email;
    }
    
    service.getAuthorInfo = function() {
        return $http.post('/viewAuthor', service.email);
    };
});
/* ViewAuthorService */

app.controller('ViewAuthorController', function ($scope, $location, ViewAuthorService) {
	
	$scope.viewAuthor = function(email) {
		// TODO: Implement viewAuthor with ViewAuthorService??
		 ViewAuthorService.getAuthorInfo().then(function(response){
			 if (response.data) {
				 let user = response.data;
			 }else{
				 console.log('reponse.data is null');
			 }
		});
			 
	}
});


/* AppController */
app.controller('AppController', function ($scope, ProfileService, ViewAuthorService) {
    $scope.tab = 'Home';

    $scope.homeTab = function () {
        $scope.tab = 'Home';


    };

    $scope.viewAuthor = function (email) {
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

app.service('HomeTabRecipesService', function () {

});

/* HomeTabController */
app.controller('HomeTabController', function ($scope, ViewDashService) {

});

app.controller('RecipeCtrl', function ($scope, $http, RecipeService, UserService) {
    'use strict';
    const API_KEY = '1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE';
    const EXCLUDED_GROUPS = ['Baby Foods', 'Fast Foods', 'Restaurant Foods'];
    const TRACKED_NUTRIENTS = ['208', '204', '205', '203'];

    $scope.food = { 'name': '', 'nutrients': { 'calories': 0, 'fat': 0, 'carbs': 0, 'protein': 0 } };
    $scope.measures = [];
    $scope.nutrientsByMeasure = {};
    $scope.ingredients = [];
    $scope.foodItems = [];
    $scope.steps = [];

    $scope.saveRecipe = function () {
        log('RecipeCtrl save recipe');
        let recipe = {
            creator: UserService.getUser(),
            name: $scope.recipeName,
            description: $scope.recipeDescription,
            directions: $scope.steps,
            imageUrl: null,
            ingredients: $scope.foodItems,
            calories: $scope.food.nutrients.calories,
            fat: $scope.food.nutrients.fat,
            carbs: $scope.food.nutrients.carbs,
            protein: $scope.food.nutrients.protein
        };

        RecipeService.saveRecipe(recipe);
    };

    $scope.addIngredient = function (quantity, fraction, measure, name) {
        log('Creating ingredient with ' + quantity + ', ' + fraction + ', ' + measure + ', ' + name);
        let ingredient = { name: name, quantity: quantity, fraction: fraction, measure: measure };
        $scope.ingredients.push(ingredient);
        $scope.foodItems.push({ name: name, amount: quantity + eval(fraction), measure: measure });
        $scope.search = null;
        $scope.selection = null;
    };

    $scope.addStep = function (step) {
        log('Adding step ' + step);
        $scope.steps.push({direction:step});
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
