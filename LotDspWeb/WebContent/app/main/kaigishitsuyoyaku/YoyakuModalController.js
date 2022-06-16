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
    var injectParams = ['$scope', '$uibModalInstance', '$rootScope'
				        , 'ModalService', 'AlertService', 'ScopeSetupService', 'KaigiYoyakuService'
				        , 'input', 'input2', 'input3'];

    // 引数は依存の内容と一致する
    var newController = function ($scope, $uibModalInstance, $rootScope
						            , ModalService, AlertService, ScopeSetupService, KaigiYoyakuService
						            , input, input2, input3) {
        
    	ScopeSetupService.setupDefault($scope);

        $scope.yoyakuInfo = input;
        
        $scope.kaigishitsuInfo = input2;
        
        $scope.yoyakuInfo.yoyakuDate = input3;
        
        $scope.yoyakuStart = input.yoyakuBlockStart;
        $scope.yoyakuEnd = input.yoyakuBlockEnd;
        
        // 毎週予約機能を使うか
        $scope.maishuCheck = false;
        
        //毎週予約終了日
        $scope.maishuEnd = null;
        
        $scope.yoyakuBlockStarts = [{value: '1', name: '8:00'},
									{value: '2', name: '8:30'},
									{value: '3', name: '9:00'},
									{value: '4', name: '9:30'},
									{value: '5', name: '10:00'},
									{value: '6', name: '10:30'},
									{value: '7', name: '11:00'},
									{value: '8', name: '11:30'},
									{value: '9', name: '12:00'},
									{value: '10', name: '12:30'},
									{value: '11', name: '13:00'},
									{value: '12', name: '13:30'},
									{value: '13', name: '14:00'},
									{value: '14', name: '14:30'},
									{value: '15', name: '15:00'},
									{value: '16', name: '15:30'},
									{value: '17', name: '16:00'},
									{value: '18', name: '16:30'},
									{value: '19', name: '17:00'},
									{value: '20', name: '17:30'},
									{value: '21', name: '18:00'},
									{value: '22', name: '18:30'},
									{value: '23', name: '19:00'},
									{value: '24', name: '19:30'},];
        
        $scope.yoyakuBlockEnds = [{value: '1', name: '8:30'},
									{value: '2', name: '9:00'},
									{value: '3', name: '9:30'},
									{value: '4', name: '10:00'},
									{value: '5', name: '10:30'},
									{value: '6', name: '11:00'},
									{value: '7', name: '11:30'},
									{value: '8', name: '12:00'},
									{value: '9', name: '12:30'},
									{value: '10', name: '13:00'},
									{value: '11', name: '13:30'},
									{value: '12', name: '14:00'},
									{value: '13', name: '14:30'},
									{value: '14', name: '15:00'},
									{value: '15', name: '15:30'},
									{value: '16', name: '16:00'},
									{value: '17', name: '16:30'},
									{value: '18', name: '17:00'},
									{value: '19', name: '17:30'},
									{value: '20', name: '18:00'},
									{value: '21', name: '18:30'},
									{value: '22', name: '19:00'},
									{value: '23', name: '19:30'},
									{value: '24', name: '20:00'},];
        
        $scope.importants = [{value: '0', name: '重要'},
							{value: '1', name: '普通'},
							{value: '2', name: '場所変更可'},
							{value: '3', name: '時間変更可'}];
        
        $scope.action = {
        		cancel() {
        			if ($scope.yoyakuInfo.isreserved != '1') {
        				$scope.yoyakuInfo.yoyakushaName = null;
        				$scope.yoyakuInfo.tel = null;
        				$scope.yoyakuInfo.importance = null;
        				$scope.yoyakuInfo.biko = null;
        			}
        			$uibModalInstance.dismiss('cancel');
        		},
        		save(yoyakuInfo) {
        			ModalService.openConfirm('予約情報を登録します。よろしいですか？').then(() => {
        				// 以下チェック機能
        				if (yoyakuInfo.yoyakushaName == null || yoyakuInfo.tel == null || yoyakuInfo.importance == null || yoyakuInfo.yoyakuBlockStart == null || yoyakuInfo.yoyakuBlockEnd == null) {
        					AlertService.addDanger('未入力の項目があります。');
        					return;
        				} else if ($scope.maishuCheck && $scope.maishuEnd == null) {
        					AlertService.addDanger('未入力の項目があります。');
        					return;
        				}
        				if (Number(yoyakuInfo.yoyakuBlockStart) > Number(yoyakuInfo.yoyakuBlockEnd)) {
        					AlertService.addDanger('予約終了時間は開始時間より後に設定してください。');
        					return;
        				}
        				if ($scope.maishuCheck && $scope.maishuEnd < new Date()) {
        					AlertService.addDanger('過去の日付を指定することはできません。');
        					return;
        				}
        				if ($scope.maishuCheck && $scope.maishuEnd - yoyakuInfo.yoyakuDate >= 31536000000) {
        					AlertService.addDanger('毎週予約の期間は1年以内にしてください。');
        					return;
        				}
        				
	        			yoyakuInfo.yoyakushaCd = $rootScope.userProfile.userId;
	        			
	        			// maishuCheckがチェックされていなければその日だけ予約 チェックされていれば定期的に予約
	        			if ($scope.maishuCheck != true) {
		        			ModalService.loading(KaigiYoyakuService.save(yoyakuInfo)).then((result) => {
		        				if(result == true) {
		        					AlertService.addSuccess('登録完了しました。');
		        					yoyakuInfo.yoyakushaCd = $rootScope.userProfile.userId;
		        					$uibModalInstance.close('cancel');
		        				} else {
		        					AlertService.addDanger('登録に失敗しました。');
		        				}
		        			})
	        			} else {
	        				ModalService.loading(KaigiYoyakuService.maishusave(yoyakuInfo, $scope.maishuEnd)).then((result) => {
	        					if(result == true) {
		        					AlertService.addSuccess('登録完了しました。');
		        					yoyakuInfo.yoyakushaCd = $rootScope.userProfile.userId;
		        					$uibModalInstance.close('cancel');
		        				} else {
		        					AlertService.addDanger('登録に失敗しました。');
		        				}
	        				})
	        			}
        			})
        		},
        		update(yoyakuInfo) {
        			console.log(yoyakuInfo);
        		},
        		yoyakudelete(yoyakuInfo) {
        			// 毎週予約でない場合
        			if (yoyakuInfo.maishuYoyakuId == null || yoyakuInfo.maishuYoyakuId == "") {
	        			ModalService.openConfirm('予約情報を削除します。よろしいですか？').then(() => {
		        			ModalService.loading(KaigiYoyakuService.yoyakudelete(yoyakuInfo.kaigishitsuCd, yoyakuInfo.yoyakuDate, yoyakuInfo.yoyakuBlockStart)).then((result) => {
		        				if (result == true) {
		        					AlertService.addSuccess('削除完了しました。');
		        				} else {
		        					AlertService.addDanger('削除失敗しました。');
		        				}
		        				$uibModalInstance.close('cancel');
		        			})
	        			})
	        		// 毎週予約の場合
        			} else {
        				ModalService.openConfirm('この予約は毎週予約です。定期的な予約を全て削除しますか？').then(() => {
    	        			ModalService.loading(KaigiYoyakuService.maishuyoyakudelete(yoyakuInfo.kaigishitsuCd, yoyakuInfo.yoyakuDate, yoyakuInfo.yoyakuBlockStart, yoyakuInfo.maishuYoyakuId)).then((result) => {
    	        				AlertService.addSuccess('削除完了しました。');
    	        				$uibModalInstance.close('cancel');
    	        			})
    	        		// 定期的な予定の削除をrejectした場合
            			}, () => {
            				ModalService.openConfirm('指定した予約情報のみ削除します。よろしいですか？').then(() => {
    		        			ModalService.loading(KaigiYoyakuService.yoyakudelete(yoyakuInfo.kaigishitsuCd, yoyakuInfo.yoyakuDate, yoyakuInfo.yoyakuBlockStart)).then((result) => {
    		        				if (result == true) {
    		        					AlertService.addSuccess('削除完了しました。');
    		        				} else {
    		        					AlertService.addDanger('削除失敗しました。');
    		        				}
    		        				$uibModalInstance.close('cancel');
    		        			})
    	        			})
            			})
        			}
        		},
        }
    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('YoyakuModalController', newController);

}());