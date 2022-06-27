(function () {

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisWarningSign = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/icon/warning_sign.html',
			replace: true
    	};
    };

    nisWarningSign.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisWarningSign', nisWarningSign);
    } catch(e) {
    	console.error(e);
    }

}());