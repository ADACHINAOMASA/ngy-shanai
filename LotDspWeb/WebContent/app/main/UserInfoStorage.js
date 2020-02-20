/**
 * Back End : 無し
 *
 * @author Y.Nakajima
 */
(function() {

	var name = 'UserInfoStorage';

	// 必要な依存を列挙
	var injectParams = [ '$http', '$q', '$localStorage' ];

	// 引数は依存の内容と一致する
	var newService = function($http, $q, $localStorage) {

		var storageName='userInfoStorage';

		var memory = {
				//ローカルストレージへ設定用の変数宣言
				ip : null,
				userID : null,
				termID : null,
				setsubiName : null,
				useKbn : null,
				termKbn : null,
				authInfo : null,

				//設定用の変数へ値を代入
	    		saveBaseWork: function(info) {
	    			this.ip = info.ip;
	    			this.userID = info.userID;
	    			this.termID = info.termID;
	    			this.setsubiName = info.setsubiName;
	    			this.useKbn = info.useKbn;
	    			this.termKbn = info.termKbn;
	    			this.authInfo = info.authInfo;
	    			this.writeLocalStrage();
	    		},
	    		//ローカルストレージへの書き込み処理
	    		writeLocalStrage: function() {
	    			$localStorage[storageName] = {
	    	    			ip : this.ip
	    	    			,userID : this.userID
	    	    			,termID : this.termID
	    	    			,setsubiName : this.setsubiName
	    	    			,useKbn : this.useKbn
	    	    			,termKbn : this.termKbn
	    	    			,authInfo : this.authInfo
	    			};
	    		},
				//-----ローカルストレージからの取得処理
	    		getUserID: function() {
	    			return this.ip;
	    		},
	    		getTermId: function() {
	    			return this.userID;
	    		},
	    		getSetsubiName: function() {
	    			return this.setsubiName;
	    		},
	    		getUserKbn: function() {
	    			return this.useKbn;
	    		},
	    		getTermKbn: function() {
	    			return this.termKbn;
	    		},
	    		getAuthInfo: function() {
	    			return this.authInfo;
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