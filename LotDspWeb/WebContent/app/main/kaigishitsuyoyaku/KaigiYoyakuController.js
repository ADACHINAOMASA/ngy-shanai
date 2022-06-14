/**
 * _コントローラー
 */
(function () {

    // 必要な依存を列挙
    // 列挙の順番
    // 1行目.angular系
    // 2行目.外部プラグイン系
    // 3行目.NIS開発系のうち、共通のもの
    // 4行目.NIS開発系のうち、固有のもの
    // 5行目.呼び出し元やStateから渡される値
    var injectParams = ['$scope', '$rootScope', '$timeout', '$uibModal', '$state', 'AlertService', 'ModalService','ModelService', 'ScopeSetupService', 'KaigiYoyakuService', 'input'];

    // 引数は依存の内容と一致する
    var newController = function ($scope, $rootScope, $timeout, $uibModal, $state
									, AlertService, ModalService, ModelService, ScopeSetupService
									, KaigiYoyakuService
									, input) {
    	
    	ScopeSetupService.setupDefault($scope);
    	
    	$scope.kaigishitsus = input;
    	
    	$scope.hizuke = new Date();
    	
    	$scope.action = {
    		back() {
    			$state.go('home');
    		},
			searchyoyaku(hizuke) {
				hizuke.setHours(0);
            	hizuke.setMinutes(0);
            	hizuke.setSeconds(0);
            	hizuke.setMilliseconds(0);
				ModalService.loading(KaigiYoyakuService.search(hizuke)).then((result) => {
					$scope.kaigishitsus = result;
				})
			},
			openYoyakuModal(yoyakuInfo, kaigishitsuInfo, hizuke) {
				let modalInstance = $uibModal.open({
                    animation: true,
                    templateUrl: 'app/main/kaigishitsuyoyaku/yoyakuModal.html',
                    controller: 'YoyakuModalController',
                    backdrop: 'static',
                    size:'lg',
                    resolve: {
                        input: yoyakuInfo,
                        input2: kaigishitsuInfo,
                        input3: () => {
                        	hizuke.setHours(0);
                        	hizuke.setMinutes(0);
                        	hizuke.setSeconds(0);
                        	hizuke.setMilliseconds(0);
                        	return hizuke;
                        }
                    }
                });
			},
    	}
    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('KaigiYoyakuController', newController);

}());