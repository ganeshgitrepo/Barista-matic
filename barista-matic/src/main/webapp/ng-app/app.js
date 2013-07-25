var app = angular.module('barista-matic', ['services']);
// configure the routes and associate each view with a controller
app.config(function ($routeProvider, $locationProvider) {
    $routeProvider
        .when('/login',
            {
                controller:     'LoginCtrl',
                templateUrl:    'ng-app/partials/login.html'
            })
        .when('/logout',
            {
                controller:     'LogoutCtrl',
                templateUrl:    'ng-app/partials/login.html'
            })
        .when('/register',
            {
                controller:     'RegisterCtrl',
                templateUrl:    'ng-app/partials/register.html'
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

        //$locationProvider.html5Mode(true);
});