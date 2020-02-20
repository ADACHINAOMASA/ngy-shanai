
(function () {

	// TODO:オプション等をどうするか考える
	//      →単純に行くとスコープ内に定義して渡すか？

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisFloatThead = function () {
    	// ディレクティブ内容
    	return {
			restrict: 'A',
			link: function(scope, elm, attrs) {
				elm.floatThead({
					scrollContainer: function($table){
						return $table.closest('.table-responsive');
					}
					,zIndex: 999
				});
				scope.$on('tableThead-reflow', function(){
					elm.floatThead('reflow');
				});
			}
    	};
    };

    nisFloatThead.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisFloatThead', nisFloatThead);
    } catch(e) {
    	console.error(e);
    }

}());