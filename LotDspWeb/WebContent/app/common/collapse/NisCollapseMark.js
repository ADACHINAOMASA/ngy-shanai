(function () {

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisCollapseMark = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/collapse/collapse_mark.html',
			scope: {
				model:'='
			},
			replace: true
    	};
    };

    nisCollapseMark.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisCollapseMark', nisCollapseMark);
    } catch(e) {
    	console.error(e);
    }

}());