//var app = angular.module('app', []);
//    
//    app.config(function($stateProvider, $urlRouterProvider){
// 	   console.log("init YumYap");
// 	   $stateProvider
// 	   		.state("dash",{
// 	   			url: "/dash",
// 	   			templateUrl: "templates/dahboard.html",
// 	   			controller: "DashboardCtrl as dash"
// 	   		})
//    });
//    
//    /*
//     * controller for the overall application
//     * holds whether we are loggedIn or not
//     */
////    app.service("UserService", function($http, $q){
//// 	   console.log("in userService");
//// 	 
//// 	   var service = this;
//// 	   
//// 	   service.user = {firstname: "",
//// 			   lastname = "",
//// 			   email = "",
//// 			   loggedIn = false};
//// 	   
//// 	   service.setUser = function(data){
//// 			service.user.email = data.email;
//// 			service.user.firstname = data.firstname;
//// 			service.user.lastname = data.lastname;
//// 			service.user.loggedin = data.loggedIn;
//// 	   }
//// 	   
//// 	   service.getLoggedIn = function(){
//// 		   console.log("In get logged in");
//// 		   console.log(service.user.loggedIn);
//// 		   return service.user.loggedIn;
//// 	   }
//// 	   
//// 	   service.loadDashboard = function(){
//// 		   console.log("in load dashboard");
//// 		   var promise;
//// 		   
//// 		   promise = $http.get('yum/user/dash', service.user)
//// 		   	.then(
//// 				   function(response){
//// 					   console.log(response);
//// 					   return response;
//// 				   },
//// 				   function(error){
//// 					   console.log("load dashboard has failed")
//// 					   return $q.reject(error);
//// 				   }
//// 		   );
//// 	   }
//// 	   
//// 	   service.getLoggedIn =function(){
//// 		   return service.user.loggedIn();
//// 	   }
//// 	   
//// 	   service.setRecipes=function(data){
//// 		   
//// 	   }
//// 	   
////    })
//    
//    app.controller('AppController', function() {
//        this.loggedIn = false;
//    });
//    
//    app.controller('ContentController', function($scope) {
//        $scope.loggedIn = UserService.getLoggedIn();
//    });
//    
////    app.controller('DashController', function() {
////    	console.log("in DashboardCtrl");
////		var dash = this;
//////		dash.recipes = UserService.loadDashboard();
////		dash.doLoad = function(){
////			var promise = UserService.loadDashboard();
////			
////			promise.then(
////					function(response){
////						console.log("in promise response");
////						UserService.setRecipes(response.data);
////					},
////					function(error){
////						console.log(error);
////					});
////		}
////    });
//    
//    /*
//     * controller for the Login/Register part of the app
//     * holds whether we are on the login page or the register page
//     */
//    app.controller('LoginController', function($scope) {
//    		console.log("in Login Controller");
//    		$scope.onlogin = UserService.getLoggedIn();
//    	});
//    
//    /*
//     * controller for the stuff once we've logged in
//     */
//    app.controller('ContentController', function() {
//        this.tab = 'Home';
//    });



(function() {
    let app = angular.module('app', []);
        
    
    /*
     * controller for the overall application
     * holds whether we are loggedIn or not
     */
    app.controller('AppController', function() {
        this.loggedIn = false;

    });
    
    
    /*
     * controller for the Login/Register part of the app
     * holds whether we are on the login page or the register page
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

})();
