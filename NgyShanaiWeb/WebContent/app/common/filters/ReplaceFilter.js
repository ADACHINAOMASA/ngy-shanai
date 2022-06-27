/**
 * Replaceフィルター
 * 正規表現Replaceを実行
 * fromRegExp:置き換え元正規表現
 * toRegExp:置き換え先正規表現
 * 使用例：{{'2015/08/20' | replace:".{2}(.{2})(.{2})":"$1/$2"}}
 * 		=> 15/08
 */
(function () {

	// 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var ReplaceFilter = function () {
        return function(text, fromRegExp, toRegExp) {
        	if (!text) {
        		return "";
        	}
        	return text.replace(new RegExp(fromRegExp, "g"), toRegExp);
        };
    };

    ReplaceFilter.$inject = injectParams;

    angular.module('app')
        .filter('replace', ReplaceFilter);

}());