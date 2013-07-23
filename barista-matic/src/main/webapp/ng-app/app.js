var app = angular.module('barista-matic', ['services']);
// configure the routes and associate each view with a controller
app.config(function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/login',
            {
                controller:     'LoginCtrl',
                templateUrl:    'ng-app/partials/login.html'
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
                templateUrl:    'ng-app/partials/report.html'
            })
        .otherwise({redirectTo: '/login' });
});