/**
 * Back End : KbnService.java
 * @author Leang-Say
 */
(function () {
	
	var name = 'KbnService';

	// 必要な依存を列挙
    var injectParams = ['$http', '$q'];

    // 引数は依存の内容と一致する
    var newService = function ($http, $q) {

    	var baseURI = 'service/kbn';

    	return {
    		meisaiGyoPtn: function(param) {
                var defferd = $q.defer();
                $http.get( baseURI + '/mitsumori/meisaiGyoPtn',{params:param})
                    .success(function(data) {
                        defferd.resolve(data);
                    })
                    .error(function(data) {
                        defferd.reject(data);
                    });
                return defferd.promise;
            },
            meisaiType: function(param) {
                var defferd = $q.defer();
                $http.get( baseURI + '/mitsumori/meisaiType',{params:param})
                    .success(function(data) {
                        defferd.resolve(data);
                    })
                    .error(function(data) {
                        defferd.reject(data);
                    });
                return defferd.promise;
            },
            formatPtn: function(param) {
                var defferd = $q.defer();
                $http.get( baseURI + '/mitsumori/formatPtn',{params:param})
                    .success(function(data) {
                        defferd.resolve(data);
                    })
                    .error(function(data) {
                        defferd.reject(data);
                    });
                return defferd.promise;
            },
		};
    };

    newService.$inject = injectParams;
    
    angular.module('app').factory(name, newService);

}());