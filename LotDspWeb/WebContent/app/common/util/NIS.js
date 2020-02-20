/**
 * NIS汎用サービス
 * 今の所は主にangularサービスとして定義されるべきユーティリティを扱う
 * 公開名には分かりやすく、かつ出来るだけ短い名前を付ける事（コードが伸びるのを防ぐ）
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['$q', '$httpParamSerializer'];

    // 引数は依存の内容と一致する
    var newService = function ($q, $httpParamSerializer) {

        /**
         * httpRequestのpromiseのwrapper
         * 実行結果のpromiseを単純に包んだpromiseを返す
         * @param {httpRequest} ラップするリクエスト
         * @returns {Promise} ラップしたPromise
         */
    	var wrapRequestPromise = function(httpRequest) {
    		var deferred = $q.defer();
    		httpRequest
				.then(function(reponse){
					deferred.resolve(reponse.data);
				})
				.catch(function(response){
					deferred.reject(response);
				});
    		return deferred.promise;
    	};

        /**
         * 独自httpParamSerializer
         * null値はserializeから除外されるので空文字にして強制的に変換
         * 残りはangularのserializerを使用
         * @param {params} 対象
         * @returns {String} 直列化した文字列
         */
    	var httpParamSerialize = function(params){
    		params = angular.copy(params);
    		_.each(_.keys(params), function(key) {
    			params[key] = params[key] || '';
    		});
    		return $httpParamSerializer(params);
    	};

        /**
         * listの中身一つ一つに対して、promiseを返す関数を順次実行する一つのpromiseを返す
         * @param {list} 対象
         * @param {promiseFn} 対象に適用される関数
         * @returns {Promise} 全体のpromise
         */
        var eachPromise = function(list, promiseFn) {
            return (function loop(idx) {
                if (idx >=  list.length) {
                    return $q.resolve();
                }
                return promiseFn(list[idx])
                        .then(function(){
                            return loop(idx + 1);
                        });
            })(0);
        };

		return {
            // util
			u : {
				wrapRequestPromise : wrapRequestPromise,
				req : wrapRequestPromise,
				path : NisUtil.request.createKeyPath,
				serialize : httpParamSerialize,
                eachp : eachPromise
			}
		};
    };

    newService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('NIS', newService);

}());