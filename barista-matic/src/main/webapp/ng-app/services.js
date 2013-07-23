app.factory('userService', function($cookieStore) {
    var userDetails = {
        username: getUsernameCookie(),
        role: getRoleCookie(),
        isLoggedIn: (getUsernameCookie() == '')
    };

    userDetails.getUserDetails = function() {
        return userDetails;
    }

    userDetails.setUserDetails = function(username, role) {
        userDetails.username= username;
        $cookieStore.put("username", username);
        userDetails.role = role;
        $cookieStore.put("role", role);
        userDetails.isLoggedIn = true;
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

    return userDetails;
})