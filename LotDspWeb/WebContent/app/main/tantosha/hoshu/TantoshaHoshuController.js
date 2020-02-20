(function () {

    var injectParams = [
        '$scope', '$state', '$window', 'SysConst', 
        'i18nService', 'uiGridConstants', 'NisGridUtil', 
        'AlertService', 'ModalService', 'LoginService', 
        'TantoshaService', 'input', 'messages', 
        'header', 'ModelService','records'
    ];

    var newController = function (
		$scope, $state, $window, SysConst, 
		i18nService, uiGridConstants, NisGridUtil, 
		AlertService, ModalService, LoginService, 
		TantoshaService, input, messages, 
		header, ModelService, records
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
			// 現在のパスワード保持用
			password: "",
		};
		// ステータス ------------------------------------------------------------------------------ ↑
        
		ModelService.newModel("TantoshaInfo").then(function(model) {
     		$scope.tantoshaInfo = model;
        });
        
        ModelService.newModel("TantoshaKensakuInfo").then(function(model) {
     		$scope.tantoshaKensakuInfo = model;
        });
        
		// 初期値 ------------------------------------------------------------------------------- ↓
		// タイトル
		$scope.title = SysConst.tantoshaHoshu.ichiran.title;
		// GridStorageName作成
		var gridStorageName = $scope.header.userProfile.userId + "_" + SysConst.tantoshaHoshu.ichiran.gridStorageName;
		// グリッドのデータ
		$scope.records = records || [];
		// 初期値 ------------------------------------------------------------------------------- ↑
		
		// Action ------------------------------------------------------------------------------ ↓
		$scope.action = {
			// 検索
			search : function() {
				TantoshaService.list(NisUtil.createRequestData($scope.tantoshaHoshuKensakuInfo))
				.then(function(data){
					$scope.records = data;
					$scope.state.searched = true;
				});
			},
			// 検索エリアクリア
			clearKensakuInfo: function () {
				AlertService.clear('validate');
                $scope.tantoshaHoshuKensakuInfo = [];
                $scope.records = [];
                $scope.gridApi.grid.clearAllFilters();
            },
            // 登録情報クリア
            clearTorokuInfo: function () {
                $scope.tantoshaInfo = angular.copy($scope.tantoshaInfo);
                $scope.state.isNew = true;
            },
            // 戻る
    		back : function() {
    			if ($scope.TantoshaHoshuTorokuForm.$dirty) {
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
					ModalService.loading(
							TantoshaService.shutoku(
		        			record.tantoshaCd, NisUtil.createRequestData(record)
		        			)
	                    ).then(function(data){
						$scope.action.clearTorokuInfo();
						$scope.tantoshaInfo = data;
                        $scope.state.isNew = false;
                        // 現在のパスワードを退避しておく
                        $scope.state.password = $scope.tantoshaInfo.password;
					});
				},
				// 複製
				fukusei : function (record) {
					ModalService.loading(
							TantoshaService.fukusei(
		        			record.tantoshaCd, NisUtil.createRequestData(record)
		        			)
	                    ).then(function(data){
						$scope.action.clearTorokuInfo();
						$scope.tantoshaInfo = data;
                        $scope.state.isNew = true;
					});
				},
			},
			// 登録
			save: function (msg) {
                AlertService.clear('validate');
                if (!NisUtil.checkFormValid($scope.TantoshaHoshuTorokuForm)) {
                    AlertService.addWarning('入力内容にエラーが残っています。', 'validate', true);
                    return;
                }
                ModalService.openConfirm({
                    message: msg + 'します。よろしいですか'
                }).then(function () {
            		// パスワードチェック回避の一時的なロジック
                	$scope.tantoshaInfo.newPassword = $scope.tantoshaInfo.password;
                	$scope.tantoshaInfo.newPasswordConfirm = $scope.tantoshaInfo.password;
                	//更新時には現在のパスワードに退避しておいたパスワードをセットしておく
                	if (!$scope.state.isNew){
                		$scope.tantoshaInfo.password = $scope.state.password;
                	}
                	// 新規の場合は登録フロントIDにセット、更新の場合には更新フロントIDにセット
                	if ($scope.state.isNew){
                		$scope.tantoshaInfo.torokuFrontid = SysConst.tantoshaHoshu.torokuFrontid;
                	} else {
            			$scope.tantoshaInfo.koshinFrontid = SysConst.tantoshaHoshu.koshinFrontid;
            		}
                    ModalService.loading(
                    		TantoshaService.save($scope.tantoshaInfo.tantoshaCd, NisUtil.createRequestData($scope.tantoshaInfo))
                    ).then(
                        function (data) {
                            $scope.action.search();
                            $scope.tantoshaInfo.password = $scope.tantoshaInfo.newPassword;
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
                    	TantoshaService.remove($scope.tantoshaInfo.tantoshaCd, NisUtil.createRequestData($scope.tantoshaInfo))
                    ).then(
                        function (data) {
                            $scope.action.clearTorokuInfo();
                            $scope.action.search();
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
					if(SysConst.tantoshaHoshu.ichiran.gridStorageEnable){
						NisGridUtil.saveDefaultState($scope.gridApi, gridStorageName);//Ｇｒｉｄの最初状態を保持
				        NisGridUtil.loadState($scope, $scope.gridApi, gridStorageName);
				        NisGridUtil.setStateSaveEvent($scope, $scope.gridApi, gridStorageName);
					}
				},
				showColumnFooter: true,
	            paginationPageSizes: SysConst.uigrid.paginationPageSizes,
	            paginationPageSize: SysConst.uigrid.paginationPageSize,
				exporterCsvFilename: '担当者一覧.csv',
				data: 'records',
				minRowsToShow: 9,
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
						displayName : '登録日時',
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
							if(SysConst.tantoshaHoshu.ichiran.gridStorageEnable){
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
        .controller('TantoshaHoshuController', newController);

}());
