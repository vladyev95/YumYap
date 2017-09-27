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

app.service("UserService", function ($http, $q) {
    console.log("in userservice");
    var service = this;
    service.user = {
        id: 8,
        following: null,
        firstname: 'Nathan',
        lastname: 'Koszuta',
        password: 'p4ssw0rd',
        email: 'koszutan94@gmail.com',
        active: 1,
        loggedIn: 1,
        favoriteRecipes: null
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

    service.user = {
        firstname: '',
        lastname: '',
        email: '',
        password: ''
    };

    service.getUser = function () {
        return service.user;
    }

    service.attemptLogin = function () {
        return $http.post('yum/user/login', service.user);

    };
});
/* LoginService */


/* RegisterService */
app.service('RegisterService', function ($http, $q) {
    let service = this;

    service.attemptRegister = function (user) {
        return $http.post('yum/user/register', user);
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


/* LoginRegisterController */
app.controller('LoginRegisterController', function ($scope) {
    $scope.onLogin = true;
});
/* LoginRegisterController */


/* LoginController*/
app.controller('LoginController', function ($scope, $location, LoginService) {

    $scope.user = LoginService.getUser();

    $scope.attemptLogin = function () {
        console.log($scope.user);
        LoginService.attemptLogin()
            .then(
            function (response) {
                console.log('attemptLogin() success response: ');
                console.log(response);
                console.log('data= ' + response.data);
                if (response.data) {
                    console.log($location.url());
                    $location.path('/app');
                    console.log($location.path());
                }

                /* reset all fields upon success */
                $scope.user = { email: '', password: '' };
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
app.controller('RegisterController', function ($scope, UserService, RegisterService) {

    $scope.user = UserService.getUser();

    $scope.attemptRegister = function () {
        console.log($scope.user);
        RegisterService.attemptRegister()
            .then(
            function (response) {
                console.log('attemptRegister() success response: ');
                console.log(response);
                if (response.firstname) {
                    $scope.registerMessage = "Success, weclome " + response.firstname;
                }
                else {
                    $scope.registerMessage = "unable to register";
                }
                /* reset all fields upon success */
                $scope.user = { firstName: '', lastName: '', email: '', password1: '', password2: '' };
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
    };
});
/* ViewAuthorService */


/* AppController */
app.controller('AppController', function ($scope, ViewAuthorService) {
    $scope.tab = 'Home';


    $scope.homeTab = function () {
        $scope.tab = 'Home';
    };

    $scope.viewAuthor = function (email) {
        $scope.tab = 'ViewAuthor';
        ViewAuthorService.setEmail(email);
    };
});
/* AppController */


/* HomeTabController */
app.controller('HomeTabController', function ($scope) {

});
/* HomeTabController */


/* ViewAuthorController */
app.controller('ViewAuthorController', function ($scope, $http, $q, ViewAuthorService) {
    let email = ViewAuthorService.getEmail();

    $scope.user = null;

    $http('getUserInfoController', email)
        .then(
        function (response) {
        },
        function (error) {

        });


    $http('getRecipesByUserIdController', user.id)
        .then(
        function (response) {
        },
        function (error) {
        });

});
/* ViewAuthorController */

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
            id: -1,
            created: null,
            creator: UserService.getUser(),
            name: $scope.recipeName,
            description: $scope.recipeDescription,
            directions: $scope.steps,
            image: null,
            ingredients: $scope.foodItems
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

function logError(error) {
    log(error.status + ' error, ' + error.statusText);
}

function log(message) {
    console.log('apitest.js -- ' + message);
}
