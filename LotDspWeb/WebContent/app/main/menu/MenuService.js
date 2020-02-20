
(function() {
	//index.htmlのng-appで定義している名前と同じ。
	//<html lang="jp" ng-app="app">
	var moduleName = 'app';

	var serviceName = 'MenuService';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', 'NIS', '$localStorage' ];

	// 引数は依存の内容と一致する
	var newService = function( $http, $q, NIS, $localStorage) {
		var baseURI = 'service/lotdsp/menu/';
		var storageName='lotDspInfo';

		var memory = {
				//ローカルストレージへ設定用の変数宣言
				searchLtno:null,
				searchKnno:null,
				ltno:null,
				knno:null,
				searchKeyInfos:null,
				lotDspUserBean:null,
				icasBean:null,
				staffCommonBean:null,
				staffProgressBean:null,
				staffManufactureBean:null,
				staffTestBean:null,
				staffQualityBean:null,
				qualityDetail:null,
				qualityInfoTabRendered:null,
				cladInfoBean:null,
				cFInfoBean:null,
				lotMaximum:null,
				nowPage:null,

				//設定用の変数へ値を代入
        		saveBaseWork: function(info) {
        			this.searchLtno = info.searchLtno;
        			this.searchKnno = info.searchKnno;
        			this.ltno = info.ltno;
        			this.knno = info.knno;
        			this.searchKeyInfos = info.searchKeyInfos;
        			this.lotDspUserBean = info.lotDspUserBean;
        			this.icasBean = info.icasBean;
        			this.staffCommonBean = info.staffCommonBean;
        			this.staffProgressBean = info.staffProgressBean;
        			this.staffManufactureBean = info.staffManufactureBean;
        			this.staffTestBean = info.staffTestBean;
        			this.staffQualityBean = info.staffQualityBean;
        			this.qualityDetail = info.qualityDetail
        			this.qualityInfoTabRendered = info.qualityInfoTabRendered
        			this.cladInfoBean = info.cladInfoBean
        			this.cFInfoBean = info.cFInfoBean
        			this.lotMaximum = info.lotMaximum
        			this.nowPage = info.nowPage
        			this.writeLocalStrage();
        		},
        		//ローカルストレージへの書き込み処理
        		writeLocalStrage: function() {
        			$localStorage[storageName] = {
            			searchLtno : this.searchLtno
            			,searchKnno : this.searchKnno
        				,ltno : this.ltno
        				,knno : this.knno
        				,searchKeyInfos : this.searchKeyInfos
        				,lotDspUserBean : this.lotDspUserBean
           				,icasBean: this.icasBean
           				,staffCommonBean:this.staffCommonBean
           				,staffProgressBean:this.staffProgressBean
           				,staffManufactureBean:this.staffManufactureBean
           				,staffTestBean:this.staffTestBean
           				,schList:this.schList
           				,teisiGrpList:this.teisiGrpList
           				,kanryoSetKensu:this.kanryoSetKensu
           				,staffQualityBean:this.staffQualityBean
           				,qualityDetail:this.qualityDetail
           				,qualityInfoTabRendered:this.qualityInfoTabRendered
           				,cladInfoBean:this.cladInfoBean
           				,lotMaximum:this.lotMaximum
           				,nowPage:this.nowPage
           				,cFInfoBean:this.cFInfoBean
        			};
        		}
		};
		return {
			memory : memory
			,searchMenu : function(data) {
				return NIS.u.req($http.post(NIS.u.path(baseURI, 'searchMenu'), data));
			}
		};
	};//End newService

	newService.$inject = injectParams;
	angular.module(moduleName).factory(serviceName, newService);
}());
