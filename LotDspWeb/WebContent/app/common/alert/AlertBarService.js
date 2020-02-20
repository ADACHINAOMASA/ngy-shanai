/*
 * アラートバーサービス
 * 画面上部にアラートを表示する
 * toasterを使う形に移行したので今は未使用
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['$location', '$anchorScroll', '$window', '$timeout'];

    // 引数は依存の内容と一致する
    var AlertService = function ($location, $anchorScroll, $window, $timeout) {
    	var alerts = [];
    	return {
    		getAlerts: function() {
    			return alerts;
    		},
			addSuccess: function(addMsg, action, clear) {
				this.addAlert({type: 'success', msg: addMsg}, action, clear);
			},
			addInfo: function(addMsg, action, clear) {
				this.addAlert({type: 'info', msg: addMsg}, action, clear);
			},
			addWarning: function(addMsg, action, clear) {
				this.addAlert({type: 'warning', msg: addMsg}, action, clear);
			},
			addDanger: function(addMsg, action, clear) {
				this.addAlert({type: 'danger', msg: addMsg}, action, clear);
			},
			addAlert: function(uiAlert, action, clear) {
				this.addAlerts([uiAlert], action, clear);
			},
			addAlerts: function(addAlerts, action, clear) {
				if (clear) {
					this.clear(action);
				}
				addAlerts.reverse();
				_.each(addAlerts, function(alert){
					alert.action = action;
					alert.timestamp = NisUtil.formatDate(new Date(), 'yyyy/MM/dd hh:mm:ss');
					alerts.unshift(alert);
				});
				// TODO:要検討
//				$location.hash('alerts');
//				$anchorScroll();
				$timeout(function(){$window.scrollTo(0, 0)}, 100);
			},
			clear: function(action) {
				if (alerts.length === 0) {
					return;
				}
				if (!action) {
					alerts.splice(0, alerts.length);
					return;
				}
				for (var i = 0;i < alerts.length;i++) {
					if (alerts[i].action === action) {
						alerts.splice(i, 1);
					}
				}
			}
		};
    };

    AlertService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('AlertService', AlertService);

}());