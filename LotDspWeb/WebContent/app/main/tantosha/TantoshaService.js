(function () {

	var name = 'TantoshaService';
	
	// 必要な依存を列挙
    var injectParams = ['$http', '$q'];

    // 引数は依存の内容と一致する
    var newService = function ($http, $q) {
    	
    	var baseURI ='service/tantosha/';
    	
		return {
			list : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'list',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,get : function(key) {
				var defferd = $q.defer();
				$http.get(baseURI + key)
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,save : function(key, param) {
				var defferd = $q.defer();
				$http.post(baseURI + key, param)
				.success(function(data){
					defferd.resolve(data);
				})
				.error(function(data){
					defferd.reject(data);
				});
				return defferd.promise;
			}
			,remove : function(key, param) {
				var defferd = $q.defer();
				$http.delete(baseURI + key, {data:param, headers: {
					   'Content-Type': 'application/json'
				}})
				.success(function(data){
					defferd.resolve(data);
				})
				.error(function(data){
					defferd.reject(data);
				});
				return defferd.promise;
			}
			,shutoku : function(key, param) {
				var defferd = $q.defer();
				$http.post(baseURI + 'shutoku/' + key, param)
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,fukusei : function(key, param) {
				var defferd = $q.defer();
				$http.post(baseURI + 'fukusei/' + key, param)
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

    newService.$inject = injectParams;

    angular.module('app').factory(name, newService);

}());