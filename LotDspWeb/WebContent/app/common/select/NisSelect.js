/**
 * ラジオボタン
 * <nis-Select model="input.shodanJokyo" Selects="Selectr" vertical="false"></nis-Select>
 * @param Selects このラジオ用のoptions コントローラーのscopeに定義されていればそれを使用、なければサーバーから取得
 * @vetical trueで縦配置
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['OptionsService'];

    // TODO:selectsの初期値をmodelのoptionsからいけるように

    // 引数は依存の内容と一致する
    var nisSelect = function (OptionsService) {
    	// ディレクティブ内容
    	return {
			restrict: 'E',
			templateUrl: 'app/common/select/select.html',
			scope: {
				model:'=',
				ctrl:'=',
				selects: '=',
				cashe: '@',
				blank: '@',
				styleClass: '@',
				// TODO:カスタムディレクティブ内でangularの属性ディレクティブが使いたかったら、このようにするしかないのだろうか？
				ngDisabled: '=',
				onChange: '&',
				selectsWatch: '@'
			},
			replace: true,
			link: function(scope, element, attrs) {
				scope.cache = scope.cache || 'true';
				scope.ctrlTouch = (function(){
					if (!scope.ctrl) {
						return angular.noop;
					}
					return function(){
						if(!scope.ctrl.$touched) {
							scope.ctrl.$setTouched();
						}
					};
				})();

				scope.ctrlChange = (function(){
					if (!scope.ctrl) {
						return angular.noop;
					}
					return function(){
						if(!scope.ctrl.$dirty) {
							scope.ctrl.$setDirty();
						}
					};
				})();

				if (attrs.onChange) {
					scope.$watch('model', function(newValue, offValue){
						if (newValue != offValue) {
							scope.onChange();
						}
					});
				}

				// TODO:パスがスコープのルート限定になってしまっている
				if (!scope.selects) {
					OptionsService.getOptions(attrs.selects, scope.cache == 'true').then(function(data){
						scope.options = data.options;
					});
				}else {
					if (scope.selectsWatch == 'true') {
						// TODO:要検証
						scope.$parent.$watchCollection(attrs.selects, function(newValue){
							scope.options = newValue;
						});
					}
					else {
						scope.options = scope.selects;
					}
				}
			}
    	};
    };

    nisSelect.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisSelect', nisSelect);
    } catch(e) {
    	console.error(e);
    }

}());