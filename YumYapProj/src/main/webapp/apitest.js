
const EXCLUDED_GROUPS = ['Baby Foods', 'Fast Foods', 'Restaurant Foods'];


let app = angular.module('app', []);
app.controller('searchCtrl', function ($scope, $http) {
    'use strict';

    $scope.getFoodReport = function (selection) {

        log('selected ' + selection);
        if (selection) {
            $http.get('https://api.nal.usda.gov/ndb/reports/?ndbno=' + selection + '&format=xml&api_key=1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE')
                .then(function (responseXml) {

                    let foodReport = new DOMParser()
                        .parseFromString(responseXml.data, 'text/xml')
                        .firstChild.children.item(0);

                    if (foodReport.tagName === 'food') {
                        $('#foodName').text(foodReport.getAttribute('name'));

                        let nutrients = foodReport.children.item(0).children;
                        for (let i = 0; i < nutrients.length; i++) {
                            let nutrient = nutrients.item(i);

                            switch (+nutrient.getAttribute('nutrient_id')) {
                                case 208:
                                    $('#foodCalories').text(nutrient.getAttribute('value'));
                                    break;
                                case 204:
                                    $('#foodFat').text(nutrient.getAttribute('value'));
                                    break;
                                case 205:
                                    $('#foodCarbs').text(nutrient.getAttribute('value'));
                                    break;
                                case 203:
                                    $('#foodProtein').text(nutrient.getAttribute('value'));
                                    break;
                                default:
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

    $scope.searchFoods = function (searchTerm) {

        if (searchTerm) {
            $http.get('https://api.nal.usda.gov/ndb/search/?format=xml&q=' + searchTerm + '&ds=Standard%20Reference&max=20&api_key=1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE')
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

                        // Go through each result food item
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
