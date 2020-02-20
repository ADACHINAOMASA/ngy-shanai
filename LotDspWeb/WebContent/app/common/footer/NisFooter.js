// <nis-footer></nis-footer>
(function () {

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisFooter = function () {
    	// ディレクティブ内容
    	return {
    		restrict: 'E',
			templateUrl: 'app/common/footer/footer.html',
			replace: true
    	};
    };

    nisFooter.$inject = injectParams;

    angular.module('app')
        .directive('nisFooter', nisFooter);

}());