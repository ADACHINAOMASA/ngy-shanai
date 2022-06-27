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
    	
    	$scope.oneDayAfter = new Date($scope.hizuke.getFullYear(), $scope.hizuke.getMonth(), $scope.hizuke.getDate() + 1);
    	$scope.twoDaysAfter = new Date($scope.hizuke.getFullYear(), $scope.hizuke.getMonth(), $scope.hizuke.getDate() + 2);
    	$scope.threeDaysAfter = new Date($scope.hizuke.getFullYear(), $scope.hizuke.getMonth(), $scope.hizuke.getDate() + 3);
    	$scope.fourDaysAfter = new Date($scope.hizuke.getFullYear(), $scope.hizuke.getMonth(), $scope.hizuke.getDate() + 4);
    	$scope.fiveDaysAfter = new Date($scope.hizuke.getFullYear(), $scope.hizuke.getMonth(), $scope.hizuke.getDate() + 5);
    	$scope.sixDaysAfter = new Date($scope.hizuke.getFullYear(), $scope.hizuke.getMonth(), $scope.hizuke.getDate() + 6);
    	
    	$scope.action = {
    		back() {
    			$state.go('home');
    		},
			searchyoyaku(hizuke) {
    			
    			$scope.oneDayAfter.setFullYear($scope.hizuke.getFullYear());
    			$scope.twoDaysAfter.setFullYear($scope.hizuke.getFullYear());
    			$scope.threeDaysAfter.setFullYear($scope.hizuke.getFullYear());
    			$scope.fourDaysAfter.setFullYear($scope.hizuke.getFullYear());
    			$scope.fiveDaysAfter.setFullYear($scope.hizuke.getFullYear());
    			$scope.sixDaysAfter.setFullYear($scope.hizuke.getFullYear());
    			
    			$scope.oneDayAfter.setMonth($scope.hizuke.getMonth());
    			$scope.twoDaysAfter.setMonth($scope.hizuke.getMonth());
    			$scope.threeDaysAfter.setMonth($scope.hizuke.getMonth());
    			$scope.fourDaysAfter.setMonth($scope.hizuke.getMonth());
    			$scope.fiveDaysAfter.setMonth($scope.hizuke.getMonth());
    			$scope.sixDaysAfter.setMonth($scope.hizuke.getMonth());
    			
    			$scope.oneDayAfter.setDate($scope.hizuke.getDate() + 1);
    			$scope.twoDaysAfter.setDate($scope.hizuke.getDate() + 2);
    			$scope.threeDaysAfter.setDate($scope.hizuke.getDate() + 3);
    			$scope.fourDaysAfter.setDate($scope.hizuke.getDate() + 4);
    			$scope.fiveDaysAfter.setDate($scope.hizuke.getDate() + 5);
    			$scope.sixDaysAfter.setDate($scope.hizuke.getDate() + 6);
    			
				hizuke.setHours(0);
            	hizuke.setMinutes(0);
            	hizuke.setSeconds(0);
            	hizuke.setMilliseconds(0);
				ModalService.loading(KaigiYoyakuService.search(hizuke)).then((result) => {
					$scope.kaigishitsus = result;
				})
			},
			dateChange(amount) {
				$scope.hizuke.setDate($scope.hizuke.getDate() + amount);
				$scope.action.searchyoyaku($scope.hizuke);
			},
			toToday() {
				$scope.hizuke = new Date();
				$scope.action.searchyoyaku($scope.hizuke);
			},
			getHizuke(motoHizuke, amount) {
				motoHizuke = motoHizuke.setDate(motoHizuke.getdate() + amount)
				return motoHizuke;
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
                }).result.then(result => {
                	$scope.action.searchyoyaku($scope.hizuke);
                })
			},
    	}
    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('KaigiYoyakuController', newController);

}());