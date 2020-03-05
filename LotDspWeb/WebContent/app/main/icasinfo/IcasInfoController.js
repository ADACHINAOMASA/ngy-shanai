
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = 'IcasInfoController';
    var lotInfoStorage = 'ngStorage-lotDspInfo';
    var lotInfoParamStorage = 'ngStorage-lotDspInfoParam';

    // 必要な依存を列挙
    var injectParams = [
        '$scope', '$state', '$interval', '$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService', 'uiGridConstants'
        , 'NisGridUtil', 'AlertService', 'ModalService', 'ModelService', 'AlertLogService'
        , '$base64','CommonService', '$localStorage','CommonStorage', 'MenuService', 'SearchService'
    ];

    // 引数は依存の内容と一致する
    var newController = function (
        $scope, $state, $interval, $httpParamSerializer, $window, $q, $timeout, i18nService, uiGridConstants
        , NisGridUtil, AlertService, ModalService, ModelService, AlertLogService
        , $base64,CommonService, $localStorage,CommonStorage, MenuService, SearchService
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');

		//変数初期化
		//共通項目
		$scope.common = {};
		//進度情報基本データ
		$scope.staffProgressInfo = {};
		
		$scope.icasInfo = {};
		$scope.icasBoxInfos = [];
		
		//---------------------------------------------------------------
		// アクション定義
        $scope.action = {
        	//初期表示時に実行
        	init:function(){
        		//共通データの設定
        		$scope.action.setInputData(JSON.parse(localStorage.getItem(lotInfoStorage)));
        		$scope.action.setCommonData(JSON.parse(localStorage.getItem(lotInfoStorage)));
        		//画面別データ取得
        		$scope.action.setIcasInfosData(JSON.parse(localStorage.getItem(lotInfoStorage)).icasBean);
		        //ICAS情報用のパラム
		        $scope.common.site = JSON.parse(localStorage.getItem(lotInfoParamStorage)).site;
        	}
	    	//共通データの設定
			,setInputData:function(data){
				//検索項目の設定
				$scope.common.searchLtno = data.searchLtno;
				$scope.common.searchKnno = data.searchKnno;
			}
	    	//共通データの設定
			,setCommonData:function(data){
		        //最終更新日
		        $scope.common.edtm = data.staffCommonBean.edtm;
		        //検索結果総件数
		        $scope.common.lotMaximum = data.lotMaximum;
		        //現在表示ページ
		        $scope.common.nowPage = data.nowPage;
		        //表示制御
		        $scope.common.tabSetRendered = data.tabSetRendered;
			}
        	//データの設定
    		,setIcasInfosData:function(data){
    			// 設定
    			$scope.icasInfo = data;
    			$scope.icasBoxInfos = [];
    			if ($scope.icasInfo.icasBoxInfos != null) {
        			if ($scope.icasInfo.icasBoxInfos.length > 0) {
        		        for(var i = 0; i < $scope.icasInfo.icasBoxInfos.length; i++) {
        	    			$scope.icasBoxInfos.push($scope.icasInfo.icasBoxInfos[i]);
        		        }
            		}
    			}
    		}
    		//検索
    		,search:function(){
		    	$("#messageArea").text("");
    			if (!$scope.common.searchLtno && !$scope.common.searchKnno) {
    		    	$("#messageArea").text("実行エラー");
    			}
    			// データ再設定
	        	var param = {
	        			lotNo: $scope.common.searchLtno, 
	        			kensaNo: $scope.common.searchKnno,
	        			nowPage: 0
	        	};
				ModalService.showProcessing(SearchService.searchAction(param), {message:'処理中・・・'}).then(function(data) {
    				if(data.errorFlg){
    					$("#messageArea").css("color", "red");
    					$("#messageArea").text(data.message);
    				}else{
    					// ローカルストレージに再設定
    					MenuService.memory.saveBaseWork(data);
    	        		//共通データの設定
    	        		$scope.action.setInputData(data);
    	        		$scope.action.setCommonData(data);
    	        		//画面別データ取得
    	        		$scope.action.setIcasInfosData(data.icasBean);
    				}
				});
    		}
    		//移動
    		,nextPage:function(){
		    	//メッセージエリアのクリア
		    	$("#messageArea").text("");
		    	//次へ移動
		        if ($scope.common.nowPage < ($scope.common.lotMaximum - 1)) {
	    			// データ再設定
		        	var param = {
		        			lotNo: $scope.util.getLtno(),
		        			kensaNo: $scope.util.getKnno(),
		        			nowPage: ($scope.common.nowPage + 1)
		        	};
					ModalService.showProcessing(SearchService.searchAction(param), {message:'処理中・・・'}).then(function(data) {
	    				if(data.errorFlg){
	    					$("#messageArea").css("color", "red");
	    					$("#messageArea").text(data.message);
	    				}else{
	    					// ローカルストレージに再設定
	    					MenuService.memory.saveBaseWork(data);
	    	        		//共通データの設定
	    	        		$scope.action.setCommonData(data);
	    	        		//画面別データ取得
	    	        		$scope.action.setIcasInfosData(data.icasBean);
	    				}
					});
		        }
        	}
    		,previousPage:function(){
		    	//メッセージエリアのクリア
		    	$("#messageArea").text("");
		    	//前へ移動
		        if ($scope.common.nowPage > 0) {
	    			// データ再設定
		        	var param = {
		        			lotNo: $scope.util.getLtno(),
		        			kensaNo: $scope.util.getKnno(),
		        			nowPage: ($scope.common.nowPage - 1)
		        	};
					ModalService.showProcessing(SearchService.searchAction(param), {message:'処理中・・・'}).then(function(data) {
	    				if(data.errorFlg){
	    					$("#messageArea").css("color", "red");
	    					$("#messageArea").text(data.message);
	    				}else{
	    					// ローカルストレージに再設定
	    					MenuService.memory.saveBaseWork(data);
	    	        		//共通データの設定
	    	        		$scope.action.setCommonData(data);
	    	        		//画面別データ取得
	    	        		$scope.action.setIcasInfosData(data.icasBean);
	    				}
					});
		        }
        	}
    		//画面遷移
			,showProgressInfo:function(){
				$state.go('ProgressInfo');
    		}
    		,showManufacturingInfo:function(){
    			$state.go('ManufacturingInfo');
        	}
			,showTestInfo:function(){
				$state.go('TestInfo');
    		}
			,showQualityInfo:function(){
				$state.go('QualityInfo');
    		}
			,showCladInfo:function(){
				$state.go('CladInfo');
    		}
			,showCFInfo:function(){
				$state.go('CFInfo');
    		}
			// 閉じる
			,close:function(){
				window.close();
    		}
        };
		//---------------------------------------------------------------
        $scope.util = {
        	//前ボタンの表示・非表示判定
        	isPreviewRendered : function(data) {
        		if(data.nowPage == 0){
        			return false;
        		}else{
        			return true;
        		}
        	}
	    	//次ボタンの表示・非表示判定
			,isNextRendered : function(data) {
				if(data.nowPage >= (data.lotMaximum -1)){
					return false;
				}else{
					return true;
				}
			}
        	//ストレージの検索条件
        	,getLtno : function() {
        		return JSON.parse(localStorage.getItem(lotInfoStorage)).searchLtno;
        	}
        	,getKnno : function() {
        		return JSON.parse(localStorage.getItem(lotInfoStorage)).searchKnno;
        	}
    		// スタイル設定
    		,styleHeader : function() {
    			var styleHeader = "staff"
    			if ($scope.common.site == "1") {
    				// 現場の場合
    				styleHeader = "works";
    			}
    		    //ロット番号
    			$scope.schItem_Label_Ltno = styleHeader + "-input-ltno";
    			/*
    	        schItem_Label_Ltno.setStyleClass(styleHeader + "-label-ltno");
    	        ltno.setStyleClass(styleHeader + "-input-ltno");
    	        //検査番号
    	        schItem_Label_Knno.setStyleClass(styleHeader + "-label-knno");
    	        knno.setStyleClass(styleHeader + "-input-knno");
    	        //検索ボタン
    	        search.setStyleClass(styleHeader + "-button-search");
    	        //閉じるボタン
    	        close.setStyleClass(styleHeader + "-button-close");
    	        //ヒット件数
    	        hitCount.setStyleClass(styleHeader + "-text-hitcount");
    	        //メッセージ
    	        message.setStyleClass(styleHeader + "-text-message");
    	        //最終更新日
    	        label_edtm.setStyleClass(styleHeader + "-label-edtm");
    	        edtm.setStyleClass(styleHeader + "-text-edtm");
    	        //前頁
    	        previous_page.setStyleClass(styleHeader + "-image-previous");
    	        //次頁
    	        next_page.setStyleClass(styleHeader + "-image-next");*/
    		}
    		,styleIcasRoll : function() {
    			if ($scope.icasInfo == null) {
    				return "white"; 
    			}
    	        if ($scope.icasInfo.jcd202 == "44") {
    	            return "red";
    	        } else {
    	            return "white";
    	        }
    		}
    		,styleIcasHotpc : function() {
    			if ($scope.icasInfo == null) {
    				return "white"; 
    			}
                if ($scope.icasInfo.hotpc == "------") {
                    return "white";
                } else {
                    return "red";
                }
    		}
    		,styleIcasRyou : function() {
    			if ($scope.icasInfo == null) {
    				return "white"; 
    			}
    	        if ($scope.icasInfo.getRYOU() == "-----") {
    	            return "white";
    	        } else {
    	            return "lime";
    	        }
    		}
    		,styleIcasBr : function() {
    			if ($scope.icasInfo == null) {
    				return "white"; 
    			}
    	        if ($scope.icasInfo.a11 != null) {
    	            if ($scope.icasInfo.a11 != "K") {
    	                return "white";
    	            }
    	        } else {
    	            var br = 0.0;
    	            if ($scope.icasInfo.br != "-----") {
    	                br = parseFloat($scope.icasInfo.br);
    	            } else {
    	                return "white";
    	            }
    	            if ($scope.icasInfo.jcd201.substring(0, 1) == "1") {
    	                if (br >= 50 ) {
    	                    return "lime";
    	                }
    	            } else if ($scope.icasInfo.jcd201.substring(0, 1) == "2") {
    	                if (br >= 35 ) {
    	                    return "lime";
    	                }
    	            } else if ($scope.icasInfo.yotoc.substring(0, 2) == "E1") {
    	                if (br >= 70 ) {
    	                    return "lime";
    	                }
    	            } else {
    	                if (br >= 60 ) {
    	                    return "lime";
    	                }
    	            }
    	        }
    	        return "red";
    	    }
    		,styleIcasTr : function() {
    			if ($scope.icasInfo == null) {
    				return "white"; 
    			}
		        if ($scope.icasInfo.a11 != null) {
		            if ($scope.icasInfo.a11 != "K") {
		                return "white";
		            }
		        } else {
		            var tr = 0.0;
		            if ($scope.icasInfo.tr != "-----") {
		                tr = parseFloat($scope.icasInfo.tr);
		            } else {
		                return "white";
		            }
		            if ($scope.icasInfo.jcd201.substring(0, 1) == "1") {
		                if (tr >= 75 ) {
		                    return "lime";
		                }
		            } else if ($scope.icasInfo.jcd201.substring(0, 1) == "2") {
		                if (tr >= 75 ) {
		                    return "lime";
		                }
		            } else if ($scope.icasInfo.yotoc.substring(0, 2) == "E1") {
		                if (tr >= 90 ) {
		                    return "lime";
		                }
		            } else {
		                if (tr >= 75 ) {
		                    return "lime";
		                }
		            }
		        }
		        return "red";
    		}
    		,styleIcasBoxJisseki : function(icasBoxInfo) {
                //仮倉入
                if ((icasBoxInfo.a11 != null) && (icasBoxInfo.proc_KBN != null) && (icasBoxInfo.jbsm != null)) {
                    if (icasBoxInfo.a11 != "K" && icasBoxInfo.proc_KBN == "J" && icasBoxInfo.jbsm.trim() == "KEN") {
                        return "white";
                    }
                }
                if (icasBoxInfo.proc_KBN == "S") {
                    //板厚チェック ＣＦ処理設定
                    var wk_ani_f = null;
                    //テーブルに於ける行インデックスの取得
                    var curIdx = progressTable.getRowIndex(); 
                    if ($scope.icasInfo.akno != null) {
                        if (parseFloat($scope.icasInfo.akno) > 1) {
                            //テーブル値に於ける実績最終設備の位置設定
                        	var row = $scope.icasBoxInfos[parseFloat($scope.icasInfo.akno) - 2];
                        	//progressTable.setRowIndex(parseFloat($scope.icasInfo.akno) - 2);
                            //var jbsm = row.jbsm;
                            var jbx = parseFloat(row.jbx);
                            //テーブルに於ける行インデックスを戻す
                            //progressTable.setRowIndex(curIdx);
                            //最終実績設備の処理板厚と製造板厚の比較
                            if (jbx != parseFloat($scope.icasInfo.ltx)) {
                                wk_ani_f = "ANI";
                            } else {
                                wk_ani_f = "ANF";
                            }                    
                        } else {
                            wk_ani_f = "XXX";
                        }
                        //ＣＦステータス
                        if (parseFloat($scope.icasInfo.akno) >= curIdx + 1) {
                            if (icasBoxInfo.sbsm == (wk_ani_f)) {
                                if (parseFloat($scope.icasInfo.cfst) == 2) {
                                    return "white";
                                } else if (parseFloat($scope.icasInfo.cfst) == 3) {
                                    return "lime";
                                }                    
                            }                    
                        }
                    }
                    return "red";
                } else {
                    //実績あり
                    //HOT実績なし
                    if (parseFloat($scope.icasInfo.akno) == 2 && icasBoxInfo.jbsm == "HOT" && icasBoxInfo.proc_KBN == "S") {
                        return "white";
                    //分完（0：完了、1：分割）
                    } else {
                        if (icasBoxInfo.jbbn != null) {
                            if (parseFloat(icasBoxInfo.jbbn) == 1) {
                                return "white";
                            }
                        }
                    }
				    // 徐冷開始時の判断
				    // 設計設備名=ANF AND SBPAT = J AND 徐冷フラグ = 1 AND 徐冷開始日 != NULL AND 徐冷終了日 = NULL
				    // 上記条件を満たした場合；実績値のセルを「文字色：黒、背景色：白」
				    var sSBSM   = "";
				    var sSBPAT  = "";
				    var sJRFLG  = "";
				    var sJRSTDT = "";
				    var sJREDDT = "";
				    if (icasBoxInfo.sbsm != null) {
				    	sSBSM = icasBoxInfo.sbsm;
	                }
				    if (icasBoxInfo.sbpat != null) {
				    	sSBPAT = icasBoxInfo.sbpat;
	                }
				    if ($scope.icasInfo.jrflg != null) {
				    	sJRFLG = $scope.icasInfo.jrflg;
	                }
				    if ($scope.icasInfo.jrstdt != null) {
				    	sJRSTDT = $scope.icasInfo.jrstdt;
	                }
				    if ($scope.icasInfo.jreddt != null) {
				    	sJREDDT = $scope.icasInfo.jreddt;
				    }
				    if (sSBSM == "ANF" && sSBPAT == "J" && sJRFLG == "1" && sJRSTDT.length() != 0 && sJREDDT.length() == 0) {
				    	return "black";
	                } else {
	                	return "lime";
	                }
	            }
    		}
    		,styleIcasBoxJissekiBack : function(icasBoxInfo) {
			    // 徐冷開始時の判断
			    // 設計設備名=ANF AND SBPAT = J AND 徐冷フラグ = 1 AND 徐冷開始日 != NULL AND 徐冷終了日 = NULL
			    // 上記条件を満たした場合；実績値のセルを「文字色：黒、背景色：白」
			    var sSBSM   = "";
			    var sSBPAT  = "";
			    var sJRFLG  = "";
			    var sJRSTDT = "";
			    var sJREDDT = "";
			    if (icasBoxInfo.sbsm != null) {
			    	sSBSM = icasBoxInfo.sbsm;
                }
			    if (icasBoxInfo.sbpat != null) {
			    	sSBPAT = icasBoxInfo.sbpat;
                }
			    if ($scope.icasInfo.jrflg != null) {
			    	sJRFLG = $scope.icasInfo.jrflg;
                }
			    if ($scope.icasInfo.jrstdt != null) {
			    	sJRSTDT = $scope.icasInfo.jrstdt;
                }
			    if ($scope.icasInfo.jreddt != null) {
			    	sJREDDT = $scope.icasInfo.jreddt;
			    }
			    if (sSBSM == "ANF" && sSBPAT == "J" && sJRFLG == "1" && sJRSTDT.length() != 0 && sJREDDT.length() == 0) {
			    	return "white";
                }
            	return "";
    		}
    		,styleIcasSydt : function() {
    			if ($scope.icasInfo == null) {
    				return "white"; 
    			}
    	        if ($scope.icasInfo.nokiymd == null) {
    	            return "white;";
    	        } else {
    	            return "lime;";
    	        }
    		}
        }
		//---------------------------------------------------------------
        
        
   		//初期表示
        $scope.action.init();
    }; //End newController

    newController.$inject = injectParams;
    angular.module(moduleName).controller(controllerName, newController);
}());

