/**
 * @author 
 */
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = 'HosoShiyoshoEtsuranGenbaTopController';

    // 必要な依存を列挙
    var injectParams = [
        '$scope', '$state', '$interval', '$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService', 'uiGridConstants'
        , 'NisGridUtil', 'AlertService', 'ModalService', 'ModelService', 'AlertLogService'
        , 'input'
        , 'HosoShiyoshoService'
    ];

    // 引数は依存の内容と一致する
    var newController = function (
        $scope, $state, $interval, $httpParamSerializer, $window, $q, $timeout, i18nService, uiGridConstants
        , NisGridUtil, AlertService, ModalService, ModelService, AlertLogService
        , input
        , HosoShiyoshoService
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		
		//ログ情報
		$scope.alertLogs = AlertLogService.logs();
    	$scope.alertLogsClear = AlertLogService.clear;
    	
    	// 初期情報
		$scope.input = input || {};
				
		// アクション定義	
        $scope.action = {
        	openGenba:function(){
        		
        		HosoShiyoshoService.getGenbaWindowOptions().then(function(data){
             		
            		var defaultWidth = data.width || 1000;
            		
            		var defaultHeight = data.height || 700;
            		        		
            		var w = data.left;
            		var h = data.top;
            		
            		if(data.top==0 && data.left==0){
            	   		w = ( $window.screen.width - defaultWidth ) / 2;
                		h = ( $window.screen.height - defaultHeight ) / 2;
            		}
            		        		
            		var url = $state.href('hososhiyosho/genba/etsuran',{data:''});
            		
            		var options = 'fullscreen='+data.fullscreen+',toolbar='+data.toolbar+',location='+data.location+',menubar='+data.menubar+',scrollbars='+data.scrollbars+',resizable='+data.resizable+',top='+h+',left='+w+',width='+defaultWidth+',height='+defaultHeight;
            		
            		//console.log(options);
            		
            		$window.open(url,'_blank',options);
            		
        		});
   
        	}	
        };
        
        $scope.action.openGenba();
                
    }; //End newController

    newController.$inject = injectParams;
    angular.module(moduleName).controller(controllerName, newController);
    
}());
