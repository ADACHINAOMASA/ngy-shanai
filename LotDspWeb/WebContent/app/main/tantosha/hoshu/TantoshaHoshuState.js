/**
 * TantoshaHoshuState.js
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider', 'SysConst'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider, SysConst) {
    	// 必要なステートを記述
        var baseURI = 'TantoshaHoshu';
        var controller = 'TantoshaHoshuController';
        var template = 'app/main/tantosha/hoshu/TantoshaHoshu.html';

        //メッセージ
		var messageParams = [
             {key: 'common.complete' },
        ];
		
        var _input = function(ModelService){
        	var input = {};
        	input.kensakuInfo = {};
        	input.torokuInfo = {};
        	return ModelService.newModel(SysConst.tantosha.kensakuInfo).then(function(_kensakuInfo) {
         		input.kensakuInfo = _kensakuInfo;
         		return ModelService.newModel(SysConst.tantosha.tourokuInfo).then(function(_torokuInfo) {
             		input.torokuInfo = _torokuInfo;
    				return input;
    			});
			});
        };
        
    	$stateProvider
	    	.state(baseURI,{
				url: '/' + baseURI,
				controller: controller,
				templateUrl: template,
				isLoginRequired: true,
				resolve: {
					header : function(HeaderService){
						return HeaderService.getHeader();
					},
					rule : function(RulesService) {
						return RulesService.load(SysConst.tantosha.kensakuInfo, SysConst.tantosha.tourokuInfo);
					},
					input : function(ModelService, $q) {
						var delay = $q.defer();
						_input(ModelService).then(function(data) {
							delay.resolve(data);
						});
						return delay.promise;
					},
                    records: function () {
                        return [];
                    },
					messages: function(MessageService){
	            		return MessageService.getMessages({messages:messageParams});
	                },
				},
				data :{
					state : 'new'
				},
				isLoginRequired: true
	    	});
    };

    newState.$inject = injectParams;

    angular.module('app')
        .config(newState);

}());