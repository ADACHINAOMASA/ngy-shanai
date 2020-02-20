/**
 * @author Leang-Say
 */
(function () {

    var name = 'HeaderService';

    // 必要な依存を列挙
    var injectParams = ['$q', 'LoginService', 'SysConst'];

    // 引数は依存の内容と一致する
    var newService = function ($q, LoginService, SysConst) {
        return {
            getHeader: function () {
                var delay = $q.defer();
                LoginService.$getUserProfile()
                    .then(function (data) {
                        var header = {};
                        header.systemName = SysConst.system.name;
                        header.userProfile = data;
                        delay.resolve(header);
                    });
                return delay.promise;

            },
        };
    };

    newService.$inject = injectParams;
    
    angular.module('app').factory(name, newService);

}());