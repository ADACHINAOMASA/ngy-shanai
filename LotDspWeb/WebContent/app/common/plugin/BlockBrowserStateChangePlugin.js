/**
 * 使い方
 * stateにisHomeStatePassRequiredプロパティを追加する
 * trueならチェックを行う、falseなら無視
 * デフォはtrue
 * uncheckStateに追加しても無視
 * こちらはlogin等共通であるだろう画面に使用する事
 */
(function () {

    var module = angular.module('app');

    module.factory('BlockBrowserStateChangeService', [function(){
    	var passHomeState = false;

    	function pass() {
    		passHomeState = true;
    	}

    	function isPass() {
    		return passHomeState;
    	}

    	return {
    		pass : pass,
    		isPass : isPass
    	};
    }]);

    // 必要な依存を列挙
    var injectParams = ['$window', '$rootScope', '$state', 'BlockBrowserStateChangeService', 'AlertService'];

    // 引数は依存の内容と一致する
    var plugin = function ($window, $rootScope, $state, BlockBrowserStateChangeService, AlertService) {
        var homeState = 'home';
        var uncheckStates = ['login'];

        console.log('run');
    	$rootScope.$on('$stateChangeStartSuccess', function(e, toState, toParams, fromState, fromParams) {
     		if (toState.name === homeState) {
    			BlockBrowserStateChangeService.pass();
    		}
    		else {
    			if (uncheckStates.indexOf(toState.name) >= 0) {
    				return;
    			}
    			var check = toState.isHomeStatePassRequired === undefined ? true : toState.isHomeStatePassRequired;
    			if (check && !BlockBrowserStateChangeService.isPass()) {
    				AlertService.addDanger('ブラウザの戻る操作、F5等のリロード操作は禁止されています。ホーム画面へ戻ります。');
        			$state.go('home');
        		}
    		}
		});

//    	$window.addEventListener('popstate', function(event){
//
//    	});
    };

    plugin.$inject = injectParams;

    try {
    	module.run(plugin);
    } catch(e) {
    	console.error(e);
    }

}());