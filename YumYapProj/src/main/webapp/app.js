//Copy - Pastable JavaScript Objects based on Dtos
//UserDto:
//user = {id: <int>,
//favoriteRecipes: {<array of recipeDtos>},
//following: {<array of users>},
//firstname: "",
//lastname: "",
//password: "",
//email: "",
//active: "", //(this is a number)
//loggedIn: "" } //this is a boolean
//RecipeDto:
//id: "",
//created: "" //this is a time and will have to be formatted
//creator: {User object}
//name: "",
//description: "",
//directions: { list of strings},
//image: image url,
//ingredients: {list of strings}


const BUCKET_PATH = 'https://s3.amazonaws.com/thisisans3bucket/';
const S3 = new AWS.S3({
    credentials: {
        accessKeyId: 'AKIAJGSJ4WJMQ6AC7UYA',
        secretAccessKey: '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'
    }
});

const API_KEY = '1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE';
const EXCLUDED_GROUPS = ['Baby Foods', 'Fast Foods', 'Restaurant Foods'];
const TRACKED_NUTRIENTS = ['208', '204', '205', '203'];


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

//This service provides information about the current user
//It returns all of the fields for a UserDto (not SimpleUserDto)
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
		console.log('setting user '+ data);
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

	service.attemptLogin = function (user) {
		console.log('attempting to login ');
		console.log(user)
		return $http.post('yum/user/attemptLogin',  user);

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

	service.viewDash = function (user) {
		console.log("getting dash");
		console.log(user);
		return $http.post('yum/user/dash', user);
	};

	service.favoriteRecipe = function(recipe){
		console.log('favoriting '+recipe);
	}

	service.viewAuthor = function(recipe){
		console.log('viewing author of '+recipe);
	}

	service.recipes = {};

	service.setRecipes = function (data) {
		service.recipes = data;
	}
	service.getRecipes = function () {
		return service.recipes;
	}
	
	service.viewComments = function(recipe){
		return $http.post('yum/comments/show', recipe)
	}
	
	service.addComment = function(recipe, user){
		return $http.post('yum/comments/create', comment)
	}


});



