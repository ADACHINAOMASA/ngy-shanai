
(function() {
	//index.htmlのng-appで定義している名前と同じ。
	//<html lang="jp" ng-app="app">
	var moduleName = 'app';
	
	var serviceName = 'CMSchViewService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', 'NIS' ];

	// 引数は依存の内容と一致する
	var newService = function( $http, $q, NIS) {
		
		var baseURI = 'service/cmschview/';

		return {
		
		};
		
	};//End newService

	newService.$inject = injectParams;
	angular.module(moduleName).factory(serviceName, newService);
}());
