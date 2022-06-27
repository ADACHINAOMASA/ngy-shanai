/**
 * _コントローラー
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider) {
        // 必要なステートを記述
        // 例
        $stateProvider
            .state('kaigiyoyaku',{
            	title: '会議室予約システム',
                url: '/kaigiyoyaku',
                controller: 'KaigiYoyakuController',
                templateUrl: 'app/main/kaigishitsuyoyaku/kaigiyoyaku.html',
                resolve: {
                	input: (KaigiYoyakuService) => {
                		let d = new Date();
                    	d.setHours(0);
                    	d.setMinutes(0);
                    	d.setSeconds(0);
                    	d.setMilliseconds(0);
                		return KaigiYoyakuService.search(d).then((result) => {
                			return result;
                			
        			})},
        			rule : function(RulesService) {
						return RulesService.loadAll();
					}
                }
            })
//            .state('kaigishitsumente',{
//            	title: '会議室メンテナンス',
//                url: '/kaigishitsumente',
//                controller: 'KaigishitsuMenteController',
//                templateUrl: 'app/main/kaigiyoyaku/kaigishitsumente.html',
//                resolve: {
//                	input: (KaigiYoyakuService) => {
//                		let d = new Date();
//                    	d.setHours(0);
//                    	d.setMinutes(0);
//                    	d.setSeconds(0);
//                    	d.setMilliseconds(0);
//                		return KaigiYoyakuService.search(d).then((result) => {
//                			return result;
//        			})},
//                },
//                permission:  ['K001'],
//            });
    };

    newState.$inject = injectParams;

    angular.module('app')
        .config(newState);

}());