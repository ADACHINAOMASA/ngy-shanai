(function () {

    var injectParams = ['$scope', '$q', '$http','$uibModalInstance'
        , 'AlertService', 'i18nService', 'UserTorokuService'
    ];

    var newController = function ($scope, $q, $http,$uibModalInstance
        , AlertService, i18nService, UserTorokuService) {

        $scope.langs = i18nService.getAllLangs();
        i18nService.setCurrentLang('ja');

        $scope.action = {
            save: function () {
                if (!NisUtil.checkFormValid($scope.userTorokuForm)) {
                    AlertService.addWarning('入力内容にエラーが残っています。', 'validate', true);
                    return;
                } else if ($scope.input.password != $scope.input.passwordConfirm) {
                	AlertService.addDanger('入力されたパスワードと確認用パスワードが一致していません。');
                	return;
                }
                UserTorokuService.userToroku($scope.input).then(result => {
                	if (result) {
                		AlertService.addSuccess('登録完了しました。');
                		$uibModalInstance.dismiss();
                	} else {
                		AlertService.addDanger('登録失敗しました。');
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
        .controller('UserTorokuController', newController);

}());