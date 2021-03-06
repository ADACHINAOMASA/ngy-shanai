(function () {

	var name = 'SagyokoteiListModalController';
	
    var injectParams = [
        '$scope', '$uibModalInstance', '$q','input', 'multiSelect', 
        'uiGridConstants', '$timeout', 'NisGridUtil', 'SysConst', 
        'i18nService', 'ModalService', 'LoginService', 'KakushuService',
        'messages', 'header'
    ];
    
    var ListModalController = function (
		$scope, $uibModalInstance, $q,input, multiSelect, 
		uiGridConstants, $timeout, NisGridUtil, SysConst, 
		i18nService, ModalService, LoginService, KakushuService,
		messages, header
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
		$scope.title = SysConst.kakushu.sagyokoteiKbn.ichiran.title;
		// 入力情報
		$scope.input = input || {};
		$scope.input.mstKbn = SysConst.kakushuMasterKbn.Sagyokotei;
		//ヘッダー情報
		$scope.header = header || {};
		//プロパティメッセージ
		$scope.messages = messages || {};
		// Grigのデータ
		$scope.records = [];
		// 初期値 ------------------------------------------------------------------------------ ↑
		
		// Action ------------------------------------------------------------------------------ ↓
		$scope.action = {
			// 検索
			search : function() {
				ModalService.loading(KakushuService.list($scope.input))
				.then(function(data){
					$scope.records = data;
					$scope.state.searched = true;
				});
			},
			// クリア
			clear : function(){
				$scope.input = {};
				$scope.input.mstKbn = SysConst.kakushuMasterKbn.Sagyokotei;
				$scope.records = [];
				
				$scope.gridApi.grid.clearAllFilters();
				$scope.state.searched = false;
			},
			// 選択
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
			// 選択キャンセル
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
				paginationPageSizes: $scope.uigrid.paginationPageSizes,
				paginationPageSize: $scope.uigrid.paginationPageSize,
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
								field: 'mstCd'
								, displayName: '工程コード'
								, width: 150
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'naiyo'
								, displayName: '工程名'
								, width: 330
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
		// 一覧 ------------------------------------------------------------------------------- ↑
		
		// 描画遅れ暫定対処 --------------------------------------------------------------------- ↓
		$timeout(function(){
			$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
		}, 200)
		.then(function(data){
			$scope.action.search();
		});
		// 描画遅れ暫定対処 --------------------------------------------------------------------- ↑
	};

	ListModalController.$inject = injectParams;
	
	angular.module('app').controller(name, ListModalController);
}());