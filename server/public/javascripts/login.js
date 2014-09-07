angular.module('ar', ['ui.bootstrap', 'ui.bootstrap.showErrors', 'ngRoute', 'ngCookies'])
    .factory('tokenFactory', function($q, $http) {
        var factory = {};

        factory.createAsync = function(auth) {
            return $http.post(globalUrls.api.tokens, auth);
        }

        return factory;
    })
    .controller('loginController', function($scope, $window, $cookieStore, tokenFactory) {
        $scope.login = function() {
            $scope.$broadcast('show-errors-check-validity');
            if (!$scope.loginForm.$valid) { return; }

            var auth = {
                email: $scope.email,
                password: $scope.password
            };

            tokenFactory.createAsync(auth).success(function(data) {
                localStorage.auth = angular.toJson(data);

                // $window.location.href = '/report/';
            });
        };
    })