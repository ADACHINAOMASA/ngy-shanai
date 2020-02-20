(function () {
	
	var name = 'BuhinKensakuListModalController';
	
    var injectParams = [
        '$scope', '$uibModalInstance', '$q','input', 'multiSelect', 
        'uiGridConstants', '$timeout', 'NisGridUtil', 'SysConst', 
        'i18nService', 'ModalService', 'LoginService', 'BuhinService',
        'messages', 'header', 'ListModalService', 'OptionsService',
        'ModelService','MeishoService','AlertService'
    ];
    
    var ListModalController = function (
		$scope, $uibModalInstance, $q,input, multiSelect, 
		uiGridConstants, $timeout, NisGridUtil, SysConst, 
		i18nService, ModalService, LoginService, BuhinService,
		messages, header, ListModalService, OptionsService,
		ModelService, MeishoService,AlertService
	) {
		$scope.uigrid = SysConst.uigrid;
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		//セッションから$scopeにユーザ情報をロードする。
		LoginService.loadUserProfile($scope);
		
		// ステータス  ---------------------------------------------------------------------------- ↓
		// ステータス  ---------------------------------------------------------------------------- ↑
		
        ModelService.newModel("BuhinKensakuInfo").then(function(model) {
        	var buhinCd = $scope.input.buhinCd;
        	var buhinMei = $scope.input.buhinMei;
        	var itaatsu = $scope.input.itaatsu;
        	var haba = $scope.input.haba;
        	var nagasa = $scope.input.nagasa;
        	var aSize = $scope.input.aSize;
        	var bSize = $scope.input.bSize;
        	var tSize = $scope.input.tSize;
        	var lSize = $scope.input.lSize;
        	var dSize = $scope.input.dSize;
        	var mSize = $scope.input.mSize;
     		$scope.input = model;
     		$scope.input.buhinCd = buhinCd;
     		$scope.input.buhinMei = buhinMei;
        	$scope.input.itaatsu = itaatsu;
        	$scope.input.haba = haba;
        	$scope.input.nagasa = nagasa;
        	$scope.input.aSize = aSize;
        	$scope.input.bSize = bSize;
        	$scope.input.tSize = tSize;
        	$scope.input.lSize = lSize;
        	$scope.input.dSize = dSize;
        	$scope.input.mSize = mSize;
     		
        });
		
		// Option ------------------------------------------------------------------------------ ↓
		
        $scope.shuyakuBunruiOptions = [];
		OptionsService.getOptions('shuyakuBunrui', 'true')
			.then(function(data) {
				$scope.shuyakuBunruiOptions = data.options;
		});
		
		$scope.shuyakuCdOptions = [];
		OptionsService.getOptions('shuyakuCd', 'true')
			.then(function(data) {
				$scope.shuyakuCdOptions = data.options;
		});
		
		$scope.biniiruShiyoOptions = [];
		OptionsService.getOptions('biniiruShiyo', 'true')
			.then(function(data) {
				$scope.biniiruShiyoOptions = data.options;
		});
		
		$scope.yukoKbnOptions = [];
		OptionsService.getOptions('yukoKbn', 'true')
			.then(function(data) {
				$scope.yukoKbnOptions = data.options;
		});
        
		// Option ------------------------------------------------------------------------------ ↑
		
		// 初期値 ------------------------------------------------------------------------------ ↓
		// タイトル
		$scope.title = SysConst.buhin.kensaku.title;
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
			openHimokuList : function() {
	        	ListModalService.openHimokuSimpleList({
	        		kensakuInfo: {
	        			himokuCd: $scope.input.himokuCd,
	        			himokuMei: $scope.input.himokuMei,
	        		}
				}).then(function(row) {
					$scope.input.himokuCd = row.himokuCd;
					$scope.input.himokuMei = row.himokuMei;
				});
	        },
	        openZaishitsuList : function() {
	        	ListModalService.openZaishitsuSimpleList({
	        		kensakuInfo: {
	        			zaishitsu: $scope.input.zaishitsu,
	        		}
				}).then(function(row) {
					$scope.input.zaishitsu = row.zaishitsu;
				});
	        },
			
			// 検索
	        search : function() {
				ModalService.loading(BuhinService.search($scope.input))
				.then(function(data){
					$scope.records = data;
					$scope.filteredData = $scope.input.resultList || [];
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
			//仕入履歴
			rireki : function(record) {
				ListModalService.openShiireRirekiList({
        			kensakuInfo: {
        				buhinCd: record.buhinCd,
        			}
				}).then(function(row) {
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
					
					NisGridUtil.setOptionsFilter($scope.gridOptions, 'yukoKbnFilter', 'yukoMei');
					NisGridUtil.setOptionsFilter($scope.gridOptions, 'biniiruShiyoFilter', 'biniiruShiyoMei');
					NisGridUtil.setOptionsFilter($scope.gridOptions, 'shuyakuBunruiFilter', 'shuyakuBunruiMei');
					
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
							NisGridUtil.columnDefButtonDefault({
								name : 'buttons',
								displayName : '仕入履歴',
								cellClass : 'text-left',
								cellTemplate : 'rireki_kensaku',
								enableFiltering : false,
								width : 100,
							}),
							NisGridUtil.columnDefDefault({
								field: 'buhinCd'
								, displayName: '部品コード'
								, width: 120
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'buhinMei'
								, displayName: '部品名'
								, width: 300
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
								, cellTooltip:true	
							})
							, NisGridUtil.columnDefDefault({
								field : 'shuyakuBunruiMei'
								, displayName: '集約分類'
								, width: 170
								,cellFilter : 'options:\'shuyakuBunruiFilter\''
								,filter : {
									type : uiGridConstants.filter.SELECT,
					        	}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'shuyakuCd'
								, displayName: '集約コード'
								, width: 120
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'shuyakuMei'
								, displayName: '集約名'
								, width: 130
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'himokuMei'
								, displayName: '費目'
								, width: 210
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'zaishitsuCd'
								, displayName: '材質'
								, width: 70
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'choshitsu'
								, displayName: '調質'
								, width: 80
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'biniiruShiyoMei'
								, displayName: 'ビニール'
								, width: 100
								,cellFilter : 'options:\'biniiruShiyoFilter\''
								,filter : {
									type : uiGridConstants.filter.SELECT,
					        	}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'hyomenMei'
								, displayName: '表面'
								, width: 130
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							, NisGridUtil.columnDefDefault({
								field : 'tanju'
								, displayName: '単重'
								, width: 90
			                    , cellClass: 'text-right'
								, cellFilter: 'number:1'
								, filter : {
									condition : function(searchTerm, cellValue, row) {
										var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
										if(!isNaN(searchValue) && !isNaN(cellValue)){
											return Number(searchValue) <= cellValue;
										}
										return false;
									},
								}
								, type: 'numberStr'
							})
							, NisGridUtil.columnDefDefault({
								field : 'tanka'
								, displayName: '単価'
								, width: 90
								, cellClass: 'text-right'
								, cellFilter: 'number:2'
								, filter : {
										condition : function(searchTerm, cellValue, row) {
											var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
											if(!isNaN(searchValue) && !isNaN(cellValue)){
												return Number(searchValue) <= cellValue;
											}
											return false;
										},
									}
								, type: 'numberStr'
							})
								, NisGridUtil.columnDefDefault({
								field : 'suryo'
								, displayName: '数量'
								, width: 90
								, cellClass: 'text-right'
								, cellFilter: 'number:0'
								,filter : {
										condition : function(searchTerm, cellValue, row) {
											var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
											if(!isNaN(searchValue) && !isNaN(cellValue)){
												return Number(searchValue) <= cellValue;
											}
											return false;
										},
									}
								, type: 'numberStr'
							})
							, NisGridUtil.columnDefDefault({
								field : 'juryo'
								, displayName: '重量'
								, width: 90
								, cellClass: 'text-right'
								, cellFilter: 'number:3'
									,filter : {
										condition : function(searchTerm, cellValue, row) {
											var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
											if(!isNaN(searchValue) && !isNaN(cellValue)){
												return Number(searchValue) <= cellValue;
											}
											return false;
										},
									}
								, type: 'numberStr'
							})
							, NisGridUtil.columnDefDefault({
								field : 'yukoMei'
								, displayName: '有無'
								, width: 100
								, cellFilter : 'options:\'yukoKbnFilter\''
								, filter : {
				                   	type : uiGridConstants.filter.SELECT,
				        		}
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
		});
		// 描画遅れ暫定対処 --------------------------------------------------------------------- ↑
		
	};

	ListModalController.$inject = injectParams;
	
	angular.module('app').controller(name, ListModalController);
	
}());