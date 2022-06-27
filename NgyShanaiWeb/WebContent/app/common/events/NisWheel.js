/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:これでいいのかは要検討

    // 必要な依存を列挙
    var injectParams = ['$parse'];

    // 引数は依存の内容と一致する
    var newDirective = function ($parse) {
    	// ディレクティブ内容
		return function(scope, elm, attr) {
			var expr = $parse(attr['nisWheel']);
			var fn = function(event, delta, deltaX, deltaY) {
				scope.$apply(function(){
					expr(scope, {
						$event: event,
						$delta: delta,
						$deltaX: deltaX,
						$deltaY: deltaY
					});
				});
			};
			if (!(hamster = elm.data('hamster'))) {
				hamster = Hamster(elm[0]);
				elm.data('hamster', hamster);
			}

			hamster.wheel(fn);

			scope.$on('$destroy', function(){
				hamster.unwheel(fn);
			});
		};
    };

    newDirective.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisWheel', newDirective);
    } catch(e) {
    	console.error(e);
    }

}());