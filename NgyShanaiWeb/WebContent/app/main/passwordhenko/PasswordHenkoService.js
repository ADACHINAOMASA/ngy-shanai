(function () {
    // 必要な依存を列挙
    var injectParams = ['$http', '$state', '$q', '$window', '$interval'];

    // 引数は依存の内容と一致する
    var newService = function ($http, $state, $q, $window, $interval) {
        var baseURI = 'service/passwordhenko';
        return {
            changePassword: function (data) {
                var defferd = $q.defer();
                $http.post(baseURI+"/changePassword",data)
                    .success(function (data, status, headers, config) {
                        defferd.resolve(data);
                    })
                    .error(function (data, status, headers, config) {
                        defferd.reject(data);
                    });
                return defferd.promise;
            }
        };
    };

    newService.$inject = injectParams;
    angular.module('app')
        .factory('PasswordHenkoService', newService);
}());