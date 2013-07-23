app.controller('LoginCtrl',  function($scope, $location, userAuthService, userService) {
    // controller setup: check if the user is logged in
    isLoggedIn();

    function isLoggedIn() {
        if (userService.getUserDetails().isLoggedIn) {
            console.log("User is currently logged in. Redirecting..");
            return true;
        } else {
            console.log("User is not logged in. Displaying login page.");
            return false;
        }
    }

    function redirectUser() {
        var role = userService.getUserDetails().role;
        if (role == "CUSTOMER") {
            $location.path('/drink').replace();
        } else if (role == "ADMINISTRATOR") {
            $location.path('/ingredient').replace();
        }
    }

    $scope.login = function(user) {
        if ($scope.user == undefined) {
            return;
        }
        userAuthService.login($scope.user.username, $scope.user.password);
        if (isLoggedIn()) {
            redirectUser();
        }
    }
});

app.controller('NavCtrl', function($scope, $location, userService) {
    $scope.username = userService.getUserDetails().username;

    $scope.$watch(function showNav() {
        $scope.isLoggedIn = userService.getUserDetails().isLoggedIn;
    });
});

app.controller('DrinkCtrl', function($scope, drinkService) {

});