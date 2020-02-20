// --------------------------------------------------------//
// <div nis-pdf-viewer='objectInfo'></div>
// --------------------------------------------------------//
/**
 * @author Leang-Say
 */
(function () {
	// 必要な依存を列挙
    var injectParams = [];

    // 引数は依存の内容と一致する
    var directive = function () {

	    var replaceElement = function(element) {
	        return function(objectInfo) {
	            element.empty();

	            var objectElem = {};

	            if (objectInfo) {
	            		var pdfFileName=objectInfo.pdfName;
	            		var pdf=objectInfo.fullContextPath+"/"+pdfFileName;
	            		
	            		if(pdfFileName.indexOf('http://') == 0 || pdfFileName.indexOf('https://') == 0){
	            			pdf=pdfFileName;
	            		}
	            		
	            		var objectTag='';
	                	objectTag+='<object id="Pdf1" CLASSID="clsid:CA8A9780-280D-11CF-A24D-444553540000" WIDTH="'+objectInfo.width+'" HEIGHT="'+objectInfo.height+'" >';
	                	objectTag+='<param name="src" value="'+toBlank(pdf)+'">';
	                	objectTag+='</object>';

	                	//console.log("nisPdfModalViewer Object Tag="+objectTag);
	                	objectElem = angular.element(objectTag);
	            }
	            else{
	            	//console.log("nisDirectPrint:"+objectInfo);
	            }

	            element.append(objectElem);
	        };
	    };

	    function toBlank(val){
	    	if(angular.isUndefined(val) || val === null){
	    		return "";
	    	}
	    	return val;
	    };

    	// ディレクティブ内容
    	return {
	        restrict: "EA",
	        scope: {// scopeにオブジェクトを指定すると、分離スコープの作成
	        	nisPdfModalViewer: "="// '='は双方向バインディング
	        },
	        link: function(scope, element) {
	            scope.$watch("nisPdfModalViewer", replaceElement(element));
	        }
    	};

    	//------------------------------------------------------------directive function end ↑
    };

    directive.$inject = injectParams;
    try {
    	angular.module('app')
    		.directive('nisPdfModalViewer', directive);
    } catch(e) {
    	console.error(e);
    }

}());
