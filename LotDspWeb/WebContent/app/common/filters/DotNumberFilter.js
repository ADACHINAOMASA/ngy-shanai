/**
 * DotNumberフィルター
 * 指定した桁数分の小数点以下数値を付与し返却する（カンマなし）
 * 数値以外の値が渡された場合は0を返却する
 *
 * {{value | dotNumber}}
 *
 * 使用例
 *	{{0 | dotNumber:1}} -> '0.0'
 *	{{'' | dotNumber:1}} -> '0'
 */
(function() {

	// 必要な依存を列挙
	var injectParams = ['$filter'];

	// 引数は依存の内容と一致する
	var DotNumberFilter = function($filter) {
		return function(number, decimal) {
			if (isNaN(Number(number))){
				return 0;
			}
			number = Number(number);
			return number.toFixed(decimal);
		};
	};

	DotNumberFilter.$inject = injectParams;

	angular.module('app').filter('dotNumber', DotNumberFilter);

}());