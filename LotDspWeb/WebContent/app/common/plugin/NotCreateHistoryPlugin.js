(function () {

    // 必要な依存を列挙
    var injectParams = ['$rootScope', '$location'];

    // 引数は依存の内容と一致する
    var plugin = function ($rootScope, $location) {
    	$rootScope.$on('$stateChangeSuccess', function(e, toState, toParams, fromState, fromParams){
	    	$location.replace();
	    });
    };

    plugin.$inject = injectParams;

    try {
    	angular.module('app').run(plugin);
    } catch(e) {
    	console.error(e);
    }

}());