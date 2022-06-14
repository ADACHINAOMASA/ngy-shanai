'use strict';
(function () {

    var injectParams = ['$scope', '$state', 'ModalService', 'AlertService']; // <- SrcScriptによる生成

    var newController = function ($scope, $state,
                                    ModalService
                                    , AlertService
                                    ) {

        $scope.oshirases = [{
            koshinHi: new Date()
            , oshirase : "テスト環境です。"
        }];

    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('HomeController', newController);

}());