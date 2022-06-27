(function () {
	
	var name = 'ShiireRirekiModalController';
	
    var injectParams = [
        '$scope', '$uibModalInstance', '$q','input', 'multiSelect', 
        'uiGridConstants', '$timeout', 'NisGridUtil', 'SysConst', 
        'i18nService', 'ModalService', 'LoginService', 'BuhinService',
        'messages', 'header', 'ShiireRirekiService'
    ];
    
    var ListModalController = function (
		$scope, $uibModalInstance, $q,input, multiSelect, 
		uiGridConstants, $timeout, NisGridUtil, SysConst, 
		i18nService, ModalService, LoginService, BuhinService,
		messages, header, ShiireRirekiService
	) {
		$scope.uigrid = SysConst.uigrid;
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		//セッションから$scopeにユーザ情報をロードする。
		LoginService.loadUserProfile($scope);
		
		// ステータス  ---------------------------------------------------------------------------- ↓
		$scope.state = {
		};
		// ステータス  ---------------------------------------------------------------------------- ↑
		
		// 初期値 ------------------------------------------------------------------------------ ↓
		// タイトル
		$scope.title = SysConst.shiireRireki.title;
		// 入力情報
		$scope.input = input || {};
		//ヘッダー情報
		$scope.header = header || {};
		//プロパティメッセージ
		$scope.messages = messages || {};
		// Grigのデータ
		$scope.records = [];
		// 初期値 ------------------------------------------------------------------------------ ↑
		// Action ---------------------------------------------------------------------------- ↓
		$scope.action = {
			// 検索
			search : function() {
				ModalService.loading(ShiireRirekiService.list($scope.input))
				.then(function(data){
					$scope.records = data;
				});
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
					
					NisGridUtil.setOptionsFilter($scope.gridOptions, 'taniFilter', 'taniMei');
				},
				data: 'records',
				columnDefs: [
							NisGridUtil.columnDefDefault({
								field: 'shiireYmd'
								, displayName: '仕入日付'
								, width: 120
								,cellFilter : 'date:"yyyy/MM/dd"'
								,filter : {
									condition : function(searchTerm, cellValue,row) {
										return NisUtil.nullToBlank(
											NisUtil.formatDate(cellValue,'yyyy/MM/dd')
										).search(searchTerm) >= 0;
									},
									placeholder : 'yyyy/MM/dd',
								}
								, type: 'date'
							})
							,NisGridUtil.columnDefDefault({
								field: 'shiiresakiCd'
								, displayName: '仕入先コード'
								, width: 120
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
							})
							,NisGridUtil.columnDefDefault({
								field: 'shiiresakiMei'
								, displayName: '仕入先名'
								, width: 300
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
								, cellTooltip:true
							})
							,NisGridUtil.columnDefDefault({
								field: 'buhin'
								, displayName: '部品'
								, width: 300
								, filter:{condition: uiGridConstants.filter.CONTAINS}
								, type: 'string'
								, cellTooltip:true	
							})
							,NisGridUtil.columnDefDefault({
								field: 'shiireSuryo'
								, displayName: '仕入数量'
								, width: 100
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
								field : 'shiireJuryo'
								, displayName: '仕入重量'
								, width: 100
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
							,NisGridUtil.columnDefDefault({
								field: 'taniMei'
								, displayName: '単位'
								, width: 80
								,cellFilter : 'options:\'taniFilter\''
								,filter : {
									type : uiGridConstants.filter.SELECT,
						        }
								, type: 'string'
							})
							,NisGridUtil.columnDefDefault({
								field: 'tanka'
								, displayName: '単価'
								, width: 100
								, cellFilter: 'number:2'
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
							,NisGridUtil.columnDefDefault({
								field: 'shiireKingaku'
								, displayName: '仕入金額'
								, width: 130
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