/* LoginController*/
app.controller('LoginController', function ($scope, $location, LoginService, UserService) {

//	$scope.user = LoginService.getUser();

	$scope.attemptLogin = function () {
		console.log('attempting to log in: ');
		console.log($scope.user);
		LoginService.attemptLogin($scope.user)
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
app.controller('RegisterController', function ($scope, RegisterService, UserService) {

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

	service.getUser = function () {
		return service.user;
	};

	service.follow = function (user) {
		console.log(user + " is now following "+service.user);
		var following = service.user;

		return $http.post('yum/user/addFollower', user, following);
	};
});
/* ViewAuthorService */

app.controller('ViewAuthorController', function ($scope, ViewAuthorService, RecipeService, UserService) {
	console.log("Inside ViewAuthorController");
	var viewAuthor = ViewAuthorService;
	var recipeService = RecipeService;
	var userService = UserService;
	var author = this;
	author.user = viewAuthor.getUser();

	$scope.user = author.user;
	$scope.recipes = author.user.recipes;

	$scope.follow = function(){
		viewAuthor.follow(userService.getUser);
	}

	$scope.viewAuthor = function(recipe){
		recipeService.viewAuthor(recipe);
	}

	$scope.favoriteRecipe = function(recipe){
		recipeService.favoriteRecipe(recipe);
	}
});


/* AppController */
app.controller('AppController', function ($scope, ViewAuthorService, RecipeService) {
	log('in AppController');
	$scope.tab = 'Home';

	$scope.onLogin = true;

	$scope.switchToHome = function () {
		log('switching to \'Home\' tab');
		$scope.tab = 'Home';
	};

	$scope.switchToCreateRecipe = function () {
		log('switching to \'Create Recipe\' tab');
		$scope.tab = 'CreateRecipe';
	};
	
	$scope.switchToSearchRecipe = function(){
		log('switching to \'Create Recipe\' tab');
		$scope.tab = 'SearchRecipes';
	}

});
/* AppController */

app.controller('RecipeCtrl', function ($scope, $http, RecipeService, UserService) {
	'use strict';
	const API_KEY = '1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE';
	const EXCLUDED_GROUPS = ['Baby Foods', 'Fast Foods', 'Restaurant Foods'];
	const TRACKED_NUTRIENTS = ['208', '204', '205', '203'];
	var i = 1;

	$scope.food = { 'name': '', 'nutrients': { 'calories': 0, 'fat': 0, 'carbs': 0, 'protein': 0 } };
	$scope.measures = [];
	$scope.nutrientsByMeasure = {};
	$scope.ingredients = [];
	$scope.ingredients2 = [];
	$scope.steps = [];
	
	$scope.uploadRecipeImage = function () {
        log('uploading recipe image');
        let image = document.getElementById('recipeImageFile').files[0];

        if (image) {

            let imageKey = new Date().getTime() + '--' + image.name;
            let params = {
                Body: image,
                Bucket: 'thisisans3bucket',
                Key: imageKey
            };
            S3.upload(params, function (err, data) {
                if (err) {
                    log(err);
                } else {

                    log('successfully uploaded image ' + imageKey);
                    setTimeout(function () {
                        $scope.recipeImageUri = BUCKET_PATH + encodeURIComponent(imageKey);
                        $('#recipeImage').attr('src', BUCKET_PATH + encodeURIComponent(imageKey));
                        log('uri= '+$scope.recipeImageUri);
                    }, 4000);
                }
            });
        }
    };

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
		console.log(recipe);
		if(recipe.name && recipe.description && recipe.ingredients && recipe.directions.length >0) {
			RecipeService.createRecipe(recipe);
			$scope.recipeName = null;
			$scope.recipeDescription = null;
			$scope.ingredients = [];
			$scope.ingredients2 = [];
			$scope.steps = [];
		}
		
	};
	
	$scope.addIngredient = function (quantity, fraction, measure, name) {
		log('Creating ingredient with ' + quantity + ', ' + fraction + ', ' + measure + ', ' + name);
		name = name.trim();
		let ingredient = { name: name, quantity: quantity, fraction: fraction, measure: measure };
		if(name && measure && (ingredient.quantity || ingredient.fraction)){
			$scope.ingredients.push(ingredient);
			$scope.ingredients2.push({ name: name, amount: quantity + eval(fraction), measure: measure });
			$scope.search = null;
			$scope.selection = null;
		}
		else{
			expect(warnning.getText()).toContain('quanity or fraction must be specifed, or specife both');
		}
	};
	$scope.addStep = function (step) {
		log('Adding step ' + step);
		step = step.trim();
		if(step){
			step = i +'. '+ step;
			$scope.steps.push(step);
			$scope.recipeStep = '';
			i++;
		}
		else{
			expect(warnning.getText()).toContain('step cannot be empty');
		}
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



app.controller('DashboardController', function ($scope, UserService, RecipeService, $http, $q) {
	var recipeService = RecipeService;
	var userService = UserService;
	$scope.currentUser = userService.getUser();
	var data = function () {
		console.log("start view");
		console.log("loading the dashboard for " + userService.getUser());
		recipeService.viewDash(userService.getUser())
		.then(
				function (response) {
					console.log(response);
					$scope.recipes = response.data;
					console.log(response.data);
					recipeService.setRecipes(response.data);
					console.log("The last");
					console.log(recipeService.getRecipes());
					return response;

				}, function (error) {
					console.log("error")
					console.log(error);
					//$scope.output = error;
				});
	}();

	var favoriteRecipe = function(recipe){
		recipeService.favoriteRecipe(recipe, userService.getUser());
		
	}
	var addComment = function(recipe){
		
		//ToDo: add add & view comments to recipe service
		recipeService.addComment(recipe, userService.getUser());
		
	}
	var viewComments = function(recipe){
		recipeService.viewComments(recipe);
		
	}
});


app.controller('SearchRecipeController', function ($scope, UserService, RecipeService, $http, $q) {

})

function logError(error) {
	log(error.status + ' error, ' + error.statusText);
}

function log(message) {
	console.log('apitest.js -- ' + message);
}