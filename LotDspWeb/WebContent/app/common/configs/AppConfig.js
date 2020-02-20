/**
 * アプリケーション全体のコンフィグ
 * アプリケーション毎に書き換えるものはこちらに
 */
(function () {

	// TODO:上書きする場合は別クラスで上書きするようにするか（デフォルト値の保持のため）

	// コンフィグ内容
    var value = {
    		HOME_STATE_NAME : 'home',
    		LOGIN_STATE_NAME : 'login',

        	// Alertサービスのデフォルト表示時間
        	// 0にするとクリックするまで消えなくなる
        	ALERT_SUCCESS_SHOW_TIME_DEFAULT : 5000,
        	ALERT_INFO_SHOW_TIME_DEFAULT : 5000,
        	ALERT_WAIT_SHOW_TIME_DEFAULT : 3000,
        	ALERT_WARNING_SHOW_TIME_DEFAULT : 5000,
        	ALERT_DANGER_SHOW_TIME_DEFAULT : 0,

        	// AlertLogの最大保存数
        	ALERT_LOG_MAX_SIZE : 10
    };

    angular.module('app').value('AppConfig', value);

}());
