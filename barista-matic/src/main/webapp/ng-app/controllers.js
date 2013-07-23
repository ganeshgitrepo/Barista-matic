app.controller('NavCtrl', function($scope) {

});

app.controller('LoginCtrl',  function($scope, $http, $location, userService) {
    // check if the user is logged in
    isLoggedIn();

    function redirectUser() {
        var role = userService.getUserDetails().role;
        if (role == "CUSTOMER") {
            $location.path('/drink').replace();
        } else if (role == "ADMINISTRATOR") {
            $location.path('/ingredient').replace();
        }
    }

    function isLoggedIn() {
        if (userService.getUserDetails().isLoggedIn) {
            console.log("User is currently logged in. Redirecting..");
            redirectUser();
        }
        console.log("User is not logged in. Displaying login page.");
    }

    $scope.loginMessage = "Wrong username / password.";
    $scope.showLoginMessage = function() {
        return $scope.loginMessage;
    }
    $scope.login = function(user) {
        if ($scope.user == undefined) {
            return;
        }
        var userPass = $scope.user.username + ':' + $scope.user.password;
        var base64 = window.btoa(unescape(encodeURIComponent(userPass)));
        $http.defaults.headers.common['Authorization'] = 'Basic ' + base64;
        $http({
            method : 'GET',
            url : 'api/user/role'
        }).success(function(data, status, headers, config) {
            console.log("We are authenticated!");
            // store authorization token
            var authToken = headers('Auth-Token');
            // store user details in userService
            userService.setUserDetails($scope.user.username, data[0].authority)
            // remove base64 username / password
            $http.defaults.headers.common['Auth-Token'] = authToken;
            $http.defaults.headers.common['Authorization'] = ' ';
            redirectUser();
        }).error(function(data, status, headers, config) {
            console.log("We could not authenticate successfully.");
            showLoginMessage();
        });
    }
});