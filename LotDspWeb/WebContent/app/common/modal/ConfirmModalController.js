(function () {

    var injectParams = ['$scope', '$uibModalInstance','message', 'processName'];

    var ConfirmModalController = function ($scope, $uibModalInstance, message, processName) {
    	$scope.message = message;
    	$scope.processName = processName;

    	$scope.ok = function () {
    		$uibModalInstance.close(true);
		};
		$scope.cancel = function () {
			$uibModalInstance.dismiss('cancel');
		};
    };

    ConfirmModalController.$inject = injectParams;

    angular.module('app')
        .controller('ConfirmModalController', ConfirmModalController);

}());
