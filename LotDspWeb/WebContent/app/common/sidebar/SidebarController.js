(function () {

    var injectParams = ['$scope', '$timeout'];

    var SidebarController = function ($scope, $timeout) {
    	$scope.open = false;

    	$scope.openFunc = function() {
    		if ($scope.open || angular.isDefined($scope.openWait)) {
    			return;
    		}
    		$scope.openWait = $timeout(function(){
    			$scope.open = true;
    			$scope.openWait = undefined;
    		}, 250);
    	};
    	$scope.closeFunc = function() {
    		if (angular.isDefined($scope.openWait)) {
    			$timeout.cancel($scope.openWait);
    			$scope.openWait = undefined;
    		}
    		$scope.open = false;
    	};
    };

    SidebarController.$inject = injectParams;

    angular.module('app')
        .controller('SidebarController', SidebarController);

}());
