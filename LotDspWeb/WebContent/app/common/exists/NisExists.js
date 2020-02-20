/**
 *
 */
(function () {

    // 必要な依存を列挙
    var injectParams = ['TorihikisakiResource', '$q'];

    // 引数は依存の内容と一致する
    var nisExists = function (TorihikisakiResource, $q) {
    	var validator = function(modelValue, viewValue){
    		var value = modelValue || viewValue;
    		var deferred = $q.defer();
    		if (!value) {
				deferred.resolve(true);
    		}
    		else {
    			TorihikisakiResource.get({id: value}, function(data){
    				if (data.torihikisakiCd) {
    					deferred.resolve(true);
    				}
    				else {
    					deferred.reject();
    				}
        		});
    		}
    		return deferred.promise;
    	};
    	// ディレクティブ内容
    	return {
    		require: 'ngModel',
			restrict: 'A',
			link: function(scope, element, attrs, ctrl) {
				ctrl.$asyncValidators.exists = validator;
			}
    	};
    };

    nisExists.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisExists', nisExists);
    } catch(e) {
    	console.error(e);
    }

}());