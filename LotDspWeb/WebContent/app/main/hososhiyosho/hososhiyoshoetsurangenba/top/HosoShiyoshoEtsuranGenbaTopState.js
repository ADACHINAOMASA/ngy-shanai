

(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    // 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider) {
        
    	var base = 'hososhiyosho/genba';
    	 
        var controller = 'HosoShiyoshoEtsuranGenbaTopController';
        
        var templateUrl = 'app/main/hososhiyosho/hososhiyoshoetsurangenba/top/HosoShiyoshoEtsuranGenbaTop.html';

        $stateProvider
            .state(base + '/top', {
                url: '/' + base + '/top' 
                , controller: controller
                , templateUrl: templateUrl
                , resolve: {
                    input : function ($stateParams, $q) {      
                    	var input = {};
                    	return input;
                    }
                }               
            })
            ;
        
    }; //End newState

    newState.$inject = injectParams;
    angular.module(moduleName).config(newState);
    
}());
