app.controller('LoginCtrl', function($scope) {

});
/*
LoginCtrl = function($scope, $resource, Base64, $http) {
    // Use this method for Angular 1.0.x.
    // Logs into a page protected by basic authentication.  Grabs
    // username and password from $scope, which would likely be bound to
    // "text" and "password" <input> tags, respectively.
    scope.login = function login() {
    // modify the Authorization header to send the username & password
    $http.defaults.headers.common.Authorization = 'Basic ' +
    Base64.encode($scope.username + ':' + $scope.password);
    // get the Resource object.
    $scope.res = $resource('/api/');
    // need to actually execute the request; do whatever with this
    $scope.res.get();
    // restore old defaults
    $http.defaults.headers.common.Authorization = 'Basic ';
    };
}*/