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

// Keeps track of the last timeout set
var timeout = -1;
// Time for a timeout message to expire in ms
var TIMEOUT_TIME = 4000;

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
	service.user = {};


	service.getUser = function () {
		console.log("in service.getUser");
		console.log(service.user);
		return service.user;
	};

	service.setUser = function (data) {
		console.log('setting user ');
		console.log(data);
		service.user.id = data.id;
		service.user.email = data.email;
		service.user.firstName = data.firstName;
		service.user.lastName = data.lastName;
		service.user.following = data.following;
		service.user.favoriteRecipes = data.favoriteRecipes;
		console.log(service.user);
	};
});


/* LoginService */
app.service('LoginService', function ($http, $q) {
	let service = this;

	service.attemptLogin = function (user) {
		console.log('attempting to login ');
		console.log(user);
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

	service.favoriteRecipe = function(recipe, user){
		console.log('favoriting a recipe in RecipeService: ');
		console.log(recipe);
		console.log(user);
		
		return $http.post('yum/user/favorite', recipe, user);
	};

	service.viewAuthor = function(recipe){
		console.log('viewing author of '+recipe);
		// TODO
	};

	service.recipes = {};

	service.setRecipes = function (data) {
		service.recipes = data;
	};
	service.getRecipes = function () {
		return service.recipes;
	};
	
	service.viewComments = function(recipe){
		return $http.post('yum/comments/show', recipe);
	};
	
	service.addComment = function(comment){
		return $http.post('yum/comments/create', comment);
	};

});


/* LoginController*/
app.controller('LoginController', function ($scope, $location, LoginService, UserService) {
	$scope.attemptLogin = function () {
		console.log('attempting to log in: ');
		console.log($scope.user);
		displaySubmitting("#loginMessage");
		LoginService.attemptLogin($scope.user)
			.then(
			function (response) {
				if (response.data) {
					console.log('attemptLogin() success response: ');
					console.log(response);
					console.log('data: ');
					console.log('sending '+ response.data);
					UserService.setUser(response.data);
					$location.path('/app');
				} else {
					console.log('reponse.data is null');
				}

			},
			function (error) {
				console.log('attemptLogin() error response: ');
				console.log(error);
				
				$("#loginMessage").text("Invalid email or password");
				displayMessage("#loginMessage", "alert alert-danger");
			}
			);
	};
});
/* LoginController */


/* RegisterController */
app.controller('RegisterController', function ($scope, $timeout, RegisterService) {
	$scope.attemptRegister = function () {
		var responseText = "#registerMessage";
		displaySubmitting(responseText);
		
		if ($scope.user.password !== $scope.user.password2) {
			console.log('passwords do not match');  
			$(responseText).text("Passwords do not match");
			displayMessage(responseText, "alert alert-danger");
		} else {
			console.log('attempting to register: ');
			console.log($scope.user);
			RegisterService.attemptRegister($scope.user)
				.then(
				function (response) {
					console.log('attemptRegister() success response: ');
					console.log(response);
					console.log('data: ');
					console.log(response.data);
					
					$(responseText).attr("class");
					$(responseText).text("Registration successful");
					$scope.user.firstName = $scope.user.lastName = $scope.user.email = $scope.user.password = $scope.user.password2 = '';
					displayMessage(responseText, "alert alert-success");
				},
				function (error) {
					console.log('attemptRegister() error response: ');
					console.log(error);
					$(responseText).attr("class");
					
					if (error.status == 406)
						$(responseText).text("A user with that email already exists");
					else $(responseText).text("something went wrong");
					displayMessage(responseText, "alert alert-danger");
				}
				);
		};
	};
});
/* RegisterController */

/**
 * @param response id of the HTML tag to display the message
 * @param setClass Sets the class of the response HTML tag
 * @param time Optional parameter to set timeout duration. Default is TIMEOUT_TIME
 */
function displayMessage(response, setClass, time, dontClear) {
	$(response).attr("class", setClass);
	
	if (!dontClear)
		clearTimeout(timeout);
	var timeout_time_ammt = TIMEOUT_TIME;
	if (time)
		timeout_time_ammt = time;
	
	// Display the message to the user and set a timeout to hide it
	$(response).attr("hidden", false);
	timeout = setTimeout(function() {
		$(response).attr("hidden", true);
	}, timeout_time_ammt);
}


/* ViewAuthorService */
app.service('ViewAuthorService', function ($http) {
	let service = this;

	console.log("Inside ViewAuthorService");

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

	service.getEmail = function () {
		return service.email;
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
		console.log("favoriteRecipe in viewAuthorController");
		recipeService.favoriteRecipe(recipe, userService.getUser());
	};
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
	
	$scope.viewAuthor = function(email) {
		console.log('viewAuthor(' + email + ')');
		$scope.tab = 'ViewAuthor';
		ViewAuthorService.setEmail(email);
	}
	
	$scope.switchToSearchRecipe = function(){
		log('switching to \'Create Recipe\' tab');
		$scope.tab = 'SearchRecipes';
	};

});
/* AppController */

app.controller('RecipeCtrl', function ($scope, $http, RecipeService, UserService) {
	'use strict';
	var i = 1;
	var recipeService = RecipeService;

	$scope.food = { 'name': '', 'nutrients': { 'calories': 0, 'fat': 0, 'carbs': 0, 'protein': 0 } };
	$scope.recipeName = null;
	$scope.recipeDescription = null;
	$scope.ingredients = [];
	$scope.ingredients2 = [];
	$scope.steps = [];
	$scope.recipeImage = [];
	$scope.recipeImageFile = [];

	$scope.uploadRecipeImage = function () {
		log('uploading recipe image');
		var responseText = "#uploadImageMessage";
		displaySubmitting(responseText);
		
		let image = document.getElementById('recipeImageFile').files[0];
		if (!image)
			return;

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
					$(responseText).attr("class");
					$(responseText).text("Image could not be saved");
					displayMessage(responseText, "alert alert-danger");
				} else {

					log('successfully uploaded image ' + imageKey);
					setTimeout(function () {
						$(responseText).attr("class");
						$(responseText).text("Image saved");
						displayMessage(responseText, "alert alert-success");
						
						$scope.recipeImageUri = BUCKET_PATH + encodeURIComponent(imageKey);
						$('#recipeImage').attr('src', BUCKET_PATH + encodeURIComponent(imageKey));
						log('uri= '+$scope.recipeImageUri);
					}, 4000);
				}
			}, function () {
				log('put image response !!!!!!!');
			});
		};
	};

	$scope.publishRecipe = function () {
		log('RecipeCtrl create recipe');
		displaySubmitting("#recipeResponse");

		let totalCalories = 0,
			totalFat = 0,
			totalCarbs = 0,
			totalProtein = 0;
		
		console.log($scope.ingredients);
		for (let i = 0; i < $scope.ingredients.length; i++) {
			let ingredient = $scope.ingredients[i];

			log('foodItem');
			log(ingredient.name);
			log(ingredient.nutrients);
			totalCalories += ingredient.nutrients.calories;
			totalFat += ingredient.nutrients.fat;
			totalCarbs += ingredient.nutrients.carbs;
			totalProtein += ingredient.nutrients.protein;
		}

		let recipe = {
			creator: UserService.getUser(),
			name: $scope.recipeName,
			description: $scope.recipeDescription,
			directions: $scope.steps,
			imageUrl: $scope.recipeImageUri,
			ingredients: $scope.ingredients2,
			calories: totalCalories,
			fat: totalFat,
			carbs: totalCarbs,
			protein: totalProtein
		};
		
		if(recipe.name && recipe.description && recipe.ingredients.length > 0 && recipe.directions.length > 0) {
			
			let responseText = "#recipeResponse";
			RecipeService.createRecipe(recipe)
				.then(
					function (response) {
						console.log(response);
						$scope.recipes = response.data.recipes;
						console.log(response.data.recipes);
						recipeService.setRecipes(response.data);
						console.log("The last");
						console.log(recipeService.getRecipes());
						return response;
						
						$(responseText).text("Recipe created");
						displayMessage(responseText, "alert alert-success");

					}, function (error) {
						console.log("error")
						console.log(error);
						
						if (error.status == 406)
							$(responseText).text("Please fill out every field");
						else if (error.status == 401)
							$(responseText).text("You must be logged in to create a recipe");
						else $(responseText).text("Something went wrong");
						displayMessage(responseText, "alert alert-danger");
					});
			$scope.recipeName = null;
			$scope.recipeDescription = null;
			$scope.ingredients = [];
			$scope.ingredients2 = [];
			$scope.steps = [];
			$scope.recipeImage = [];
			$scope.recipeImageFile = [];

			i = 1;
			
			$(responseText).text("Recipe successfully created");
			displayMessage(responseText, "alert alert-success");
		} else{
			$(responseText).text("name, discription, direction, and steps cannot be empty");
			displayMessage(responseText, "alert alert-danger");
		}

		var responseText = "#recipeMessage";
		RecipeService.createRecipe(recipe)
			.then(
				function (response) {
					console.log(response);
					$scope.recipes = response.data.recipes;
					console.log(response.data.recipes);
					recipeService.setRecipes(response.data);
					console.log("The last");
					console.log(recipeService.getRecipes());
					return response;
					
					$(responseText).text("Recipe created");
					displayMessage(responseText, "alert alert-success");

				}, function (error) {
					console.log("error")
					console.log(error);
					
					if (error.status == 406)
						$(responseText).text("Please fill out every field");
					else if (error.status == 401)
						$(responseText).text("You must be logged in to create a recipe");
					else $(responseText).text("Something went wrong");
					displayMessage(responseText, "alert alert-danger");
				});
	};

	$scope.addIngredient = function (quantity, fraction, measure, name) {
		log('Creating ingredient with ' + quantity + ', ' + fraction + ', ' + measure + ', ' + name);
		let ingredient = { name: name, quantity: quantity, fraction: fraction, measure: measure, nutrients: $scope.food.nutrients };
		$scope.ingredients.push(ingredient);
		$scope.ingredients2.push({ name: name, amount: quantity + eval(fraction), measure: measure });
		$scope.search = null;
		$scope.selection = null;
		$scope.quantity = null;
		$scope.fraction = null;
		$scope.measure = null;
	};

	$scope.addStep = function (step) {
		if (!step)
			return;
		log('Adding step ' + step);
		step = i + '. ' + step;
		$scope.steps.push(step);
		$scope.recipeStep = '';
		i++;
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
	
	$scope.addIngredient = function (quantity, fraction, measure, name) {
		log('Creating ingredient with ' + quantity + ', ' + fraction + ', ' + measure + ', ' + name);
		name = name.trim();
		let ingredient = { name: name, quantity: quantity, fraction: fraction, measure: measure, nutrients: $scope.food.nutrients };
		if(name && measure && (ingredient.quantity || ingredient.fraction)){
			$scope.ingredients.push(ingredient);
			$scope.ingredients2.push({ name: name, amount: quantity + eval(fraction), measure: measure });
			$scope.search = null;
			$scope.selection = null;
		} else{
			//expect(warnning.getText()).toContain('quanity or fraction must be specifed, or specife both');
		}
	};
	$scope.addStep = function (step) {
		if (!step)
			return;
		log('Adding step ' + step);
		step = step.trim();
		if(step){
			step = i +'. '+ step;
			$scope.steps.push(step);
			$scope.recipeStep = '';
			i++;
		} else{
			//expect(warnning.getText()).toContain('step cannot be empty');
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
							};
						};
					};
				} else {

					// Bad food ndbno
				};
			},
			function (error) {
				logError(error);
			});
		};
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
					};
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

