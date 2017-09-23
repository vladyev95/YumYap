'use strict';

let app = angular.module('app', []);

let excludeGroups = ['Baby Foods', 'Fast Foods', 'Restaurant Foods'];

app.controller('searchCtrl', function ($scope, $http) {
    'use strict';
    log('in apitest controller');

    $scope.$watch('search', function (newVal, oldVal) {

        $http.get('https://api.nal.usda.gov/ndb/search/?format=xml&q=' + newVal + '&ds=Standard%20Reference&sort=n&max=25&api_key=1dvNA9ailiF7xHYu1V2ogW374YZpjcMS1NsvOySE')
            .then(function (responseXml) {
                //log('API response: '+responseXml.data);

                let results = new DOMParser()
                    .parseFromString(responseXml.data, 'text/xml')
                    .firstChild.children;

                if (results) {
                    $scope.foodItems = [];
                    for (let i = 0; i < results.length; i++) {
                        let group = results.item(i).getElementsByTagName('group')[0].innerHTML;
                        if (excludeGroups.indexOf(group) !== -1) {
                            continue;
                        }

                        let name = results.item(i).getElementsByTagName('name')[0].innerHTML;
                        let ndbno = results.item(i).getElementsByTagName('ndbno')[0].innerHTML;

                        let item = { 'name':name, 'ndbno':ndbno, 'group':group };
                        log('#' + ndbno + ', ' + name);
                        $scope.foodItems.push(item);
                    }
                }

            }, function (error) {
                log('API status ' + error.status + ': ' + error.statusText);
            });
    });
});

let bread = 'Bread, wheat, sprouted';
let flour = 'Wheat flours, bread, unenriched';
let eggBread = 'Bread, egg';

let rx = new RegExp(/egg|bread/i);

log(eggBread.search(rx));

function log(message) {
    console.log('apitest.js -- ' + message);
}
