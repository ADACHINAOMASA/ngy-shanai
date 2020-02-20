(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q', '$uibModal', 'uiGridConstants'];

    // 引数は依存の内容と一致する
    var ListService = function ($http, $q, $uibModal, uiGridConstants) {

    	var sets = {};

    	return {
			open: function(param) {
				param = param || {};
				var set = sets[param.setKey] || {};
				var data = param.data || set.data;
				var columnDefs = param.columnDefs || set.columnDefs;
				var multiSelect = param.multiSelect || false;
				var size = param.size || 'md';
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/common/list/list.html',
					controller: 'ListModalController',
					size: size,
					resolve: {
						'data' : data,
						'columnDefs' : columnDefs,
						'multiSelect' : multiSelect
					}
				});
				return modalInstance.result;
			}
		};
    };

    ListService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('ListService', ListService);

}());