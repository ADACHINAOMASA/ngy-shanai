// <nis-header></nis-header>
(function () {

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var newDirective = function () {
    	// ディレクティブ内容
    	return {
    		restrict: 'E',
			templateUrl: 'app/main/header/header.html',
			replace: true
    	};
    };

    newDirective.$inject = injectParams;

    angular.module('app')
        .directive('nisHeader', newDirective);

}());