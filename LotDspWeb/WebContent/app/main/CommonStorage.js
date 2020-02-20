/**
 * Back End : 無し
 *
 * @author Y.Nakajima
 */
(function() {

	var name = 'CommonStorage';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', '$localStorage' ];

	// 引数は依存の内容と一致する
	var newService = function($http, $q, $localStorage) {

		var storageName='commonStorage';

		var memory = {
				//ローカルストレージへ設定用の変数宣言
				errorFlg : false,
				message : null,

				//設定用の変数へ値を代入
	    		saveBaseWork: function(info) {
	    			this.errorFlg = info.errorFlg;
	    			this.message = info.message;
	    			this.writeLocalStrage();
	    		},
				//設定用の変数を初期化
	    		resetBaseWork: function() {
	    			this.errorFlg = false;
	    			this.message = null;
	    			this.writeLocalStrage();
	    		},
	    		//ローカルストレージへの書き込み処理
	    		writeLocalStrage: function() {
	    			$localStorage[storageName] = {
	    					errorFlg : this.errorFlg
	    					,message : this.message
	    			};
	    		},
				//-----ローカルストレージからの取得処理
	    		getErrorFlg: function() {
	    			return this.errorFlg;
	    		},
	    		getMessage: function() {
	    			return this.message;
	    		}
	    		//-----ローカルストレージからの取得処理
		};

		return {
			memory:memory
		};
	};

	newService.$inject = injectParams;

	angular.module('app').factory(name, newService);

}());