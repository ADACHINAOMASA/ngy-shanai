(function () {

    var injectParams = ['$scope', '$uibModalInstance', 'param'];

    var InfoModalController = function ($scope, $uibModalInstance, param) {
    	$scope.param = param;
    	$scope.ok = function () {
    		$uibModalInstance.close(true);
		};
    };

    InfoModalController.$inject = injectParams;

    angular.module('app')
        .controller('InfoModalController', InfoModalController);

}());
