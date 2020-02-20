/**
 * Replaceフィルター 正規表現Replaceを実行 fromRegExp:置き換え元正規表現 toRegExp:置き換え先正規表現
 * 使用例：{{'2015/08/20' | replace:".{2}(.{2})(.{2})":"$1/$2"}} => 15/08
 */
(function() {

	// 必要な依存を列挙
	var injectParams = [];

	// 引数は依存の内容と一致する
	var DateElapsedFilter = function() {
		return function(date) {
			if (!date) {
				return "";
			}
			if (!angular.isDate(date)) {
				date = new Date(date);
			}
			var current = new Date();
			var dateMargin = current.getTime() - date.getTime();

			// ミリ秒除去
			dateMargin = Math.floor(dateMargin / 1000);

			// 分未満
			if (60 > dateMargin) {
				return dateMargin + '秒前';
			}
			// 時間未満
			if (60 * 60 > dateMargin) {
				return Math.floor(dateMargin / 60) + '分前';
			}
			// 5時間以内
			if (60 * 60 * 5 > dateMargin) {
				return Math.floor(dateMargin / 60 / 60) + '時間前';
			}
			// その日以内
			if (NisUtil.date.equals(date, current)) {
				return (date.getHours() < 13 ? 'am ': 'pm ') + NisUtil.date.formatDate(date, 'hh:mm');
			}
			// 10日以内
			if (60 * 60 * 24 * 10 > dateMargin) {
				var dayMargin = Math.floor(current.getTime() / 1000 / 60 / 60 / 24)
									- Math.floor(date.getTime() / 1000 / 60 / 60 / 24);
				if (dayMargin == 1) {
					return "昨日";
				}
				if (dayMargin == 2) {
					return "一昨日";
				}
				return dayMargin + '日前'
			}
			// 以降
			return NisUtil.date.formatDate(date, 'yyyy-MM-dd');
		};
	};

	DateElapsedFilter.$inject = injectParams;

	angular.module('app').filter('elapse', DateElapsedFilter);

}());