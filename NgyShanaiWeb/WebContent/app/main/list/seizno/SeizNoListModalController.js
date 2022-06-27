(function () {

    var injectParams = ['$scope', '$uibModalInstance', '$q','input', 'multiSelect'
                        , 'uiGridConstants', '$timeout', 'NisGridUtil'
                        , 'i18nService', 'ModalService', 'SeisakuShijiService'
                        , 'messages', 'header', 'SysConst'
                        ];
    
    var ListModalController = function ($scope, $uibModalInstance, $q,input, multiSelect
        , uiGridConstants, $timeout, NisGridUtil
        , i18nService, ModalService, SeisakuShijiService
        , messages, header, SysConst){
		
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');

		$scope.input = input;
		$scope.records = [];
		
		$scope.title = "製造番号一覧";
		
		$scope.state = {
			searched : false
		};
		
		$scope.action = {
			search : function() {
				ModalService.loading(SeisakuShijiService.search($scope.input))
				.then(function(data){
					$scope.records = data;
					$scope.state.searched = true;
				});
			},
			clear : function(){
				$scope.input = {};
				$scope.records = [];
				
				$scope.gridApi.grid.clearAllFilters();
				$scope.state.searched = false;
			},
			ok : function() {
				var selectedRows = $scope.gridApi.selection.getSelectedRows();
	    		if (selectedRows.length == 0) {
	    			$scope.action.cancel();
	    		}
	    		if (!$scope.gridOptions.multiSelect) {
	    			selectedRows = selectedRows[0];
	    		}
	    		$uibModalInstance.close(selectedRows);
			},
			cancel : function() {
				$uibModalInstance.dismiss('cancel');
				return;
			},
		}
		
		$scope.gridOptions = NisGridUtil.gridOptionsDefault({
				enableGridMenu: true,
				enableSorting: true,
				enableColumnResizing: true,
				enableRowSelection: true,
				enableFiltering: true,
				multiSelect: !!multiSelect,
				paginationPageSizes: SysConst.uigrid.paginationPageSizes,
				paginationPageSize: SysConst.uigrid.paginationPageSize,
				onRegisterApi: function(gridApi){
					$scope.gridApi = gridApi;
					if (!multiSelect) {
						gridApi.selection.on.rowSelectionChanged($scope,function(row){
							 if (!row.isSelected) {
								 $scope.gridApi.selection.selectRow(row.entity);
								 $scope.action.ok();
							 }
						});
					}
				},
				data: 'records',
				columnDefs: [
							NisGridUtil.columnDefDefault({
								field: 'seizoNoHyojiYo'
								, displayName: '製造番号'
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
					],
				gridMenuCustomItems: [{
					title: 'フィルター',
					action: function ($event) {
						$scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
						$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
					},
					order: 210
				}]
		});
		
		// 描画遅れ暫定対処
		$timeout(function(){
			$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		}, 200)
		.then(function(data){
			$scope.action.search();
		});
	};

	ListModalController.$inject = injectParams;

	angular.module('app')
		.controller('SeizNoListModalController', ListModalController);
}());