/**
 * _フィルター
 */
(function () {

	// 必要な依存を列挙
	// 列挙の順番
	// 1.angular系
	// 2.外部プラグイン系
	// 3.NIS開発系
    var injectParams = ['one', 'two'];

    // 引数は依存の内容と一致する
    var newFilter = function (one, two) {
    	// フォーマット系フィルター
//    	return function(text) {
//    		// 文字列
//    		return text;
//    	}
    	// 絞り込み系フィルター
    };

    newFilter.$inject = injectParams;

    angular.module('app')
        .filter('_', newFilter);

}());