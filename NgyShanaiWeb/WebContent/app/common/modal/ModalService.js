(function () {

	// 必要な依存を列挙
    var injectParams = ['$http', '$q', '$uibModal', '$timeout'];

    // 引数は依存の内容と一致する
    var ModalService = function ($http, $q, $uibModal, $timeout) {
    	var _openConfirm = function(param){
    		param = param || {};
			var processName = param.processName || '';
			var message = param;
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/common/modal/confirm.html',
				controller: 'ConfirmModalController',
				backdrop: 'static',
				size:param.size || 'md',
				resolve: {
					'message': function() {
						return message;
					},
					'processName': function() {
						return processName;
					}
				}
			});
			return modalInstance.result;
    	}, _openInfo = function(param){
    		param = param || {};
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/common/modal/info.html',
				controller: 'InfoModalController',
				backdrop: param.backdrop || true,
				size:param.size || 'md',
				resolve: {
					'param': function() {
						return param;
					}
				}
			});
			return modalInstance.result;
    	}, _openAlert = function(param){
    		param = param || {};
			var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/common/modal/alert.html',
				controller: 'AlertModalController',
				backdrop: param.backdrop || 'static',
				size:param.size || 'md',
				resolve: {
					'param': function() {
						return param;
					}
				}
			});
			return modalInstance.result;
    	}, _loading = function(promise, notOpen) {
    		if (notOpen) {
    			return promise;
    		}
    		var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/common/modal/loading.html',
				backdrop: 'static',
				keyboard:false
			});
    		var deferred = $q.defer();
    		// 処理の終了が早すぎるとmodalが表示される前にcloseが実行される
    		// 見え方も含めて制御が必須
    		modalInstance.rendered.then(function(){
    			promise.then(function(data){
    				// TODO:Argumentsにする？
					modalInstance.close(data);
        		},function(err){
					modalInstance.dismiss(err);
        		});
    		});
    		modalInstance.result.then(function(data){
    			return deferred.resolve(data);
    		},function(err){
    			return deferred.reject(err);
    		});

    		return deferred.promise;
    	},
    	
    	_simpleLoading = function(promise, notOpen) {
    		if (notOpen) {
    			return promise;
    		}
    		var modalInstance = $uibModal.open({
				animation: true,
				templateUrl: 'app/common/modal/simpleLoading.html',
				backdrop: 'static',
				keyboard : false,
				size: 'sm', 
			});
    		var deferred = $q.defer();
    		
    		modalInstance.rendered.then(function(){
    			promise.then(function(data){
					modalInstance.close(data);
        		},function(err){
					modalInstance.dismiss(err);
        		});
    		});
    		modalInstance.result.then(function(data){
    			return deferred.resolve(data);
    		},function(err){
    			return deferred.reject(err);
    		});
    		
    		return deferred.promise;
    	}
    	;
    	
    	
    	/**
    	 * 処理中...を表示する。
    	 */
        function _showProcessing(promise,param,preCallback,postCallback) {
    		param = param || {};
			var processName = param.processName || '';
			var message = param.message || 'Loading...';
			
    		var modalInstance = $uibModal.open({
    			animation: false,
    			templateUrl: 'app/common/modal/processing.html',
    			controller: 'ProcessingModalController',
    			
    			backdrop: 'static',
    			keyboard : false,
    			animation: false,
    			size: 'sm',
				resolve: {
					'message': function() {
						return message;
					},
					'processName': function() {
						return processName;
					}
				}
    		});
    		
    		var deferred = $q.defer();
    		modalInstance.rendered.then(function(){
    			if(preCallback){
    				//console.log("preCallback.....");
    				preCallback();
    			}
    			promise.then(function(data){
    				modalInstance.close(data);
        		},function(err){
    				modalInstance.dismiss(err);
        		});
    		});
    		modalInstance.result.then(function(data){
    			return deferred.resolve(data);
    		},function(err){
    			return deferred.reject(err);
    		});
    		modalInstance.closed.then(function(){
    			 if(postCallback){
    				 //console.log("postCallback.....");
    				 postCallback();
    				 
    			 }
    		});
    		return deferred.promise;
    	}
    	    	
    	return {
			wrapConfirm: function(param){
				return function(){
					return _openConfirm(param);
				};
			},
			openConfirm: function(param) {
				return _openConfirm(param);
			},
			wrapInfo: function(param){
				return function(){
					return _openInfo(param);
				};
			},
			openInfo: function(param) {
				return _openInfo(param);
			},
			wrapAlert: function(param){
				return function(){
					return _openAlert(param);
				};
			},
			openAlert: function(param) {
				return _openAlert(param);
			},
			loading: function(promise, notOpen) {
				return _loading(promise, notOpen);
			},
			
			// 2017/02/02 追加　サイ
			//-------------------------------------------------------↓
			simpleLoading: function(promise, notOpen) {
				return _simpleLoading(promise, notOpen);
			},
			showProcessing: _showProcessing
			//-------------------------------------------------------↑
			
		};
    };

    ModalService.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .factory('ModalService', ModalService);

}());