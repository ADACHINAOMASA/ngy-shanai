
/**
 * 行操作モジュール
 * @author Leang-Say
 */
var LineUtil = (function() {
	
	// 公開関数
	return {
		moveUp : function(dataList,index) {
			if(!dataList){
				return;
			}
			if(index==0){
				console.log("First element cannot move up.");
			}else{
				var sNum = index;
				var rNum = sNum - 1;
				dataList.splice(rNum, 2, dataList[sNum], dataList[rNum]);
			}			
		}
		,moveDown : function(dataList,index) {
			if(!dataList){
				return;
			}
			if(index==(dataList.length-1)){
				console.log("Last element cannot move down.");
			}else{
				var sNum = index + 1;
				var rNum = sNum - 1;
				dataList.splice(rNum, 2, dataList[sNum], dataList[rNum]);
			}
		}
		,remove : function(dataList,index,ModalService,callbackIfEmptyAfterRemove,postCallback) {
			if(!dataList || dataList.length==0){
				return;
			}			
			if(ModalService){
				ModalService.openConfirm({message: '行No='+(index+1)+'を削除します。よろしいですか。'}).then(function () {
					 dataList.splice(index, 1); 
					 
					 if(callbackIfEmptyAfterRemove){
						 if(!dataList || dataList.length==0){
							var model=callbackIfEmptyAfterRemove();
							if(model){
								dataList.push(model);
							}
						 }
					 }
					 
					if(postCallback){
						postCallback();
					}
					 
	            });	
			}else{
				dataList.splice(index, 1);
				
				 if(callbackIfEmptyAfterRemove){
					 if(!dataList || dataList.length==0){
						 var model=callbackIfEmptyAfterRemove();
						 if(model){
							dataList.push(model);
							
							if(postCallback){
								postCallback();
							}
							
						 }
					 }
				 }
				
				 if(postCallback){
					postCallback();
				 }
				 
			}
		}
		,add : function(dataList,index,newModel) {
			if(!dataList){
				return;
			}
			var nextIndex = index+1;
			var currentObj = {};
			dataList.splice(nextIndex, 0, currentObj);
			
			if(newModel){
				dataList[nextIndex] = newModel;
			}
		}
		
		,addLast : function(dataList,newModel) {
			if(!dataList){
				return;
			}
			var nextIndex = dataList.length;
			var currentObj = {};
			dataList.splice(nextIndex, 0, currentObj);
			if(newModel){
				dataList[nextIndex] = newModel;
			}
		}
		
		,copyToNext : function(dataList,index,callback) {
			if(!dataList){
				return;
			}
			
			var nextIndex = index+1;
			var currentObj = angular.copy(dataList[index]);
			dataList.splice(nextIndex, 0, currentObj);
			
			if(callback){
				callback();
			}
			
		}
		,resetIndex : function(dataList,prop) {
        	if(!dataList){
        		return;
        	}       	
        	for (var i = 0; i < dataList.length; i++) {
        		var data=dataList[i];
        		data[prop]=i;
        	}	
		}
		,lastIndex : function(dataList) {
        	if(!dataList){
        		return -1;
        	}    
        	return dataList.length-1;
		}
		
	}
	
})();