
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    // 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider) {
    	//直接別画面へ遷移する際にIndex画面を表示しないためにbaseとtemplateUrlを二つに分けて対応。
    	var base = '/lotdsp/menu/';
    	var basedummy = '/lotdsp/menu';

        var controller = 'MenuController';

        var templateUrl = 'app/main/menu/Menu.html';
        var templateUrlDummy = 'app/main/menu/MenuDummy.html';

        $stateProvider
        	.state(base, {
        		url: base
        		, controller: controller
        		, templateUrl: templateUrl
        		, resolve: {
        			rule : function(RulesService, $q) {
        				var delay = $q.defer();
        				RulesService.load('AuthenticationInfo','CommonInfo').then(function(data){
        					delay.resolve(data);
        				});
        				return delay.promise;
        			}
        	    	,authenticationInfo: function (AuthenticationService, $q) {
        	    		var delay = $q.defer();
        	    		AuthenticationService.auth().then(function(data){
        	    			delay.resolve(data);
        	    		});
        	    		return delay.promise;
        	    	}
        	    	,input: function (ModalService, ModelService, $q, $stateParams) {
        	    		var delay = $q.defer();
        	    		ModalService.simpleLoading(ModelService.newModel('CommonInfo')).then(function (data) {
        	    			delay.resolve(data);
        	    		});
        	    		return delay.promise;
        	    	}
        		}
        	})
            ;

    }; //End newState

    newState.$inject = injectParams;
    angular.module(moduleName).config(newState);

}());