app.service('CommentService', function ($http, $q){
	service = this;	
	
	service.comment = {
			id: '',
			commenter: {},
			recipe: {},
			content: ''
	}
	
	service.setComment = function(data){
		service.comment.content = data;
	}
	
	service.setUser = function(user){
		service.comment.commenter = user;
	}
	
	service.setRecipe = function(recipe){
		service.comment.recipe = recipe;
	}
	
	service.getComment = function(){
		return service.comment;
	}
	
	
});


app.controller('DashboardController', function ($scope, UserService, CommentService, RecipeService, $http, $q) {
	var recipeService = RecipeService;
	var userService = UserService;
	var commentService = CommentService;
	
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

	$scope.favoriteRecipe = function(recipe){
		console.log("favoriteRecipe in DasboardController");
		recipeService.favoriteRecipe(recipe, userService.getUser());
		
	}
	
	$scope.addComment = function(recipe){
		commentService.setComment($scope.commentContent);
		commentService.setUser(userService.getUser());
		commentService.setRecipe(recipe);
		recipeService.addComment(commentService.getComment()).then(
				function(response){
					console.log(response);
				},
				function(error){
					console.log(error);
				});
		
	}
	$scope.viewComments = function(recipe){
		recipeService.viewComments(recipe).then(
				function(response){
					
				},
				function(error){
					
				});
		
	}
	
});

	
	


