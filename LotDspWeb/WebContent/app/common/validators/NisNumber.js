/**
 * 数字入力フィールド用ディレクティブ
 * 同時定義属性として、maxlength,scale,minus,decimallength
 * 例：<input ng-model="search.bumonCd" nis-number maxlength="9" scale="3" minus="false">
 * 整数6桁,小数3桁,マイナス不可
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['$filter', 'AppConst', 'RulesService'];

    // TODO:ショートカットが効かない(ショートカットの挙動についてどうするか）
    //      とりあえずAltとCtrlが押されている場合は入力制御を行わない
    // TODO:ペースト時には入力制御が走らない（validateは走る）
    // TODO:linkだと重くなる可能性があるらしいので、compileにする？
    // TODO:linkスコープ内のクロージャ変数は定義しても大丈夫？
    // TODO:validate用のlogicをどうやるか考え中（なるべく処理が軽いものに、とりあえず正規表現で）
    // TODO:必須項目の設定可能可
    //      実現方法は確保してあるが、色々面倒そうなので後回しに
    // TODO:validateはparse内でなくwatchに追加した方が良い？

    // 引数は依存の内容と一致する
    var nisNumber = function ($filter, AppConst, RulesService) {
    	var requiredValidator = function(modelValue, viewValue) {
        	var value = modelValue || viewValue;
        	return !!value && (new Number(value)) != 0;
        };
    	// ディレクティブ内容
    	return {
    		require: 'ngModel',
			restrict: 'A',
			scope: {
				maxlength: '=',
				decimallength: '=',
				minus: '=',
				scale: '=',
				size: '=',
				sizing: '=',
				rule: '=nisNumber'
			},
			link: function(scope, elm, attrs, ctrl) {
				// TODO:パフォーマンス様子見
				var rule = scope.rule;
				if (!rule) {
					var rulename = elm.attr('nis-number');
					if(rulename.indexOf('.') == -1) {
						rulename = rulename + '.' + elm.attr('name');
					}
					rule = RulesService.get(rulename) || {}
				}
				var required = String(rule.required || '') || 'false';
				var minus = String(scope.minus || '') || String(rule.minus || '') || 'false';
				var maxlength = Number(scope.maxlength) || rule.maxlength;
				var scale = Number(scope.scale) || rule.scale || 0;
				var decimallength = Number(scope.decimallength) || maxlength - scale;
				var formatlength = (decimallength + Math.floor(decimallength > 3 ?(decimallength - 1) / 3 : 0))
									+ (scale > 0 ? 1 + scale : 0)
									+ (minus == 'true' ? 1 : 0);
				var size = Number(scope.size) || rule.size || formatlength;
				var sizing = String(scope.sizing || '') || String(rule.sizing || '') || 'true';
				var scaleExp = '^[^\\.]*$|\\.\\d{1,' + scale + '}$';
				var decimalExp = '^$|^\\d{1,' + decimallength + '}$';
				var filterPattern = "NUMBER";
				if (scale > 0) {
					filterPattern += "_DECIMAL";
				}
				if (minus == 'true') {
					filterPattern += "_MINUS";
				}
				var filterValue = AppConst.filterPattern[filterPattern];
				attrs.$set('maxlength', formatlength);
				if (sizing == 'true') {
					attrs.$set('size', size);
				}
				elm.addClass('input_number')
				.bind('keypress', function(e){
					if (e.charCode == 0 || e.ctrlKey || e.altKey) {
						return;
					}
//					if (NisUtil.checkKeyDownExKey(e.which)) {
//						return;
//					}
					if (NisUtil.testFilterKeyCode(String.fromCharCode(e.charCode), filterValue)) {
						e.preventDefault();
						return;
					}
				});

				ctrl.$formatters.unshift(function(data){
					return $filter('number')(ctrl.$modelValue);
				});
				ctrl.$parsers.unshift(function(viewValue) {
					if (/^$|^\-?\d+((\.|\,)\d+)*$/.test(viewValue)) {
						ctrl.$setValidity('number', true);
					}
					else {
						ctrl.$setValidity('number', false);
						return undefined;
					}
					var plainValue = viewValue.replace(/[^\d|\-+|\.+]/g, '') || '0';
					var number = plainValue.split('.')[0] || plainValue;
					var decimal = plainValue.split('.')[1] || '';
					elm.val($filter('number')(number) + (decimal ? '.' + decimal : ''));
					if (!viewValue) {
						ctrl.$setViewValue('0');
					}
					if (new RegExp(decimalExp).test(viewValue.replace(/\-|\,|\..*/g, ''))) {
						ctrl.$setValidity('number-decimal-length', true);
					}
					else {
						ctrl.$setValidity('number-decimal-length', false);
						return undefined;
					}
					if (scale > 0) {
						if (new RegExp(scaleExp).test(viewValue)) {
							ctrl.$setValidity('number-scale-length', true);
						}
						else {
							ctrl.$setValidity('number-scale-length', false);
							return undefined;
						}
					}
					return plainValue;
				});
				if (NisUtil.isTrue(rule.required)
						|| (typeof attrs.required !== 'undefined')) {
					ctrl.$validators.required = requiredValidator;
				}
			}
    	};
    };

    nisNumber.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisNumber', nisNumber);
    } catch(e) {
    	console.error(e);
    }

}());