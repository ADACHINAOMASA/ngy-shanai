/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:これでいいのかは要検討

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisHasError = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'A',
			link: function(scope, elm, attrs, ctrl) {
				var watch = '';
				angular.forEach(attrs.nisHasError.split(','), function(elm){
					if (watch) {
						watch = watch + ' || ';
					}
					var elmName = elm.trim();
					watch = watch + '(' + elmName + '.$invalid && (' + elmName + '.$dirty || ' + elmName + '.$touched))';
				});
				scope.$watch(watch, function(newValue){
					if (newValue) {
						attrs.$addClass('has-error');
					}
					else {
						attrs.$removeClass('has-error');
					}
				});
			}
    	};
    };

    nisHasError.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisHasError', nisHasError);
    } catch(e) {
    	console.error(e);
    }

}());