app.service('SearchRecipeService', function($http, $q){
    var service = this;
    
    service.search = function(search){
    		return $http.post('yum/recipe/search', search);
    }
    
});
app.controller('SearchRecipesController', function($scope, RecipeService, SearchRecipeService, UserService, CommentService, $http, $q){
    searchService = SearchRecipeService;
    commentService = CommentService;
    userService = UserService;
    recipeService = RecipeService
//    $scope.foundRecipes = '';
//    $scope.showRecipes = false;
    
    
    $scope.search = function(){
    		searchService.search($scope.recipeName).then(
                function(response){
                		console.log(response.data);
                		$scope.foundRecipes = response.data;
                		if(response.data){
                		$scope.showRecipes = true;
                		}
                },
                function(error){
                    ;
                });
    }
    
    $scope.favoriteRecipe = function(recipe){
		console.log("favoriting a recipe in SearchRecipesController");
		var responseText = "#recipe-" + recipe.id;
		
    	recipeService.favoriteRecipe(recipe, userService.getUser()).then(
				function (response) {
					$(responseText).text("Recipe successfully favorited");
					
					displayMessage(responseText, "alert alert-success", undefined, true);
				},
				function (error) {
					
					if (error == 409)
						$(responseText).text("You have already favorited that recipe");
					else if (error == 406)
						$(responseText).text("User or Recipe are not valid");
					else $(responseText).text("Something went wrong");
					
					displayMessage(responseText, "alert alert-danger", undefined, true);
				}
				);
		
	}
    
    
    this.index;
    
    $scope.startComment = function(number){
    		console.log("providing text space for a comment");
    		$scope.addingComment = true;
    		this.index = number;
    }
    
    $scope.addComment = function(recipe){
		commentService.setComment($scope.index);
		console.log($scope.recipes.recipe.creator);
		console.log($scope.recipe.comment);
		commentService.setUser(userService.getUser());
		commentService.setRecipe(recipe);
		console.log('sending ');
		console.log(commentService.getComment());
		recipeService.addComment(commentService.getComment()).then(
				function(response){
					console.log(response);
				},
				function(error){
					console.log('error');
					console.log(error);
				});
		
	}
	$scope.viewComments = function(recipe){
		recipeService.viewComments(recipe).then(
				function(response){
					
				},
				function(error){
					
				});
		
	}
	
	$scope.viewAuthor = function(recipe){
		recipeService.viewAuthor(recipe);
	}
    
//    $scope.showRecipe = function(recipe){
//        recipe.show = true;  
//    }
})


/**
 * @param message id of the HTML tag to display the message
 */
function displaySubmitting(message) {
	$(message).text("Submitting...");
	
	displayMessage(message, "alert alert-warning", TIMEOUT_TIME*3);
}

function logError(error) {
	log(error.status + ' error, ' + error.statusText);
}

function log(message) {
	console.log('apitest.js -- ' + message);
}