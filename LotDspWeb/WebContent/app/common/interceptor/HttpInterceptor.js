
(function () {

	// 必要な依存を列挙
    var injectParams = ['$q', '$location', 'AlertService', '$rootScope', '$timeout'];

    // 引数は依存の内容と一致する
    var HttpInterceptor = function ($q, $location, AlertService, $rootScope, $timeout) {

    	var sessionTimeoutAlertDelay = undefined;

    	function formatAction(config) {
    		return config.url + '_' + config.method;
    	}

    	return {
    		request: function (config) {
//    			AlertService.clear(formatAction(config));
    			return config;
    		},
			response: function (response) {
				var data = response.data;
				if (jQuery.isPlainObject(data) && data.responseObj) {
					// TODO:同アクションからのアラートを消す
					//AlertService.init();
					if (_.size(data.alerts.alerts)) {
						AlertService.addAlerts(data.alerts.alerts, formatAction(response.config));
					}
					if (data.byteData) {
						var ab = new Uint8Array(data.entity.length);
						var i = 0;
						angular.forEach(data.entity, function(b){
							ab[i++] = b;
						});
						response.data = ab.buffer;
					}
					else{
						response.data = data.entity;
					}
					if (data.status == 'ERROR') {
						return $q.reject(response);
					}
				}
				return response;
			},
			responseError: function (response) {
				if (response.status === 401) {
					if (sessionTimeoutAlertDelay) {
						$timeout.cancel(sessionTimeoutAlertDelay);
					}
					sessionTimeoutAlertDelay = $timeout(function(){
						AlertService.addDanger('セッションが切断されています。再ログインして下さい。');
						sessionTimeoutAlertDelay = undefined;
					}, 500);
				} else if(response.status >= 400 && response.status <= 500) {
					AlertService.addAlerts([{type:'danger', msg: response.status + ':' + response.statusText}]);
				}
				return $q.reject(response);
			}
		};
    };

    HttpInterceptor.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('HttpInterceptor', HttpInterceptor);

}());