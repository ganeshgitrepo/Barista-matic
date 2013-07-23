app.controller('LoginCtrl',  function($scope, $location, userAuthService, userService) {

    $scope.$watch(function redirectUser() {
        var role = userService.getUserDetails().role;
        if (role == "CUSTOMER") {
            $location.path('/drink').replace();
        } else if (role == "ADMINISTRATOR") {
            $location.path('/ingredient').replace();
        }
    });

    $scope.login = function(user) {
        if ($scope.user == undefined) {
            return;
        }
        userAuthService.login($scope.user.username, $scope.user.password);
    }
});

app.controller('NavCtrl', function($scope, $location, userService) {
    $scope.$watch(function showNav() {
        $scope.isLoggedIn = userService.getUserDetails().isLoggedIn;
        $scope.username = userService.getUserDetails().username;
    });
});

app.controller('LogoutCtrl', function($scope, $location, userAuthService) {
    userAuthService.logout();
    $location.path('/login').replace();
});

app.controller('DrinkCtrl', function($scope, drinkService) {

});