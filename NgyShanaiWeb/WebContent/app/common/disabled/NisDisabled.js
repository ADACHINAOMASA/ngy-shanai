/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:これでいいのかは要検討

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisDisabled = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'A',
			link: function(scope, elm, attrs, ctrl) {
				scope.$watch(attrs.nisDisabled, function(newValue){
					if (elm.hasClass('nis-not-authority')) {
						elm.prop('disabled', true);
						return;
					}
					if (newValue) {
						elm.prop('disabled', true);
					}
					else {
						elm.prop('disabled', false);
					}
				});
			}
    	};
    };

    nisDisabled.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisDisabled', nisDisabled);
    } catch(e) {
    	console.error(e);
    }

}());