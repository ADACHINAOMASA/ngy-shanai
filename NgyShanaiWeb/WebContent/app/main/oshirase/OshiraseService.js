(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q', '$interval', 'AlertService'];

    // 引数は依存の内容と一致する
    var newService = function ($http, $q, $interval, AlertService) {
    	var watch;
    	var nextReqDate;
		return {
			list : function(param) {
				var defferd = $q.defer();
				$http.get('service/oshirase/list',{params:param})
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,get : function(key) {
				var defferd = $q.defer();
				$http.get('service/oshirase/' + key)
					.success(function(data){
						defferd.resolve(data);
					})
					.error(function(data){
						defferd.reject(data);
					});
				return defferd.promise;
			}
			,save : function(key, param) {
				var defferd = $q.defer();
				$http.post('service/oshirase/' + key, param)
				.success(function(data){
					defferd.resolve(data);
				})
				.error(function(data){
					defferd.reject(data);
				});
				return defferd.promise;
			}
			,remove : function(key, param) {
				var defferd = $q.defer();
				$http.delete('service/oshirase/' + key, {data:param, headers: {
					   'Content-Type': 'application/json'
				 }})
				.success(function(data){
					defferd.resolve(data);
				})
				.error(function(data){
					defferd.reject(data);
				});
				return defferd.promise;
			}
			,watchStart : function() {
				if (angular.isDefined(watch)) {
					return;
				}
				nextReqDate = nextReqDate || new Date();
				watch = $interval(function() {
					var reqTime = NisUtil.date.format(nextReqDate, 'yyyy-MM-dd-hh-mm-ss');
					nextReqDate = new Date();
					$http.get('service/oshirase/newlist',{params:{time : reqTime}}).success(function(data){
						angular.forEach(data, function(oshirase){
							AlertService.addInfo((oshirase.juyoKbn === '3' ? '≪重要≫' : '') + 'お知らせが更新されました。<br>【' + oshirase.title + '】 - ' + oshirase.sakuseishaMei + '<br>'
									+ (oshirase.naiyo.length > 30 ? oshirase.naiyo.substring(0, 30) + '...' : oshirase.naiyo), 0);
						});
					});
		        }, 30000);
			}
			,watchEnd : function() {
				if (watch) {
					$interval.cancel(watch);
					watch = undefined;
					nextReqDate = undefined;
				}
			}
		};
    };

    newService.$inject = injectParams;

    angular.module('app')
        .factory('OshiraseService', newService);

}());