/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:これでいいのかは要検討

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var newDirective = function () {
    	// ディレクティブ内容
		return function(scope, elm, attr) {
			elm.bind('scroll', function() {
				scope.$apply(attr.nisScroll);
			});
		};
    };

    newDirective.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisScroll', newDirective);
    } catch(e) {
    	console.error(e);
    }

}());