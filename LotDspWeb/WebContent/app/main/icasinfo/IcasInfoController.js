
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = 'IcasInfoController';
    var lotInfoStorage = 'ngStorage-lotDspInfo';

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
            	//進度情報基本データの設定
        		$scope.action.setStaffProgressInfoData(JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean);
        		//画面別データ取得
        		$scope.action.setIcasInfosData(JSON.parse(localStorage.getItem(lotInfoStorage)).icasBean);
        	}
	    	//共通データの設定
			,setInputData:function(data){
				//検索項目の設定
				$scope.common.searchLtno = data.searchLtno;
				$scope.common.searchKnno = data.searchKnno;
			}
	    	//共通データの設定
			,setCommonData:function(data){
				//検索項目の設定
				$scope.common.searchLtno = data.searchLtno;
				$scope.common.searchKnno = data.searchKnno;
		        //ロット番号～Yﾏｰｸ日
		        $scope.common.ltno = data.staffCommonBean.ltno;
		        $scope.common.cyno = data.staffCommonBean.cyno;
		        $scope.common.knno = data.staffCommonBean.knno;
		        $scope.common.juno = data.staffCommonBean.juno;
		        $scope.common.jbcd = data.staffCommonBean.jbcd;
		        $scope.common.bucode = data.staffCommonBean.bucode;
		        $scope.common.yotoc = data.staffCommonBean.yotoc;
		        $scope.common.yotoname = data.staffCommonBean.yotoname;
		        $scope.common.chikucd = data.staffCommonBean.chikucd;
		        $scope.common.jujsno = data.staffCommonBean.jujsno;
		        $scope.common.enoki = data.staffCommonBean.enoki;
		        $scope.common.jcd105 = data.staffCommonBean.jcd105;
		        $scope.common.jup = data.staffCommonBean.jup;
		        $scope.common.ymark = data.staffCommonBean.ymark;
		        //材質～重量
		        $scope.common.jua = data.staffCommonBean.jua;
		        $scope.common.jub = data.staffCommonBean.jub;
		        $scope.common.jux = data.staffCommonBean.jux;
		        $scope.common.juy = data.staffCommonBean.juy;
		        $scope.common.juz = data.staffCommonBean.juz;
		        $scope.common.juw = data.staffCommonBean.juw;
		        $scope.common.lta = data.staffCommonBean.lta;
		        $scope.common.ltb = data.staffCommonBean.ltb;
		        $scope.common.ltx = data.staffCommonBean.ltx;
		        $scope.common.lty = data.staffCommonBean.lty;
		        $scope.common.ltz = data.staffCommonBean.ltz;
		        $scope.common.ryow = data.staffCommonBean.ryow;
		        //得意先～納入先
		        $scope.common.tokui = data.staffCommonBean.tokui;
		        $scope.common.oname = data.staffCommonBean.oname;
		        $scope.common.nonyu = data.staffCommonBean.nonyu;
		        //倉入日～納期符号
		        $scope.common.datakbn = data.staffCommonBean.datakbn;
		        $scope.common.ksd = data.staffCommonBean.ksd;
		        $scope.common.juno202 = data.staffCommonBean.juno202;
		        $scope.common.shikenno = data.staffCommonBean.shikenno;
		        $scope.common.karikon = data.staffCommonBean.karikon;
		        $scope.common.nokifugo = data.staffCommonBean.nokifugo;
		        //最終更新日
		        $scope.common.edtm = data.staffCommonBean.edtm;
		        //検索結果総件数
		        $scope.common.lotMaximum = data.lotMaximum;
		        //現在表示ページ
		        $scope.common.nowPage = data.nowPage;
	
			}
	    	//進度情報基本データの設定
			,setStaffProgressInfoData:function(data){
				//比重～試験回数
				$scope.staffProgressInfo.hijyu = data.hijyu;
				$scope.staffProgressInfo.sbd = data.sbd;
				$scope.staffProgressInfo.hohdt = data.hohdt;
				$scope.staffProgressInfo.tanw = data.tanw;
				$scope.staffProgressInfo.kbd = data.kbd;
				$scope.staffProgressInfo.hokdt = data.hokdt;
				$scope.staffProgressInfo.sw = data.sw;
				$scope.staffProgressInfo.sraito = data.sraito;
				$scope.staffProgressInfo.kkn = data.kkn;
				//納管出荷日～引当
				$scope.staffProgressInfo.nokiymd = data.nokiymd;
				$scope.staffProgressInfo.jcd112 = data.jcd112;
				$scope.staffProgressInfo.saitehailtno = data.saitehailtno;
				$scope.staffProgressInfo.sai_count = data.sai_count;
				$scope.staffProgressInfo.hikikbn = data.hikikbn;
				$scope.staffProgressInfo.hikicode = data.hikicode;
				//仕様～製造
				$scope.staffProgressInfo.msnok = data.msnok;
				$scope.staffProgressInfo.msnoh = data.msnoh;
				$scope.staffProgressInfo.msnos = data.msnos;
				//設備名～送り先名
				$scope.staffProgressInfo.gai_IDX1 = data.gai_IDX1;
				$scope.staffProgressInfo.gai_SETUBI1 = data.gai_SETUBI1;
				$scope.staffProgressInfo.gai_CD1 = data.gai_CD1;
				$scope.staffProgressInfo.gai_TODAY1 = data.gai_TODAY1;
				$scope.staffProgressInfo.gai_OKURI1 = data.gai_OKURI1;
				$scope.staffProgressInfo.gai_IDX2 = data.gai_IDX2;
				$scope.staffProgressInfo.gai_SETUBI2 = data.gai_SETUBI2;
				$scope.staffProgressInfo.gai_CD2 = data.gai_CD2;
				$scope.staffProgressInfo.gai_TODAY2 = data.gai_TODAY2;
				$scope.staffProgressInfo.gai_OKURI2 = data.gai_OKURI2;
				$scope.staffProgressInfo.gai_IDX3 = data.gai_IDX3;
				$scope.staffProgressInfo.gai_SETUBI3 = data.gai_SETUBI3;
				$scope.staffProgressInfo.gai_CD3 = data.gai_CD3;
				$scope.staffProgressInfo.gai_TODAY3 = data.gai_TODAY3;
				$scope.staffProgressInfo.gai_OKURI3 = data.gai_OKURI3;
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
    	            	//進度情報基本データの設定
    	        		$scope.action.setStaffProgressInfoData(data.staffProgressBean);
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
	    	            	//進度情報基本データの設定
	    	        		$scope.action.setStaffProgressInfoData(data.staffProgressBean);
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
	    	            	//進度情報基本データの設定
	    	        		$scope.action.setStaffProgressInfoData(data.staffProgressBean);
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

