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
			.state('login',{
				url: '/',
				controller: 'LoginController',
				templateUrl: 'app/main/login/login.html'
			});
    };

    newState.$inject = injectParams;

    angular.module('app').config(newState);

}());