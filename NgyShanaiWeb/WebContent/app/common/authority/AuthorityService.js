(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q', 'UserService'];

    // 引数は依存の内容と一致する
    var AuthorityService = function ($http, $q, UserService) {
    	return {
    		has : function(permission, target, field) {
    			var names;
    			if (_.isString(permission) || _.isArray(permission)) {
    				names = permission;
    			}
    			else if (_.isObject(permission)){
    				names = permission.auth;
    				target = target || permission.target;
    				field = field || permission.field;
    			}
    			target = target || "authority.auths";
    			field = field || "name";
    			var prof = UserService.getUserProfileSync();
    			if (!prof) {
    				UserService.loadUserProfile();
    				return false;
    			}
    			return this.checkAuth(prof, names, NisUtil.property.getProperty(prof, target), field);
    		},
    		checkAuth: function(prof, names, auths, field) {
    			names = _.isArray(names) ? names : [names];
				return _.any(auths, function(auth){
					return _.any(names, function(name){
						if (_.isString(auth)) {
							return auth === name
						}
						else {
							return auth[field] === name
						}
					});
				});
    		}
    	};
    };

    AuthorityService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('AuthorityService', AuthorityService);

}());