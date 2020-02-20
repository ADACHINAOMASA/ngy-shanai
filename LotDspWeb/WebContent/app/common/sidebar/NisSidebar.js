// <nis-header></nis-header>
(function () {

    // 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var nisSidebar = function () {
    	// ディレクティブ内容
    	return {
    		restrict: 'E',
			templateUrl: 'app/common/sidebar/sidebar.html',
			controller: 'SidebarController',
			replace: true
    	};
    };

    nisSidebar.$inject = injectParams;

    angular.module('app')
        .directive('nisSidebar', nisSidebar);

}());