var app = angular.module('barista-matic', ['ngResource', 'ur.http.auth']);
// configure the routes and associate each view with a controller
app.config(function ($routeProvider, $httpProvider, requestQueueProvider) {
    $routeProvider
        .when('/login',
            {
                controller:     'LoginCtrl',
                templateUrl:    'ng-app/partials/login.html'
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
    requestQueueProvider.subscribeTo($httpProvider);
});