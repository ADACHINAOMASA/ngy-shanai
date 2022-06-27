(function () {

    var injectParams = ['$scope', '$uibModalInstance','data', 'columnDefs', 'multiSelect', 'uiGridConstants', '$timeout', 'SysConst'];

    var ListModalController = function ($scope, $uibModalInstance, data, columnDefs, multiSelect, uiGridConstants, $timeout, SysConst) {
    	
		// 初期化
		$scope.uigrid = SysConst.uigrid;
    	
    	$scope.gridOptions = {
				enableGridMenu: true,
				enableSorting: true,
				enableColumnResizing: true,
				enableRowSelection: true,
				enableFiltering: true,
				multiSelect: !!multiSelect,
				paginationPageSizes: $scope.uigrid.paginationPageSizes,
				paginationPageSize: $scope.uigrid.paginationPageSize,
				onRegisterApi: function(gridApi){
					$scope.gridApi = gridApi;
					if (!multiSelect) {
						gridApi.selection.on.rowSelectionChanged($scope,function(row){
							 if (!row.isSelected) {
								 $scope.gridApi.selection.selectRow(row.entity);
								 $scope.ok();
							 }
						});
					}
				},
				'data': data,
				'columnDefs' : columnDefs,
				gridMenuCustomItems: [{
					title: 'フィルター',
					action: function ($event) {
						$scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
						$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
					},
					order: 210
					}
				]
		};

    	$scope.ok = function () {
    		var selectedRows = $scope.gridApi.selection.getSelectedRows();
    		if (selectedRows.length == 0) {
    			$uibModalInstance.dismiss('cancel');
    			return;
    		}
    		if (!$scope.gridOptions.multiSelect) {
    			selectedRows = selectedRows[0];
    		}
    		$uibModalInstance.close(selectedRows);
		};
		$scope.cancel = function () {
			$uibModalInstance.dismiss('cancel');
		};

		// 描画遅れ暫定対処
		$timeout(function(){
			$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		}, 200);
    };

    ListModalController.$inject = injectParams;

    angular.module('app')
        .controller('ListModalController', ListModalController);

}());
