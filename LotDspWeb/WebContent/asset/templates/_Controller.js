/**
 * @author 
 */
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = '_Controller';

    // 必要な依存を列挙
    var injectParams = [
        '$scope', '$state', '$interval', '$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService', 'uiGridConstants'
        , 'NisGridUtil', 'AlertService', 'ModalService', 'ModelService', 'AlertLogService'
        , 'input', 'header', 'messages', 'SysConst', 'SharedObject'
    ];

    // 引数は依存の内容と一致する
    var newController = function (
        $scope, $state, $interval, $httpParamSerializer, $window, $q, $timeout, i18nService, uiGridConstants
        , NisGridUtil, AlertService, ModalService, ModelService, AlertLogService
        , input, header, messages, SysConst, SharedObject
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		
		//ログ情報
		$scope.alertLogs = AlertLogService.logs();
    	$scope.alertLogsClear = AlertLogService.clear;
    	
    	// 初期情報
    	$scope.header = header;
		$scope.messages = messages;
		$scope.input = input || {};
		
		var constName="XXX";
		$scope.title = SysConst[constName].toroku.title;
		$scope.input.torokuFrontid = SysConst[constName].toroku.id;
		$scope.input.koshinFrontid = $scope.input.torokuFrontid;

		// 初期化	---------------------------------------------------------------------------------------- ↓	
		
		//　カレンダーの初期化
		//AppUtil.initCalendar($scope);
		
		// コードにより名称の取得を初期化
		//AppUtil.initGetMeisho($scope,MeishoService);

		// アクション定義	------------------------------------------------------------------------------------ ↓		
        $scope.action = {
            back: function () {
                AlertService.clear();
                var backPath = SharedObject.transition.from;
                if (!NisUtil.hasValue(backPath)) {
                    backPath = 'home';
                } else {
                    backPath = SharedObject.transition.from + "/review";
                }
                $state.go(backPath);
            }
            , toPageTop: function () {
                $window.scrollTo(0, 0);
            }
        , }; //End action
        
        // Util系のアクション定義	-------------------------------------------------------------------------------- ↓	        
        $scope.util = {

        }; //End util
        
        // チェック系のアクション定義	---------------------------------------------------------------------------- ↓	
        $scope.validate = {
            isValidForm: function () {
                if(!$scope.validate.isValidKihonJoho()){
                	return false;
                }
                return true;
            }
            , isValidKihonJoho: function () {
                /*
                if (!NisUtil.checkFormValid($scope.XXXKihonForm)) {
                	AlertService.addWarning('基本情報の'+SysConst.message.inputError, 'validate', '警告');
                	return false;
                }
                */
                return true;
            }
            , toValidForm: function () {
                //$scope.XXXKihonForm.$dirty=false;
            }
            , isChanged: function () {
                /*
                if($scope.XXXKihonForm.$dirty){
                	return true;
                }
                */
                return false;
            }
            , isNotChanged: function () {
                return !$scope.validate.isChanged();
            }
        }; //End validate

        // 一覧系（モーダル）のアクション定義	------------------------------------------------------------------------ ↓	
        $scope.list = {
        		
        }// Edn list
        
    }; //End newController

    newController.$inject = injectParams;
    angular.module(moduleName).controller(controllerName, newController);
}());
