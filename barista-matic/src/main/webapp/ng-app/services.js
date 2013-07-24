var services = angular.module('services', ['ngResource', 'ngCookies']).
    factory('userService', function($cookieStore) {
        var service = {
            username: getUsernameCookie(),
            role: getRoleCookie(),
            isLoggedIn: (getUsernameCookie() !== '')
        };

        service.getUserDetails = function() {
            return service;
        }

        service.setUserDetails = function(username, role, isLoggedIn) {
            service.username = username;
            $cookieStore.put("username", username);
            service.role = role;
            $cookieStore.put("role", role);
            service.isLoggedIn = isLoggedIn;
            $cookieStore.put
        };

        function getUsernameCookie() {
            var cookie = $cookieStore.get("username");
            return (cookie !== undefined) ? cookie : '';
        }

        function getRoleCookie() {
            var cookie = $cookieStore.get("role");
            return (cookie !== undefined) ? cookie : '';
        }

        return service;
    }).
    service('userAuthService', ['$http', 'userService', function($http, userService) {
        this.login = function(username, password) {
            var successful = false;
            var userPass = username + ':' + password;
            var base64 = window.btoa(unescape(encodeURIComponent(userPass)));
            $http.defaults.headers.common['Authorization'] = 'Basic ' + base64;
            $http.get('api/user/role').success(function(data, status, headers, config) {
                console.log("We are authenticated!");
                // store authorization token
                var authToken = headers('Auth-Token');
                // set HTTP headers
                $http.defaults.headers.common['Auth-Token'] = authToken;
                $http.defaults.headers.common['Authorization'] = ' ';
                userService.setUserDetails(username, data[0].authority, true);
            }).error(function(data, status, headers, config) {
                console.log("We could not authenticate successfully.");
            });
        };

        this.logout = function() {
            userService.setUserDetails(undefined, null, false);
        }
    }]).
    factory('drinkService', function($http) {
        var service = {};

        service.getDrinks = function() {
            $http.get('api/drink').success(function(data, status, headers, config) {
                console.log("Successfully received drinks.");
                console.log("Data: " + data);
            }).error(function(data, status, headers, config) {
            	console.log("we couldn't get drinks.");
            })
        }

        return service;
    });