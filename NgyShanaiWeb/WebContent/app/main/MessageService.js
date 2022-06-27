/**
 * Back End : MessagesService.java
 * @author Leang-Say
 */
(function() {

	var name = 'MessageService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q' ];

	// 引数は依存の内容と一致する
	var newService = function($http, $q) {

		var baseURI = 'service/message';

		return {
			getAll : function() {
				var defferd = $q.defer();
				$http.get(baseURI + '/getAll').success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			},
			get : function(key, param1, param2, param3) {
				var uri = "/" + key;
					uri = uri + "/" + param1;
					uri = uri + "/" + param2;
					uri = uri + "/" + param3;
				var defferd = $q.defer();
				$http.get(baseURI + uri).success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			},
			getMessages : function(keyArr) {
				var defferd = $q.defer();
				$http.post(baseURI + '/getMessages', keyArr)
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