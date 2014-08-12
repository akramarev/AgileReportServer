angular.module('ar', ['ui.bootstrap', 'btford.markdown', 'ngClipboard', 'ngAnimate', 'ngRoute'])
    .config(['ngClipProvider', function(ngClipProvider) {
        ngClipProvider.setPath("js/bower_components/zeroclipboard/dist/ZeroClipboard.swf");
    }])
    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : '../assets/views/reports.html',
                controller  : 'reportsController'
            })
            .when('/view', {
                templateUrl : '../assets/views/report.html',
                controller  : 'reportController'
            })
            .when('/new', {
                templateUrl : '../assets/views/new-report.html',
                controller  : 'newReportController'
            })
    })
    .controller('reportsController', function($scope) {

    })
    .controller('reportController', function($scope) {

    })
    .controller('newReportController', function($scope) {
        $scope.date = new Date();

        $scope.model = angular.fromJson(localStorage.model);
        if(typeof $scope.model == 'undefined')
        {
            $scope.model = {
                done: "1. [RED-123](https://jira.internal.syncplicity.com/browse/RED-123) Fixed. Please review.",
                inprogress: "* VPN connection problem _resolving_.",
                next: "* I'm going to call EMC support tomorrow, and ask to check my connection **again**.",
                reviewed: "* [RED-777](https://jira.internal.syncplicity.com/browse/RED-777) Looks good. Merged."
                    + "\n * [DB Master PR](https://github.com/syncp/syncp-database/pull/531)",
                questions: "```NSLog (@\"Some smart question should be here\");```"
            }
        }

        $scope.$watchCollection('model', function(newValue) {
            localStorage.model = angular.toJson(newValue);
        });

        $scope.sendReport = function() {
            var link = "mailto:manager@localhost"
                + "?subject=" + encodeURIComponent($('.report .rName').text())
                + "&body=";

            angular.forEach($('.setup .form-group'), function (value, key) {
                var v = $(value).find('input, textarea').val();
                if (v.length > 0)
                {
                    link += encodeURIComponent("#" + $(value).find('label').text() + "# \n ");
                    link += encodeURIComponent(v + " \n\n ");
                }
            });

            window.location.href = link;
        }

        $scope.getReportHtmlToCopy = function() {
            return $('.report').html();
        }

        $scope.copyReportClick = function() {
            alert('Copied');
        }
    })