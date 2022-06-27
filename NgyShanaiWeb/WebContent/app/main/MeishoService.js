/**
 * Back End : MeishoService.java
 * 
 * @author Leang-Say
 */
(function() {
	
	var moduleName = 'app';

	var serviceName = 'MeishoService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', 'NIS' ];
	// 引数は依存の内容と一致する
	var newService = function($http, $q, NIS) {

		var baseURI = 'service/meisho';

		return {
			getMeisho : function(data) {
								
				return NIS.u.req($http.post(NIS.u.path(baseURI, 'getMeisho'),data));
			}

		};// End return

	};// End newService

	newService.$inject = injectParams;
	angular.module(moduleName).factory(serviceName, newService);
}());
