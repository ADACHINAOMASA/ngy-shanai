/**
 * アラートサービス
 * 画面上にアラートをポップアップさせる。
 * timeに0を渡すとクリックするまで消えなくなる
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['toaster', 'AppConfig', 'AlertLogService'];

    // 引数は依存の内容と一致する
    var AlertService = function (toaster, AppConfig, AlertLogService) {
    	var
    		SUCCESS_SHOW_TIME_DEFAULT = AppConfig.ALERT_SUCCESS_SHOW_TIME_DEFAULT,
    		INFO_SHOW_TIME_DEFAULT = AppConfig.ALERT_INFO_SHOW_TIME_DEFAULT,
    		WAIT_SHOW_TIME_DEFAULT = AppConfig.ALERT_WAIT_SHOW_TIME_DEFAULT,
    		WARNING_SHOW_TIME_DEFAULT = AppConfig.ALERT_WARNING_SHOW_TIME_DEFAULT,
    		DANGER_SHOW_TIME_DEFAULT = AppConfig.ALERT_DANGER_SHOW_TIME_DEFAULT;

    	return {
			addSuccess: function(msg, time, label) {
				time = time || (time === 0 ? 0 : SUCCESS_SHOW_TIME_DEFAULT);
				label = label || '成功';
				toaster.pop('success', label, '<p>' + msg + '</p>', time, 'trustedHtml');
				AlertLogService.addSuccess(label, msg);
			},
			addInfo: function(msg, time, label) {
				time = time || (time === 0 ? 0 : INFO_SHOW_TIME_DEFAULT);
				label = label || '情報';
				toaster.pop('info', label, '<p>' + msg + '</p>', time, 'trustedHtml');
				AlertLogService.addInfo(label, msg);
			},
			addWait: function(msg, time, label) {
				msg = msg || '時間がかかる場合があります';
				time = time || (time === 0 ? 0 : WAIT_SHOW_TIME_DEFAULT);
				label = label || '処理中';
				toaster.pop('wait', label, '<p>' + msg + '</p>', time, 'trustedHtml');
				AlertLogService.addWait(label, msg);
			},
			addWarning: function(msg, time, label) {
				time = time || (time === 0 ? 0 : WARNING_SHOW_TIME_DEFAULT);
				label = label || '警告';
				toaster.pop('warning', label, '<p>' + msg + '</p>', time, 'trustedHtml');
				AlertLogService.addWarning(label, msg);
			},
			addDanger: function(msg, time, label) {
				time = time || (time === 0 ? 0 : DANGER_SHOW_TIME_DEFAULT);
				label = label || '失敗';
				toaster.pop('error', label, '<p>' + msg + '</p>', time, 'trustedHtml');
				AlertLogService.addDanger(label, msg);
			},
			addAlert: function(uiAlert) {
				this.addAlerts([uiAlert]);
			},
			addAlerts: function(addAlerts) {
				var that = this;
				_.each(addAlerts, function(alert){
					switch(alert.type) {
						case 'success' :
							that.addSuccess(alert.msg);
							break;
						case 'info' :
							that.addInfo(alert.msg);
							break;
						case 'warning' :
							that.addWarning(alert.msg);
							break;
						case 'danger' :
							that.addDanger(alert.msg);
							break;
					}
				});
			},
			clear: function() {
				toaster.clear();
			}
		};
    };

    AlertService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('AlertService', AlertService);

}());