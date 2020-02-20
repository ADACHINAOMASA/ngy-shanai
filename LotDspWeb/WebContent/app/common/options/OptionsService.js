(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q'];

    // もう少し汎用性を持たせるか

    // 引数は依存の内容と一致する
    var OptionsService = function ($http, $q) {
    	// TODO:レスポンスを考えてキャッシュを持ちたい、持つとしてサーバーサイドとクライアントサイドどちらに持つか
    	//      両方で持つ？
    	var optionsCashe = NisUtil.createContainer();
    	return {
			getOptions: function(optionsKey, cache){
				if (cache) {
					if (optionsCashe.containKey(optionsKey)) {
						var deferred = $q.defer();
						deferred.resolve(optionsCashe.get(optionsKey));
						return deferred.promise;
					}
				}
				return $http.get('service/options/' + optionsKey + '?refresh=' + !cache).then(function(results){
					optionsCashe.put(optionsKey, results.data);
					return results.data;
				});
			},
			putOptions: function(optionsKey, options) {
				optionsChashe.put(optionsKey, options);
			},
			// テーブル参照にする？
			getLabel: function(optionsKey, code) {
				return this.getOptions(optionsKey, true).then(function(data){
					var option = _.find(data.options, function(option){
						return option.value == code;
					});
					return option ? option.label : '';
				});
			},
			getValue: function(optionsKey, label) {
				return this.getOptions(optionsKey, true).then(function(data){
					var option = _.find(data.options, function(option){
						return option.label == label;
					});
					return option ? option.value : '';
				});
			},
			contains: function(optionsKey, code) {
				return this.getLabel(optionsKey, code).then(function(data){
					return data !== '';
				});
			},
			clearCache: function() {
				optionsCashe.clear();
			},
			refreshAll: function() {
				return $http.get('service/options/refresh').then(function(results){
					optionsCashe.clear();
					return results.data;
				});
			},
			getAll: function() {
			    return $http.get('service/options/refresh').then(function(results) {
			    	angular.forEach(results.data, function(value, key) {
			    		//optionsCashe.clear();
			    		optionsCashe.put(value.key, value);
			    	});
			    	console.log(_.size(results.data) + '個のOptionsをloadしました');
			        return results.data;
			    });
			}
		};
    };

    OptionsService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('OptionsService', OptionsService);

}());