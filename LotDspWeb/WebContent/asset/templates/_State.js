/**
 * @author 
 */
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    // 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider) {

        var base = 'seisakuShijiToroku';
        var controller = 'SeisakuShijiTorokuController';
        var templateUrl = 'app/main/seisakushiji/seisakushijitoroku/SeisakuShijiToroku.html';

        var messageParams = [{
                key: 'common.confirm'
            }
            , {
                key: 'common.edited.confirm'
            }
            , {
                key: 'common.complete'
            }
        ];

        var _loadRules = function (RulesService) {
            RulesService.loadAll();
        };

        $stateProvider
            //新規
            .state(base + '/create', {
                url: '/' + base + '/create'
                , controller: controller
                , templateUrl: templateUrl
                , resolve: {
                    header: function (HeaderService) {
                        return HeaderService.getHeader();
                    }
                    , messages: function (MessageService) {
                        return MessageService.getMessages({
                            messages: messageParams
                        });
                    }
                    , rule: function (RulesService) {
                        return _loadRules(RulesService);
                    }
                    , input: function (ModalService, SeisakuShijiService, $stateParams, $q) {
                    	/*
                    	var delay = $q.defer();
                        
                        ModalService.simpleLoading(SeisakuShijiService.newModel())
                            .then(function (data) {
                                delay.resolve(data);
                            });
                        
                        return delay.promise;
                        */
                    }
                }
                , data: {
                    state: 'create'
                }
                , isLoginRequired: true
            })
            //訂正
            .state(base + '/' + 'teisei', {
                url: '/' + base + '/' + 'teisei' + '/' + ':seizoNo'
                , controller: controller
                , templateUrl: templateUrl
                , resolve: {
                    header: function (HeaderService) {
                        return HeaderService.getHeader();
                    }
                    , messages: function (MessageService) {
                        return MessageService.getMessages({
                            messages: messageParams
                        });
                    }
                    , rule: function (RulesService) {
                        return _loadRules(RulesService);
                    }
                    , input: function (ModalService, SeisakuShijiService, $stateParams, $q) {
                    	/*
                    	var delay = $q.defer();
                        ModalService.simpleLoading(SeisakuShijiService.getWithoutKakoInfo($stateParams.genkaNo, $stateParams.genkaNoHansu)).then(function (data) {
                        	delay.resolve(data);
                        });
                        return delay.promise;
                        */
                    }
                }
                , data: {
                    state: 'teisei'
                }
                , isLoginRequired: true
            });

    }; //End newState

    newState.$inject = injectParams;
    angular.module(moduleName)
        .config(newState);
}());
