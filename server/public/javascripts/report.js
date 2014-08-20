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
                controller  : 'reportController'
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
            completed: 'Completed',
            archived: 'Archived'
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

        factory.deleteAsync = function(report) {
            return $http.delete(globalUrls.api.report + report.id);
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
    .factory('intervalFactory', function () {
        var factory = {
            isSet: false,
            interval: 5000,
            f: function() {}
        };

        factory.set = function()
        {
            factory.isSet = true;
        };

        factory.reset = function()
        {
            factory.isSet = false;
        };

        factory.init = function(f, interval) {
            factory.interval = typeof interval !== 'undefined' ? interval : factory.interval;
            factory.f = f;

            window.setInterval(function() {
                if (factory.isSet) {
                    factory.f();
                    factory.reset();
                }
            }, factory.interval);
        };

        return factory;
    })
    .controller('reportsController', function($scope, reportFactory) {
        reportFactory.getManyAsync().success(function(data) {
            $scope.reports = data;
        });
    })
    .controller('reportController', function($scope, $routeParams, $location, reportFactory, reportFormatFactory, intervalFactory) {
        var init = function() {
            if (typeof $routeParams.id  == 'undefined') {
                $scope.report = reportFormatFactory.getReportStub();

                reportFormatFactory.getOneAsync()
                    .then(function(format) {
                        $scope.report.body = format.body;
                        $scope.report.status = reportFactory.reportStatus.draft;

                        reportFactory.createAsync($scope.report).success(function(data) {
                            $scope.report = data;
                            setReportAction();

                            intervalFactory.init(autoSave);
                            $scope.$watch('report.body', intervalFactory.set, true);
                        });
                    })
            }
            else
            {
                reportFactory.getOneAsync($routeParams.id).success(function(data) {
                    $scope.report = data;
                    setReportAction();

                    intervalFactory.init(autoSave);
                    $scope.$watch('report.body', intervalFactory.set, true);
                });
            }
        };

        var autoSave = function()
        {
            if($scope.report.status == reportFactory.reportStatus.draft)
            {
                reportFactory.changeAsync($scope.report);
            }
        };

        var setReportAction = function()
        {
            $scope.actionMode = $scope.report.status == reportFactory.reportStatus.completed
                ? reportFactory.reportAction.view
                : reportFactory.reportAction.edit;
        };

        init();

        $scope.completeReport = function() {
            $scope.report.status = reportFactory.reportStatus.completed;
            reportFactory.changeAsync($scope.report).success(function() {
                $location.path( "/" );
            });
        };

        $scope.deleteDraftReport = function() {
            reportFactory.deleteAsync($scope.report).success(function() {
                $location.path( "/" );
            });
        };

        $scope.archiveReport = function() {
            $scope.report.status = reportFactory.reportStatus.archived;
            reportFactory.changeAsync($scope.report).success(function() {
                $location.path( "/" );
            });
        };
    })