(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q'];

    // 引数は依存の内容と一致する
    var RulesService = function ($http, $q) {

    	/*
    	 * ディレクティブ内でリクエストを送って取得させようかと思ったが、
    	 * 非同期なのでキャッシュされる前に一斉にリクエストが送られてしまう事と、
    	 * Ruleが読み込まれるまで制御が働かない事を考えて、
    	 * resolveで事前にロードする形とする
    	 *
    	 * resolveのタイミングとしては、全体として軽いプロジェクトであれば、homeなどでloadAllを
    	 * 重いものは各画面で使うメッセージクラスのものをloadする
    	 */

    	// TODO:キャッシュはangularのものを使った方が良い？
    	var rulesCashe = NisUtil.createContainer();
    	var allLoaded = false;
    	return {
    		loadAll: function() {
    			if (allLoaded) {
    				return;
    			}
    			return $http.get('service/rules/all').then(function(results){
    				for (var key in results.data) {
    					rulesCashe.put(key, results.data[key])
    				}
    				console.log(_.size(results.data) + '個のRuleをloadしました');
    				allLoaded = true;
				});
    		},
    		load: function() {
    			var reqParams = [];
    			_.each(arguments, function(key){
    				if (!rulesCashe.containKey(key)) {
    					reqParams.push(key);
    				}
    			});
    			if (!_.size(reqParams)) {
    				var deferred = $q.defer();
    				deferred.resolve();
					return deferred.promise;
    			}
    			return $http.get('service/rules',{params:{rules: reqParams}}).then(function(results){
    				if (_.size(reqParams) != _.size(results.data)) {
    					console.log('loadされていないRuleがあります。');
    				}
    				for (var key in results.data) {
    					console.log('Ruleをloadしました:' + key);
    					rulesCashe.put(key, results.data[key])
    				}
				});
    		},
			getObj: function(key) {
				if (rulesCashe.containKey(key)) {
					return rulesCashe.get(key);
				}
				else {
					console.log('loadされていないRuleを取得しようとしました:' + key);
				}
			},
			// TODO:パフォーマンス様子見
			get: function(key) {
				var names = key.split('.');
				var rule = this.getObj(names[0]) || {};
				for (var i = 1;i < names.length;i++) {
					rule = rule[names[i]];
					if (!rule) {
						console.log('存在しないRuleです：' + key);
						break;
					}
				}
				return rule;
			},
			
			//サイ　2016/03/28 追加 -----------------------------------------------------------↓  
    		load2: function(params,aCallback) {
    			var reqParams = [];

    			_.each(params, function(key){
    				if (!rulesCashe.containKey(key)) {
    					reqParams.push(key);
    				}
    			});

    			if (!_.size(reqParams)) {
    				var deferred = $q.defer();
    				deferred.resolve();
					return deferred.promise;
    			}

    			return $http.get('service/rules',{params:{rules: reqParams}}).then(function(results){
    				if (_.size(reqParams) != _.size(results.data)) {
    					console.log('loadされていないRuleがあります。');
    				}
    				for (var key in results.data) {
    					console.log('Ruleをloadしました:' + key);
    					rulesCashe.put(key, results.data[key])
    				}
    				aCallback();
				});
    		}
			 //---------------------------------------------------------------------------↑
			
		};
    };

    RulesService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('RulesService', RulesService);

}());