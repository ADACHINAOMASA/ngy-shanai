(function () {

	// 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var ShareContainerService = function () {
    	// TODO:要検討
    	var _commonContainer = NisUtil.createContainer();
    	var _scopeCacheContainer = NisUtil.createContainer();
    	function _createScopeCache(){
    		return (function(){
    			var val = {};
        		var _save = function(scope){
        			var saveProps;
        			if (arguments.length == 1) {
        				saveProps = _.filter(_.keys(scope), function(key){
        					// TODO:
        					return key.charAt(0) != '$';
        				});
        			}
        			else {
        				saveProps = _.rest(_.toArray(arguments), 1);
        			}
        			val = {};
        			_.each(saveProps, function(prop){
        				val[prop] = angular.copy(NisUtil.getProperty(scope, prop));
        			});
        		},
        		_load = function(scope) {
        			var loadProps;
        			if (arguments.length > 1) {
        				loadProps = _.rest(_.toArray(arguments), 1);
        			}
        			_.each(val, function(value, key){
        				if (loadProps && !_.contains(loadProps, key)) {
        					return;
        				}
        				NisUtil.setProperty(scope, key, value);
        			});
        		},
        		_get = function(key) {
        			return val[key];
        		},
        		_clear = function() {
        			val = {};
        		};
        		return {
        			save : _save,
        			load : _load,
        			clear : _clear,
        			get : _get
        		};
    		})();
    	};

    	(function(){
    		_commonContainer.put('StateScrollMemory', NisUtil.createContainer());
    	})();

    	return {
			getCommonContainer : function(){
				return _commonContainer;
			},
			getStateScrollMemory : function() {
				return _commonContainer.get('StateScrollMemory');
			},
			getScopeCache : function(key) {
				var scopeCache = _scopeCacheContainer[key];
				if (!scopeCache) {
					scopeCache = _createScopeCache();
					_scopeCacheContainer[key] = scopeCache;
				}
				return scopeCache;
			}
		};
    };

    ShareContainerService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('ShareContainerService', ShareContainerService);

}());