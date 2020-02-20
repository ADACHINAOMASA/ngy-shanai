/**
 * 包装仕様書閲覧サービス
 */
(function() {
	//index.htmlのng-appで定義している名前と同じ。
	//<html lang="jp" ng-app="app">
	var moduleName = 'app';
	
	var serviceName = 'HosoShiyoshoService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', 'NIS','$uibModal' ];

	// 引数は依存の内容と一致する
	var newService = function( $http, $q, NIS , $uibModal ) {
		
		var baseURI = 'service/hososhiyosho/';

		return {
			
			soapClient : function(data) {
            	return NIS.u.req($http.post(NIS.u.path(baseURI, 'soapClient'), data));
			}
		
            ,search : function(data) {
            	return NIS.u.req($http.post(NIS.u.path(baseURI, 'search'), data));
			}
	        ,searchGenba : function(data) {
	        	return NIS.u.req($http.post(NIS.u.path(baseURI, 'searchGenba'), data));
			}
			, outputExcel : function(info){
				return NIS.u.req($http.post(NIS.u.path(baseURI, 'outputExcel'), info));
			}
			,openCommentModal: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/hososhiyosho/comment/CommentModal.html',
					controller: 'CommentModalController',
					size: param.size || 'md',
					resolve: {
						input: function(){
							return param.data || {};
						},
					},
				});
				return modalInstance.result;
			}
			, getGenbaWindowOptions : function(){
				return NIS.u.req($http.get(NIS.u.path(baseURI, 'genba','windowOptions')));
			}
			
		};
		
	};//End newService

	newService.$inject = injectParams;
	angular.module(moduleName).factory(serviceName, newService);
}());
