/**
 * _サービス
 */
(function () {

    // 必要な依存を列挙
    // 列挙の順番（カテゴリー毎に改行を）
    // 1.angular系
    // 2.外部プラグイン系
    // 3.NIS開発系のうち、共通のもの
    // 4.NIS開発系のうち、固有のもの
    // 5.resolveで渡される値
    var injectParams = ['$http', 'NIS'];

    // 引数は依存の内容と一致する
    var newService = function ($http, NIS) {
        
    	let baseURI = 'service/yoyaku';
    	
    	return {
    		search(hizuke) {
    			return NIS.u.req($http.get(NIS.u.path(baseURI, 'searchkaigishitsu', hizuke)));
    		},
    		save(yoyakuInfo) {
    			return NIS.u.req($http.post(NIS.u.path(baseURI, 'save'), yoyakuInfo));
    		},
    		update(yoyakuInfo) {
    			return NIS.u.req($http.post(NIS.u.path(baseURI, 'update'), yoyakuInfo));
    		},
    		maishusave(yoyakuInfo, maishuEnd) {
    			return NIS.u.req($http.post(NIS.u.path(baseURI, maishuEnd), yoyakuInfo));
    		},
    		yoyakudelete(yoyakuCd) {
    			return NIS.u.req($http.delete(NIS.u.path(baseURI, yoyakuCd), {headers: {
                    'Content-Type': 'application/json'
                }}));
    		},
    		maishuyoyakudelete(kaigishitsuCd, yoyakuDate, yoyakuBlockStart, maishuYoyakuCd) {
    			return NIS.u.req($http.delete(NIS.u.path(baseURI, kaigishitsuCd, yoyakuDate, yoyakuBlockStart, maishuYoyakuCd), {headers: {
                    'Content-Type': 'application/json'
                }}));
    		},
    	}
    };

    newService.$inject = injectParams;

    angular.module('app')
        // factory | service | provider
        .factory('KaigiYoyakuService', newService);

}());