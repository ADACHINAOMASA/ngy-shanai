(function () {

    // 必要な依存を列挙
    var injectParams = ['$timeout'];

    // 引数は依存の内容と一致する
    var nisLazyLoadScroll = function ($timeout) {
    	// ディレクティブ内容
    	return {
			restrict: 'A',
			scope: {
				nisLazyLoadScroll: '='
				,nisLazyLoadScrollAdd: '='
				,nisLazyLoadScrollMax: '='
			},
			link:function(scope, elm, attr) {
				elm.get(0).scrollTop = 0;
				var add = scope.nisLazyLoadScrollAdd || 50;
				// TODO:このままだと直下の子孫でしか使えない
				var hideTimer = undefined;
				elm.on('scroll', function(e){
					if (!counter.is(':visible')) {
						counter.fadeIn(500);
					}
					if (!angular.isUndefined(hideTimer)) {
						$timeout.cancel(hideTimer);
						hideTimer = undefined;
					}
					hideTimer = $timeout(function(){
						counter.fadeOut(500);
						hideTimer = undefined;
					}, 1000);
					counter.text((scope.nisLazyLoadScroll < scope.nisLazyLoadScrollMax ? scope.nisLazyLoadScroll : scope.nisLazyLoadScrollMax)  + " / " + scope.nisLazyLoadScrollMax);
					if(!($(this).scrollTop() + $(this).innerHeight()>=$(this)[0].scrollHeight)){
						return;
                    }
					if (scope.nisLazyLoadScroll > scope.nisLazyLoadScrollMax) {
						return;
					}
					scope.$parent.$apply(function(scope){
						scope[attr.nisLazyLoadScroll] += add;
					});
				});
				var counter = $('<span class="bg-info small" style="position:absolute;bottom:20px;right:20px;display:none;"></span>');
				elm.append(counter);
			},
			replace: false
    	};
    };

    nisLazyLoadScroll.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisLazyLoadScroll', nisLazyLoadScroll);
    } catch(e) {
    	console.error(e);
    }

}());