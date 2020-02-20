(function () {

    var injectParams = ['$scope', '$q', '$http','$uibModalInstance'
        , 'AlertService', 'i18nService','PasswordHenkoService', 'input'
    ];

    var newController = function ($scope, $q, $http,$uibModalInstance
        , AlertService, i18nService,PasswordHenkoService, input) {

        $scope.langs = i18nService.getAllLangs();
        i18nService.setCurrentLang('ja');

        $scope.input = input;

        $scope.action = {
            save: function () {
                if (!NisUtil.checkFormValid($scope.passwordHenkoTorokuForm)) {
                    AlertService.addWarning('入力内容にエラーが残っています。', 'validate', true);
                    return;
                }
                PasswordHenkoService.changePassword($scope.input).then(function (data) {
                	$scope.input=data;
                	if(data.hasError===false){
                        $uibModalInstance.close($scope.input);
                	}
                });
            }
            , back: function () {
            	$uibModalInstance.dismiss();
            }
            , clear: function () {
            	//$scope.input = {};
            	
            	var userId=$scope.input.userId;
                $scope.input = {};
            	$scope.input.userId=userId;
            }
        };

    };

    newController.$inject = injectParams;
    angular.module('app')
        .controller('PasswordHenkoController', newController);

}());