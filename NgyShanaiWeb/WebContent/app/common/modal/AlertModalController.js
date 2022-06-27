(function () {

    var injectParams = ['$scope', '$uibModalInstance', 'param'];

    var AlertModalController = function ($scope, $uibModalInstance, param) {
    	$scope.param = param;

    	$scope.ok = function () {
    		$uibModalInstance.close(true);
		};
    };

    AlertModalController.$inject = injectParams;

    angular.module('app')
        .controller('AlertModalController', AlertModalController);

}());
