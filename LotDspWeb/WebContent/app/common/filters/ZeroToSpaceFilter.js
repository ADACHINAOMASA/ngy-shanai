/**
 * ZeroToSpaceフィルター
 * 0を空文字に変える、それ以外の場合はそのまま返却する
 *
 * {{value | zeroToSpace}}
 *
 * 使用例
 *	{{0 | zeroToSpace}} -> ''
 */
(function() {

	// 必要な依存を列挙
	var injectParams = ['$filter'];

	// 引数は依存の内容と一致する
	var newFilter = function($filter) {
		return function(befnumber) {
			var number = befnumber;
			if (angular.isString(number)) {
				number = Number(number);
			}
			if (number === 0) {
				return '';
			}
			return befnumber;
		};
	};

	newFilter.$inject = injectParams;

	angular.module('app').filter('zeroToSpace', newFilter);

}());