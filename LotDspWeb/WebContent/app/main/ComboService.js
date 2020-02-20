
(function () {

    angular.module('app')
    	// factory | service | provider
        .factory('ComboService', service);

    service.$inject = ['$http', '$q'];

    // 引数は依存の内容と一致する
    function service($http, $q) {
    	var baseURI='service/combo/';
		
    	return {
			refreshAll : function() {
				var defferd = $q.defer();
				$http.get(baseURI +"refreshAll/")
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}

		};
		
    };
}());