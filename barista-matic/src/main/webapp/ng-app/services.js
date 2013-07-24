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
                console.log("User is authenticated.");
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
service('drinkService', function($http) {
	this.getDrinks = function(callback) {
		$http.get('api/drink').success(function(data, status, headers, config) {
			console.log("Received drinks.");
			callback(data);
		})
	};

	this.buyDrink = function(drink, callback) {
	    $http.put('api/drink/' + drink.drinkId)
	    .success(function(data, status, headers, config) {
	        console.log("Purchased " + drink.drinkName);
	        return true;
	    }).error(function(data, status, headers, config) {
            console.log("Drink could not be purchased. Response: " + data);
            return false;
        });
	}
}).
factory('ingredientService', function($http) {
	var service = {};
	service.getIngredients = function(callback){
		$http.get('api/ingredient')
		.success(function(data, status, headers, config) {
			console.log("Received ingredients.");
			callback(data);
		})
	}
	
	service.restockIngredient = function(ingredient, amount, callback) {
		$http.put('api/ingredient/' + ingredient.ingredientId + "?amount=" + amount)
		.success(function(data, status, headers, config) {
			console.log(ingredient.ingredientName + " restocked.");
			callback(data);
		})
	}
	
	service.restockIngredients = function(ingredients, amount, callback) {
	    for (var i = 0; i < ingredients.length; i++) {
	        var ingredient = ingredients[i];
	        $http.put('api/ingredient/' + ingredient.ingredientId + "?amount=" + amount)
	        .success(function(data, status, headers, config) {
	            console.log(ingredient.ingredientName + " restocked.");
	            callback(data);
	        }
	    )}
	}
	
	return service;
});
