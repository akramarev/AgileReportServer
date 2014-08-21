angular.module('ar', ['ui.bootstrap', 'ui.bootstrap.showErrors', 'ngRoute'])
    .controller('loginController', function($scope, $window) {
        $scope.login = function() {
            $scope.$broadcast('show-errors-check-validity');
            if (!$scope.loginForm.$valid) { return; }

            $window.location.href = '/report/';
        };
    })