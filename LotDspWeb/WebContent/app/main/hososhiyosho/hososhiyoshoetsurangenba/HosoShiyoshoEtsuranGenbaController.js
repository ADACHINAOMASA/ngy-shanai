/**
 * @author 
 */
(function () {
    // index.htmlのng-appで定義している名前と同じ。
    // <html lang="jp" ng-app="app">
    var moduleName = 'app';

    var controllerName = 'HosoShiyoshoEtsuranGenbaController';

    // 必要な依存を列挙
    var injectParams = [
        '$scope', '$state', '$interval', '$httpParamSerializer', '$window', '$q', '$timeout', 'i18nService', 'uiGridConstants'
        , 'NisGridUtil', 'AlertService', 'ModalService', 'ModelService', 'AlertLogService'
        , 'input','SysConst', 'SharedObject'
        , 'HosoShiyoshoService'
        , '$base64'
    ];

    // 引数は依存の内容と一致する
    var newController = function (
        $scope, $state, $interval, $httpParamSerializer, $window, $q, $timeout, i18nService, uiGridConstants
        , NisGridUtil, AlertService, ModalService, ModelService, AlertLogService
        , input , SysConst, SharedObject
        , HosoShiyoshoService
        , $base64
    ) {
		$scope.langs = i18nService.getAllLangs();
		i18nService.setCurrentLang('ja');
		
		//ログ情報
		$scope.alertLogs = AlertLogService.logs();
    	$scope.alertLogsClear = AlertLogService.clear;
    	
    	// 初期情報
		$scope.input = input || {};
		$scope.searchResult = {};
		$scope.selectedImageInfo = {};      

		// 初期化	---------------------------------------------------------------------------------------- ↓	
		$scope.noLen = 6;
		
		//viewImageのサイズを取得
		PkgSpecUtil.setViewImageSize($scope.input);
		
		ModelService.newModel('HosoShiyoshoInfo').then(function(data){
			$scope.searchResult = data || {};
		});
		
		PkgSpecUtil.now($scope,$interval);
		
		$scope.blinkMessage = '';
		$scope.message = '';
		$scope.imgLoading = false;
	    
	    $scope.totalItems = 0;
	    $scope.currentPage = 1;
	    $scope.setPage = function (pageNo) {
	      $scope.currentPage = pageNo;
	    };
	    $scope.pageChanged = function() {
	    	
	    	$scope.img.resetDynImageSize();
	    		    	
	    	$scope.selectedImageInfo = $scope.action.selectImage($scope.currentPage-1);
	    	
	    	$scope.exist = true;
        	
	    	$scope.input.scalingFactor = 1;

	    	PkgSpecUtil.mouse.init($scope);
        	
	    	$scope.img.resizeDynImage($scope.selectedImageInfo.imgSrc,0,0);
	    	
	    	$scope.thumbnail.select($scope.selectedImageInfo.seq-1);

	    };
	    $scope.itemsPerPage=1;	    	    
	   
	    $scope.exist = false;
	    
	    //初期値（Nextermが取得可能なカラー画像サイズ（2倍：2634x3726））
	    var defImgWidth = 1651;     //生産部キャノン製カラー複合機から取得した画像ｻｲｽﾞ（幅  :1651）
	    var defImgHeight = 2335;    //生産部キャノン製カラー複合機から取得した画像ｻｲｽﾞ（高さ:2335)
	    	    
	    //----------------------------------------------------------------------------------↓

	    $scope.flag = false;
	    $scope.xdif = 0;
	    $scope.ydif = 0;

	    $scope.imgName = 'dynImage';

	    //マウスの初期化
	    PkgSpecUtil.mouse.create($scope);
	    	    	    
	    $scope.dragOn = function($event) {
	        $event.stopPropagation();
	        $event.preventDefault();

	        $scope.flag = true;
	        var el = angular.element($event.target);
	        $scope.imgName = el.attr('id');

	        /*
	        var pos = PkgSpecUtil.element.getOffset(el);
	        var event = PkgSpecUtil.event.getXY($event);
	        $scope.xdif = event.X - pos.left;
	        $scope.ydif = event.Y - pos.top;
	        PkgSpecUtil.mouse.save($scope,event);
	        */
	        
	        var pos = PkgSpecUtil.element.getPosition(el);
	        var event = PkgSpecUtil.event.XY($event);
	        $scope.xdif = event.X - pos.left;
	        $scope.ydif = event.Y - pos.top;
	        PkgSpecUtil.mouse.save($scope,event);
	        
	        //console.log('start....flag=' + $scope.flag + " mouse(" + $scope.mouse.x + "," + $scope.mouse.y + ")" + ' (top=' + pos.top + ',left=' + pos.left + ') ');
	    };

	    $scope.dragOff = function() {
	        $scope.flag = false;
		    $scope.xdif = 0;
		    $scope.ydif = 0;
	        //console.log('stop....flag=' + $scope.flag);
	    };

	    $scope.dragImg = function($event) {
	        $event.preventDefault();

	        if (!$scope.flag) {
	            return;
	        }
	        
	        /*
	        var event = PkgSpecUtil.event.getXY($event);
	        var top  = event.Y - $scope.ydif;
	        var left = event.X - $scope.xdif;
	        $scope.img.setDynImageOffset(top, left);
	        PkgSpecUtil.mouse.save($scope,event);
	        */
	        
	        var event = PkgSpecUtil.event.XY($event);
	        var top  = event.Y - $scope.ydif;
	        var left = event.X - $scope.xdif;
	        $scope.img.setDynImagePosition(top, left);
	        PkgSpecUtil.mouse.save($scope,event);
	        
	        //console.log('   drag...flag=' + $scope.flag);
	    };
   
	    $scope.img = {
	    	loadCompleted: function(){
	    	    $scope.imgLoading = false;
	    	}	
    	    ,dynImageId: function() {
    	        return "#" + $scope.imgName;
    	    }
	        , getDynImageElement:function(){
	        	return angular.element($scope.img.dynImageId());
	        }
    	    , resetDynImageSize: function() {
    	        var el = angular.element($scope.img.dynImageId());
    	        el.attr("src", "");
    	        el.removeAttr("width");
    	        el.removeAttr("height");
    	        PkgSpecUtil.element.setPosition(el, 0, 0);
    	    }
    	    , resizeDynImage1: function(src, top, left, rWidth, rHeight) {
    	        var el = angular.element($scope.img.dynImageId());
    	        el.attr("src", src);
    	        el.attr("width", rWidth);
    	        el.attr("height", rHeight);
    	        PkgSpecUtil.element.setPosition(el,top,left);
    	    }
    	    , resizeDynImage: function(src, top, left) {
    	        var el = angular.element($scope.img.dynImageId());
    	        el.attr("src", src);
    	        PkgSpecUtil.element.setPosition(el,top,left);
    	    }
    	    , setDynImageSize: function(width,height){
    	        var el = angular.element($scope.img.dynImageId());
    	        el.attr("width", width);
    	        el.attr("height", height);
    	    }
    	    , setDynImageOffset: function(top, left) {
    	        var el = angular.element($scope.img.dynImageId());
    	        el.offset({top: top, left: left});
    	    }
    	    , getDynImagePosition: function() {
    	        var el = angular.element($scope.img.dynImageId());
    	        return PkgSpecUtil.element.getPosition(el);    
    	    }
    	    , setDynImagePosition: function(top, left) {
    	        var el = angular.element($scope.img.dynImageId());
    	        PkgSpecUtil.element.setPosition(el,top,left);
    	    }
    	    
        	, calcDynImageTopLeft:function(defaultWidth,defaultHeight,type){
        		
        		var el = $scope.img.getDynImageElement();
        		var pos = PkgSpecUtil.element.getPosition(el);
        		
                var imgTop = pos.top || 0;
                var imgLeft = pos.left || 0;
                var imgWidth = el.attr("width") || defaultWidth;
                var imgHeight = el.attr("height") || defaultHeight;
                                
                var mouseX  = $scope.mouse.x;
                var mouseY  = $scope.mouse.y;
                
                var scale   = $scope.input.scalingFactor;                 
                var befScale = 1;
                var defImgW = 0;
                var defImgH = 0;
                if(type == 'plus'){
                	befScale = scale - 1;
                }
                else if(type == 'minus'){
                	befScale = scale + 1;
                }
                                
                defImgW = Math.round(imgWidth / befScale);
                defImgH = Math.round(imgHeight / befScale);
                var calcTop =  mouseY -  ( Math.round(((mouseY - imgTop)/imgHeight * scale * defImgH)) ) ;
                var calcLeft = mouseX - ( Math.round(((mouseX - imgLeft)/imgWidth * scale * defImgW)) ) ;

                //console.log("     calcTop="+calcTop+",calcLeft="+calcLeft);
                
                return {top:calcTop,left:calcLeft};
            }
    	};
        
	    
	    $scope.thumbnail = {
		    	getId:function(i){
		    		return "#thumbnail"+i;
		    	}	
	    		,mouseenter:function(i){
	    			PkgSpecUtil.img.toHover(angular.element($scope.thumbnail.getId(i)));
	    		}
	    		,mouseleave:function(i){
	    			PkgSpecUtil.img.toDefault(angular.element($scope.thumbnail.getId(i)));
	    			if(i == ($scope.selectedImageInfo.seq-1)){
	    				PkgSpecUtil.img.toSelected(angular.element($scope.thumbnail.getId(i)));
	    			}
	    		}
	    		,select:function(i){
	            	var imgNum = $scope.searchResult.images.length;
	            	for(var j = 0;j<imgNum;j++){
	            		var el = angular.element($scope.thumbnail.getId(j));
	            		if(i == j){
	            			PkgSpecUtil.img.toSelected(el);
	            		}else{
	            			PkgSpecUtil.img.toDefault(el);
	            		}
	            	}
	    		}
		    };
	    
	    //---------------------------------------------------------------------------------↑
	    	    
		// アクション定義	------------------------------------------------------------------------------------ ↓		
	    $scope.searchedWord='';
	    
	    $scope.isEntered=false;
	    
	    $scope.enterKey=false;
	    
	    $scope.action = {
        	pressEnter:function(e){
            	if (e.which == 13) {
            		
            		$scope.enterKey=true;
            		
            		if($scope.input.pkgSpecNo){
            			//console.log('entered...');
            			$scope.isEntered=true;
            			$scope.action.search();
            			
            		}else{
            			$scope.util.init();
            			$scope.message = PkgSpecUtil.message.required();
            			$scope.isEntered=false;
            		}
            		
            	 }else{
            		 $scope.enterKey=false;
            	 }
            }
        	,select:function(e){
        		//console.log('select...');
           	 	var el = angular.element(e.target);
           	 	el.focus();
           	 	el.select();
           	 	
           	 	$scope.enterKey=false;
           	 
        	}
            ,selectText:function(e){
            	//console.log('selectText...');
            	
            	if($scope.isEntered==true){
            		return;
            	}
            	
            	$scope.enterKey=false;

        	 	if($scope.selectedImageInfo){
        	 		
        	 		if($scope.selectedImageInfo.pkgSpecNo == $scope.input.pkgSpecNo){
        	 			PkgSpecUtil.setlectNo();
        	 		}else{

        	 			if($scope.input.pkgSpecNo.length >= $scope.noLen ){
        	 				
        	 				if(!$scope.selectedImageInfo.pkgSpecNo){
        	 					
        	 					if($scope.searchedWord == $scope.input.pkgSpecNo){
        	 						
        	 						PkgSpecUtil.setlectNo();
        	 						
        	 					}
        	 					else{
        	 						        	 						
        	 						$scope.action.search();
        	 						
        	 					}
        	 					
        	 				}
        	 				else{
        	 					
        	 					var no = $scope.input.pkgSpecNo.substring(0,6);
        	 					        	 					
        	 					if($scope.selectedImageInfo.pkgSpecNo == no){
        	 						PkgSpecUtil.setlectNo();
        	 					}
        	 					else{
        	 						$scope.action.search();
        	 					}
        	 				}
        	 				
        	 			}else{
        	 				
        	 				if($scope.input.pkgSpecNo == '' ){
        	 					
        	 					if($scope.message == ''){
        	 						
        	 						if($scope.selectedImageInfo.pkgSpecNo){
        	 							
        	 							$scope.action.search();
        	 						}
        	 						else{
        	 							PkgSpecUtil.setlectNo();
        	 						}
        	 						
        	 					}
        	 					else{
        	 						$scope.action.search();
        	 					}
        	 					
        	 				}
        	 				else{
        	 					
        	 					$scope.action.search();
        	 				}
        	 				
        	 			}
        	
        	 		}
        	 	}
        	 	
            }
        	,search:function(){
        		
        		if(!$scope.validate.check()){
        			$scope.util.init();
        			PkgSpecUtil.setlectNo();
        			$scope.isEntered=false;
        			
        			return;
        		}

				PkgSpecUtil.setViewImageSize($scope.input);
				
				$scope.searchedWord='';
				
            	ModalService.showProcessing(HosoShiyoshoService.searchGenba($scope.input),{message:'検索中・・・'}).then(function(data) {
            		if(data.returnMessage){
            			$scope.util.init();
            			$scope.message = data.returnMessage;
            			
            			$scope.searchedWord = $scope.input.pkgSpecNo;
                      	
            			$timeout(function() {
                      		PkgSpecUtil.setlectNo();
                      		
                      		$scope.isEntered=false;
                      		
        				}, 1100);
                      	
            		}else{
            			$scope.blinkMessage = '';
            			$scope.message = '';  
            			$scope.searchedWord = '';
            			
        				$scope.searchResult = data;     				        				
        				$scope.totalItems = $scope.searchResult.images.length;
        				$scope.currentPage = 1;
        				
        				$scope.imgLoading = true;
                    	
                    	$timeout(function() {
                    		$scope.action.selectThumbnail(0);
        				}, 1000);
                   		
                    	PkgSpecUtil.checker.checkIssueDate(data.issueDate,$scope);
                    	
                    	if($scope.blinkMessage){
                    		
                    		$scope.action.showCommentModal();
                    		
                    	}
                    	else{
                    		
                          	$timeout(function() {
                          		PkgSpecUtil.setlectNo();
                          		$scope.isEntered=false;
            				}, 1100);
                    		
                    	}
                    	
                    	
                    	
                    }
		        });	
            	
            	
        	} 	
            ,selectThumbnail:function(i){
            	if(i < 0 || !$scope.searchResult){
            		return;
            	}
                
            	$scope.img.resetDynImageSize();
            	
            	$scope.selectedImageInfo = $scope.action.selectImage(i);
            		
            	$scope.exist = true;            	
            	
            	$scope.setPage(i+1);
            	
            	$scope.pageChanged();
            	
            	$scope.input.scalingFactor = 1;

            	PkgSpecUtil.mouse.init($scope);
            	   	
            	$scope.img.resizeDynImage($scope.selectedImageInfo.imgSrc,0,0);
            	
            	$scope.thumbnail.select(i);
            	
            }
            ,selectImage : function(i){
            	var imageInfo = angular.copy($scope.searchResult.images[i]);
            	
    	    	var forceLoading = true;
    	    	
    	    	if(forceLoading == true){
    	    		$scope.imgLoading = true;
    	    	}
    	    	else{
        	    	if(imageInfo.loaded == false){
        	    		$scope.imgLoading = true;
        	    		$scope.searchResult.images[i].loaded = true;	    	    		
        	    	}	
    	    	}
    	    	
    	    	return imageInfo;
            }
        	,zoomIn:function(){
        		var scale = $scope.input.scalingFactor;
        		if(scale < 3){
        			var newScale = scale + 1;
        			$scope.input.scalingFactor = newScale;
        			  
                    var vWidth  = parseInt($scope.selectedImageInfo.setWidth * newScale);
                    var vHeight = parseInt($scope.selectedImageInfo.setHeight  * newScale);
                    var vAngle  = $scope.selectedImageInfo.rotatedAngle;
                    var vFileFormat = $scope.selectedImageInfo.fileFormat;
                    
                    var rotatedSize = PkgSpecUtil.img.setImageRotatedSize(vWidth,vHeight,vAngle);
                    
                    var calcPos = $scope.img.calcDynImageTopLeft($scope.selectedImageInfo.setWidth,$scope.selectedImageInfo.setHeight,'plus');

                    $scope.img.resizeDynImage1($scope.selectedImageInfo.imgSrc,calcPos.top,calcPos.left,rotatedSize.rWidth,rotatedSize.rHeight);

                    if (PkgSpecUtil.img.checkImageSize($scope.defImgWidth, $scope.defImgHeight, vWidth, vHeight, vAngle)) {	
                    	var url = PkgSpecUtil.img.createFineImageUrl($scope.selectedImageInfo.imgBaseUrl , vWidth , vHeight);
                    	url = url + '&gtime=' + $scope.searchResult.getTime;
                    	$scope.selectedImageInfo.imgSrc = url;
                    }
        		}
        	}
        	,zoomOut:function(){  		
        		var scale = $scope.input.scalingFactor;
        		
        		if(scale > 1){
        			var newScale = scale - 1;
        			$scope.input.scalingFactor = newScale;
        		
                    var vWidth  = parseInt($scope.selectedImageInfo.setWidth * newScale);
                    var vHeight = parseInt($scope.selectedImageInfo.setHeight  * newScale);
                    var vAngle  = $scope.selectedImageInfo.rotatedAngle;
                    var vFileFormat = $scope.selectedImageInfo.fileFormat;
                    
                    var rotatedSize = PkgSpecUtil.img.setImageRotatedSize(vWidth,vHeight,vAngle);
                                        
                    var calcPos = $scope.img.calcDynImageTopLeft($scope.selectedImageInfo.setWidth,$scope.selectedImageInfo.setHeight,'minus');
         
                    $scope.img.resizeDynImage1($scope.selectedImageInfo.imgSrc,calcPos.top,calcPos.left,rotatedSize.rWidth,rotatedSize.rHeight);

                    if (PkgSpecUtil.img.checkImageSize($scope.defImgWidth, $scope.defImgHeight, vWidth, vHeight, vAngle)) {	
                    	var url = PkgSpecUtil.img.createFineImageUrl($scope.selectedImageInfo.imgBaseUrl , vWidth , vHeight);
                    	url = url + '&gtime=' + $scope.searchResult.getTime;	
                    	$scope.selectedImageInfo.imgSrc = url;
                    }
                }	
        		
        	}
        	,displayEntire:function(){       		
        		if(!$scope.selectedImageInfo){
        			return;
        		}
        		
        		$scope.img.resetDynImageSize();
        		
        		$scope.input.scalingFactor = 1;	

        		PkgSpecUtil.mouse.init($scope);
        		        		
        		$scope.imgLoading = true;
        		        		        		
        		$scope.selectedImageInfo.imgSrc = $scope.selectedImageInfo.defImgSrc1;  
        		
        		//$scope.img.resizeDynImage($scope.selectedImageInfo.imgSrc,0,0,$scope.selectedImageInfo.defWidth,$scope.selectedImageInfo.defHeight);
        		$scope.img.resizeDynImage($scope.selectedImageInfo.imgSrc,0,0);
        	}
        	,adjustWidth:function(){
        		if(!$scope.selectedImageInfo){
        			return;
        		}
        		
        		$scope.img.resetDynImageSize();
        		
        		$scope.input.scalingFactor = 1;
 
        		PkgSpecUtil.mouse.init($scope);
        		
        		$scope.imgLoading = true;
        		        		
        		$scope.selectedImageInfo.imgSrc = $scope.selectedImageInfo.setImgSrc1;  
        		
        		//$scope.img.resizeDynImage($scope.selectedImageInfo.imgSrc,0,0,$scope.selectedImageInfo.setWidth,$scope.selectedImageInfo.setHeight);
        		$scope.img.resizeDynImage($scope.selectedImageInfo.imgSrc,0,0);
        	}
        	,showCommentModal:function(){
        		AlertService.clear();
        		
        		if(!$scope.validate.check()){
        			return;
        		}
            	var param = {data:$scope.searchResult};
            	
        		HosoShiyoshoService.openCommentModal(param).then(function(data){
        			//console.log('openCommentModal....');
        			        			
        			$timeout(function() {
        				PkgSpecUtil.setlectNo();
                  		$scope.isEntered=false;
    				}, 1200);
        			
        		});
        		
        	}
        	,close:function(){
        		if($scope.enterKey==true){
        			//do nothing
        			$scope.enterKey=false;
        			
        		}else{
        			$window.close();
        		}
        		
        	}
            , toPageTop: function () {
                $window.scrollTo(0, 0);
            }
         }; //End action
        
        // Util系のアクション定義	-------------------------------------------------------------------------------- ↓	   

        $scope.util = {
        	init:function(){
    	        
    	        $scope.imgLoading = false;
    	        
        		$scope.searchResult = {};
				
        		$scope.selectedImageInfo = {};
				
        		$scope.totalItems = 0;
				
        		$scope.currentPage = 1;
				
        		$scope.exist = false;
	
            	PkgSpecUtil.mouse.init($scope);
            	
            	$scope.input.scalingFactor = 1;
            	
            	$scope.blinkMessage = '';
            	            	            	
    	        $scope.img.resetDynImageSize();

        	}
            
        }; //End util
        
        // チェック系のアクション定義	---------------------------------------------------------------------------- ↓	
        $scope.validate = {
	         check: function () {
	            
	        	if (!$scope.input.pkgSpecNo) {
	            	$scope.message = PkgSpecUtil.message.required();
	            	return false;
	            }
	            
	            if($scope.input.pkgSpecNo.length < $scope.noLen){
	            	$scope.message = PkgSpecUtil.message.digitError();
	            	return false;
	            }
	            	            
	            return true;
	        }
        }; 
        
        //初期実行
        /*
        if($scope.input.pkgSpecNo){
        	$scope.action.search();
        }
        */
        
        //仕様書No入力の監視
        $scope.$watch("input.pkgSpecNo", function(newVal, oldVal) {
        	
        	//console.log(newVal.length);
        	
            if (newVal !== oldVal) {
            	
              if(newVal.length >= $scope.noLen){	 
            	
            	$timeout(function() {
              		
            		if(newVal.length == 6){
            			
                  		PkgSpecUtil.setlectNo();
                  		
            			
            		}else if(newVal.length == 8){
            			//console.log('watch...'+newVal.length);
            			
            			PkgSpecUtil.setlectNo();
            			            			
            			if($scope.isEntered==true){
            				return;
            			}

                  		$scope.isEntered=true;
            			$scope.action.search(); 			            			
            			
            		}else{
            			
            		}
            		
				}, 1200);
            	
              }
              
            }
            
        });
        
        
                        
    }; //End newController

    newController.$inject = injectParams;
    angular.module(moduleName).controller(controllerName, newController);
    
}());
