
	
/*
 ,'ngFileUpload'
 ,'angularResizable'
*/
angular.module('app'
		   , ['ngMessages', 'ngResource', 'ngAnimate', 'ngCookies', 'ngStorage' , 'angularFileUpload','ngSanitize'
		      
		   , 'ui.router'
		   , 'ui.router.stateHelper'
		   
		   , 'ui.bootstrap'
		   
		   , 'ui.validate'
		   , 'toaster'
		   , 'ui.grid', 'ui.grid.pagination', 'ui.grid.moveColumns', 'ui.grid.resizeColumns'
		   , 'ui.grid.selection','ui.grid.pinning', 'ui.grid.exporter', 'ui.grid.edit', 'ui.grid.cellNav'
		   , 'ui.grid.autoResize', 'ui.grid.grouping', 'ui.grid.saveState'
		   
		 
		   , 'base64'
		   
		   /*'base64'
		   , 'angularScreenfull'		  
		   , 'FBAngular'
		   */
		   
		   ])
		   
	 /*  
	.directive('spinnerLoad', [function spinnerLoad() {
	    return {
	        restrict: 'A',
	        link: function spinnerLoadLink(scope, elem, attrs) {
	            scope.$watch('ngSrc', function watchNgSrc() {
	                elem.hide();
	                elem.after('<i class="fa fa-spinner fa-lg fa-spin"></i>');  // add spinner
	            });
	            elem.on('load', function onLoad() {
	                elem.show();
	                elem.next('i.fa-spinner').remove(); // remove spinner
	            });
	        }
	    };
	}])
	*/
	
	/*	   
	.directive('myLoad', function($parse, $rootScope) {
	  return {
	    restrict: 'A',
	    compile: function($element, attr) {
	      var fn = $parse(attr['myLoad']);
	      return function ngEventHandler(scope, element) {
	        element.on('load', function(event) {
	          var callback = function() {
	            fn(scope, {$event: event});
	          };
	          if ($rootScope.$$phase) {
	            scope.$evalAsync(callback);
	          } else {
	            scope.$apply(callback);
	          }
	        });
	      };
	    }
	  };
	})
	*/
	
	/*	   
	.directive('selectOnClick', function () {
	    return {
	        restrict: 'A',
	        link: function (scope, element) {
	            var focusedElement;
	            element.on('click', function () {
	                if (focusedElement != this) {
	                    this.select();
	                    focusedElement = this;
	                }
	            });
	            element.on('blur', function () {
	                focusedElement = null;
	            });
	        }
	    };
	})
	*/
		   

    /*
	.directive('popoverHtmlUnsafePopup', function () {
	    return {
	        restrict: 'EA',
	        replace: true,
	        scope: { title: '@', content: '@', placement: '@', animation: '&', isOpen: '&' },
	        template: '<div class="popover {{placement}}" ng-class="{ in: isOpen(), fade: animation() }"><div class="arrow"></div><div class="popover-inner"><h3 class="popover-title" bind-html-unsafe="title" ng-show="title"></h3><div class="popover-content" bind-html-unsafe="content"></div></div></div>'
	    };
	})
	.directive('popoverHtmlUnsafe', [ '$tooltip', function ( $tooltip ) {
	    return $tooltip('popoverHtmlUnsafe', 'popover', 'click' );
	}])
	*/

	/*	   
    .filter('formatString', function() {
      return function(input, defaultValue) {
    	 // console.log('input='+input+',defaultValue='+defaultValue);
    	if(angular.isUndefined(input) || input === null){
	    	return defaultValue;
	    }
	    return input;
      };
    })
    */
		   
	/*	   
	.filter('formatNumber', function() {
      return function(input, decimalPlaces) {
       if(isNaN(input)){
            return input;
       }else {
          return input.toFixed(decimalPlaces);
       }
      };
    })
    */

//----------------------------------------------------------↓



