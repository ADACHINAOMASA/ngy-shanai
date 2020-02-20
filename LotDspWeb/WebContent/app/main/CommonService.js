/**
 * Back End : CommonService.java
 * 
 * @author Leang-Say
 */
(function() {

	var name = 'CommonService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q' ];

	// 引数は依存の内容と一致する
	var newService = function($http, $q) {

		var baseURI = 'service/common';

		return {
			getHttpRequestInfo : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + '/httpRequestInfo', {
					params : param
				}).success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			},
			getSvfWebClientObjectInfo : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + '/svfWebClientObjectInfo', {
					params : param
				}).success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			},
			juchuRenkeiUrl1 : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + '/juchuRenkeiUrl1', {
					params : param
				}).success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			},
			juchuRenkeiUrl2 : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + '/juchuRenkeiUrl2', {
					params : param
				}).success(function(data) {
					defferd.resolve(data);
				}).error(function(data) {
					defferd.reject(data);
				});
				return defferd.promise;
			}
			,getWindowOptionsInfo : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + '/getWindowOptionsInfo', {
					params : param
				}).success(function(data) {
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