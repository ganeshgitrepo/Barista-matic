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
    };
});

app.controller('LogoutCtrl', function($scope, $location, userAuthService) {
    userAuthService.logout();
    $location.path('/login').replace();
});

app.controller('DrinkCtrl', function($scope, drinkService) {
	drinkService.getDrinks(function (response){
	    console.log(response);
	    $scope.drinks = response;
	});

	$scope.buyDrink = function(drink){
		$scope.selection = drink;
		console.log($scope.selection);
	};
});

app.controller('IngredientCtrl', function($scope,ingredientService) {
	ingredientService.getIngredients(function(response){
		console.log(response);
		$scope.ingredients = response;
	});

	$scope.restockIngredient = function(ingredient, amount, response) {
		$scope.selection = ingredient;
		console.log($scope.selection.inventory);
	};
});

app.controller('NavCtrl', function($scope, $location, userService) {
	$scope.$watch(function showNav() {
		$scope.isLoggedIn = userService.getUserDetails().isLoggedIn;
		$scope.username = userService.getUserDetails().username;
		if (userService.getUserDetails().role == "CUSTOMER") {
			$scope.isCustomer = true;
			$scope.isAdmin = false;
		} else {
			$scope.isAdmin = true;
			$scope.isCustomer = false;
		}
	});
});