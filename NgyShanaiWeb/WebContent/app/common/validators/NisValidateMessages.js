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
    var nisValidateMessages = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/validators/validateMessages.html',
			scope: {
				target: '=',
				exps: '='
			},
			replace: true
    	};
    };

    nisValidateMessages.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisValidateMessages', nisValidateMessages);
    } catch(e) {
    	console.error(e);
    }

}());