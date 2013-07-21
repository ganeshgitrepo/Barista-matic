angular.module('barista-matic', []).config(['$routeProvider', function($routeProvider) {
    $routeProvider.
        when('/login', {templateUrl: 'partials/login.html', controller: LoginCtrl
        });
}])