var services = angular.module('services', ['ngResource', 'ngCookies']).
    factory('userService', function($cookieStore, $http) {
        init();

        var service = {
            auth : '',
            userId: '',
            username: '',
            role: '',
            isLoggedIn: false
        };

        // if both cookies are stored, retrieve user
        function init() {
            var auth = $cookieStore.get('auth');
            if (auth != null) {
                console.log("Credentials saved. Retrieving user..");
                $http.defaults.headers.common['Authorization'] = auth;
                $http.post('api/user/auth').success(function(data, status, headers, config) {
                    service.userId = data.userId;
                    service.username = data.userName;
                    service.role = data.role;
                    service.isLoggedIn = true;
                });
            }
        }

        service.getUserDetails = function() {
            return service;
        }

        service.setUserDetails = function(userId, username, auth, role) {
            service.userId = userId;
            service.username = username;
            service.auth = auth;
            $cookieStore.put("auth", auth);
            service.role = role;
            service.isLoggedIn = true;
        }

        service.destroyUserDetails = function() {
            $cookieStore.remove("auth");
            service = {};
        }

        return service;
    }).
    service('userAuthService', function($http, userService) {
        this.login = function(username, password) {
            var userPass = username + ':' + password;
            var base64 = window.btoa(unescape(encodeURIComponent(userPass)));
            var customHeaders = 'Basic ' + base64;
            $http.defaults.headers.common['Authorization'] = customHeaders;
            $http.post('api/user/auth').success(function(data, status, headers, config) {
                console.log("We are authenticated!");
                userService.setUserDetails(data.userId, username, customHeaders, data.role);
                return true;
            }).error(function(data, status, headers, config) {
                console.log("We could not authenticate successfully.");
                return false;
            });
        };

        this.logout = function() {
            userService.destroyUserDetails();
        }
    }).
    factory('drinkService', function($http) {
        service = {};
        service.getDrinks = function(callback) {
            $http.get('api/drink').success(function(data, status, headers, config) {
                console.log("Received drinks.");
                callback(data);
            })
        };
        return service;
    });