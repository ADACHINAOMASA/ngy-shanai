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
			var raw = elm[0];
			elm.bind('scroll', function() {
				if (raw.scrollTop + raw.offsetHeight >= raw.scrollHeight) {
	                scope.$apply(attr.nisScrollEnd);
	            }
			});
		};
    };

    newDirective.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisScrollEnd', newDirective);
    } catch(e) {
    	console.error(e);
    }

}());