
var PkgSpecUtil = (function() {

	function _getWidthHeightById(id){
		if(!id){
			return {width:0,height:0};
		}
		var el = angular.element(id);
		var w = el.css('width').replace('px','');
		var h = el.css('height').replace('px','');
		return {width:w,height:h};
	}
	
	function _setViewSize(id,viewObj){
		if(!id || !viewObj){
			return;
		}
		var viewImageSize = _getWidthHeightById(id);
		viewObj.viewWidth  = viewImageSize.width;
		viewObj.viewHeight = viewImageSize.height;
	}
	
	function _setViewImageSize(viewObj){
		_setViewSize('#viewImage',viewObj);
	}
	
	function _initMouse($scope){
        $scope.mouse.x = 0;
        $scope.mouse.y = 0;
	}
	
	function _saveMouseXY($scope,$event){
	    $scope.mouse.x = $event.X;
	    $scope.mouse.y = $event.Y;
	}
	
	function _now($scope,$interval){
		$scope.now = new Date();
	    var t = $interval(function() {
	        $scope.now = new Date();
	    }, 500);
	}
	
	function _checkIssueDate(date,$scope){
 	   if(!date){
		   return;
	   }
       //発行日から30日以内は確認メッセージを表示
       var issueDateValue = date;
       var issueDate = new Date(issueDateValue);
       var nowDate = new Date();
       var difDay = (nowDate.getTime() - issueDate.getTime())/(24*60*60*1000);
       
       if(difDay <= 30) {
    	   $scope.blinkMessage = "要確認!!!" + issueDateValue + "に更新されました。";
       } else {
    	   $scope.blinkMessage = '';
       }
	}
	
	function _selection(id){
		var el=angular.element(id);
		el.select();
	}
		
	// 公開関数
	return {
		now : _now,
		getSizeById : _getWidthHeightById,
		setViewSize : _setViewSize,
		setViewImageSize : _setViewImageSize,
		select : _selection,
		
		setlectNo : function(){
			var el = angular.element('#pkgSpecNo');
			el.focus();
			el.select();
		}
	
		,event:{
			pageXY:function($event){
				return {Y:$event.pageY,X:$event.pageX};
			},
			clientXY:function($event){
				return {Y:$event.clientY,X:$event.clientX};
			},
			screenXY:function($event){
				return {Y:$event.screenY,X:$event.screenX};
			},
			getXY:function($event){
				return {Y:$event.pageY,X:$event.pageX};
			}
			,XY:function($event){
			    var el = angular.element('#viewImage');
			    var pos = PkgSpecUtil.element.getOffset(el);
			    			    
				var dx = pos.left + 2 || 0;//+border=2
				var dy = pos.top  + 2 || 0;//+border=2
				
				var nx = (-dx*2) + $event.pageX;
				var ny = (-dy*2) + $event.pageY;
				
				return {Y : ny , X : nx };
			}
		},
		mouse:{
			create:function($scope){
				$scope.mouse = {x : 0 , y : 0};
			},
			init:_initMouse,
			save:_saveMouseXY
		},
		checker:{
			checkIssueDate:_checkIssueDate,
		},
		
		img:{
            createFineImageUrl : function(baseUrl , vWidth , vHeight){
            	return baseUrl + '&w=' + vWidth + '&h=' + vHeight;
            }
            , createAraiImageUrl : function(baseUrl , vWidth , vHeight){
            	return baseUrl + '&w=' + vWidth + '&h=' + vHeight +'&q=1';
            }
            , createPrintImageUrl : function(baseUrl , hist , mod , seq){
            	return baseUrl + '&hist=' + hist + '&mod=' + mod + '&seq=' + seq + '&angle=0'; 
            }
            
        	, checkImageSize:function(vDefImgWidth, vDefImgHeight, vWidth, vHeight, vAngle){
        	    var cmpWidth = 0;
        	    var cmpHeight = 0;
        	    cmpWidth = vWidth;
        	    cmpHeight = vHeight;
        	    if (vDefImgWidth > cmpWidth && vDefImgHeight > cmpHeight ) {     
        	        return true;
        	    } else {
        	        return false;
        	    }
        	}
    	    , setImageRotatedSize: function(vWidth, vHeight, vAngle) {
    	        var rotatedSize = {
    	            rWidth: 0
    	            , rHeight: 0
    	        };
    	        switch (eval(vAngle)) {
    	            case 0:
    	            case 180:
    	                rotatedSize.rWidth = vWidth;
    	                rotatedSize.rHeight = vHeight;
    	                break;
    	            case 90:
    	            case 270:
    	                rotatedSize.rWidth = vHeight;
    	                rotatedSize.rHeight = vWidth;
    	                break;
    	        }
    	        return rotatedSize;
    	    }
    	    , getRotationStyle: function(vAngle) {
    	        var imgStyle = "";
    	        switch (eval(vAngle)) {
    	            case 0:
    	                imgStyle = "r0";
    	                break;
    	            case 90:
    	                imgStyle = "r90";
    	                break;
    	            case 180:
    	                imgStyle = "r180";
    	                break;
    	            case 270:
    	                imgStyle = "r270";
    	                break;
    	        }
    	        return imgStyle;
    	    }
    	    , getRotationValue: function(vAngle) {
    	        var rotation = "";
    	        switch (eval(vAngle)) {
    	            case 0:
    	                rotation = "0";
    	                break;
    	            case 90:
    	                rotation = "1";
    	                break;
    	            case 180:
    	                rotation = "2";
    	                break;
    	            case 270:
    	                rotation = "3";
    	                break;
    	        }
    	        return rotation;
    	    }
    	    
	    	,toSelected:function(img){
	    		if(img){
	    			img.css('border','4px solid red');
	    		}
	    	}
	    	,toDefault:function(img){
	    		if(img){
	    			img.css('border','2px solid gray');
	    		}
	    	}
	    	,toHover:function(img){
	    		if(img){
	    			img.css('border','2px solid blue');
	    		}
	    	}
	    	
		},
		
		element:{
    	     setPosition: function(el, top, left) {
    	        if (!el) {
    	            return;
    	        }
    	        el.css('top', top + 'px');
    	        el.css('left', left + 'px');
    	    }
    	    , getPosition: function(el) {
    	        if (!el) {
    	            return pos;
    	        }
    	        var pos = {top: 0, left: 0};
    	        pos.top = el.css('top').replace('px', '') || 0;
    	        pos.left = el.css('left').replace('px', '') || 0;
    	        return pos;
    	    }
    	    , setOffset: function(el, top, left) {
    	        if (!el) {
    	            return;
    	        }
    	        el.offset({top: top, left: left});
    	    }
    	    , getOffset: function(el) {
    	        if (!el) {
    	            return;
    	        }
    	        return el.offset();
    	    }
		}
		
		,message:{
			required:function(){
				return "包装仕様書Noを指定して下さい。";
			}
			,digitError:function(){
				return "包装仕様書Noの桁数が足りません。";
			}
			,nodata:function(){
				return "データがありません";
			}
			,isNoData:function(msg){
				return 'データがありません'==msg;
			}
		}
		,printWindowOpenOptions:function($window){
            var w = 600;
            var h = 400;
         
            var winl = ($window.screen.width - w )/2;
            var wint = ($window.screen.height - h )/2;
            var settings ='height='+h+',';
            settings +='width='+w+',';
            settings +='top='+wint+',';
            settings +='left='+winl+',';
            settings +='scrollbars=yes,';
            settings +='resizable=yes,';
            settings +='toolbar=yes,';
            settings +='menubar=yes,';
            settings +='status=yes,';
            settings +='directories=no,';
            //settings +='location=no,';
            settings +='screenX=1,';
            settings +='screenY=1';
            return settings;
		}
	}
	
})();