
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = 'CFInfoController';
    var lotInfoStorage = 'ngStorage-lotDspInfo';

    // 必要な依存を列挙
    var injectParams = [
        '$scope', '$state', '$interval', '$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService', 'uiGridConstants'
        , 'NisGridUtil', 'AlertService', 'ModalService', 'ModelService', 'AlertLogService'
        , '$base64','CommonService', '$localStorage','CommonStorage', 'MenuService'
    ];

    // 引数は依存の内容と一致する
    var newController = function (
        $scope, $state, $interval, $httpParamSerializer, $window, $q, $timeout, i18nService, uiGridConstants
        , NisGridUtil, AlertService, ModalService, ModelService, AlertLogService
        , $base64,CommonService, $localStorage,CommonStorage, MenuService
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');

		//変数初期化
		//共通項目
		$scope.common = {};
		//進度情報基本データ
		$scope.staffProgressInfo = {};
		
		$scope.cFInfo = {};
		$scope.cFBoxInfos = [];
		
		//---------------------------------------------------------------
		// アクション定義
        $scope.action = {
        	//初期表示時に実行
        	init:function(){
        		//共通データの設定
        		$scope.action.setCommonData();
            	//進度情報基本データの設定
        		$scope.action.setStaffProgressInfoData();
        		//画面別データ取得
        		$scope.action.setStaffCFInfosData();
        	}
	    	//共通データの設定
			,setCommonData:function(){
				//検索項目の設定
				$scope.common.searchLtno = JSON.parse(localStorage.getItem(lotInfoStorage)).searchLtno;
				$scope.common.searchKnno = JSON.parse(localStorage.getItem(lotInfoStorage)).searchKnno;
		        //ロット番号～Yﾏｰｸ日
		        $scope.common.ltno = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.ltno;
		        $scope.common.cyno = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.cyno;
		        $scope.common.knno = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.knno;
		        $scope.common.juno = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.juno;
		        $scope.common.jbcd = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.jbcd;
		        $scope.common.bucode = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.bucode;
		        $scope.common.yotoc = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.yotoc;
		        $scope.common.yotoname = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.yotoname;
		        $scope.common.chikucd = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.chikucd;
		        $scope.common.jujsno = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.jujsno;
		        $scope.common.enoki = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.enoki;
		        $scope.common.jcd105 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.jcd105;
		        $scope.common.jup = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.jup;
		        $scope.common.ymark = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.ymark;
		        //材質～重量
		        $scope.common.jua = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.jua;
		        $scope.common.jub = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.jub;
		        $scope.common.jux = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.jux;
		        $scope.common.juy = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.juy;
		        $scope.common.juz = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.juz;
		        $scope.common.juw = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.juw;
		        $scope.common.lta = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.lta;
		        $scope.common.ltb = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.ltb;
		        $scope.common.ltx = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.ltx;
		        $scope.common.lty = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.lty;
		        $scope.common.ltz = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.ltz;
		        $scope.common.ryow = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.ryow;
		        //得意先～納入先
		        $scope.common.tokui = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.tokui;
		        $scope.common.oname = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.oname;
		        $scope.common.nonyu = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.nonyu;
		        //倉入日～納期符号
		        $scope.common.datakbn = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.datakbn;
		        $scope.common.ksd = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.ksd;
		        $scope.common.juno202 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.juno202;
		        $scope.common.shikenno = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.shikenno;
		        $scope.common.karikon = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.karikon;
		        $scope.common.nokifugo = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.nokifugo;
		        //最終更新日
		        $scope.common.edtm = JSON.parse(localStorage.getItem(lotInfoStorage)).staffCommonBean.edtm;
		        //検索結果総件数
		        $scope.common.lotMaximum = JSON.parse(localStorage.getItem(lotInfoStorage)).lotMaximum;
		        //現在表示ページ
		        $scope.common.nowPage = JSON.parse(localStorage.getItem(lotInfoStorage)).nowPage;
	
			}
        	//進度情報基本データの設定
    		,setStaffProgressInfoData:function(){
    			//比重～試験回数
    			$scope.staffProgressInfo.hijyu = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.hijyu;
    			$scope.staffProgressInfo.sbd = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.sbd;
    			$scope.staffProgressInfo.hohdt = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.hohdt;
    			$scope.staffProgressInfo.tanw = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.tanw;
    			$scope.staffProgressInfo.kbd = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.kbd;
    			$scope.staffProgressInfo.hokdt = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.hokdt;
    			$scope.staffProgressInfo.sw = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.sw;
    			$scope.staffProgressInfo.sraito = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.sraito;
    			$scope.staffProgressInfo.kkn = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.kkn;
    			//納管出荷日～引当
    			$scope.staffProgressInfo.nokiymd = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.nokiymd;
    			$scope.staffProgressInfo.jcd112 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.jcd112;
    			$scope.staffProgressInfo.saitehailtno = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.saitehailtno;
    			$scope.staffProgressInfo.sai_count = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.sai_count;
    			$scope.staffProgressInfo.hikikbn = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.hikikbn;
    			$scope.staffProgressInfo.hikicode = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.hikicode;
    			//仕様～製造
    			$scope.staffProgressInfo.msnok = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.msnok;
    			$scope.staffProgressInfo.msnoh = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.msnoh;
    			$scope.staffProgressInfo.msnos = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.msnos;
    			//設備名～送り先名
    			$scope.staffProgressInfo.gai_IDX1 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_IDX1;
    			$scope.staffProgressInfo.gai_SETUBI1 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_SETUBI1;
    			$scope.staffProgressInfo.gai_CD1 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_CD1;
    			$scope.staffProgressInfo.gai_TODAY1 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_TODAY1;
    			$scope.staffProgressInfo.gai_OKURI1 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_OKURI1;
    			$scope.staffProgressInfo.gai_IDX2 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_IDX2;
    			$scope.staffProgressInfo.gai_SETUBI2 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_SETUBI2;
    			$scope.staffProgressInfo.gai_CD2 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_CD2;
    			$scope.staffProgressInfo.gai_TODAY2 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_TODAY2;
    			$scope.staffProgressInfo.gai_OKURI2 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_OKURI2;
    			$scope.staffProgressInfo.gai_IDX3 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_IDX3;
    			$scope.staffProgressInfo.gai_SETUBI3 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_SETUBI3;
    			$scope.staffProgressInfo.gai_CD3 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_CD3;
    			$scope.staffProgressInfo.gai_TODAY3 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_TODAY3;
    			$scope.staffProgressInfo.gai_OKURI3 = JSON.parse(localStorage.getItem(lotInfoStorage)).staffProgressBean.gai_OKURI3;
    		}
        	//データの設定
    		,setStaffCFInfosData:function(){
    			$scope.cFInfo = JSON.parse(localStorage.getItem(lotInfoStorage)).cFInfoBean;
    			if ($scope.cFInfo.cfinfoBox != null) {
        			if ($scope.cFInfo.cfinfoBox.length > 0) {
        		        for(var i = 0; i < $scope.cFInfo.cfinfoBox.length; i++) {
        	    			$scope.cFBoxInfos.push($scope.cFInfo.cfinfoBox[i]);
        		        }
            		}
    			}
    		}
    		//画面遷移
			,showProgressInfo:function(){
				$state.go('/lotdsp/progressinfo');
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
    			if(data.nowPage == data.lotMaximum){
    				return false;
    			}else{
    				return true;
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

