(function() {
	// index.htmlのng-appで定義している名前と同じ。
	// <html lang="jp" ng-app="app">
	var moduleName = 'app';

	var controllerName = 'ErrorPageController';

	// 必要な依存を列挙
	var injectParams = [ '$scope', '$state', '$interval', '$document','$base64',
			'$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService',
			'uiGridConstants', 'AlertService', 'ModalService',
			'ModelService', 'AlertLogService','CommonService'
			];

	// 引数は依存の内容と一致する
	var newController = function($scope, $state, $interval, $document,$base64,
			$httpParamSerializer, $window, $q, $timeout, i18nService,
			uiGridConstants, AlertService, ModalService,
			ModelService, AlertLogService ,CommonService
		) {

		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');

		// アクション定義
		$scope.action = {
			close : function() {
				$window.close();
			}
		};

	}; // End newController

	newController.$inject = injectParams;
	angular.module(moduleName).controller(controllerName, newController);

}());
