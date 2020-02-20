(function () {

    var injectParams = ['$scope', '$interval', 'SysConst'];

    var newController = function ($scope, $interval, SysConst) {
    	$scope.copyright = SysConst.company.copyright;
    	$scope.copyright1 = SysConst.company.copyright1;
    	$scope.copyright2 = SysConst.company.copyright2;
    	$scope.copyright3 = SysConst.company.copyright3;
    };

    newController.$inject = injectParams;

    angular.module('app')
        .controller('FooterController', newController);

}());
