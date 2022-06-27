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
	    	.state('home',{
				url: '/home',
				controller: 'HomeController',
				templateUrl: 'app/main/home/home.html',
				resolve: {
					// TODO:ホーム遷移時に全てのルールをロードするか？検証
					// TODO:重い場合は各画面で使うものをロードする
					//		ホーム遷移時に全てだと開発時に更新が出来なくて面倒なので、とりあえず両方でロード
					rule : function(RulesService) {
						return RulesService.loadAll();
					}
				},
				isLoginRequired: true,
				title: 'Home'
			});
    };

    newState.$inject = injectParams;

    angular.module('app')
        .config(newState);

}());