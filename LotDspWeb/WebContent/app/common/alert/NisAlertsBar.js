// <div nis-alerts-bar alerts="alertService.alerts"></div>
(function () {

    // 必要な依存を列挙
    var injectParams = ['AlertService'];

    // 引数は依存の内容と一致する
    var nisAlertsBar = function (AlertService) {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/alert/alertsbar.html',
			controller: ['$scope', 'AlertService', function($scope, AlertService){
				$scope.alerts = AlertService.getAlerts();
				$scope.closeAlert = function(index) {
					$scope.alerts.splice(index, 1);
				};
				$scope.clearAlerts = function(index) {
					AlertService.clear();
				};
				$scope.isClearButtonShow = function(index) {
					return _.size($scope.alerts) >= 2;
				};
			}]
		};
    };

    nisAlertsBar.$inject = injectParams;

    angular.module('app')
        .directive('nisAlertsBar', nisAlertsBar);

}());