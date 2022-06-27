(function () {

    var injectParams = ['$scope', '$uibModalInstance', '$window', 'input'];

    var newController = function ($scope, $uibModalInstance, $window, input) {

    	$scope.input = input;

    	$scope.action = {
    		ok : function() {
    			if (!NisUtil.checkFormValid($scope.oshiraseHenshuForm)) {
    				$window.alert('入力内容にエラーがあります。')
				    return;
    			}
    			$uibModalInstance.close($scope.input);
    		},
    		cancel : function() {
    			$uibModalInstance.dismiss('cancel');
    		}
    	};

    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('OshiraseHenshuController', newController);

}());
