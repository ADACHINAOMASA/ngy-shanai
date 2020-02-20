
(function() {
	//index.htmlのng-appで定義している名前と同じ。
	//<html lang="jp" ng-app="app">
	var moduleName = 'app';

	var serviceName = 'AuthenticationService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', 'NIS' ];

	// 引数は依存の内容と一致する
	var newService = function( $http, $q, NIS) {
		var baseURI = 'service/lotdsp/authentication/';
		return {
			auth : function(param) {
				return NIS.u.req($http.get(NIS.u.path(baseURI, 'auth'), {params:{mode: param}}));
			}
		};
	};//End newService

	newService.$inject = injectParams;
	angular.module(moduleName).factory(serviceName, newService);

}());
