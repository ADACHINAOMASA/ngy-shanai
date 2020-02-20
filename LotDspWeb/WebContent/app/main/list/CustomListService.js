(function () {
	// 必要な依存を列挙
    var injectParams = ['$http', '$q', '$uibModal', 'uiGridConstants'];
    
    // 引数は依存の内容と一致する
    var newService = function ($http, $q, $uibModal, uiGridConstants) {
    	var baseURI ='service/list/';
    	
		return {
			mitsumoriNoList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'mitsumoriNoList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
		
			,genkaMitsumoriNoList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'genkaMitsumoriNoList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			
			,juchuNoList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'juchuNoList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			
			,juchuNoListFromSeisakuShiji : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'juchuNoListFromSeisakuShiji',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			
			,genkaJuchuNoList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'genkaJuchuNoList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			
			,seizoNoList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'seizoNoList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			
			,genkaSeizoNoList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'genkaSeizoNoList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			
			,tantoshaList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'tantoshaList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,torihikisakiList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'torihikisakiList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,nonyusakiList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'nonyusakiList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,buhinList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'buhinList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,genkaNoList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'genkaNoList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,himokuList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'himokuList',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,zaishitsuList : function(param) {
				var defferd = $q.defer();
				$http.get(baseURI + 'zaishitsuList',{params:param})
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

    angular.module('app')
        .factory('CustomListService', newService);
}());