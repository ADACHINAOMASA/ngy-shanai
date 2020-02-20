/**
 * @author Leang-Say
 */
(function () {
	
    var name = 'SharedObject';

    var injectParams = [];

    function factory() {
    	
        var _commonContainer = NisUtil.createContainer();
    	
        return {
        	
            map: function () {
                return _commonContainer;
            }
            
            ,transition: {
                from: '',
                history: [],
            }
            
            ,hyojiMode: ''
            
        };
    };

    factory.$inject = injectParams;
    angular.module('app').factory(name, factory);

}());