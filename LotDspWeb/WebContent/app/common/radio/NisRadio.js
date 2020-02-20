/**
 * ラジオボタン
 * <nis-radio model="input.shodanJokyo" radios="radior" vertical="false"></nis-radio>
 * @param radios このラジオ用のoptions コントローラーのscopeに定義されていればそれを使用、なければサーバーから取得
 * @vetical trueで縦配置
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['OptionsService'];

    // 引数は依存の内容と一致する
    var nisRadio = function (OptionsService) {
    	var requiredValidator = function(modelValue, viewValue) {
        	var value = modelValue || viewValue;
        	return !!value;
        };
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/radio/radio.html',
			scope: {
				model:'=',
				radios: '=',
				ctrl: '=',
				name: '@',
				vertical: '@',
				cashe: '@',
				all: '@',
				// TODO:カスタムディレクティブ内でangularの属性ディレクティブが使いたかったら、このようにするしかないのだろうか？
				ngDisabled: '='
			},
			replace: true,
			link: function(scope, element, attrs) {
				scope.vertical = scope.vertical || 'false';
				scope.all = scope.all || 'false';
				scope.cache = scope.cache || 'true';
				scope.required = (typeof attrs.required !== 'undefined' ? 'true' : 'false');

				scope.ctrlTouch = (function(){
					if (!scope.ctrl) {
						return function(){};
					}
					return function(){
						if(!scope.ctrl.$touched) {
							scope.ctrl.$setTouched();
						}
					};
				})();

				scope.ctrlChange = (function(){
					if (!scope.ctrl) {
						return function(){};
					}
					return function(){
						if(!scope.ctrl.$dirty) {
							scope.ctrl.$setDirty();
						}
					};
				})();

				if (!scope.radios) {
					OptionsService.getOptions(attrs.radios, scope.cache == 'true').then(function(data){
						scope.options = data.options;
					});
				}else {
					scope.options = scope.radios;
				}
			}
    	};
    };

    nisRadio.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisRadio', nisRadio);
    } catch(e) {
    	console.error(e);
    }

}());