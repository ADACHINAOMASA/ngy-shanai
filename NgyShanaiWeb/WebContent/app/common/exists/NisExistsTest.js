/**
 *
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['TorihikisakiResource', '$q'];

    // 引数は依存の内容と一致する
    var nisExistsTest = function (TorihikisakiResource, $q) {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			scope: {
				test: '='
			},
			link: function(scope, element, attrs) {
				scope.$parent.test = function(){
					console.log(this.juchuTorokuForm);
				};
			}
    	};
    };

    nisExistsTest.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisExistsTest', nisExistsTest);
    } catch(e) {
    	console.error(e);
    }

}());