(function () {
	
	var name = 'CommentModalController';
	
    var injectParams = [
        '$scope', '$uibModalInstance', '$q',
        'uiGridConstants', '$timeout',  'SysConst', 
        'i18nService', 'ModalService', 'AlertLogService',
        'input'
    ];
    
    var newModalController = function (
		$scope, $uibModalInstance, $q,
		uiGridConstants, $timeout,  SysConst, 
		i18nService, ModalService, AlertLogService,
		input
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		
		$scope.alertLogs = AlertLogService.logs();
    	$scope.alertLogsClear = AlertLogService.clear;
    	
    	$scope.input = input || {};
		
		$scope.action = {
			close : function() {
				//$uibModalInstance.dismiss('cancel');
				//return;
				$uibModalInstance.close('close');
			}
		}
		
	};//End controller

	newModalController.$inject = injectParams;
	angular.module('app').controller(name, newModalController);
	
}());