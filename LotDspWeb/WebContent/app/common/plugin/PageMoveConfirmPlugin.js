(function () {

    // 必要な依存を列挙
    var injectParams = ['$window'];

    // 引数は依存の内容と一致する
    var plugin = function ($window) {
    	$window.addEventListener("beforeunload", function (event) {
    		event.preventDefault();
		});
    };

    plugin.$inject = injectParams;

    try {
    	angular.module('app').run(plugin);
    } catch(e) {
    	console.error(e);
    }

}());