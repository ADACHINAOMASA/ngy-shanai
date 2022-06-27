(function () {
	
	var name = 'HinmokuListModalController';
	
    var injectParams = [
        '$scope', '$uibModalInstance', '$q','input', 'multiSelect', 
        'uiGridConstants', '$timeout', 'NisGridUtil', 'SysConst', 
        'i18nService', 'ModalService', 'LoginService', 'KakoKeisanService',
        'messages', 'header', 'OptionsService'
    ];
    
    var ListModalController = function (
		$scope, $uibModalInstance, $q,input, multiSelect, 
		uiGridConstants, $timeout, NisGridUtil, SysConst, 
		i18nService, ModalService, LoginService, KakoKeisanService,
		messages, header, OptionsService
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
			// 製造購入区分フラグ
			isSeizoKonyuBunrui : false,
			// 製造購入区分
			seizoKonyuBunruiKonyuhin: SysConst.seizoKonyuBunrui1.konyuhin,
		};
		// ステータス  ---------------------------------------------------------------------------- ↑
		
		// 初期値 ------------------------------------------------------------------------------ ↓
		// タイトル
		$scope.title = SysConst.hinmoku.ichiran.title;
		// 入力情報
		$scope.input = input || {};
		//ヘッダー情報
		$scope.header = header || {};
		//プロパティメッセージ
		$scope.messages = messages || {};
		// Grigのデータ
		$scope.records = [];
		// 初期値 ------------------------------------------------------------------------------ ↑
		
		// 区 分 --------------------------------------------------------------------------------- ↓
		$scope.kbn = {
			// 選択区分
			sentakuFlg : {
				on : SysConst.flag.on,
				off : SysConst.flag.off,
			},
			// 確定フラグ
			kakuteiFlg : {
				kakutei : SysConst.kakuteiFlg.kakutei,
				dorafuto : SysConst.kakuteiFlg.dorafuto,
			},
		};
		// 区 分 --------------------------------------------------------------------------------- ↑
		
		// Option ------------------------------------------------------------------------------ ↓
		// 製造購入区分（大分類）
		$scope.seizoKonyuBunrui1Options = [];
		OptionsService.getOptions('seizoKonyuBunrui1', 'true')
			.then(function(data) {
				if (data) {
					$scope.seizoKonyuBunrui1Options = data.options;
				}
		});
		// 製造購入区分（中分類）
		$scope.seizoKonyuBunrui2Options = [];
		OptionsService.getOptions('seizoKonyuBunrui2', 'true')
			.then(function(data) {
				if (data) {
					$scope.seizoKonyuBunrui2Options = data.options;
				}
		});
		// Option ------------------------------------------------------------------------------ ↑
		
		// Action ------------------------------------------------------------------------------ ↓
		$scope.action = {
			// 製造購入区分（中分類）選択有無
			seizoKonyuBunruiChenge : function() {
				// 購入品のみ製造購入区分(中分類)選択可能
				if ($scope.input.seizoKonyuBunrui1Cd == $scope.state.seizoKonyuBunruiKonyuhin) {
					$scope.state.isSeizoKonyuBunrui = true;
				} else {
					$scope.state.isSeizoKonyuBunrui = false;
					$scope.input.seizoKonyuBunrui2Cd = null;
					$scope.input.seizoKonyuBunrui2Mei = null;
				}
			},
			// 検索
			search : function() {
				ModalService.loading(KakoKeisanService.list($scope.input))
				.then(function(data){
					$scope.action.clear();
					
					$scope.input = data || {};
					$scope.records = data.meisais;

					$scope.state.searched = true;
				});
			},
			// クリア
			clear : function(){
				$scope.input = {};
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
		
		// 一覧 ------------------------------------------------------------------------------- ↓
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
					NisGridUtil.setOptionsFilter($scope.gridOptions, 'kakuteiFlgFilter', 'kakuteiFlgMei');// option名、コラム名順
					
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
								field: 'hinmokuCdHyouzi'
								, displayName: '品目コード'
								, width: 150
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'hinmokuMei'
								, displayName: '品目名'
								, width: 250
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'kakuteiFlgMei'
								, displayName: 'ドラフト'
								, cellFilter : 'options:\'kakuteiFlgFilter\''
								, filter: {type: uiGridConstants.filter.SELECT}
						    	, cellTemplate: ''
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