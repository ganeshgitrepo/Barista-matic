app.controller('LoginCtrl', function($scope, $http) {
    $scope.login = function(user) {
        if ($scope.user == undefined) {
            return;
        }
        var userPass = $scope.user.username + ':' + $scope.user.password;
        var base64 = window.btoa(unescape(encodeURIComponent(userPass)));
        $http.defaults.headers.common['Authorization'] = 'Basic ' + base64;
        $http({
            method : 'GET',
            url : 'api/user/roles'
            }).success(function(data, status, headers, config) {
                console.log("We are authenticated!");
                var authToken = headers('Auth-Token');
                $http.defaults.headers.common['Auth-Token'] = authToken;
                $http.defaults.headers.common['Authorization'] = ' ';
            }).error(function(data, status, headers, config) {
                console.log("We could not authenticate successfully.");
            });
    }
});