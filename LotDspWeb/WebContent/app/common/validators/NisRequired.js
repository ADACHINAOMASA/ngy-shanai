/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:これでいいのかは要検討

    // 必要な依存を列挙
    var injectParams = ['RulesService'];

    // 引数は依存の内容と一致する
    var nisRequired = function (RulesService) {
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
    	// ディレクティブ内容
    	return {
    		require:'ngModel',
			restrict: 'A',
			link: function(scope, elm, attrs, ctrl) {
				var rulename = elm.attr('nis-input');
				var rule = scope[rulename];
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
				var validator;
				switch(angular.lowercase(inputclass)) {
					case 'string' :
						validator = stringRequiredValidator;
						break;
					case 'number' :
						validator = numberRequiredValidator;
						break;
					case 'date' :
						validator = dateRequiredValidator;
						break;
				}

				scope.$watch(attrs.nisRequired, function(newValue){
					if (newValue) {
						ctrl.$validators.required = validator;
						ctrl.$validate();
					}
					else {
						delete ctrl.$validators.required;
						ctrl.$setValidity('required', true);
					}
				});
			}
    	};
    };

    nisRequired.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisRequired', nisRequired);
    } catch(e) {
    	console.error(e);
    }

}());