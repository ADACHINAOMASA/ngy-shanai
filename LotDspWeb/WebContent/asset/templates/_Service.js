/**
 * _サービス
 */
(function() {
	//index.htmlのng-appで定義している名前と同じ。
	//<html lang="jp" ng-app="app">
	var moduleName = 'app';
	
	var serviceName = '_Service';//TODO サービス名を定義すること。

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q' ];

	// 引数は依存の内容と一致する
	var newService = function( $http, $q ) {
		
		var baseURI = 'service/xxx/';//TODO xxx ：サービスコンテキストを定義すること。

		return {
			
//TODO 以下はサンプルです。削除してください。
//			list : function(param) {
//				var defferd = $q.defer();
//				$http.get(baseURI + 'list', {
//					params : param
//				}).success(function(data) {
//					defferd.resolve(data);
//				}).error(function(data) {
//					defferd.reject(data);
//				});
//				return defferd.promise;
//			},
//			get : function(key1, key2) {
//				var defferd = $q.defer();
//				$http.get(baseURI + key1 + '/' + key2).success(function(data) {
//					defferd.resolve(data);
//				}).error(function(data) {
//					defferd.reject(data);
//				});
//				return defferd.promise;
//			}
			
		};//End return
	};//End newService

	newService.$inject = injectParams;
	angular.module(moduleName).factory(serviceName, newService);
}());
