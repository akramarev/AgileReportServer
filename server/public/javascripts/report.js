angular.module('ar', ['ui.bootstrap', 'btford.markdown', 'ngAnimate', 'ngRoute'])
    .config(function($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl : globalUrls.assets.views + 'reports.html',
                controller  : 'reportsController'
            })
            .when('/view/:id', {
                templateUrl : globalUrls.assets.views +'edit-report.html',
                controller  : 'reportController'
            })
            .when('/new', {
                templateUrl : globalUrls.assets.views +'edit-report.html',
                controller  : 'newReportController'
            })
    })
    .factory('reportFactory', function($http) {
        var factory = {};

        factory.reportAction = {
            add: 'add',
            edit: 'edit',
            view: 'view'
        };

        factory.reportStatus = {
                draft: 'Draft',
                completed: 'Completed'
        };

        factory.getOneAsync = function(id) {
            var url = globalUrls.api.report + id;
            return $http.get(url);
        };

        factory.getManyAsync = function() {
            return $http.get(globalUrls.api.reports);
        };

        factory.createAsync = function(report) {
            return $http.post(globalUrls.api.reports, report);
        }

        factory.changeAsync = function(report) {
            return $http.put(globalUrls.api.report + report.id, report);
        }

        return factory;
    })
    .factory('reportFormatFactory', function($q, $http) {
        var factory = {};

        factory.getReportStub = function() {
            return {
                status: 'Draft',
                dateCreatedUtc: new Date(),
                dateUpdatedUtc: new Date(),
                dateCompletedUtc: new Date(),
                body: []
            };
        };

        factory.getOneAsync = function() {
            return factory.getManyAsync().then(function(response) {
                if (response.data.length > 0)
                {
                    return response.data[0];
                }
            });

            return factory.getManyAsync().success(function(data) {
                return data[0];
            });
        };

        factory.getManyAsync = function() {
            return $http.get(globalUrls.api.report_formats);
        };

        return factory;
    })
    .controller('reportsController', function($scope, reportFactory) {
        reportFactory.getManyAsync().success(function(data) {
            $scope.reports = data;
        });
    })
    .controller('reportController', function($scope, $routeParams, $location, reportFactory) {
        reportFactory.getOneAsync($routeParams.id).success(function(data) {
            $scope.report = data;

            $scope.actionMode = $scope.report.status == reportFactory.reportStatus.completed
                ? reportFactory.reportAction.view
                : reportFactory.reportAction.edit;
        });

        $scope.completeReport = function() {
            $scope.report.status = reportFactory.reportStatus.completed;
            reportFactory.changeAsync($scope.report).success(function() {
                $location.path( "/" );
            });
        };

        $scope.saveDraftReport = function() {
            $scope.report.status = reportFactory.reportStatus.draft;
            reportFactory.changeAsync($scope.report).success(function() {
                $location.path( "/" );
            });
        };
    })
    .controller('newReportController', function($scope, $location, reportFactory, reportFormatFactory) {
        $scope.$watch('report.body', function(newValue) {
            // TODO add autosave
        }, true);

        if(typeof $scope.report == 'undefined') {
            $scope.report = reportFormatFactory.getReportStub();

            reportFormatFactory.getOneAsync().then(function(format) {
                $scope.report.body = format.body;
            });
        }

        $scope.actionMode = reportFactory.reportAction.add;

        $scope.completeReport = function() {
            $scope.report.status = reportFactory.reportStatus.completed;
            reportFactory.createAsync($scope.report).success(function() {
                $location.path( "/" );
            });
        };

        $scope.saveDraftReport = function() {
            $scope.report.status = reportFactory.reportStatus.draft;
            reportFactory.createAsync($scope.report).success(function() {
                $location.path( "/" );
            });
        };
    })