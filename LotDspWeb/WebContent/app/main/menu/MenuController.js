(function() {
	// index.htmlのng-appで定義している名前と同じ。
	// <html lang="jp" ng-app="app">
	var moduleName = 'app';

	var controllerName = 'MenuController';

	var userInfoStorage = 'ngStorage-userInfoStorage';
	var lotInfoStorage = 'ngStorage-lotDspInfo';

	// 必要な依存を列挙
	var injectParams = [ '$scope', '$state', '$interval', '$document','$base64',
			'$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService',
			'uiGridConstants', 'AlertService', 'ModalService',
			'ModelService', 'AlertLogService','CommonService','MenuService'
			, 'input', 'authenticationInfo','UserInfoStorage', 'params'

			];

	// 引数は依存の内容と一致する
	var newController = function($scope, $state, $interval, $document,$base64,
			$httpParamSerializer, $window, $q, $timeout, i18nService,
			uiGridConstants, AlertService, ModalService,
			ModelService, AlertLogService ,CommonService, MenuService
			, input, authenticationInfo,UserInfoStorage, params
		) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');

		// 初期情報
		$scope.input = input || {};
		$scope.authenticationInfo = authenticationInfo || {};
		$scope.params = params || {};

		$scope.input.mode = $scope.authenticationInfo.mode;
		$scope.input.ltno = $scope.authenticationInfo.ltno;
		$scope.input.knno = $scope.authenticationInfo.knno;

		// 認証情報
		$scope.input.error = $scope.authenticationInfo.error || false;
		$scope.input.errorMessage = $scope.authenticationInfo.errorMessage ||  '';
		$scope.input.version = $scope.authenticationInfo.version || '';

		//認証時にエラーが発生していた場合はエラーページに遷移する。
		if ($scope.input.error == true) {
			$state.go('ErrorPage');
			return;
		}

		//認証情報を変数にセット
		$scope.auth = authenticationInfo || {};
		UserInfoStorage.memory.saveBaseWork($scope.input);

		// アクション定義
		$scope.action = {
			init : function() {
				// 必要なパラメーターだけ先にローカルストレージへ保存する
				MenuService.memoryParam.saveBaseWorkParam({site: $scope.params.site});
				if (Object.keys($scope.params).length > 0) {
					// パラメータをinput設定
					$scope.input.searchLtno = $scope.params.ltno;
					$scope.input.searchKnno = $scope.params.knno;
					$scope.input.paramCyno = $scope.params.cyno;
					$scope.input.paramLinkkey = $scope.params.linkkey;
					$scope.input.tab = $scope.params.tab;
					$scope.input.site = $scope.params.site;
					// ローカルストレージへ保存
					ModalService.showProcessing(MenuService.searchMenuParams($scope.input),{message:'処理中・・・'}).then(function(data) {
		    			if(data.errorFlg){
		    				$("#messageArea").css("color", "red");
		    				$("#messageArea").text(data.message);
		    			}else{
		    				if (data.nextGamen != null) {
			    				//返却されたデータをローカルストレージに保存する
			    				MenuService.memory.saveBaseWork(data);
			    				$("#messageArea").css("color", "white");
			    				$("#messageArea").text("");
			    				$state.go(data.nextGamen);
		    				}
		    			}
					});
				}
			},
			search : function() {
				ModalService.showProcessing(MenuService.searchMenu($scope.input),{message:'処理中・・・'}).then(function(data) {
    				if(data.errorFlg){
    					$("#messageArea").css("color", "red");
    					$("#messageArea").text(data.message);
    				}else{
    					//返却されたデータをローカルストレージに保存する
    					MenuService.memory.saveBaseWork(data);
    					$("#messageArea").css("color", "white");
    					$("#messageArea").text("");
    					$state.go(data.nextGamen);
    				}
				});
			},
			close : function() {
				$window.close();
			},
			toPageTop : function() {
				$window.scrollTo(0, 0);
			}
		};
		
		// 初期処理
		$scope.action.init();
	}; // End newController

	newController.$inject = injectParams;
	angular.module(moduleName).controller(controllerName, newController);

}());
