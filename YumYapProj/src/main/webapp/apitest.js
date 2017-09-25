
const API_KEY = '1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE';
const EXCLUDED_GROUPS = ['Baby Foods', 'Fast Foods', 'Restaurant Foods'];
const TRACKED_NUTRIENTS = ['208', '204', '205', '203'];

let app = angular.module('app', []);
app.controller('searchCtrl', function ($scope, $http) {
    'use strict';
    $scope.food = { 'name': '', 'nutrients': { 'calories': 0, 'fat': 0, 'carbs': 0, 'protein': 0 } };
    $scope.measures = [];
    $scope.nutrientsByMeasure = {};

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
                        for (let i = 0; i < m.length; i++) {
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
            nutrients[attr] = (Math.round(quantity) + eval(fraction)) * nutrientsForMeasur[TRACKED_NUTRIENTS[i++]];
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

let bread = 'Bread, wheat, sprouted';
let flour = 'Wheat flours, bread, unenriched';
let eggBread = 'Bread, egg';

let rx = new RegExp(/egg|bread/i);

log(eggBread.search(rx));

function logError(error) {
    log(error.status + ' error, ' + error.statusText);
}

function log(message) {
    console.log('apitest.js -- ' + message);
}
