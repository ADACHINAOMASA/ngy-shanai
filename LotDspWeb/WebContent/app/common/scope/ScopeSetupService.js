/**
 * 各コントローラーで使う$scopeに対して行うセットアップを纏めたもの
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['AppConfig', 'AppConst', 'i18nService'];

    // 引数は依存の内容と一致する
    var newService = function (AppConfig, AppConst, i18nService) {

    	var 
    		// DatePickerの開閉制御
    		_setupDatePickerOpenFlg = function(scope){
    			if (scope.calOpen) {
    				console.log('calOpenがscopeに存在していますが、setupで上書きしました。')
    			}
    			if (scope.toggleDatePicker) {
    				console.log('toggleDatePickerがscopeに存在していますが、setupで上書きしました。')
    			}
    			(function(){
    				this.calOpen = {};
    				this.toggleDatePicker = function($event, flag) {
    	    			$event.stopPropagation();
    	    			
    	    			//add prevent multi calendar open 
    	    			/*
    	    			for(var key in this.calOpen) {
    	    			    var value = this.calOpen[key];
    	    			    this.calOpen[key]=false;
    	    			}
    	    			*/
    	    			//----------------------------
    	    			
    	    			this.calOpen[flag] = !this.calOpen[flag];
    	    		};
    			}).apply(scope);
    		},
    		// DatePickerのデフォルトのOptions
	    	_setupDefaultDatePickerOptions = function(scope){
    			if (scope.datePickerOptions) {
    				console.log('datePickerOptionsがscopeに存在していますが、setupで上書きしました。')
    			}
    			scope.datePickerOptions = AppConst.defaultDatePickerOptions;
			},
            // langの設定
			_setupLangs = function(scope){
    			if (scope.langs) {
    				console.log('langsがscopeに存在していますが、setupで上書きしました。')
    			}
    			scope.langs = i18nService.getAllLangs();
			}
			
    		;
    	/**
         * スコープの初期設定の実行
         * @param {scope} セットアップしたいスコープ
         */
    	this.setupDefault = function(scope){
    		_setupDatePickerOpenFlg(scope);
    		_setupDefaultDatePickerOptions(scope);
    		_setupLangs(scope);
    	};
    	
    };

    newService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .service('ScopeSetupService', newService);

}());