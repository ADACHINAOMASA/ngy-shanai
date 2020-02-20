/**
 * @author 
 */
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = 'HosoShiyoshoEtsuranEigyoPrintController';

    // 必要な依存を列挙
    var injectParams = [
        '$scope', '$state', '$interval', '$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService', 'uiGridConstants'
        , 'NisGridUtil', 'AlertService', 'ModalService', 'ModelService', 'AlertLogService'
        , 'input'
    ];

    // 引数は依存の内容と一致する
    var newController = function (
        $scope, $state, $interval, $httpParamSerializer, $window, $q, $timeout, i18nService, uiGridConstants
        , NisGridUtil, AlertService, ModalService, ModelService, AlertLogService
        , input
        
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		
		//ログ情報
		$scope.alertLogs = AlertLogService.logs();
    	$scope.alertLogsClear = AlertLogService.clear;
    	    	
        var el = angular.element('#printImage');
        el.removeAttr("width");
        el.removeAttr("height");
        el.css('top', '0px');
        el.css('left', '0px');
            	 
    	// 初期情報
		$scope.input = input || {};
					
		// アクション定義	
        $scope.action = {};
        // Util系のアクション定義	   
        $scope.util = {}; 
        // チェック系のアクション定義
        $scope.validate = {}; 
                
    }; //End newController

    newController.$inject = injectParams;
    angular.module(moduleName).controller(controllerName, newController);
    
}());
