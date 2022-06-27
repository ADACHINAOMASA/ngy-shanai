(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$state', '$q', '$window', '$interval', 'UserService'];

    // 引数は依存の内容と一致する
    var newService = function ($http, $state, $q, $window, $interval, UserService) {
    	var loggedIn = false;
    	function getUserAgent() {
    		return $window.navigator.userAgent.toLowerCase();
    	};
    	function getAppVersion() {
    		return $window.navigator.appVersion.toLowerCase();
    	};
    	function isUnSupportedAgent() {
    		if (getUserAgent().indexOf("msie") != -1) {
    			if (getAppVersion().indexOf("msie 6.") != -1) {
    			    return true;
    			} else if (getAppVersion().indexOf("msie 7.") != -1) {
    			    return true;
    			} else if (getAppVersion().indexOf("msie 8.") != -1) {
    			    return true;
    			} else if (getAppVersion().indexOf("msie 9.") != -1) {
    			    return true;
    			}
    		}
    		return false;
    	};

    	// 開発版機能
    	var keep;

    	return {
			login: function(userId, password) {
				if (isUnSupportedAgent()) {
					$window.alert('このブラウザはサポートされていません。IEの場合はバージョンを10以上にして下さい。');
					return;
				}
				var data = {'id': userId,'password': password};
				var defferd = $q.defer();
				$http.post('service/login', '',{params:data, headers:{'Content-Type': 'application/x-www-form-urlencoded'}})
					.success(function(data, status, headers, config){
						loggedIn = true;
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			},
			isLoggedIn: function() {
				return loggedIn;
			},
			logout: function() {
				$http.post('service/logout')
				.success(function(data, status, headers, config){
					loggedIn = false;
					$state.go('login');
				});
			},
			$getUserProfile: function() {
				var defferd = $q.defer();
				$http.get('service/getSessionScope/userProfile')
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			},
			reloadLoggedIn: function(){
				var defferd = $q.defer();
				UserService.getLoggedIn().then(function(data){
					loggedIn = NisUtil.isTrue(data);
					defferd.resolve(data);
				});
				return defferd.promise;
			},
			isSessionKeep: function() {
				return !!keep;
			},
			toSessionKeep: function() {
				if (angular.isDefined(keep) ){
					return;
				}
				keep = $interval(function() {
					$http.get('service/getSessionScope/userProfile');
		        }, 60000 * 5);
			},
			toNotSessionKeep: function() {
    			if (angular.isDefined(keep) ){
    				$interval.cancel(keep);
    				keep = undefined;
    			}
			}
			
			,loadUserProfile:function($scope){
				var defferd = $q.defer();
				$http.get('service/getSessionScope/userProfile')
					.success(function(data){
						  $scope.userProfile = data;
					      for(var obj in $scope.userProfile.authority.auths){
					    		
					    		/*
					    		if($scope.userProfile.authority.auths[obj].name == CompoConst.kengen.kanrisha){
					    			$scope.kengen.isKanrisha = true;
					    		}
					    		*/
					    	}
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			
		};
    };

    newService.$inject = injectParams;

    angular.module('app')
        .factory('LoginService', newService);
}());