(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q'];

    // TODO:LoginServiceと機能重複があるのでどこかで整理を

    // 引数は依存の内容と一致する
    var newService = function ($http, $q) {
    	var userProfile = undefined;
    	return {
    		getLoggedIn : function () {
    			var delay = $q.defer();
    			$http.get('service/isLoggedIn')
    				.success(function(data){
    					delay.resolve(data);
    				})
    				.error(function(data){
    					delay.reject(data);
    				});
    			return delay.promise;
    		},
    		getUserProfile : function () {
    			var delay = $q.defer();
    			if (userProfile) {
    				delay.resolve(userProfile);
    				return;
    			}
    			return this.loadUserProfile();
    		},
    		getUserProfileSync : function () {
    			if (!angular.isDefined(userProfile)) {
					console.err('userProfileがロードされていない状態で同期取得を行いました。');
				}
				return userProfile;
    		},
    		loadUserProfile : function () {
    			var delay = $q.defer();
	    		$http.get('service/user')
					.success(function(data){
						userProfile = data;
						delay.resolve(data);
					})
					.error(function(data){
						userProfile = undefined;
						delay.reject(data);
					});
				return delay.promise;
    		}
    	};
    };

    newService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('UserService', newService);

}());