/**
 * BsUtil(Bootstrap)関連のモジュール
 * ※required : bootstrap,angular,jQuery
 * ※target : <button><i></i></button>
 * @author Leang-Say
 */
var BsUtil = (function() {
	
	//Boostrap default info -------------------------------------------↓
	
	function _defaultDataToggleSelector(){
		return '.btn[data-toggle="collapse"]';
	}
	function _defaultDataTargetSelector(name){
		return '.btn[data-target="'+name+'"]';
	}
    function _defaultPanelCollapseSelector(){
    	return '.panel-collapse';
    }
    function _defaultOpenIconClass(){
    	return 'fa-chevron-down';
    }
    function _defaultCloseIconClass(){
    	return 'fa-chevron-right';
    }
    function _open(selector){
   	 	$(selector).collapse('show');
    }
    function _close(selector){
    	$(selector).collapse('toggle');
    }
    
    //---------------------------------------------------------------↑
    
    function _valueOrDefault(val,defaultVal){
    	if(val){
    		return val;
    	}
    	return defaultVal;
    }
    
    function _isOpenState(event, openIconClass, closeIconClass) {
        var el = event.srcElement ? event.srcElement : event.target;
        var element = angular.element(el);
        var child = element.children('i');
        var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        if (child.hasClass(open)) {
        	return true;
        }
        return false;
    }
    
    function _collapse(event, openIconClass, closeIconClass) {
        var el = event.srcElement ? event.srcElement : event.target;
        var element = angular.element(el);
        var iconElement = element.children('i');
        
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
        
        if (iconElement.hasClass(close)) {
        	_toOpenIcon(iconElement,open,close);
        } else {
        	_toCloseIcon(iconElement,open,close);
        }
    }
    
    function _collapseAll(event, openIconClass, closeIconClass) {
        var el = event.srcElement ? event.srcElement : event.target;
        var element = angular.element(el);
        var child = element.children('i');
        
        var dataToggleSelector = _defaultDataToggleSelector();        
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
                
        var isOpened=false;
        if (child.hasClass(close)) {
        	_toOpenIcon(child,open,close);
        	isOpened=true;
        } else {
        	_toCloseIcon(child,open,close);
        	isOpened=false;
        }       
    	
        $(dataToggleSelector).each(function(i, el) {
        	var iconElement = angular.element(el).children('i');       
        	        	
            if(isOpened==true){            	
            	if (iconElement.hasClass(close)) {
            		
                	_open(_defaultPanelCollapseSelector());
                	_toOpenIcon(iconElement, open, close);
            	}
            }else{            	
            	if (iconElement.hasClass(open)) {
            		
            		_close(_defaultPanelCollapseSelector());
                	_toCloseIcon(iconElement, open, close);
            	}
            } 
                
            
        });        
    }
    
    function _closeAll(event, openIconClass, closeIconClass) {
        var el = event.srcElement ? event.srcElement : event.target;
        var element = angular.element(el);
        var child = element.children('i');
        
        var dataToggleSelector = _defaultDataToggleSelector();        
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
           	
        $(dataToggleSelector).each(function(i, el) {
        	var iconElement = angular.element(el).children('i');
        	_close(_defaultPanelCollapseSelector());
        	_toCloseIcon(iconElement, open, close);
        });
        
    }
        
    function _openAllChildren(openIconClass, closeIconClass){
    	
    	var dataToggleSelector = _defaultDataToggleSelector();  
    	
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
    	
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
            	
        $(dataToggleSelector).each(function(i, el) {
        	
            var iconElement = angular.element(el).children('i');
            
            _open(_defaultPanelCollapseSelector());
            
            _toOpenIcon(iconElement, open, close);
            
        });
        
    }
    
    function _closeAllChildren(openIconClass, closeIconClass){
    	
    	var dataToggleSelector = _defaultDataToggleSelector();
    	    	
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
        
        $(dataToggleSelector).each(function(i, el) {
            var iconElement = angular.element(el).children('i');         
            _close(_defaultPanelCollapseSelector());
            _toCloseIcon(iconElement, open, close);
        });
    }
    
    function _changeIcon(iconElement, openIconClass, closeIconClass) {
    	if(!iconElement){
    		return;
    	}
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
        
        if (iconElement.hasClass(close)) {
        	_toOpenIcon(iconElement,open,close);
        } else {
        	_toCloseIcon(iconElement,open,close);
        }
    }
    
    function _toOpenIcon(iconElement, openIconClass, closeIconClass) {
    	if(!iconElement){
    		return;
    	}
    	
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
                
        iconElement.removeClass(open);
        iconElement.removeClass(close);
        iconElement.addClass(open);
    }
    
    function _toCloseIcon(iconElement, openIconClass, closeIconClass) {
    	if(!iconElement){
    		return;
    	}
    	var open = _valueOrDefault(openIconClass,_defaultOpenIconClass());
        var close = _valueOrDefault(closeIconClass,_defaultCloseIconClass());
        iconElement.removeClass(open);
        iconElement.removeClass(close);
        iconElement.addClass(close);
    }
    
    function _changeToCloseIcon(event, openIconClass, closeIconClass) {
        var el = event.srcElement ? event.srcElement : event.target;
        var element = angular.element(el);
        var iconElement = element.children('i');
        
        _toCloseIcon(iconElement, openIconClass, closeIconClass);        
    }
           
        
    function _openCollapse(id, openIconClass, closeIconClass) {	
    	var selector = _defaultDataTargetSelector(id);
    	var element = angular.element($(selector)[0]);
    	var iconElement = element.children('i'); 

        _toOpenIcon(iconElement, openIconClass, closeIconClass);
        _open(id);
    }
    
    function _closeCollapse(id, openIconClass, closeIconClass) {	
    	var selector = _defaultDataTargetSelector(id);
    	var element = angular.element($(selector)[0]);
    	var iconElement = element.children('i'); 

    	_toCloseIcon(iconElement, openIconClass, closeIconClass);
        _close(id);
    }

	// 公開関数
	return {
		collapse : _collapse,
		collapseAll : _collapseAll,
		
		openCollapse : _openCollapse,
		closeCollapse : _closeCollapse,
		isExpanding : _isOpenState,
		
		closeAllChildren:_closeAllChildren,
		openAllChildren:_openAllChildren,
		
		changeToCloseIcon:_changeToCloseIcon
	}
	

})();