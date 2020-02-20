(function () {

    var injectParams = ['$scope', '$state','$cookies','$uibModal',
                        'SysConst','LoginService','AlertService'];

    var newController = function ($scope, $state, $cookies,$uibModal, 
    					SysConst,LoginService,AlertService) {

    	// 初期設定
    	$scope.companyName=SysConst.company.name;
    	$scope.abbrCompanyName=SysConst.company.abbrName;
    	$scope.systemName=SysConst.system.name;
    	    	
    	// 内部状態等を一括でリセットする仕組みを用意するべきか
    	$scope.userIdMemory = $cookies.get('userIdMemory') || '0';
    	$scope.userId = $cookies.get('savedUserId') || '';

    	$scope.action = {
			login : function() {
				LoginService.login($scope.userId, $scope.password)
					.then(function(){
						if ($scope.userIdMemory === '1') {
							$cookies.put('savedUserId', $scope.userId);
						}
						$state.go($scope.moveTo || 'home');
					});
			},
			userIdMemoryChange : function() {
				$cookies.put('userIdMemory', $scope.userIdMemory);
				if ($scope.userIdMemory === '0') {
					$cookies.remove('savedUserId');
				}
			}
            , passwordHenko: function () {
                var modalInstance = $uibModal.open({
                    animation: $scope.animationsEnabled
                    , templateUrl: 'app/main/passwordhenko/PasswordHenko.html'
                    , controller: 'PasswordHenkoController'
                    , backdrop: 'static'
                    , size: 'md'
                    , resolve: {
                        rule: function (RulesService) {
                            return RulesService.load('PasswordHenkoInfo');
                        }
                        , input: {
                            userId: $scope.userId
                            , password: $scope.password
                        }
                    }
                });
                modalInstance.result.then(function (data) {
                	if(data){
                        $scope.userId = data.userId;
                        $scope.password = data.newPassword;
                        AlertService.addSuccess('パスワード変更をしました。新たしいパスワードを入力し、再度ログインしてください。', 2000);
                	}
                }, function () {});
            }
            
    	};
    	
    	$scope.$watch('moveTo', function(newValue){
    		$cookies.put('loginMove', newValue);
    	});

    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('LoginController', newController);

}());
