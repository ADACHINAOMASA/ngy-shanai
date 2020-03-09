/**
 * ChikuCdフィルター
 * 1文字以下の地区コードを2文字（頭0埋め）に変える、それ以外の場合はそのまま返却する
 *
 * {{value | chikuCdFl}}
 *
 * 使用例
 *	{{'5' | chikuCdFl}} -> '05'''
 */
(function() {

	// 必要な依存を列挙
	var injectParams = ['$filter'];

	// 引数は依存の内容と一致する
	var newFilter = function($filter) {
		return function(bestring) {
			var afstring = bestring.toString().trim();
			if (afstring.length <= 1) {
				afstring = '00' + afstring;
				afstring = afstring.substr(-2)
			}
			return afstring;
		};
	};

	newFilter.$inject = injectParams;

	angular.module('app').filter('chikuCdFl', newFilter);

}());