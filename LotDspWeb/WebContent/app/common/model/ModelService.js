(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q', 'RulesService'];

    // 引数は依存の内容と一致する
    var ModelService = function ($http, $q, RulesService) {
    	return {
    		newModel: function(key) {
    			return $http.get('service/model', {params:{model: key}}).then(function(results){
    				return results.data;
				});
    		}
		};
    };

    ModelService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('ModelService', ModelService);

}());