
(function() {
	//index.htmlのng-appで定義している名前と同じ。
	//<html lang="jp" ng-app="app">
	var moduleName = 'app';

	var serviceName = 'SearchService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', 'NIS' ];

	// 引数は依存の内容と一致する
	var newService = function( $http, $q, NIS) {
		var baseURI = 'service/lotdsp/search/';

		return {
			searchAction : function(params) {
				return NIS.u.req($http.post(NIS.u.path(baseURI, 'searchAction'), params));
			},
		};
	};//End newService

	newService.$inject = injectParams;
	angular.module(moduleName).factory(serviceName, newService);
}());
