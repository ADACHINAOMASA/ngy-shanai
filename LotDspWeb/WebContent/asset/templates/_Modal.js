(function() {
	// index.htmlのng-appで定義している名前と同じ。
	// <html lang="jp" ng-app="app">
	var moduleName = 'app';
	
	var modalName = '_Modal';//TODO 変更すること

	// 必要な依存を列挙
	var injectParams = [ '$uibModal' ];

	// 引数は依存の内容と一致する
	var newModal = function($uibModal) {

		var controller = '[コントローラー名]';//TODO 変更すること
		var templateUrl = '[テンプレートURL]';//TODO 変更すること

		// TODO 以下はサンプルです。不要であれば、削除してください。
		var messageParams = [ 
		                      {key : 'common.confirm',params : []},
		                      {key : 'common.complete',params : []} 
		                    ];

		return {
			open : function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation : true,
					templateUrl : templateUrl,
					controller : controller,
					size : param.size || 'lg',
					backdrop : 'static',
					resolve : {
						// TODO 以下はサンプルです。不要であれば、削除してください。
						/*
						messages : function(MessageService) {
							return MessageService.getMessages({
								messages : messageParams
							});
						},
						input : param.shoninWorkflowInfo || {},
						*/
					},
				});
				return modalInstance.result;
			}
		};// End return
		
	};// End newModal

	newModal.$inject = injectParams;
	angular.module(moduleName).factory(modalName, newModal);
}());