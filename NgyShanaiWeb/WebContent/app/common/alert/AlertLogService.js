/**
 * アラートログサービス
 * アラートサービスによって追加されたアラートを保持する
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['AppConfig'];

    // 引数は依存の内容と一致する
    var newService = function (AppConfig) {

    	var alertLogs = [];

    	return {
    		add: function(type, label, msg) {
        		alertLogs.unshift({
        			type: type,
        			label: label,
        			msg: msg,
        			timestamp: new Date()
        		});
        		if (alertLogs.length > AppConfig.ALERT_LOG_MAX_SIZE) {
        			alertLogs.pop();
        		}
    		},
    		addSuccess: function(label, msg) {
    			this.add('success', label, msg);
    		},
    		addInfo: function(label, msg) {
    			this.add('info', label, msg);
    		},
    		addWait: function(label, msg) {
    			this.add('wait', label, msg);
    		},
    		addWarning: function(label, msg) {
    			this.add('warning', label, msg);
    		},
    		addDanger: function(label, msg) {
    			this.add('danger', label, msg);
    		},
    		logs: function() {
    			return alertLogs;
    		},
    		remove: function(index) {
    			if (index >= alertLogs.length) {
    				return;
    			}
    			alertLogs.aplice(index, 1);
    		},
    		clear: function() {
    			alertLogs.splice(0, alertLogs.length);
    		}
		};
    };

    newService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('AlertLogService', newService);

}());