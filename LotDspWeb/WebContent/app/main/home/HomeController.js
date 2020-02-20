(function () {

    var injectParams = ['$rootScope','$scope','i18nService', 'ShohinRenkeiService','$uibModal',
                        'OptionsService', 'AlertService', 'ModalService', 'ModelService', 
                        'OshiraseService', 'SharedObject', 'header', 'CommonService', 'AlertLogService','ComboService'];

    var newController = function ($rootScope,$scope,i18nService, ShohinRenkeiService,$uibModal,
    								OptionsService, AlertService, ModalService, ModelService, 
    								OshiraseService, SharedObject, header, CommonService, AlertLogService,ComboService) {
    	  
        $scope.langs = i18nService.getAllLangs();
        i18nService.setCurrentLang('ja');
    	//ヘッダー情報
		$scope.header = header;
		// ログ保存情報
		$scope.alertLogs = AlertLogService.logs();
    	$scope.alertLogsClear = AlertLogService.clear;
    	
		//初期化
		SharedObject.transition.from='';
        SharedObject.hyojiMode='';
				
    	$scope.oshirases = [];
    	$scope.pageingOshirases = [];
    	$scope.currentPage = 1;
    	$scope.itemsPerPage = 10;    	
    	
    	$scope.hani = [{label:'過去一か月', value:'1'},{label:'過去三か月', value:'3'},{label:'過去半年', value:'6'},{label:'全て', value:'*'}];
    	$scope.henshuMode = {};
    	$scope.input = {
    		haniKbn : '1'
    	};
    			
    	$scope.action = {
        		refreshOptions : function(){
        			OptionsService.refreshAll().then(function(){
        				AlertService.addSuccess('コンボデータを更新しました。');
        			});
        		}
        		,oshirase : {
        			toroku : function(){
            			var modalInstance = $uibModal.open({
        					animation: $scope.animationsEnabled,
        					templateUrl: 'app/main/oshirase/oshiraseHenshu.html',
        					controller: 'OshiraseHenshuController',
        					backdrop: 'static',
        					resolve: {
        				        input: function () {
        				        	return ModelService.newModel('OshiraseInfo');
        				        }
        				      }
        					});
        				modalInstance.result.then(function (oshirase) {
        					OshiraseService.save(oshirase.oshiraseNo || 'new', NisUtil.createRequestData(oshirase)).then(
        						function(data) {
        							$scope.action.oshirase.reload();
        						}
        					);
        				}, function () {

        				});
            		},
            		henshu : function(oshirase){
            			var modalInstance = $uibModal.open({
        					animation: $scope.animationsEnabled,
        					templateUrl: 'app/main/oshirase/oshiraseHenshu.html',
        					controller: 'OshiraseHenshuController',
        					backdrop: 'static',
        					resolve: {
        				        input: function () {
        				        	return angular.copy(oshirase);
        				        }
        				      }
        					});
        				modalInstance.result.then(function (oshirase) {
        					OshiraseService.save(oshirase.oshiraseNo, NisUtil.createRequestData(oshirase)).then(
        						function(data) {
        							$scope.action.oshirase.reload();
        						}
        					);
        				}, function () {

        				});
            		},
            		sakujo : function(oshirase){
            			ModalService.openConfirm({
        					message : '削除します。よろしいですか'
        				}).then(function(){
        					OshiraseService.remove(oshirase.oshiraseNo, NisUtil.createRequestData(oshirase)).then(
        						function(data) {
        							$scope.action.oshirase.reload();
        						}
        					);
        				});
            		},
            		henshin : function(motoOshirase, naiyo) {
            			ModelService.newModel('OshiraseInfo').then(function(input){
            				input.naiyo = naiyo;
            				input.juyoKbn = '0'
            				input.henshinOshiraseNo = motoOshirase.oshiraseNo;
            				OshiraseService.save('new', NisUtil.createRequestData(input)).then(
        						function(data) {
        							$scope.action.oshirase.reload();
        						}
        					);
            			});
            		},
            		reload : function() {
            			OshiraseService.list($scope.input).then(function(data){
            	    		$scope.oshirases = data;
            	    		
            	    		$scope.henshuMode = {};
            	    		$scope.action.oshirase.paging();
            	    		$scope.todayOshirase = _.filter($scope.oshirases, function(oshirase){
            	    			return NisUtil.date.equalsDate(oshirase.koshinYmd, new Date());
            	    		}).length;
            	    	});
            		},
            		scrollNaiyo : function(oshiraseNo) {
            			// 要様子見
            			$('#oshirase-naiyo').animate({scrollTop: $('#' + oshiraseNo)[0].offsetTop - 6});
            		},
            		paging : function() {
            			$scope.pageingOshirases = $scope.oshirases.slice(($scope.currentPage - 1) * $scope.itemsPerPage, ($scope.currentPage) * $scope.itemsPerPage);
            		}
        		},
        		master: {
        			shohinRenkei: function() {
        				ShohinRenkeiService.openShohinRenkei();
        			},
        			
        			kensho: function() {
        				KenshoService.openKensho();
        			},
        		},
        		showKikanSystem:function() {
    				CommonService.juchuRenkeiUrl1().then(function(data){
    					var url = data;
    					console.log("基幹システムの業務メニュー="+url);
    					$scope.subwin=JuchuRenkeiDialog.open(url);
    				});
        		},
        		refreshComboData:function(){
        		  	ComboService.refreshAll().then(function (data) {
                		$scope.action.refreshOptions();
        		  	});
        		}
        	};//action end
    	
    	$scope.action.oshirase.reload();
		
    };//newController End

    newController.$inject = injectParams;

    angular.module('app')
        .controller('HomeController', newController);

}());
