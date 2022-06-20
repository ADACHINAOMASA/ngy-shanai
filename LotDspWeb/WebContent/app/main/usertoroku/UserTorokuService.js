(function () {
    // 必要な依存を列挙
    var injectParams = ['$http', '$state', '$q', 'NIS', '$window', '$interval'];

    // 引数は依存の内容と一致する
    var newService = function ($http, $state, $q, NIS, $window, $interval) {
        var baseURI = 'service/usertoroku';
        return {
            userToroku: function (data) {
            	return NIS.u.req($http.post(NIS.u.path(baseURI, 'toroku'), data));
//                var defferd = $q.defer();
//                $http.post(baseURI+"/toroku",data)
//                    .success(function (data, status, headers, config) {
//                        defferd.resolve(data);
//                    })
//                    .error(function (data, status, headers, config) {
//                        defferd.reject(data);
//                    });
//                return defferd.promise;
            }
        };
    };

    newService.$inject = injectParams;
    angular.module('app')
        .factory('UserTorokuService', newService);
}());