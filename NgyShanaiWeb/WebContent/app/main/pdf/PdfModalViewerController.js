/**
 * @author Leang-Say
 */
(function() {

    angular.module('app')
        .controller('PdfModalViewerController', controller);

    controller.$inject = ['$scope', '$state', '$uibModal', '$window', 'i18nService'
                          , '$uibModalInstance','input', 'CommonService'];

    function controller($scope, $state, $uibModal, $window, i18nService
    					, $uibModalInstance,input, CommonService) {

        $scope.langs = i18nService.getAllLangs();
        i18nService.setCurrentLang('ja');

        $scope.input = input;
        //console.log("input=" + angular.toJson($scope.input));

        var PDF_W=800;
        var PDF_H=600;

        if(!$scope.input.contextPath){
            CommonService.getHttpRequestInfo().then(function(data) {
            	$scope.objectInfo = data;
                $scope.objectInfo.pdfName = $scope.input.pdfFileName;
                $scope.objectInfo.width = PDF_W;
                $scope.objectInfo.height = PDF_H;
                $scope.w=PDF_W+50;
                $scope.h=PDF_H+50;
                //console.log("PdfModalViewerController HttpRequestInfo=" + angular.toJson(data));
            });
        }

        $scope.action = {
            cancel: function() {
                $uibModalInstance.dismiss('cancel');
            },
            close: function() {
                $uibModalInstance.dismiss('cancel');
            }
        };

    };

}());