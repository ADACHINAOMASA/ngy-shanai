
(function () {

    var injectParams = ['$scope', '$interval', 'LoginService','UserService'];

    var newController = function ($scope, $interval, LoginService,UserService) {
    	$scope.logout = LoginService.logout;
    	$scope.alertLogs = AlertLogService.logs();
    	$scope.alertLogsClear = AlertLogService.clear;
    	
        $scope.isSessionKeep = LoginService.isSessionKeep();
        $scope.$watch('isSessionKeep', function (newValue) {
            if (newValue) {
                LoginService.toSessionKeep();
            } else {
                LoginService.toNotSessionKeep();
            }
        });
    	
    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('HeaderController', newController);

}());
