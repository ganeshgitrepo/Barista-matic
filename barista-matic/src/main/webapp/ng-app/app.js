var app = angular.module('barista-matic', ['ngResource']);

// configure the routes and associate each view with a controller
app.config(function ($routeProvider) {
    $routeProvider
        .when('/login',
            {
                controller:     'LoginCtrl',
                templateUrl:    'partials/login.html'
            })
        .when('/drink',
            {
                controller:     'DrinkCtrl',
                templateUrl:    'partials/drink.html'
            })
        .when('/ingredient',
            {
                controller:     'IngredientCtrl',
                templateUrl:    'partials/ingredient.html'
            })
        .when('/report',
            {
                controller:     'ReportCtrl',
                templateUrl:    'partials/drink.html'
            })
        .otherwise({redirectTo: '/login' });
});