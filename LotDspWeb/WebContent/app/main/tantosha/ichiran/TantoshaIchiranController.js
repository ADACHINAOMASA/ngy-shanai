(function () {

    var injectParams = [
        '$scope', '$state', '$window', 'SysConst', 
        'i18nService', 'uiGridConstants', 'NisGridUtil', 
        'AlertService', 'ModalService', 'LoginService', 
        'TantoshaService', 'input', 'messages', 'header'
    ];

    var newController = function (
		$scope, $state, $window, SysConst, 
		i18nService, uiGridConstants, NisGridUtil, 
		AlertService, ModalService, LoginService, 
		TantoshaService, input, messages, header
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		//セッションから$scopeにユーザ情報をロードする。
		LoginService.loadUserProfile($scope);

		// 入力情報
		$scope.input = input || {};
		//ヘッダー情報
		$scope.header = header || {};
		//プロパティメッセージ
		$scope.messages = messages || {};
    	
		// ステータス ------------------------------------------------------------------------------ ↓
		$scope.state = {
			// 検索フラグ
			searched : false,
			// 新規覚醒フラグ
			isNew: true,
		};
		// ステータス ------------------------------------------------------------------------------ ↑
		
		// 初期値 ------------------------------------------------------------------------------- ↓
		// タイトル
		$scope.title = SysConst.tantosha.ichiran.title;
		// GridStorageName作成
		var gridStorageName = $scope.header.userProfile.userId + "_" + SysConst.tantosha.ichiran.gridStorageName;
		// 初期値 ------------------------------------------------------------------------------- ↑
		
		// Action ------------------------------------------------------------------------------ ↓
		$scope.action = {
			// 検索
			search : function() {
				ModalService.loading(
					TantoshaService.list(NisUtil.createRequestData($scope.input.kensakuInfo))
				).then(function(data){
					$scope.records = data;
					$scope.state.searched = true;
				});
			},
			// 検索エリアクリア
			clearKensakuInfo: function () {
                $scope.input.kensakuInfo = {};
                $scope.records = [];
                $scope.gridApi.grid.clearAllFilters();
            },
            // 登録情報クリア
            clearTorokuInfo: function () {
                $scope.input.torokuInfo = {};
                $scope.state.isNew = true;
            },
            // 戻る
    		back : function() {
    			if ($scope.TantoshaTorokuForm.$dirty) {
                    ModalService.openConfirm({
                        message: '保存されていない変更がありますが、戻ってよろしいですか'
                    }).then(function () {
                        $state.go('home');
                    });
                    return;
                }
                $state.go('home');
			},
			// 画面トップへ
			toPageTop: function() {
				$window.scrollTo(0, 0);
			},
			// 一覧ボタン
			list : {
				// 編集
				select : function (record) {
					ModalService.loading(TantoshaService.get(record.tantoshaCd)).then(function(data){
						$scope.input.torokuInfo = data;
                        $scope.state.isNew = false;
					});
				},
			},
			// 登録
			save: function (msg) {
                AlertService.clear('validate');
                if (!NisUtil.checkFormValid($scope.TantoshaTorokuForm)) {
                    AlertService.addWarning('入力内容にエラーが残っています。', 'validate', true);
                    return;
                }
                ModalService.openConfirm({
                    message: msg + 'します。よろしいですか'
                }).then(function () {
                    ModalService.loading(
                    		TantoshaService.save($scope.input.torokuInfo.tantoshaCd, NisUtil.createRequestData($scope.input.torokuInfo))
                    ).then(
                        function (data) {
                            $scope.action.clearTorokuInfo();
                            $scope.action.search();
                            $scope.loginMasterTorokuForm.$dirty = false;
                        }
                    )
                }).then(function () {});
            },
            // 削除
			remove : function() {
				ModalService.openConfirm({
	                   message: '削除します。よろしいですか'
                }).then(function () {
                    ModalService.loading(
                    	TantoshaService.remove($scope.input.torokuInfo.tantoshaCd, NisUtil.createRequestData($scope.input.torokuInfo))
                    ).then(
                        function (data) {
                            $scope.action.clearTorokuInfo();
                            $scope.action.search();
                            $scope.loginMasterTorokuForm.$dirty = false;
                        }
                    )
                }).then(function () {});
			},
		};
		// Action ------------------------------------------------------------------------------ ↑
		
		// 頁遷移 ------------------------------------------------------------------------------- ↓
		$scope.gridOptions = NisGridUtil.gridOptionsDefault({
				onRegisterApi: function(gridApi){
					$scope.gridApi = gridApi;
					// Ｇｒｉｄの状態をLocalStorageに保存
					if(SysConst.tantosha.ichiran.gridStorageEnable){
						NisGridUtil.saveDefaultState($scope.gridApi, gridStorageName);//Ｇｒｉｄの最初状態を保持
				        NisGridUtil.loadState($scope, $scope.gridApi, gridStorageName);
				        NisGridUtil.setStateSaveEvent($scope, $scope.gridApi, gridStorageName);
					}
				},
				showColumnFooter: true,
				exporterCsvFilename: '担当者一覧.csv',
				data: 'records',
				minRowsToShow: 20,
				columnDefs: [
				    NisGridUtil.columnDefButtonDefault({
				    	name:'buttons', 
				    	displayName: '', 
				    	cellTemplate: 'record_buttons', 
				    	width: 80,
				    }),
				    NisGridUtil.columnDefDefault({
				    	name:'tantoshaCd', 
				    	displayName: '担当者コード', 
				    	width: 120,
            		}),
	            	NisGridUtil.columnDefDefault({
				    	name:'tantoshaMei', 
				    	displayName: '担当者名', 
				    	width: 180,
            		}),
	            	NisGridUtil.columnDefDefault({
        				name:'tantoshaKana', 
				    	displayName: '担当者カナ', 
				    	width: 180,
            		}),
	            	NisGridUtil.columnDefDefault({
        				name:'jigyoshoCd', 
				    	displayName: '事業所コード', 
				    	width: 120,
            		}),
	            	NisGridUtil.columnDefDefault({
        				name:'bumonCd', 
				    	displayName: '部門コード', 
				    	width: 120,
            		}),
	            	NisGridUtil.columnDefDefault({
        				name:'shiyotanmatsu1', 
				    	displayName: '使用端末1', 
				    	width: 180,
            		}),
	            	NisGridUtil.columnDefDefault({
        				name:'shiyotanmatsu2', 
				    	displayName: '使用端末2', 
				    	width: 180,
            		}),
	            	NisGridUtil.columnDefDefault({
        				name:'shiyotanmatsu3', 
				    	displayName: '使用端末3', 
				    	width: 180,
	            	}),
	            	NisGridUtil.columnDefDefault({
	                    name:'juchuKengen', 
				    	displayName: '受注管理権限', 
				    	width: 150,
	                }),
	                NisGridUtil.columnDefDefault({
	                    name:'kobaiKengen', 
				    	displayName: '仕入管理権限', 
				    	width: 150,
	                }),
	                NisGridUtil.columnDefDefault({
	                    name:'uriageKengen', 
				    	displayName: '売上管理権限', 
				    	width: 150,
	               }),
	               NisGridUtil.columnDefDefault({
				    	name:'zaikoKengen', 
				    	displayName: '在庫管理権限', 
				    	width: 150,
	               }),
	               NisGridUtil.columnDefDefault({
	                    name:'urikakeKengen', 
				    	displayName: '売掛管理権限', 
				    	width: 150,
	               }),
	               NisGridUtil.columnDefDefault({
	                    name:'kyotsuKengen', 
				    	displayName: '共通管理権限', 
				    	width: 150,
	               }),
	               NisGridUtil.columnDefDefault({
	                    name:'biko', 
				    	displayName: '備考', 
				    	width: 300,
	               }),
	               NisGridUtil.columnDefDefault({
	                   field: 'version',
	                   displayName: 'バージョン',
	                   visible: false,
	                   width: 100,
	               }),
	               NisGridUtil.columnDefDefault({
	                   field: 'torokuUserid',
	                   displayName: '登録ユーザーID',
	                   visible: false,
	                   width: 180,
	               }),
	               NisGridUtil.columnDefDefault({
						field : 'torokuYmdhms',
						displayName : '更新日時',
						visible: false,
						cellFilter : 'date:"yyyy/MM/dd HH:mm:ss"',
						filter : {
							condition : function(searchTerm, cellValue,row) {
								return NisUtil.nullToBlank(
									NisUtil.formatDate(cellValue,'yyyy/MM/dd HH:mm:ss')
								).search(searchTerm) >= 0;
							},
							placeholder : 'yyyy/MM/dd HH:mm:ss',
						},
						type : 'date',
						width : 180,
	               }),
	               NisGridUtil.columnDefDefault({
                	   field: 'torokuFrontid',
	                   displayName: '登録フロントID',
	                   visible: false,
	                   width: 180,
	               }),
	               NisGridUtil.columnDefDefault({
	               	   field: 'KoshinUserid',
	                   displayName: '更新ユーザーID',
	                   visible: false,
	                   width: 180,
	               }),
	               NisGridUtil.columnDefDefault({
						field : 'koshinYmdhms',
						displayName : '更新日時',
						visible: false,
						cellFilter : 'date:"yyyy/MM/dd HH:mm:ss"',
						filter : {
							condition : function(searchTerm, cellValue,row) {
								return NisUtil.nullToBlank(
									NisUtil.formatDate(cellValue,'yyyy/MM/dd HH:mm:ss')
								).search(searchTerm) >= 0;
							},
							placeholder : 'yyyy/MM/dd HH:mm:ss',
						},
						type : 'date',
						width : 180,
					}),
					NisGridUtil.columnDefDefault({
	                   field: 'koshinFrontid',
	                   displayName: '更新フロントID',
	                   visible: false,
	                   width: 180,
	               }),
	            ],
				gridMenuCustomItems: [{
						title: 'フィルター',
						action: function ($event) {
							$scope.gridOptions.enableFiltering = !$scope.gridOptions.enableFiltering;
							$scope.gridApi.core.notifyDataChange( uiGridConstants.dataChange.COLUMN );
						},
						order: 210
					}
					,{
						title: '初期化',
						action: function ($event) {
							// LocalStorageからＧｒｉｄの最初状態をロードする。
							if(SysConst.tantosha.ichiran.gridStorageEnable){
								NisGridUtil.loadDefaultState($scope, $scope.gridApi, gridStorageName);
							}
						},
						order: 211
					}
				]
		});

    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('TantoshaIchiranController', newController);

}());
