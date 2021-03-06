/**
 * 文字入力フィールド用ディレクティブ
 * <input ng-model="search.tantoshaCd" nis-string>
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['AppConst', '$log', 'RulesService', 'OptionsService', '$q', '$filter'
                        ,'dateFilter', 'uibDatepickerPopupConfig'];

    // TODO:linkだと重くなる可能性があるらしいので、compileにする？
    // TODO:文字型入力フィールドに必要な機能を考える（入力制限ぐらい？）
    // TODO:バイトサイズでの桁数制御（カットまで行う？）
    // TODO:parseが行われないタイミングがある
    // TODO:blurタイプにした方が良い？
    //      →全てblurで制御を行うのは一考に値する
    // TODO:touched関係は様子見

    // 引数は依存の内容と一致する
    var nisInput = function (AppConst, $log, RulesService, OptionsService, $q, $filter
    		,dateFilter, uibDatepickerPopupConfig) {
    	var stringRequiredValidator = function(modelValue, viewValue) {
        	var value = modelValue || viewValue;
        	return !!value;
        }
    	,dateRequiredValidator = function(modelValue, viewValue) {
        	var value = modelValue;
        	return !!value;
        }
        ,numberRequiredValidator = function(modelValue, viewValue) {
        	var value = modelValue || viewValue;
        	return !!value && (new Number(value)) != 0;
        };

        var setString = function(rule, elm, attrs, ctrl, scope) {
        	var maxlength = Number(attrs.maxlength) || rule.maxlength;
			var size = Number(attrs.size) || rule.size || maxlength;
			var multibyte = String(attrs.multibyte || '') || String(rule.multibyte || '') || 'true';
			var uppercase = String(attrs.uppercase || '') || String(rule.uppercase || '') || 'false';
			var sizing = String(attrs.sizing || '') || String(rule.sizing || '') || 'true';
			var pattern = attrs.pattern || rule.pattern;
			var options = attrs.options || rule.options;
			// TODO:カナをプロパティルールから指定できるようにするか検討中（ケースが限られる為）
			var kana = attrs.kana || rule.kana;
			attrs.$set('maxlength', maxlength);
			if (sizing == 'true') {
				attrs.$set('size', size);
			}
			if(!ctrl.$validators.maxlength && maxlength) {
				// TODO:元々のmaxlengthvalidatorを使いたい
				ctrl.$validators.maxlength = function(modelValue, viewValue) {
					var value = modelValue || viewValue || '';
					return value.length <= maxlength;
				};
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
				var regExp = new RegExp('^' + pattern + '*$');
				ctrl.$validators.invalidstring = function(modelValue, viewValue){
					var value = modelValue || viewValue;
					if (!value) {
						return true;
					}
					return regExp.test(value);
				};
			}
			// 暫定
			if (kana == 'HK') {
				elm.bind('blur', function(e){
					if (elm.val()) {
						ctrl.$setViewValue(new Moji(elm.val()).convert('HG', 'KK').convert('ZK', 'HK').toString());
						ctrl.$render();
					}
				});
			}
			ctrl.$parsers.unshift(function(viewValue) {
				if (multibyte == 'true' && maxlength) {
					// TODO:parserの中でvalidateはあまりやらない方がよい
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
				ctrl.$validators.required = stringRequiredValidator;
			}
			if (options) {
				if (scope[options]) {
					ctrl.$validators.optionExists = function(modelValue, viewValue){
						var scopeOptions = scope[options];
						var value = modelValue || viewValue;
						if (!value) {
							return true;
						}
						return _.any(scopeOptions, function(option){
							return option.value == value;
						});
					};
					scope.$watchCollection(options, function(newValue, oldValue){
						ctrl.$validate();
					});
				}
				else {
					ctrl.$asyncValidators.optionExists = function(modelValue, viewValue){
						var value = modelValue || viewValue;
						if (!value) {
							var defferd = $q.defer();
							defferd.resolve(true);
							return defferd.promise;
						}
						return OptionsService.contains(options, value).then(function(data){
							return data ? true : $q.reject();
						});
					};
				}
			}
        },
        // TODO:ショートカットが効かない(ショートカットの挙動についてどうするか）
        //      とりあえずAltとCtrlが押されている場合は入力制御を行わない
        // TODO:ペースト時には入力制御が走らない（validateは走る）
        // TODO:linkだと重くなる可能性があるらしいので、compileにする？
        // TODO:linkスコープ内のクロージャ変数は定義しても大丈夫？
        // TODO:validate用のlogicをどうやるか考え中（なるべく処理が軽いものに、とりあえず正規表現で）
        // TODO:validateはparse内でなくwatchに追加した方が良い？

        // TODO:20151116 入力制御がかなり不安定なので全体見直し必須
        //				原因の一つとして、modelController上の値とmodel値の分離がある

        // TODO:updateOn:blurでの形に変更

        setNumber = function(rule, elm, attrs, ctrl, scope){
        	var minus = String(attrs.minus || '') || String(rule.minus || '') || 'false';
			var maxlength = Number(attrs.maxlength) || Number(rule.maxlength);
			var scale = Number(attrs.scale) || Number(rule.scale) || 0;
			var decimallength = Number(attrs.decimallength) || maxlength - scale;
			var formatlength = (decimallength + Math.floor(decimallength > 3 ?(decimallength - 1) / 3 : 0))
								+ (scale > 0 ? 1 + scale : 0)
								+ (minus == 'true' ? 1 : 0);
			var size = Number(attrs.size) || rule.size || formatlength;
			var sizing = String(attrs.sizing || '') || String(rule.sizing || '') || 'true';
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
			// TODO:parserが$validateで呼び出されない対応
			if(!ctrl.$validators.maxlength && maxlength) {
				ctrl.$validators.maxlength = function(modelValue, viewValue) {
					var value = modelValue || viewValue || '';
					if (angular.isNumber(value)) {
						value = String(value);
					}
					return value.length <= decimallength + (scale ? scale + 1:0);
				};
			}
			if (sizing == 'true') {
				attrs.$set('size', size);
			}

			// updateOnに何も設定されていないのなら、blurにする
			// やや強引過ぎるか
			var modelOptions = ctrl.$options || {};
			modelOptions.updateOn = modelOptions.updateOn || 'blur';
			ctrl.$options = modelOptions;

			elm.addClass('input_number')
			.bind('keypress', function(e){
				if (!ctrl.$touched) {
					ctrl.$setTouched();
				}
				if (e.charCode == 0 || e.ctrlKey || e.altKey) {
					return;
				}
//				if (NisUtil.checkKeyDownExKey(e.which)) {
//					return;
//				}
				if (NisUtil.testFilterKeyCode(String.fromCharCode(e.charCode), filterValue)) {
					e.preventDefault();
					return;
				}
			// TODO:以下は様子見
			})
			.bind('focus', function(e) {
				if(/^0\.?0*$/.test(elm.val())) {
					elm.val('');
				}
				else {
					//TODO サイ　一時コメントアウトする。動きがおかしい！！！
					
					//var viewValue = elm.val().replace(/[^\d|\-+|\.+]/g, '') || '0';
					//elm.val(viewValue);
					//ctrl.$setViewValue(viewValue);
				}
				
			})
			.bind('blur', function(e) {
				if(elm.val() === '') {
					if(scale>0){
						var str = '0.';
						for(var i=0;i<scale;i++){
							str = str+'0'
						}
						elm.val(str);
					}
					else{
						elm.val('0');	
					}
					//elm.val('0.0');
				}
				else {
					var viewValue = $filter('number')(elm.val(), scale);
					if (viewValue) {
						elm.val(viewValue);
						ctrl.$setViewValue(viewValue);
					}
				}
			});

			ctrl.$formatters.unshift(function(data){
				if (!data) {
					return '0';
				}
				return $filter('number')(ctrl.$modelValue, scale);
			});

			ctrl.$parsers.unshift(function(viewValue) {
				var plainValue = viewValue.replace(/[^\d|\-+|\.+]/g, '') || '0';
				return Number(plainValue);
			});

			ctrl.$validators.number = function(modelValue, viewValue) {
				return /^$|^\-?\d+((\.|\,)\d+)*$/.test(viewValue);
	        };
	        ctrl.$validators.numberDecimalLength = function(modelValue, viewValue) {
	        	if (!viewValue) {
	        		return true;
	        	}
				return new RegExp(decimalExp).test(viewValue.replace(/\-|\,|\..*/g, ''));
	        };
	        if (scale > 0) {
		        ctrl.$validators.numberScaleLength = function(modelValue, viewValue) {
					return new RegExp(scaleExp).test(viewValue);
		        };
	        }
	        if (NisUtil.isTrue(rule.required)
					|| (typeof attrs.required !== 'undefined')) {
				ctrl.$validators.required = numberRequiredValidator;
			}
        },
        // TODO:linkだと重くなる可能性があるらしいので、compileにする？
        /*
         * ui-bootstrapのdatepicker-popupディレクティブは、設定するとparser、formatter、validaterを追加する
         * parserの実行はこれより手前となる
         * また、この後でrenderが実行され、その中でもvalidが設定される
         * 現実装はui-bootstrap0.13.0のparseDate挿入を前提とする
         * 一時的にviewとmodelが非同期状態になってしまうので微妙？→viewの中でもさらに非同期になるので、他の処理でrenderされると危ない
         */

        setDate = function(rule, elm, attrs, ctrl, scope){
        	var dateFormat = attrs.uibDatepickerPopup || uibDatepickerPopupConfig.datepickerPopup;
			elm
			.addClass('input_date')
			// IEではBSといったキーはそもそもkeypressが発火しないので注意
			.bind('keypress', function(e){
				if (!ctrl.$touched) {
					ctrl.$setTouched();
				}
				if (e.charCode == 0 || e.ctrlKey || e.altKey) {
					return;
				}
				if (NisUtil.testFilterKeyCode(String.fromCharCode(e.charCode), AppConst.filterPattern.DATE)) {
					e.preventDefault();
					return;
				}
			})
			// keydownとkeypressが同時に設定されている場合、down→pressの順に発火
//			.bind('keydown', function(e){
//				if (e.keyCode == 38 || e.keyCode == 40) {
//					if(ctrl.$modelValue && ctrl.$valid && angular.isDate(ctrl.$modelValue)) {
//						var add = (e.keyCode == 38 ? 1 : -1);
//						if (e.shiftKey) {
//							ctrl.$modelValue.setMonth(ctrl.$modelValue.getMonth() + add);
//						}
//						else {
//							ctrl.$modelValue.setDate(ctrl.$modelValue.getDate() + add);
//						}
//						ctrl.$setViewValue(dateFilter(ctrl.$modelValue, dateFormat));
//						ctrl.$render();
//					}
//				}
//			})
			.bind('change', function(e){
				var val = elm.val();
				if (!val) {
					return;
				}
				var dateVal = NisUtil.date.parse(val);
				if (dateVal) {
					var formatedVal = NisUtil.date.format(dateVal, dateFormat);
					elm.val(formatedVal);
					ctrl.$setViewValue(formatedVal);
				}
			})
			;

			var modelOptions = ctrl.$options || {};
			modelOptions.updateOn = modelOptions.updateOn || 'blur';
			ctrl.$options = modelOptions;

			attrs.$set('maxlength', dateFormat.length);
			attrs.$set('size', dateFormat.length);

			if (NisUtil.isTrue(rule.required)
					|| (typeof attrs.required !== 'undefined')) {
				ctrl.$validators.required = dateRequiredValidator;
			}
        };
        
        //暫定　サイ　2016/03/28 追加 -----------------------------------------------------------↓     
        var setRule = function(rule , scope , elm, attrs, ctrl){
			var inputclass = attrs.inputclass || rule.inputclass;
			switch(angular.lowercase(inputclass)) {
				case 'string' :
					setString(rule, elm, attrs, ctrl, scope);
					break;
				case 'number' :
					setNumber(rule, elm, attrs, ctrl, scope);
					break;
				case 'date' :
					setDate(rule, elm, attrs, ctrl, scope);
					break;
				default :
					alert(attrs.name + ' :inputclass未指定');
			}
        };
        //---------------------------------------------------------------------------↑
        
    	// ディレクティブ内容
    	return {
    		require: 'ngModel',
			restrict: 'A',
			link: function(scope, elm, attrs, ctrl) {
				/*
				// TODO:パフォーマンス様子見
				var rulename = elm.attr('nis-input');
				var rule = NisUtil.property.getProperty(scope, rulename);
				if (!rule && rulename) {
					if(rulename.indexOf('.') == -1) {
						rulename = rulename + '.' + elm.attr('name');
					}
					rule = RulesService.get(rulename);
					if (!rule) {
						console.log('rule未取得:' + rulename);
					}
				}
				rule = rule || {};

				var inputclass = attrs.inputclass || rule.inputclass;
				switch(angular.lowercase(inputclass)) {
					case 'string' :
						setString(rule, elm, attrs, ctrl, scope);
						break;
					case 'number' :
						setNumber(rule, elm, attrs, ctrl, scope);
						break;
					case 'date' :
						setDate(rule, elm, attrs, ctrl, scope);
						break;
					default :
						alert(attrs.name + ' :inputclass未指定');
				}
				*/
				
				//暫定　サイ　2016/03/28 追加 -----------------------------------------------------------↓
				var rulename = elm.attr('nis-input');
				if(!rulename){
					console.log('nis-input 属性が定義されていません。');
					return;
				}
				var motoRulename = rulename;
				var rule = NisUtil.property.getProperty(scope, rulename);
				if (!rule && rulename) {
					if(rulename.indexOf('.') == -1) {
						rulename = rulename + '.' + elm.attr('name');
					}
					rule = RulesService.get(rulename);
					if (!rule) {
						console.log('rule未取得:' + rulename);
					}
				}
				rule = rule || {};
				if(!angular.equals({}, rule)){
					setRule(rule , scope , elm, attrs, ctrl);
				}else{
					//Ruleを取得できなかった場合、再度取得する。
					RulesService.load2([motoRulename],function(){
						setRule(RulesService.get(rulename) , scope , elm, attrs, ctrl);
					});
				}
				//---------------------------------------------------------------------------↑
				
			}
    	};
    };

    nisInput.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisInput', nisInput);
    } catch(e) {
    	console.error(e);
    }

}());