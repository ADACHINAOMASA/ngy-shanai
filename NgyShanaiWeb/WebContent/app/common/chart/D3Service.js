(function () {

	// 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var D3Service = function () {
    	return {
    		d3: d3
		};
    };

    D3Service.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('D3Service', D3Service);

}());