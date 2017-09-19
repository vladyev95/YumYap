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