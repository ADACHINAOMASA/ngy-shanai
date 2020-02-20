(function () {
	
	var name = 'BuhinKakoJohoKensakuController';
	
    var injectParams = [
        '$scope', '$uibModalInstance', '$q','input', 'multiSelect', 
        'uiGridConstants', '$timeout', 'NisGridUtil', 'SysConst', 
        'i18nService', 'ModalService', 'LoginService', 'BuhinSeisakuChokurohiIchiranService',
        'messages', 'header', 'ListModalService', 'OptionsService',
        'ModelService','MeishoService', 'AlertService'
    ];
    
    var ListModalController = function (
		$scope, $uibModalInstance, $q,input, multiSelect, 
		uiGridConstants, $timeout, NisGridUtil, SysConst, 
		i18nService, ModalService, LoginService, BuhinSeisakuChokurohiIchiranService,
		messages, header, ListModalService, OptionsService,
		ModelService, MeishoService, AlertService
	) {
		$scope.uigrid = SysConst.uigrid;
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		//セッションから$scopeにユーザ情報をロードする。
		LoginService.loadUserProfile($scope);
		
		// ステータス  ---------------------------------------------------------------------------- ↓
		// ステータス  ---------------------------------------------------------------------------- ↑
		
        ModelService.newModel("BuhinSeisakuChokurohiKensakuInfo").then(function(model) {
        	var buhinSeisakuNo = $scope.input.buhinSeisakuNo;
        	var meisho = $scope.input.meisho;
        	var zairyoBunruiCd = $scope.input.zairyoBunruiCd;
        	var yotoBunruiCd = $scope.input.yotoBunruiCd;
        	var yotoBunruiMei = $scope.input.yotoBunruiMei;
     		$scope.input = model;
     		$scope.input.buhinSeisakuNo = buhinSeisakuNo;
     		$scope.input.meisho = meisho;
     		$scope.input.zairyoBunruiCd = zairyoBunruiCd;
     		$scope.input.yotoBunruiCd = yotoBunruiCd;
     		$scope.input.yotoMei = yotoBunruiMei;
        });
		
		// Option ------------------------------------------------------------------------------ ↓
		
        $scope.zairyoBunruiOptions = [];
		OptionsService.getOptions('zairyoBunrui', 'true')
			.then(function(data) {
				$scope.zairyoBunruiOptions = data.options;
		});
        
		// Option ------------------------------------------------------------------------------ ↑
		
		// 初期値 ------------------------------------------------------------------------------ ↓
		// タイトル
		$scope.title = SysConst.buhinKakoJoho.title;
		// 入力情報
		$scope.input = input || {};
		//ヘッダー情報
		$scope.header = header || {};
		//プロパティメッセージ
		$scope.messages = messages || {};
		// Grigのデータ
		$scope.records = [];
		// 初期値 ------------------------------------------------------------------------------ ↑
		
		// 名称を取得する関数を初期化
		AppUtil.initGetMeisho($scope,MeishoService);
		
		// Action ---------------------------------------------------------------------------- ↓
		$scope.action = {
			// 検索
				search : function() {
					ModalService.loading(
						BuhinSeisakuChokurohiIchiranService.list(NisUtil.createRequestData($scope.input))
					).then(function(data){
						$scope.records = data;
					});
				},
			// クリア
			clear : function(){
				AlertService.clear();
				$scope.input = {};
				$scope.records = [];
				
				$scope.gridApi.grid.clearAllFilters();
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
			//用途分類
			openYotoBunruiList : function() {
				AlertService.clear('validate');
				
				ListModalService.openYotoBunruiSimpleList({
					kensakuInfo: {
						mstCd: $scope.input.yotoBunruiCd,
						naiyo: $scope.input.yotoMei,
					}
				}).then(function(row) {
					$scope.input.yotoBunruiCd = row.mstCd;
					$scope.input.yotoMei = row.naiyo;
				});
			}
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
						field : 'buhinSeisakuNo',
						displayName : '部品製作番号',
						width : 120,
					}),
					NisGridUtil.columnDefDefault({
						field : 'meisho',
						displayName : '名称',
						width : 120,
						cellTooltip : true,
					}),
					NisGridUtil.columnDefDefault({
						field : 'zairyoBunruiCd',
						displayName : '材料分類コード',
						width : 140,
					}),
					NisGridUtil.columnDefDefault({
						field : 'zairyoMei',
						displayName : '材料分類名',
						width : 120,
						cellTooltip : true,
					}),
					NisGridUtil.columnDefDefault({
						field : 'yotoBunruiCd',
						displayName : '用途分類コード',
						width : 140,
					}),
					NisGridUtil.columnDefDefault({
						field : 'yotoMei',
						displayName : '用途分類名',
						width : 120,
						cellTooltip : true,
					}),
					NisGridUtil.columnDefDefault({
						field : 'kosu',
						displayName : '工数',
						cellFilter: 'number:3',
						cellClass: 'text-right',
						filter : {
							condition : function(searchTerm, cellValue, row) {
								var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
								if(!isNaN(searchValue) && !isNaN(cellValue)){
									return Number(searchValue) <= cellValue;
								}
								return false;
							},
						},
						type : 'numberStr',
						width : 120,
					}),
					NisGridUtil.columnDefDefault({
						field : 'jissekiKosu',
						displayName : '実績工数',
						cellFilter: 'number:3',
						cellClass: 'text-right',
						filter : {
							condition : function(searchTerm, cellValue, row) {
								var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
								if(!isNaN(searchValue) && !isNaN(cellValue)){
									return Number(searchValue) <= cellValue;
								}
								return false;
							},
						},
						type : 'numberStr',
						width : 120,
					}),
					NisGridUtil.columnDefDefault({
						field : 'chokurohi',
						displayName : '直労費',
						cellClass: 'text-right',
						cellFilter: 'currency: "&#xa5":0',
						filter : {
							condition : function(searchTerm, cellValue, row) {
								var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
								if(!isNaN(searchValue) && !isNaN(cellValue)){
									return Number(searchValue) <= cellValue;
								}
								return false;
							},
						},
						type : 'numberStr',
						width : 120,
					}),
					NisGridUtil.columnDefDefault({
						field : 'jissekiChokurohi',
						displayName : '実績直労費',
						cellClass: 'text-right',
						cellFilter: 'currency: "&#xa5":0',
						filter : {
							condition : function(searchTerm, cellValue, row) {
								var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
								if(!isNaN(searchValue) && !isNaN(cellValue)){
									return Number(searchValue) <= cellValue;
								}
								return false;
							},
						},
						type : 'numberStr',
						width : 120,
					}),
					NisGridUtil.columnDefDefault({
						field : 'torokuYmdhms',
						displayName : '登録日時',
						cellFilter : 'date:"yyyy/MM/dd HH:mm:ss"',
						filter : {
							condition : function(searchTerm, cellValue,row) {
								return NisUtil.nullToBlank(
									NisUtil.formatDate(cellValue,'yyyy/MM/dd hh:mm:ss')
								).search(searchTerm) >= 0;
							},
							placeholder : 'yyyy/MM/dd hh:mm:ss',
						},
						type : 'date',
						width : 150,
					}),
					NisGridUtil.columnDefDefault({
						field : 'koshinYmdhms',
						displayName : '更新日時',
						cellFilter : 'date:"yyyy/MM/dd HH:mm:ss"',
						filter : {
							condition : function(searchTerm, cellValue,row) {
								return NisUtil.nullToBlank(
									NisUtil.formatDate(cellValue,'yyyy/MM/dd hh:mm:ss')
								).search(searchTerm) >= 0;
							},
							placeholder : 'yyyy/MM/dd hh:mm:ss',
						},
						type : 'date',
						width : 150,
					}),
					NisGridUtil.columnDefDefault({
						field : 'saishuKoshinshaCd',
						displayName : '最終更新者コード',
						width : 150,
					}),
					NisGridUtil.columnDefDefault({
						field : 'saishuKoshinshaMei',
						displayName : '最終更新者名',
						width : 120,
						cellTooltip : true,
					}),
					
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
			//$scope.action.search();
		});
		// 描画遅れ暫定対処 --------------------------------------------------------------------- ↑
	};

	ListModalController.$inject = injectParams;
	
	angular.module('app').controller(name, ListModalController);
	
}());