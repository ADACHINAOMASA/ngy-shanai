/**
 * 文字入力フィールド用ディレクティブ
 * <input ng-model="search.tantoshaCd" nis-string>
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['AppConst', '$log', 'RulesService', 'OptionsService', '$q'];

    // TODO:linkだと重くなる可能性があるらしいので、compileにする？
    // TODO:文字型入力フィールドに必要な機能を考える（入力制限ぐらい？）
    // TODO:バイトサイズでの桁数制御（カットまで行う？）
    // TODO:parseが行われないタイミングがある
    // TODO:blurタイプにした方が良い？

    // 引数は依存の内容と一致する
    var nisString = function (AppConst, $log, RulesService, OptionsService, $q) {
    	var requiredValidator = function(modelValue, viewValue) {
        	var value = modelValue || viewValue;
        	return !!value;
        };
    	// ディレクティブ内容
    	return {
    		require: 'ngModel',
			restrict: 'A',
			scope: {
				maxlength: '=',
				size: '=',
				sizing: '=',
				multibyte: '=',
				uppercase: '=',
				pattern: '=',
				options: '@',
				rule: '=nisString'
			},
			link: function(scope, elm, attrs, ctrl) {
				// TODO:パフォーマンス様子見
				var rule = scope.rule;
				if (!rule) {
					var rulename = elm.attr('nis-string');
					if(rulename.indexOf('.') == -1) {
						rulename = rulename + '.' + elm.attr('name');
					}
					rule = RulesService.get(rulename) || {};
				}
				var maxlength = Number(scope.maxlength) || rule.maxlength;
				var size = Number(scope.size) || rule.size || maxlength;
				var multibyte = String(scope.multibyte || '') || String(rule.multibyte || '') || 'true';
				var uppercase = String(scope.uppercase || '') || String(rule.uppercase || '') || 'false';
				var sizing = String(scope.sizing || '') || String(rule.sizing || '') || 'true';
				var pattern = scope.pattern || rule.pattern;
				var options = scope.options || '';
				attrs.$set('maxlength', maxlength);
				if (sizing == 'true') {
					attrs.$set('size', size);
				}
				if (pattern) {
					elm
					.bind('keypress', function(e){
						if (e.charCode == 0 || e.ctrlKey || e.altKey) {
							return;
						}
						if (NisUtil.testFilterKeyCode(String.fromCharCode(e.charCode), new RegExp(pattern))) {
							e.preventDefault();
							return;
						}
					});
				}
				ctrl.$parsers.unshift(function(viewValue) {
					if (multibyte == 'true') {
						if (NisUtil.getByteLength(viewValue) <= maxlength) {
							ctrl.$setValidity('maxbytelength', true);
						}
						else {
							ctrl.$setValidity('maxbytelength', false);
							return undefined;
						}
					}
					if (uppercase == 'true') {
						viewValue = viewValue.toUpperCase();
						elm.val(viewValue);
					}
					return viewValue;
				});
				if (multibyte != 'true') {
					elm.addClass('input_singlebyte');
				}
				if (NisUtil.isTrue(rule.required)
						&& (typeof attrs.required === 'undefined')) {
					ctrl.$validators.required = requiredValidator;
				}
				if (options) {
					ctrl.$asyncValidators.optionExists = function(modelValue, viewValue){
						var value = modelValue || viewValue;
						return OptionsService.contains(options, value).then(function(data){
							return data ? true : $q.reject();
						});
					};
				}
			}
    	};
    };

    nisString.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisString', nisString);
    } catch(e) {
    	console.error(e);
    }

}());