(function() {

	var name = 'ListModalService';
	
	// 必要な依存を列挙
	var injectParams = [
        '$http', '$q', '$uibModal', 'uiGridConstants', 'SysConst',
        'ModelService', 'ListService', 'TantoshaService', 
        'MitsumoriService', 'TorihikisakiService', 'BuhinService', 
        'KakoKeisanService', 'CustomListService', 'HinmokubetsuChokurohiService', 
        'KakushuService', 'GenkaSekisanService'
    ];

	// 引数は依存の内容と一致する
	var ListModalService = function(
		$http, $q, $uibModal, uiGridConstants, SysConst,
		ModelService, ListService, TantoshaService, 
		MitsumoriService, TorihikisakiService, BuhinService, 
		KakoKeisanService, CustomListService, HinmokubetsuChokurohiService, 
		KakushuService, GenkaSekisanService
	) {

        //メッセージ
		var messageParams = [ 
		 {key: 'common.confirm'  , params: []}, 
		 {key: 'common.complete' , params: []},
		 {key: 'common.done'     , params: []},
		 {key: 'error.common.required',params:[]},
		 {key: 'error.common.free',params:[]}
        ];
		
		return {
			// 見積番号一覧検索
			openMitsumoriNoList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/mitsumorino/MitsumoriNoListModal.html',
					controller: 'MitsumoriNoListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openMitsumoriNoSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.mitsumoriNoList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'mitsumoriNoWithHansu',
							displayName: '見積番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'kenmei',
							displayName: '件名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			
			openGenkaMitsumoriNoSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.genkaMitsumoriNoList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'mitsumoriNoWithHansu',
							displayName: '見積番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'kenmei',
							displayName: '件名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			
			// 受注番号一覧検索	
			openJuchuNoList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/juchuno/JuchuNoListModal.html',
					controller: 'JuchuNoListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openJuchuNoSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.juchuNoList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'juchuNoWithKoNo',
							displayName: '受注番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'hinmei',
							displayName: '品名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			
			openGenkaJuchuNoSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.genkaJuchuNoList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'juchuNoWithKoNo',
							displayName: '受注番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'hinmei',
							displayName: '品名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			
			
			//製造番号と紐づいた受注リスト
			openJuchuNoSimpleListFromSeisakuShiji: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.juchuNoListFromSeisakuShiji(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'juchuNoWithKoNo',
							displayName: '受注番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'hinmei',
							displayName: '品名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 製造番号一覧検索
			openSeizNoList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/seizno/SeizNoListModal.html',
					controller: 'SeizNoListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openSeizNoSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.seizoNoList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'seizoNo',
							displayName: '製造番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'hinmei',
							displayName: '品名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			
			openGenkaSeizNoSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.genkaSeizoNoList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'seizoNo',
							displayName: '製造番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'hinmei',
							displayName: '品名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			
			// 担当者一覧検索
			openTantoshaList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/tantosha/TantoshaListModal.html',
					controller: 'TantoshaListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openTantoshaSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.tantoshaList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'tantoshaCd',
							displayName: '担当者コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'tantoshaMei',
							displayName: '担当者名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 得意先一覧検索
			openTokuisakiList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/tokuisaki/TokuisakiListModal.html',
					controller: 'TokuisakiListModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							var kensakuInfo = param.kensakuInfo || {};
							kensakuInfo.kbnTokuisaki = SysConst.torihikisaki.kbnTokuisaki.on;
							return kensakuInfo;
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openTokuisakiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						var kensakuInfo = param.kensakuInfo || {};
						kensakuInfo.kbnTokuisaki = SysConst.torihikisaki.kbnTokuisaki.on;
						return CustomListService.torihikisakiList(kensakuInfo);
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'torihikisakiCd',
							displayName: '得意先コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string',
						}, {
							field: 'torihikisakiMei',
							displayName: '得意先名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 納入先一覧検索
			openNonyusakiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.nonyusakiList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'nonyusakiCd',
							displayName: '納入先コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'nonyusakiMei',
							displayName: '納入先名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 部品番号検索
			openBuhinList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/buhin/BuhinSimpleListModal.html',
					controller: 'BuhinSimpleListModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			// 部品検索
			openBuhinKensakuList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/buhin/BuhinKensakuListModal.html',
					controller: 'BuhinKensakuListModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openBuhinSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.buhinList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'buhinCd',
							displayName: '部品番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'buhinMei',
							displayName: '部品名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md',
				});
			},
			// 仕入一覧
			openShiiresakiList: function(param) {
				param = param || {};
				
				var kensakuInfo = param.kensakuInfo || {};
				kensakuInfo.kbnShiresaki = SysConst.torihikisaki.kbnShiresaki.on;
				
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/shiiresaki/ShiiresakiListModal.html',
					controller: 'ShiiresakiListModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openShiiresakiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						var kensakuInfo = param.kensakuInfo || {};
						kensakuInfo.kbnShiresaki = SysConst.torihikisaki.kbnShiresaki.on;
						return TorihikisakiService.list(kensakuInfo);
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'torihikisakiCd',
							displayName: '仕入先コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'torihikisakiMei',
							displayName: '仕入先名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 外注加工先一覧
			openGaichusakiList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/gaichusaki/GaichusakiListModal.html',
					controller: 'GaichusakiListModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openGaichusakiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return TorihikisakiService.list(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'torihikisakiCd',
							displayName: '外注加工先コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'torihikisakiMei',
							displayName: '外注加工先名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 用途検索
			openYotoList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/yoto/YotoListModal.html',
					controller: 'YotoListModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			// 品目一覧
			openHinmokuList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/hinmoku/HinmokuListModal.html',
					controller: 'HinmokuListModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openHinmokuSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						if (param.isList) {
							return param.data || [];
						} else {
							return KakoKeisanService.hinmokuList(param.kensakuInfo || {});
						}
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'hinmokuCdHyouzi',
							displayName: '品目コード',
							width: 120,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'hinmokuMei',
							displayName: '品目名',
							width: 250,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'kakuteiFlgMei',
							displayName: 'ドラフト',
							width: 132,
							enableFiltering: false
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 単価一覧（品目別直浪費一覧）
			openHinmokubetsuChokurohiList: function(param) {
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/hinmokubetsuchokurohi/HinmokubetsuChokurohiListModal.html',
					controller: 'HinmokubetsuChokurohiListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openHinmokubetsuChokurohiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						return HinmokubetsuChokurohiService.list(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'hinmokuCd',
							displayName: '品目コード',
							width: 120,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'zairyoBunruiCd',
							displayName: '材料分類コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'yotoBunruiCd',
							displayName: '用途分類コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'chokuroHi',
							displayName: '直労費',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							cellFilter: 'number:0',
							cellClass: 'text-right',
							type: 'numberStr'
						}, {
							field: 'jissekiChokuroHi',
							displayName: '実績直労費',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							cellFilter: 'number:0',
							cellClass: 'text-right',
							type: 'numberStr'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 材料区分一覧
			openZairyoKbnList: function(param) {
				param = param || {};
				
				var kensakuInfo = param.kensakuInfo || {};
				kensakuInfo.mstKbn = SysConst.kakushuMasterKbn.ZairyoKbn;
				
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/zairyokbn/ZairyoKbnListModal.html',
					controller: 'ZairyoKbnListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openZairyoKbnSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						var kensakuInfo = param.kensakuInfo || {};
						kensakuInfo.mstKbn = SysConst.kakushuMasterKbn.ZairyoKbn;
						
						return KakushuService.list(kensakuInfo);
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'mstCd',
							displayName: '材料区分コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'naiyo',
							displayName: '材料区分名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 作業工程一覧
			openSagyokoteiList: function(param) {
				param = param || {};
				
				var kensakuInfo = param.kensakuInfo || {};
				kensakuInfo.mstKbn = SysConst.kakushuMasterKbn.Sagyokotei;
				
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/sagyokotei/SagyokoteiListModal.html',
					controller: 'SagyokoteiListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openSagyokoteiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						var kensakuInfo = param.kensakuInfo || {};
						kensakuInfo.mstKbn = SysConst.kakushuMasterKbn.Sagyokotei;
						
						return KakushuService.list(kensakuInfo);
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'mstCd',
							displayName: '工程コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'naiyo',
							displayName: '工程名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			// 設備一覧
			openSagyoSetsubiList: function(param) {
				param = param || {};
				
				var kensakuInfo = param.kensakuInfo || {};
				kensakuInfo.mstKbn = SysConst.kakushuMasterKbn.SagyoSetsubi;
				
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/sagyosetsubi/SagyoSetsubiListModal.html',
					controller: 'SagyoSetsubiListModalController',
					size: param.size || 'md', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
			openSagyoSetsubiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						var kensakuInfo = param.kensakuInfo || {};
						kensakuInfo.mstKbn = SysConst.kakushuMasterKbn.SagyoSetsubi;
						
						return KakushuService.list(kensakuInfo);
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'mstCd',
							displayName: '設備コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'naiyo',
							displayName: '設備名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			//用途分類一覧
			openYotoBunruiSimpleList: function(param) {
				param = param || {};
				return ListService.open({
					data: function() {
						var kensakuInfo = param.kensakuInfo || {};
						kensakuInfo.mstKbn = SysConst.kakushuMasterKbn.YotoBunrui;
						
						return KakushuService.list(kensakuInfo);
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'mstCd',
							displayName: '用途分類コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'naiyo',
							displayName: '用途分類名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			//原価番号一覧
			openGenkaNoSimpleList: function(param){
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.genkaNoList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'genkaNoWithMeisaiNo',
							displayName: '原価番号',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'kenmei',
							displayName: '件名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			//費目一覧
			openHimokuSimpleList: function(param){
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.himokuList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'himokuCd',
							displayName: '費目コード',
							width: 150,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}, {
							field: 'himokuMei',
							displayName: '費目名',
							width: 352,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			//材質一覧
			openZaishitsuSimpleList: function(param){
				param = param || {};
				return ListService.open({
					data: function() {
						return CustomListService.zaishitsuList(param.kensakuInfo || {});
					},
					columnDefs: function() {
						return param.columnDefs || [{
							field: 'zaishitsu',
							displayName: '材質',
							width: 502,
							filter: {
								condition: uiGridConstants.filter.CONTAINS
							},
							type: 'string'
						}];
					},
					setKey: param.setKey || null,
					multiSelect: param.multiSelect || false,
					size: param.size || 'md', // sm, md, lg
				});
			},
			//仕入履歴一覧
			openShiireRirekiList: function(param){
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/shiirerireki/ShiireRirekiModal.html',
					controller: 'ShiireRirekiModalController',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
		               		return MessageService.getMessages({messages:messageParams});
		                   },
					},
				});
				return modalInstance.result;
			},
			//部品加工情報検索
			openBuhinKakoJohoKensakuList: function(param){
				param = param || {};
				var modalInstance = $uibModal.open({
					animation: true,
					templateUrl: 'app/main/list/buhinkakojoho/BuhinKakoJohoKensaku.html',
					controller: 'BuhinKakoJohoKensakuController',
					windowClass: 'c-modal-window',
					size: param.size || 'lg', // sm, md, lg
					resolve: {
						input: function(){
							return param.kensakuInfo || {};
						},
						'multiSelect': function(){
							return param.multiSelect || false;
						},
						header : function(HeaderService){
							return HeaderService.getHeader();
						},
						messages: function(MessageService){
	                		return MessageService.getMessages({messages:messageParams});
	                    },
					},
				});
				return modalInstance.result;
			},
		};
	};
	
	ListModalService.$inject = injectParams;
	
	angular.module('app')
		// factory | service | provider
		.factory(name, ListModalService);
	
}());