var app = angular.module('Barista-matic', ['ngResource']);

// configure the routes and associate each view with a controller
app.config(function ($routeProvider) {
    $routeProvider
        .when('/#login',
            {
                controller:     'LoginCtrl',
                templateUrl:    '/ng-app/partials/login.html'
            })
        .when('/drink',
            {
                controller:     'DrinkCtrl',
                templateUrl:    'ng-app/partials/drink.html'
            })
        .when('/ingredient',
            {
                controller:     'IngredientCtrl',
                templateUrl:    'ng-app/partials/ingredient.html'
            })
        .when('/report',
            {
                controller:     'ReportCtrl',
                templateUrl:    'ng-app/partials/drink.html'
            })
        .otherwise({redirectTo: '/#login' });
});