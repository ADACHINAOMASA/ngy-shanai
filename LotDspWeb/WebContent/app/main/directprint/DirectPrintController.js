(function () {

        angular.module('app')
                .controller('DirectPrintController', controller);

        controller.$inject = ['$scope', '$state', '$uibModal', '$window', 'i18nService'
                              , 'input', 'AlertService', '$uibModalInstance','CommonService'
                             ];

        function controller($scope, $state, $uibModal, $window, i18nService
                , input, AlertService, $uibModalInstance,CommonService) {

                $scope.langs = i18nService.getAllLangs();
                i18nService.setCurrentLang('ja');

                $scope.input = input;
            	//console.log("input="+angular.toJson($scope.input));

            	CommonService.getSvfWebClientObjectInfo().then(function(data){
                	$scope.objectInfo=data;
                	$scope.objectInfo.pdfName=$scope.input.fileName;
                	//console.log("Server ObjectInfo="+angular.toJson($scope.objectInfo));
                });

                $scope.action = {
                    cancel: function () {
                    	$uibModalInstance.dismiss('cancel');
                    }
                };

        };

}());