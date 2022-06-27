/**
 * Optionsフィルター
 * Optionsのvalueからlabelを表示
 * 第2引数はOptionsのキー
 * 使用例：{{input.rank | options : 'rank'}}
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['OptionsService'];

    // 引数は依存の内容と一致する
    var OptionsFilter = function (OptionsService) {
        var data = {}, serviceInvoked = {};

        function realFilter(text, optionsKey) {
            return data[optionsKey][text];
        }

        filterStub.$stateful = true;
        function filterStub(text, optionsKey) {
            if(!data[optionsKey]) {
                if(!serviceInvoked[optionsKey]) {
                	serviceInvoked[optionsKey] = true;
                	OptionsService.getOptions(optionsKey).then(function(options){
                		if(!options) {
                			console.log('options:' + optionsKey + ' が見つかりません。')
                		}
                		data[optionsKey] = _.object(_.map(options.options, function(obj){
                			return [obj.value, obj.label];
                		}));
            		});
                }
                return "-";
            }
            else {
            	return realFilter(text, optionsKey);
            }
        }

        return filterStub;
    };

    OptionsFilter.$inject = injectParams;

    angular.module('app')
        .filter('options', OptionsFilter);

}());