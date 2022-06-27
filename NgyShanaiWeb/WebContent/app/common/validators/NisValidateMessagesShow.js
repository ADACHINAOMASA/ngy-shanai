/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:処理速度の為に元から分岐させた
	// TODO:良い方法が見つかればいずれ統合させる

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisValidateMessagesShow = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/validators/validateMessagesShow.html',
			scope: {
				target: '=',
				exps: '='
			},
			replace: true
    	};
    };

    nisValidateMessagesShow.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisValidateMessagesShow', nisValidateMessagesShow);
    } catch(e) {
    	console.error(e);
    }

}());