//------------------------------------------------------------------検証↑

		   
    .filter('formatPercentage', function() {
      return function(input) {
    	  if(angular.isUndefined(input) || input === null){
  	    	return input;
  	    }
        return input + '%';
      };
    })
		   
	.config(['$stateProvider', 'stateHelperProvider', '$urlRouterProvider', 'SysConst'
	         , function($stateProvider, stateHelperProvider, $urlRouterProvider, SysConst){
		// TODO: resolveで色々やる形になると、純粋さを損なう
		//       何か考えた方が良いかもしれない
		$urlRouterProvider.otherwise('/');
	}])
	.config(['$httpProvider', function($httpProvider){
		$httpProvider.interceptors.push('HttpInterceptor');
		//GETメソッド用のヘッダーを初期化
		if (!$httpProvider.defaults.headers.get) {
		    $httpProvider.defaults.headers.get = {};
		}
		//IEのAjaxリクエストキャッシュを無効にする
		$httpProvider.defaults.headers.get['If-Modified-Since'] = '0'
	}])
	.config(['uibDatepickerConfig', function(uibDatepickerConfig) {
		angular.extend(uibDatepickerConfig, {
			showWeeks: false,
			formatDayTitle: 'yyyy年 MMMM',
			formatMonthTitle: 'yyyy年'
		});
	}])
	.config(['uibDatepickerPopupConfig', function(uibDatepickerPopupConfig) {
		angular.extend(uibDatepickerPopupConfig, {
			datepickerPopup: 'yyyy/MM/dd',
			shortcutPropagation: true,
			currentText: '今日',
			clearText: 'クリア',
			closeText: '閉じる'
		});
	}])
	.config(['$cookiesProvider', function($cookiesProvider) {
		var expire = new Date();
		expire.setMonth(expire.getMonth() + 12);
		$cookiesProvider.defaults.expires = expire;
	}])
	.run(['$rootScope', '$state', '$urlRouter', '$window', '$timeout', '$location', 'UserService','LoginService', 'ShareContainerService', 'AlertService', 'ModalService' ,'AuthorityService','CryptoService',
	      function ($rootScope, $state, $urlRouter, $window, $timeout, $location, UserService, LoginService, ShareContainerService, AlertService, ModalService, AuthorityService,CryptoService) {

		var createHandleSessionListener = function(){
			return $rootScope.$on('$stateChangeStart', function(e, toState, toParams, fromState, fromParams){
				ShareContainerService.getStateScrollMemory().put(fromState.templateUrl, $window.scrollY);
				deRegister();
		    	deRegister = createStateChangeCheckListener();
		    	e.preventDefault();

		    	//SessionCheckRequestHandler.javaも追加が必要
		    	if($location.path().indexOf("schapp")>=0 || $location.path().indexOf("icasapp")>=0){		
		    		$state.go(toState,toParams);
		    		return;
		    	}
		    	
		    			    	
		    	LoginService.reloadLoggedIn().then(function(){
		    		if (LoginService.isLoggedIn()) {
		    			return UserService.loadUserProfile();
		    		}
		    	}).then(function(){
		    		
		    		//--------------------------------------------------↓
		    		// 許可URLの場合
		    		if(AuthUtil.isAuthorizedUrl($location.path())){
			    		var params={};
			    		params.userId=toParams.userId;
			    		params.password=toParams.password;
			    		
			    		if(!NisUtil.hasValue(params.userId) || !NisUtil.hasValue(params.password)){
			    			$state.go(toState,toParams);
			    		}else{
			    			CryptoService.decrypt(params).then(function(data){
			    				
			    				if(!NisUtil.hasValue(data.userId) || !NisUtil.hasValue(data.password)){
			    					$state.go('login');
			    					return;
			    				}
			    				LoginService.login(data.userId, data.password).then(function(){
									if(LoginService.isLoggedIn()){

										$state.go(toState,toParams);
									}
								});
			    			});
			    		}
		    		}
		    		//-----------------------------------------------↑
		    		
		    		else{
		    			$state.go(toState, toParams);
		    		}
		    		
		    	}, function(){
		    		$state.go(toState, toParams);
		    	});
		    });
		},
		createStateChangeCheckListener = function(){
			return $rootScope.$on('$stateChangeStart', function(e, toState, toParams, fromState, fromParams){
				deRegister();
		    	deRegister = createHandleSessionListener();
				if (toState.isLoginRequired) {
					if (!LoginService.isLoggedIn()) {
						AlertService.clear();
						AlertService.addDanger('セッションが切断されています。ログイン画面に移動します。');
						if (fromState.name === 'login') {
							e.preventDefault();
						}else {
							LoginService.logout();
						}
						return;
					}
				}
				if (toState.permission && !AuthorityService.has(toState.permission)) {
					AlertService.addDanger('この画面を表示する権限がありません。');
					if (fromState.name) {
						e.preventDefault();
					}
					else {
						$state.go('home');
					}
					return;
				}
				$rootScope.$emit('$stateChangeStartSuccess', toState, toParams, fromState, fromParams);
		    });
		},
		deRegister = createHandleSessionListener();

	    $rootScope.$on('$stateChangeSuccess', function(e, toState, toParams, fromState, fromParams){
	    	if (toState.keepScroll) {
	    		// TODO:挙動が自然になる他の方法はないか
	    		var y = ShareContainerService.getStateScrollMemory().get(toState.templateUrl);
	    		if (y) {
	    			$timeout(function(){$window.scrollTo(0, y);}, 500);
	    		}
	    	}
	    	else {
	    		$window.scrollTo(0,0);
	    	}
	    });
		$rootScope.$on('event:loginRequired', function(){
			$state.go('login');
		});
		
	}])
	
	.run(['$anchorScroll', function($anchorScroll) {
		$anchorScroll.yOffset = 100;
	}])

;

