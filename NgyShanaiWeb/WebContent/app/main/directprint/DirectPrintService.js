(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q', '$uibModal', '$timeout'];

    // 引数は依存の内容と一致する
    var service = function ($http, $q, $uibModal, $timeout) {

    	var baseURI = 'service/directprint';

    	var _print = function(fileName) {
    		var params={fileName:fileName,contextPath:''};
    		
            var modalInstance = $uibModal.open({
                animation: true
                , templateUrl: 'app/main/directprint/DirectPrint.html'
                , controller: 'DirectPrintController'
                , backdrop: 'static'
                , size: 'sm'
                , keyboard:false
                , resolve: {
                        rule: {}
                        , input: params
                }
            });

            //modalInstance.result.then(function (data) {}, function () {});
            $timeout(function() {
            	modalInstance.close();
            }, 3*1000);//3秒

            return modalInstance.result;
    	};

    	return {
			/**
			 * 指定されたEMFファイルを直接印刷する。
			 * 前提条件：EMFファイル名に該当するデータはセッションに存在すること。
			 * 使用例：DirectPrintService.print("sample");　or DirectPrintService.print("sample.svfwdp")
			 * @param {string} fileName - EMFファイル名
			 */
			print: function(fileName) {
				return _print(fileName);
			}
		};
    };

    service.$inject = injectParams;

    angular.module('app')
        .factory('DirectPrintService', service);

}());