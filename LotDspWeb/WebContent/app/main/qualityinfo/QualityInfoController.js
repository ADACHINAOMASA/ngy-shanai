
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = 'QualityInfoController';
    var lotInfoStorage = 'LotDspInfo';
    var userStorage='UserInfoStorage';

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

		$scope.staffQualitySelectInfo = {};
		$scope.staffQualityBoxInfos = [];

		$scope.selectIndex = 0;
		//modeの設定
		$scope.common.mode = $localStorage[userStorage].mode;
		//---------------------------------------------------------------
		// アクション定義
        $scope.action = {
        	//初期表示時に実行
        	init:function(){
        		//共通データの設定
        		$scope.action.setInputData($localStorage[lotInfoStorage]);
        		$scope.action.setCommonData($localStorage[lotInfoStorage]);
            	//進度情報基本データの設定
        		$scope.action.setStaffProgressInfoData($localStorage[lotInfoStorage].staffProgressBean);
        		//画面別データ取得
        		$scope.action.getStaffQualityInfosData(angular.copy($localStorage[lotInfoStorage].staffQualityBean));
        	}
	    	//共通データの設定
			,setInputData:function(data){
				//検索項目の設定
				$scope.common.searchLtno = data.searchLtno;
				$scope.common.searchKnno = data.searchKnno;
			}
			,setCommonData:function(data){
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
		        $scope.common.tantosha_MEI = data.staffCommonBean.tantosha_MEI;
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
		        $scope.common.css = data.staffCommonBean.css;
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
		        //表示制御
		        $scope.common.tabSetRendered = data.tabSetRendered;
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
    		,getStaffQualityInfosData:function(data){
    			$scope.selectIndex = 0;
    			$scope.staffQualityBoxInfos = [];
    			if (data.staffQualityBoxInfos.length > 0) {
    		        for(var i = 0; i < data.staffQualityBoxInfos.length; i++) {
    	    			$scope.staffQualityBoxInfos.push(data.staffQualityBoxInfos[i]);
    		        }
        		}
    			$scope.action.setStaffQualityInfosData();
    		}
    		,setStaffQualityInfosData:function(){
    			$scope.staffQualitySelectInfo = {};
    			if ($scope.staffQualityBoxInfos.length > 0) {
    		        for(var i = 0; i < $scope.staffQualityBoxInfos.length; i++) {
    		            if (i == $scope.selectIndex) {
    		            	$scope.staffQualityBoxInfos[i].selected = "1";
    		    			$scope.staffQualitySelectInfo = $scope.staffQualityBoxInfos[i];
    		            } else {
    		            	$scope.staffQualityBoxInfos[i].selected = "0";
    		            }
    		        }
    			}
    		}
    		//検索
    		,search:function(){
				//入力されたロット番号と検査番号を大文字にする
    			$scope.common.searchLtno = $scope.common.searchLtno.toUpperCase();
    			$scope.common.searchKnno = $scope.common.searchKnno.toUpperCase();
		    	//メッセージエリアのクリア
		    	$("#messageArea").text("");
    			if (!$scope.common.searchLtno && !$scope.common.searchKnno) {
    		    	$("#messageArea").text("実行エラー");
					$scope.common.lotMaximum = 0;
					$scope.common.tabSetRendered = false;
    		    	return;
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
    					$scope.common.lotMaximum = 0;
    					$scope.common.tabSetRendered = data.tabSetRendered;
    				}else{
    					// ローカルストレージに再設定
    					MenuService.memory.saveBaseWork(data);
    	        		//共通データの設定
    	        		$scope.action.setInputData(data);
    	        		$scope.action.setCommonData(data);
    	            	//進度情報基本データの設定
    	        		$scope.action.setStaffProgressInfoData(data.staffProgressBean);
    	        		//画面別データ取得
    	        		$scope.action.getStaffQualityInfosData(angular.copy(data.staffQualityBean));
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
	    	        		$scope.action.getStaffQualityInfosData(angular.copy(data.staffQualityBean));
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
	    	        		$scope.action.getStaffQualityInfosData(angular.copy(data.staffQualityBean));
	    				}
					});
		        }
        	}
    		//画面遷移
			,showIcasInfo:function(){
				$state.go('IcasInfo');
    		}
			,showProgressInfo:function(){
				$state.go('ProgressInfo');
    		}
    		,showManufacturingInfo:function(){
    			$state.go('ManufacturingInfo');
        	}
			,showTestInfo:function(){
				$state.go('TestInfo');
    		}
			,showCladInfo:function(){
				$state.go('CladInfo');
    		}
			,showCFInfo:function(){
				$state.go('CFInfo');
    		}
			//リンク選択
			,select:function(index){
				$scope.selectIndex = index;
    			$scope.action.setStaffQualityInfosData();
    		}
			,saitehaiLink:function(key){
				MenuService.getSaiTehaiLink().then(function(data){
					if(!data.saitehaiUrl){
						alert('再手配URLを取得できませんでした。');
					}else{
						window.open(data.saitehaiUrl + "?linkkey=" + key + "&mode=2");
					}
				});
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
        		return $localStorage[lotInfoStorage].searchLtno;
        	}
        	,getKnno : function() {
        		return $localStorage[lotInfoStorage].searchKnno;
        	}
        };
		//---------------------------------------------------------------


   		//初期表示
        $scope.action.init();
    }; //End newController

    newController.$inject = injectParams;
    angular.module(moduleName).controller(controllerName, newController);
}());

