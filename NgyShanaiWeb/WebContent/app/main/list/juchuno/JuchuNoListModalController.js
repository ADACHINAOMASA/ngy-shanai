(function () {

    var injectParams = [
    	'$scope', '$uibModalInstance', '$q','input', 'multiSelect'
        , 'uiGridConstants', '$timeout', 'NisGridUtil'
        , 'i18nService', 'ModalService', 'CustomListService'
        , 'messages', 'header', 'SysConst', 'LoginService'
        ];
    
    var ListModalController = function (
    	$scope, $uibModalInstance, $q,input, multiSelect
        , uiGridConstants, $timeout, NisGridUtil
        , i18nService, ModalService, CustomListService
        , messages, header, SysConst, LoginService
        ) {
		
    	$scope.uigrid = SysConst.uigrid;
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		//セッションから$scopeにユーザ情報をロードする。
		LoginService.loadUserProfile($scope);

		// ステータス  ---------------------------------------------------------------------------- ↓
		$scope.state = {
			// 検索フラグ
			searched : false,
		};
		// ステータス  ---------------------------------------------------------------------------- ↑
		
		// 初期値 ------------------------------------------------------------------------------ ↓
		// タイトル
		$scope.title = "受注一覧";
		// 入力情報
		$scope.input = input || {};
		$scope.input.mstKbn = SysConst.kakushuMasterKbn.SagyoSetsubi;
		//ヘッダー情報
		$scope.header = header || {};
		//プロパティメッセージ
		$scope.messages = messages || {};
		// Grigのデータ
		$scope.records = [];
		// 初期値 ------------------------------------------------------------------------------ ↑
		
		// Action ------------------------------------------------------------------------------ ↓
		$scope.action = {
			search : function() {
				ModalService.loading(CustomListService.juchuNoList($scope.input))
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
		// Action ---------------------------------------------------------------------------- ↑
		
		$scope.gridOptions = NisGridUtil.gridOptionsDefault({
				enableGridMenu: true,
				enableSorting: true,
				enableColumnResizing: true,
				enableRowSelection: true,
				enableFiltering: true,
				multiSelect: !!multiSelect,
				paginationPageSizes: SysConst.uigrid.paginationPageSizes,
				paginationPageSize: SysConst.uigrid.paginationPageSizes,
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
							, NisGridUtil.columnDefDefault({
								field: 'juchuNo'
								, displayName: '受注番号'
								, width: 150
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'juchuKoNo'
								, displayName: '受注子番号'
								, width: 290
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
		.controller('JuchuNoListModalController', ListModalController);
}());