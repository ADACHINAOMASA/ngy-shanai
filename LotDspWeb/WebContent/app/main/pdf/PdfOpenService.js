/**
 * @author Leang-Say
 */
(function () {

	var name='PdfOpenService';
	
	// 必要な依存を列挙
    var injectParams = ['$http', '$q', '$uibModal', '$timeout','$window','CommonService'];

    // 引数は依存の内容と一致する
    var service = function ($http, $q, $uibModal, $timeout, $window,CommonService) {

    	var _openModal = function(pdfFileName,contextPath) {
    		var params={pdfFileName:pdfFileName,contextPath:contextPath};
            var modalInstance = $uibModal.open({
                animation: true
                , templateUrl: 'app/main/pdf/PdfModalViewer.html'
                , controller: 'PdfModalViewerController'
                , backdrop: 'static'
                , size: 'sm'
                , keyboard:false
                , resolve: {
                        rule: {}
                        , input: params
                }
            });

            modalInstance.result.then(function (data) {}, function () {});

            return modalInstance.result;
    	};
    	
    	var _openWindow = function(pdfFileName,contextPath) {
    		if(!pdfFileName){
    			$window.alert("PDFファイル名がありません。");
    			return;
    		}
    		if(pdfFileName.indexOf('http://') == 0 || pdfFileName.indexOf('https://') == 0){
    			$window.open(pdfFileName);
    			return;
    		}
    		var url="";
    		if(!contextPath){
    			CommonService.getHttpRequestInfo().then(function(result){
    				//console.log("HttpRequestInfo="+angular.toJson(result));
    				url = result.fullContextPath+"/"+pdfFileName;
        			//console.log("Pdf Open URL="+url);
    				$window.open(url);
				});
    		}else{
    			url = contextPath+"/"+pdfFileName;
    			//console.log("Pdf Open URL="+url);
    			$window.open(url);
    		}
    	};

    	return {
			/**
			 * PDFファイルをモーダルで開く。
			 * 前提条件：PDFファイル名に該当するデータはセッションに存在すること。
			 * 使用例：PdfOpenService.openPdfAsModal("sample.pdf"); or PdfOpenService.openAsModal(""http://localhost:9082/○○Web/sample.pdf"");
			 * @param {string} pdfFileName - PDFファイル名
			 * @param {string} contextPath - コンテキストパス
			 */
			openPdfAsModal: function(pdfFileName) {
				return _openModal(pdfFileName);
			}
			,openAsModal: function(pdfFileName) {
				return _openModal(pdfFileName);
			}
				
			/**
			 * PDFファイルを別Windowで開く。
			 * 前提条件：PDFファイル名に該当するデータはセッションに存在すること。
			 * 使用例：PdfOpenService.openPdf("sample.pdf");　or PdfOpenService.open(""http://localhost:9082/○○Web/sample.pdf"");
			 * @param {string} pdfFileName - PDFファイル名
			 * @param {string} contextPath - コンテキストパス
			 */
    		,openPdf: function(pdfFileName) {
    			return _openWindow(pdfFileName);
			}
    		,open: function(pdfFileName) {
    			return _openWindow(pdfFileName);
			}
		};
    };

    service.$inject = injectParams;

    angular.module('app')
        .factory(name, service);

}());