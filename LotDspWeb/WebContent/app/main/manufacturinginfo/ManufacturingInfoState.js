
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    // 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider) {

        var base = 'ManufacturingInfo';
        var controller = 'ManufacturingInfoController';
        var templateUrl = 'app/main/manufacturinginfo/ManufacturingInfo.html';

        $stateProvider
            .state(base, {
                url: '/lotdsp/faces/' + base + '.jsp'
                , controller: controller
                , templateUrl: templateUrl
            })
            ;
    }; //End newState

    newState.$inject = injectParams;
    angular.module(moduleName)
        .config(newState);
}());
