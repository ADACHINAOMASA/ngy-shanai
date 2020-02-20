
// --------------------------------------------------------//
// <div nis-direct-print='objectInfo'></div>
// --------------------------------------------------------//

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
	            		var docName="";
	            		var codeBase=objectInfo.context+"/"+objectInfo.codeBase;
	            		var rawData=objectInfo.context+"/"+objectInfo.pdfName;
	            		
	            		if(rawData.indexOf(".svfwdp")==-1){
	            			rawData=rawData+".svfwdp";
	            			docName=objectInfo.pdfName;
	            		}else{
	            			var strArr = objectInfo.pdfName.split('.');
	            			docName=strArr[0];
	            		}
	            		
	                	var objectTag='';
	                	objectTag+='<object ID="'+toBlank(objectInfo.id)+'" CLASSID="'+toBlank(objectInfo.classId)+'" CODEBASE="'+toBlank(codeBase)+'">';
	                	objectTag+='<param name="Raw" value="'+toBlank(rawData)+'">';
	                	objectTag+='<param name="Printer" value="'+toBlank(objectInfo.printer)+'">';
	                	objectTag+='<param name="DocName" value="'+toBlank(docName)+'">';
	                	objectTag+='<param name="EndUrl" value="'+toBlank(objectInfo.endUrl)+'">';
	                	objectTag+='<param name="ErrorUrl" value="'+toBlank(objectInfo.errorUrl)+'">';
	                	objectTag+='<param name="CancelUrl" value="'+toBlank(objectInfo.cancelUrl)+'">';
	                	objectTag+='</object>';

	                	//console.log("Object Tag="+objectTag);
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
	        	nisDirectPrint: "="// '='は双方向バインディング
	        },
	        link: function(scope, element) {
	            scope.$watch("nisDirectPrint", replaceElement(element));
	        }
    	};

    	//------------------------------------------------------------directive function end ↑
    };

    directive.$inject = injectParams;
    try {
    	angular.module('app')
    		.directive('nisDirectPrint', directive);
    } catch(e) {
    	console.error(e);
    }

}());
