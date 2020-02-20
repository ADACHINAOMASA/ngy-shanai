
(function () {

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var libraryInDirectiveTest = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'A',
			link: function(scope, elm, attrs) {

			}
    	};
    };

    libraryInDirectiveTest.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('libraryInDirectiveTest', libraryInDirectiveTest);
    } catch(e) {
    	console.error(e);
    }

}());