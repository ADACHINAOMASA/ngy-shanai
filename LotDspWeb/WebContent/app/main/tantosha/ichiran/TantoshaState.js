/**
 * _コントローラー
 */
(function () {

	// 必要な依存を列挙
    var injectParams = ['$stateProvider', 'stateHelperProvider', '$urlRouterProvider', 'SysConst'];

    // 引数は依存の内容と一致する
    var newState = function ($stateProvider, stateHelperProvider, $urlRouterProvider, SysConst) {
    	// 必要なステートを記述
        var baseURI = 'tantoshaMente';
        var controller = 'TantoshaIchiranController';
        var template = 'app/main/tantosha/ichiran/tantoshaIchiran.html';
        
    	$stateProvider
	    	.state(baseURI,{
				url: '/' + baseURI,
				controller: controller,
				templateUrl: template,
				isLoginRequired: true,
				resolve: {
					rule : function(RulesService) {
						return RulesService.load(SysConst.tantosha.kensakuInfo, SysConst.tantosha.tourokuInfo);
					},
					input : function(ModelService) {
						return ModelService.newModel(SysConst.tantosha.kensakuInfo);
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