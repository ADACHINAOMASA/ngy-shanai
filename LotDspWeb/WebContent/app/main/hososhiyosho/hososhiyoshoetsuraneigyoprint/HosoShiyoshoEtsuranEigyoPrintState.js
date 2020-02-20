/**
 * @author 
 */
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    // 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider) {
        
    	var base = 'hososhiyosho/eigyo';
    	 
        var controller = 'HosoShiyoshoEtsuranEigyoPrintController';
        
        var templateUrl = 'app/main/hososhiyosho/hososhiyoshoetsuraneigyoprint/HosoShiyoshoEtsuranEigyoPrint.html';

        $stateProvider
            .state(base + '/print', {
                url: '/' + base + '/print/:imageUrl' 
                , controller: controller
                , templateUrl: templateUrl
                , resolve: {
                    input : function ($stateParams, $q,$base64) {      
                    	var input = {};
                    	input.imgSrc = $base64.decode($stateParams.imageUrl);
                    	return input;
                    }
                }               
            })
            
            ;
    }; //End newState

    newState.$inject = injectParams;
    angular.module(moduleName).config(newState);
    
}());
