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
        
        var controller = 'HosoShiyoshoEtsuranEigyoController';
        
        var templateUrl = 'app/main/hososhiyosho/hososhiyoshoetsuraneigyo/HosoShiyoshoEtsuranEigyo.html';

        $stateProvider
            .state(base + '/etsuran' , {
                url: '/' + base  + '/etsuran'
                , controller: controller
                , templateUrl: templateUrl
                , resolve: {
					rule : function(RulesService, $q) {
                        var delay = $q.defer();
                        RulesService.load('HosoShiyoshoHeaderInfo','HosoShiyoshoInfo').then(function(data){
                        	delay.resolve(data);
						});
						return delay.promise;
					}
                     ,input: function (ModalService, ModelService,$stateParams, $q) {
                        var delay = $q.defer();
                        ModalService.simpleLoading(ModelService.newModel('HosoShiyoshoHeaderInfo')).then(function (data) {
                        	delay.resolve(data);
                        });
                        return delay.promise;
                    }
                }
            })
            .state(base + '/view' , {
                url: '/' + base + '/view/:no' 
                , controller: controller
                , templateUrl: templateUrl
                , resolve: {
					rule : function(RulesService, $q) {
                        var delay = $q.defer();
                        RulesService.load('HosoShiyoshoHeaderInfo','HosoShiyoshoInfo').then(function(data){
                        	delay.resolve(data);
						});
						return delay.promise;
					}
                    ,input: function (ModalService, ModelService,$stateParams, $q) {
                        var delay = $q.defer();
                        ModalService.simpleLoading(ModelService.newModel('HosoShiyoshoHeaderInfo')).then(function (data) {
                        	data.pkgSpecNo = $stateParams.no;
                        	delay.resolve(data);
                        });
                        return delay.promise;
                    }
                }
            })            
            ;

    }; //End newState

    newState.$inject = injectParams;
    angular.module(moduleName)
        .config(newState);
}());
