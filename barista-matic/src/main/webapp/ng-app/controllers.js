app.controller('NavCtrl', function($scope, $location, userService) {
	$scope.$watch(function showNav() {
		$scope.isLoggedIn = userService.getUserDetails().isLoggedIn;
		$scope.username = userService.getUserDetails().username;
	});
});

app.controller('LoginCtrl', function($scope, $location, userAuthService,
		userService) {
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

app.controller('LogoutCtrl', function($scope, $location, userAuthService) {
	userAuthService.logout();
	$location.path('/login').replace();
});

app.controller('DrinkCtrl', function($scope) {
	$scope.drinks = [ {
		"name" : "Coffee",
		"cost" : "$3.85"
	}, {
		"name" : "Latte",
		"cost" : "$2.85"
	}, {
		"name" : "Americano",
		"cost" : "$3.85"
	} ];

	$scope.buyDrink = function(drink) {
		$scope.selection = drink;
		console.log($scope.selection);
	};
});

app.controller('IngredientCtrl', function($scope) {
	
	$scope.inventory = [ {
		"ingredient" : "Coffee",
		"currentUnits" : 10
	}, {
		"ingredient" : "Sugar",
		"currentUnits" : 10
	}, {
		"ingredient" : "Espresso",
		"currentUnits" : 10
	} ];

	$scope.restock = function(ingredient) {
		$scope.selection = ingredient;
		$scope.selection.currentUnits=ingredient.currentUnits;
		console.log($scope.selection.currentUnits);
	};
});