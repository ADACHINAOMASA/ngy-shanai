/**
 * 日付入力フィールド用ディレクティブ
 * <input ng-model="search.sakuseiHi" nis-date>
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['$filter', 'AppConst', '$log', 'dateFilter', 'datepickerPopupConfig', 'RulesService'];

    // TODO:linkだと重くなる可能性があるらしいので、compileにする？
    // TODO:datePicker側にもvalidateがあるらしく、競合して妙な動作になる
    /*
     * ui-bootstrapのdatepicker-popupディレクティブは、設定するとparser、formatter、validaterを追加する
     * parserの実行はこれより手前となる
     * また、この後でrenderが実行され、その中でもvalidが設定される
     * 現実装はui-bootstrap0.13.0のparseDate挿入を前提とする
     * 一時的にviewとmodelが非同期状態になってしまうので微妙？→viewの中でもさらに非同期になるので、他の処理でrenderされると危ない
     */

    //TODO:20140601→2014/06/01にしたい
    //		parserが作動しないので別のタイミングで実行する必要がある

    //		孤立したスコープにする事は出来ない（datePickerが既に使っている為）

    // 引数は依存の内容と一致する
    var nisDate = function ($filter, AppConst, $log, dateFilter, datepickerPopupConfig, RulesService) {
    	var requiredValidator = function(modelValue, viewValue) {
        	var value = modelValue;
        	return !!value;
        };
    	// ディレクティブ内容
    	return {
    		require: 'ngModel',
			restrict: 'A',
			link: function(scope, elm, attrs, ctrl) {
				// 孤立スコープに出来ないので他とルールの取得方を変える
				var rulename = elm.attr('nis-date');
				var rule = scope[rulename];
				if (!rule && rulename) {
					if(rulename.indexOf('.') == -1) {
						rulename = rulename + '.' + elm.attr('name');
					}
					rule = RulesService.get(rulename);
				}
				rule = rule || {};

				var dateFormat = attrs.datepickerPopup || datepickerPopupConfig.datepickerPopup;
				elm
				.addClass('input_date')
				// IEではBSといったキーはそもそもkeypressが発火しないので注意
				.bind('keypress', function(e){
					if (e.charCode == 0 || e.ctrlKey || e.altKey) {
						return;
					}
					if (NisUtil.testFilterKeyCode(String.fromCharCode(e.charCode), AppConst.filterPattern.DATE)) {
						e.preventDefault();
						return;
					}
				})
				// keydownとkeypressが同時に設定されている場合、down→pressの順に発火
				.bind('keydown', function(e){
					if (e.keyCode == 38 || e.keyCode == 40) {
						if(ctrl.$modelValue && ctrl.$valid && angular.isDate(ctrl.$modelValue)) {
							var add = (e.keyCode == 38 ? 1 : -1);
							if (e.shiftKey) {
								ctrl.$modelValue.setMonth(ctrl.$modelValue.getMonth() + add);
							}
							else {
								ctrl.$modelValue.setDate(ctrl.$modelValue.getDate() + add);
							}
							ctrl.$setViewValue(dateFilter(ctrl.$modelValue, dateFormat));
							ctrl.$render();
						}
					}
				})
				.bind('change', function(e){
					var viewValue = ctrl.$viewValue;
					if (angular.isDate(ctrl.$modelValue) && dateFilter(ctrl.$modelValue, dateFormat) != viewValue) {
						ctrl.$setViewValue(dateFilter(ctrl.$modelValue, dateFormat));
						ctrl.$render();
						return;
					}
				})
				;
				attrs.$set('maxlength', dateFormat.length);
				attrs.$set('size', dateFormat.length);

				ctrl.$parsers.unshift(function(viewValue) {
					if (!viewValue) {
						ctrl.$setViewValue(new Date());
						return null;
					}
					return viewValue;
				});
				if (NisUtil.isTrue(rule.required)
						|| (typeof attrs.required !== 'undefined')) {
					ctrl.$validators.required = requiredValidator;
				}
			}
    	};
    };

    nisDate.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisDate', nisDate);
    } catch(e) {
    	console.error(e);
    }

}());