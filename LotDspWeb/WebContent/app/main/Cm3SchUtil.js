var Cm3SchUtil = (function() {
	var userInfoStorage = 'ngStorage-userInfoStorage';

	function _now($scope, $interval) {
		$scope.now = new Date();
		var t = $interval(function() {
			$scope.now = new Date();
		}, 500);
	}

	// 公開関数
	return {
		now : _now,
		message : {
			nodata : function() {
				return "データがありません";
			}
		},
		transition:{
			toMenu:function($state){
				$state.go('/schapp/menu');
			}
		}
		,commonCheck:function($scope,$state,AlertService,menuMei){
			//認証がなければ、メニュー画面へ
			if(!Cm3SchUtil.checkAuth($scope,$state,AlertService)){
				return false;
			}
			//メニュー画面からの遷移でなければ、メニュー画面へ
			if(!Cm3SchUtil.checkSenimoto($scope,$state,AlertService,"スケジュール一覧")){
				return false;
			}
			return true;
		}
		,checkAuth:function($scope,$state,AlertService){
			if($scope.auth.error == true){
				AlertService.addDanger($scope.auth.errorMessage);
				Cm3SchUtil.transition.toMenu($state);
				return false;
			}
			return true;
		}
		,checkSenimoto:function($scope,$state,AlertService,menuMei){
			if($scope.input.senimoto !='menu'){
				AlertService.addDanger("メニュー画面から"+menuMei+"を選択してください。");
				Cm3SchUtil.transition.toMenu($state);
				return false;
			}
			return true;
		}
		,setAuthInfo:function($scope){
			if(!$scope.input || !$scope.auth){
				return;
			}

			$scope.input.ip = $scope.auth.ip;
			$scope.input.userID = $scope.auth.userID;
			$scope.input.termID = $scope.auth.termID;
			$scope.input.setsubiName = $scope.auth.setsubiName;
			$scope.input.useKbn = $scope.auth.useKbn;
			$scope.input.termKbn = $scope.auth.termKbn;
			$scope.input.authInfo = $scope.auth.authInfo;
		}
		,setCommonAuthInfo:function($scope){
			$scope.input.ip = JSON.parse(localStorage.getItem(userInfoStorage)).ip;
			$scope.input.userID = JSON.parse(localStorage.getItem(userInfoStorage)).userID;
			$scope.input.termID = JSON.parse(localStorage.getItem(userInfoStorage)).termID;
			$scope.input.setsubiName = JSON.parse(localStorage.getItem(userInfoStorage)).setsubiName;
			$scope.input.useKbn = JSON.parse(localStorage.getItem(userInfoStorage)).useKbn;
			$scope.input.termKbn = JSON.parse(localStorage.getItem(userInfoStorage)).termKbn;
			$scope.input.authInfo = JSON.parse(localStorage.getItem(userInfoStorage)).authInfo;
		}
		,window : {
			createOptions : function(data,$window) {
				if(!data){
					return '';
				}

				var defaultWidth = data.width || 1024;
        		var defaultHeight = data.height || 768;

				var w = data.left;
        		var h = data.top;

        		if(!w && !h){
            		if($window){
                	    w = ($window.screen.width - defaultWidth )/2;
                        h = ($window.screen.height - defaultHeight )/2;
            		}
        		}

				return 'fullscreen=' + data.fullscreen + ',toolbar='
						+ data.toolbar + ',location=' + data.location
						+ ',menubar=' + data.menubar + ',scrollbars='
						+ data.scrollbars + ',resizable=' + data.resizable
						+ ',top=' + h + ',left=' + w + ',width=' + defaultWidth
						+ ',height=' + defaultHeight;
			}
			,createOptionsMozilla : function(data,$window) {
				if(!data){
					return '';
				}
				//ディスプレイのサイズを取得
				var wid = window.parent.screen.width;
				var height = window.parent.screen.height;
			return 'top=0' + ',left=0'
					+ ',width=' + $window.screen.width
					+ ',height=' + $window.screen.height;
			}
		}

	}

})();