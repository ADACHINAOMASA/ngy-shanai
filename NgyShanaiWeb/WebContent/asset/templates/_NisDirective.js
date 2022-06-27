/**
 * ディレクティブ名
 * ディレクティブ名は他ライブラリと競合しないよう、頭にNisを付けてください
 * 適用方法など
 */
(function () {
	
	var name = 'nisDirective';
    // 必要な依存を列挙
	// 列挙の順番
	// 1.angular系
	// 2.外部プラグイン系
	// 3.NIS開発系
    var injectParams = ['one', 'two'];

    // 引数は依存の内容と一致する
    var newDirective = function (one, two) {
    	// ディレクティブ内容
    	return {

    	};
    };

    newDirective.$inject = injectParams;

    angular.module('app').directive(name, newDirective);

}());