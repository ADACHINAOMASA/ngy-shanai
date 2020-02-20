(function () {

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisRequiredMark = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/required/required_mark.html',
			replace: true
    	};
    };

    nisRequiredMark.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisRequiredMark', nisRequiredMark);
    } catch(e) {
    	console.error(e);
    }

}());