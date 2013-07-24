app.controller('LoginCtrl', function ($scope, $location, userAuthService, userService) {
    $scope.$watch(function redirectUser() {
        var role = userService.getUserDetails().role;
        if (role == "CUSTOMER") {
            $location.path('/drink').replace();
        } else if (role == "ADMINISTRATOR") {
            $location.path('/ingredient').replace();
        }
    });

    $scope.$watch(function displayLoginMsg() {
        
    });

    $scope.login = function (user) {
        if (typeof $scope.user === 'undefined') {
            return;
        }
            userAuthService.login($scope.user.username, $scope.user.password, function(response) {
                $scope.loginMsg = response;
            })
    };
});

app.controller('LogoutCtrl', function ($scope, $location, userAuthService) {
    userAuthService.logout();
    $location.path('/login').replace();
});

app.controller('DrinkCtrl', function ($scope, drinkService) {
    drinkService.getDrinks(function (response) {
        console.log(response);
        $scope.drinks = response;
    });

    $scope.buyDrink = function (drink) {
        if (typeof drink === 'undefined') {
            return;
        }

        drinkService.buyDrink(drink, function(response) {
            $scope.receipt = response;
        })
    };
});

app.controller('IngredientCtrl', function ($scope, ingredientService) {
        init();

        function init() {
            ingredientService.getIngredients(function (response) {
                console.log(response);
                $scope.ingredients = response;
            })
        }

    $scope.restockIngredient = function (ingredient, amount) {
        if (typeof amount === 'undefined') {
            return;
        }

        ingredientService.restockIngredient(ingredient, amount, function(response) {
            init();
            $scope.receipt = response;
        })
    };

    $scope.restockIngredients = function(ingredients, amount) {
        ingredientService.restockIngredients(ingredients, amount, function(response) {
            init();
        })
    };
});

app.controller('NavCtrl', function ($scope, $location, userService) {
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