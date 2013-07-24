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
        this.login = function(username, password, callback) {
            var userPass = username + ':' + password;
            var base64 = window.btoa(unescape(encodeURIComponent(userPass)));
            var customHeaders = 'Basic ' + base64;
            $http.defaults.headers.common['Authorization'] = customHeaders;
            $http.post('api/user/auth').success(function(data, status, headers, config) {
                console.log("User is authenticated.");
                userService.setUserDetails(data.userId, username, customHeaders, data.role);
            }).error(function(data, status, headers, config) {
                console.log("We could not authenticate successfully.");
                callback("Wrong username or password.");
            });
        };

	this.logout = function() {
		userService.destroyUserDetails();
	}
}).
service('drinkService', function($http, $filter) {
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
	        callback("The cost of your order is " + $filter('currency')(drink.cost));
	    }).error(function(data, status, headers, config) {
            console.log("Drink could not be purchased. Response: " + data);
            callback("We're sorry, your order was not successful. Please try again later.");
        });
	}
}).
service('ingredientService', function($http) {
	this.getIngredients = function(callback){
		$http.get('api/ingredient')
		.success(function(data, status, headers, config) {
			console.log("Received ingredients.");
			callback(data);
		})
	}
	
	this.restockIngredient = function(ingredient, amount, callback) {
		$http.put('api/ingredient/' + ingredient.ingredientId + "?amount=" + amount)
		.success(function(data, status, headers, config) {
			console.log(ingredient.ingredientName + " restocked.");
			callback("Successfully restocked " + data.ingredientName
			    + ". Updated inventory is " + data.inventory);
		}).error(function(data, status, headers, config) {
		    console.log("Ingredient could not be restocked. Response: " + data);
		    callback("We're sorry. Your restock request could not be processed.");
		})
	}
	
	this.restockIngredients = function(ingredients, amount, callback) {
	    for (var i = 0; i < ingredients.length; i++) {
	        var ingredient = ingredients[i];
	        $http.put('api/ingredient/' + ingredient.ingredientId + "?amount=" + amount)
	        .success(function(data, status, headers, config) {
	            console.log(ingredient.ingredientName + " restocked.");
	            callback(data);
	        }
	    )}
	}
});
