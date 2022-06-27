/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:もう少し汎用性等考える必要がある
	// TODO:処理速度も様子見

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisYubinBango = function () {
    	// ディレクティブ内容
    	return {
    		require: 'ngModel',
			restrict: 'A',
			link: function(scope, elm, attrs, ctrl) {
				ctrl.$parsers.unshift(function(viewValue) {
					if(/^\d{7}$/.test(viewValue)) {
						viewValue
							= viewValue.replace(/(^\d{3})(\d{4}$)/g, '$1-$2');
						elm.val(viewValue);
					}
					return viewValue;
				});
				ctrl.$validators.illegalFormat =function(modelValue, viewValue) {
		        	var value = modelValue || viewValue;
		        	if (!value) {
		        		return true;
		        	}
		        	return /^\d{3}\-\d{4}$/.test(value);
		        }

			},
			replace: true
    	};
    };

    nisYubinBango.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisYubinBango', nisYubinBango);
    } catch(e) {
    	console.error(e);
    }

}());