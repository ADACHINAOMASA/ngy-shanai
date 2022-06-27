/**
 *
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['$timeout'];

    // 引数は依存の内容と一致する
    var nisInitFocus = function ($timeout) {
    	// ディレクティブ内容
    	return {
    		restrict: 'A',
    		scope: {
    			nisInitFocusIf:'='
    		},
			link: function(scope, element, attrs, ctrl) {
				if (angular.isDefined(scope.nisInitFocusIf) && !scope.nisInitFocusIf){
					return;
				}
				$timeout(function(){
					element.focus();
				});
			}
    	};
    };

    nisInitFocus.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisInitFocus', nisInitFocus);
    } catch(e) {
    	console.error(e);
    }

}());