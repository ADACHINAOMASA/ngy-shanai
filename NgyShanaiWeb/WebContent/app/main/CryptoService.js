/**
 * Back End : CryptoService.java
 * @author Leang-Say
 */
(function() {

	var name = 'CryptoService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q' ];

	// 引数は依存の内容と一致する
	var newService = function($http, $q) {
		var baseURI = 'service/crypto/';
		return {
			encrypt : function(param) {
				var defferd = $q.defer();
				$http.post(baseURI + 'encrypt', param).success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			},
			decrypt : function(param) {
				var defferd = $q.defer();
				$http.post(baseURI + 'decrypt', param).success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			}
		};
	};

	newService.$inject = injectParams;

	angular.module('app').factory(name, newService);

}());