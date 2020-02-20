/**
 * NmtPpm用のUtilモジュール
 * @author Leang-Say
 */
var AppUtil = (function() {

    var ON = '1';
    var OFF = '0';
    var ASC = 'asc';
    var DESC = 'desc';

    //private ----------------------------------------------------------------------------
    function _hasValue(val) {
        if (val === undefined) {
            return false;
        }
        if (val == null || val == '') {
            return false;
        }
        return true;
    }
    function _hasNoValue(val) {
    	return !_hasValue(val);
    }
    function _nvl(val, replace) {
        if (_hasValue(val)) {
            return val;
        }
        if (replace === undefined) {
            return '';
        }
        if (replace == null || replace == '') {
            return '';
        }
        return replace;
    }
    function _listHasValue(list) {
        if (!list || list.length == 0) {
            return false;
        }
    	return true;
    }
    function _listHasNoValue(list) {
    	return !_listHasValue(list);
    }
    function _isHasValue(prop, list) {
    	if(_hasNoValue(prop)){
    		return false;
    	}
    	if(_listHasNoValue(list)){
    		return false;
    	}
    	return true;
    }
    function _isHasNoValue(prop, list) {
    	return !_isHasValue(prop,list);
    }
    function _copy(obj){
    	return angular.copy(obj);
    }

    //public ----------------------------------------------------------------------------
    function getSingleSelectedObject(prop, list) {
    	if(_hasNoValue(prop)){
    		return list;
    	}
    	if(_listHasNoValue(list)){
    		return list;
    	}
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            if (_nvl(obj[prop], OFF) === ON) {
                return obj;
            }
        }
        return null;
    }

    function contain(val, prop, list) {
        if(_isHasNoValue(prop,list)){
        	return false;
        }
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            if (obj[prop] === val) {
                return true;
            }
        }
        return false;
    }

    function selectAll(prop, list) {
    	if(_hasNoValue(prop)){
    		return;
    	}
    	if(_listHasNoValue(list)){
    		return;
    	}
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            obj[prop] = ON;
        }
    }

    function unSelectAll(prop, list) {
    	if(_hasNoValue(prop)){
    		return;
    	}
    	if(_listHasNoValue(list)){
    		return;
    	}
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            obj[prop] = OFF;
        }
    }

    function isAllSelected(prop, list) {
        if(_isHasNoValue(prop,list)){
        	return false;
        }
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            if (_nvl(obj[prop], OFF) === OFF) {
                return false;
            }
        }
        return true;
    }

    function countSelectedObject(prop, list) {
        if(_isHasNoValue(prop,list)){
        	return 0;
        }
        var cnt = 0;
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            if (_nvl(obj[prop], OFF) === ON) {
            	 cnt = cnt + 1;
            }
        }
        return cnt;
    }

    function getUnselectedList(prop, list) {
    	if(_hasNoValue(prop)){
    		return list;
    	}
    	if(_listHasNoValue(list)){
    		return list;
    	}
        var restList = [];
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            if (_nvl(obj[prop], OFF) === OFF) {
                restList.push(_copy(obj));
            }
        }
        return restList;
    }
    
    function getSelectedList(prop, list) {
    	if(_hasNoValue(prop)){
    		return list;
    	}
    	if(_listHasNoValue(list)){
    		return list;
    	}
        var restList = [];
        for (var i = 0; i < list.length; i++) {
            var obj = list[i];
            if (_nvl(obj[prop], OFF) === ON) {
                restList.push(_copy(obj));
            }
        }
        return restList;
    }
    
    function _classify(prop, list, onList, offList) {
    	
    	if(_hasNoValue(prop)){
    		return list;
    	}
    	
    	if(_listHasNoValue(list)){
    		return list;
    	}
            	
        for (var i = 0; i < list.length; i++) {
        	
            var obj = list[i];
            
            if (_nvl(obj[prop], OFF) === ON) {
            	onList.push(_copy(obj));
            }
            else{
            	offList.push(_copy(obj));
            }
        }
    }
    
    function sortBy(list, func, orderBy) {
        if (orderBy) {
            if (orderBy.toLowerCase() === ASC) {
                return _.sortBy(list, func);
            } else if (orderBy.toLowerCase() === DESC) {
                return _.sortBy(list, func).reverse();
            } else {
                return _.sortBy(list, func);
            }
        } else {
            return _.sortBy(list, func);
        }
    }
    
    function isNullOrEmptyList(list){
    	if(!list || list.length==0){
    		return true;
    	}
    	return false;
    }
    
    function _initCalendar($scope){
		
    	$scope.calOpen = {};
		
		$scope.toggleDatePicker = function($event,flag,index) {
			
			$event.stopPropagation();
						
			for(var key in $scope.calOpen) {
			    var value = $scope.calOpen[key];
			    $scope.calOpen[key] = false;
			}
						
			var targetFlag=flag;
			if(index){
				targetFlag=targetFlag+"_"+index;
			}
			
			//$scope.calOpen[flag] = !$scope.calOpen[flag];
			$scope.calOpen[targetFlag] = !$scope.calOpen[targetFlag];
						
		};
				
    }
    
    function _initGetMeisho($scope, MeishoService) {

        $scope._getMeisho = function (prefix, type, paramField, returnField, returnField1, returnField2, returnField3, returnField4) {

            var localScope = $scope;

            if (prefix) {
                localScope = $scope[prefix];
            }

            var info = {
                type: type
                , parameters: {
                    code: localScope[paramField]
                }
                , results: {
                    code: ''
                    , meisho: ''
                    , meisho1: ''
                    , meisho2: ''
                    , meisho3: ''
                    , meisho4: ''
                }
            };
            
            if(_hasNoValue(info.parameters.code)){
            	return;
            }

            MeishoService.getMeisho(info).then(function (data) {
                if (data.results.code) {
                    localScope[paramField] = data.results.code;
                }
                if (returnField) {
                    if(data.results.meisho){
                    	localScope[returnField] = data.results.meisho;
                    }
                }
                if (returnField1) {
                	if(data.results.meisho1){
                    	localScope[returnField1] = data.results.meisho1;
                    }
                }
                if (returnField2) {                   
                    if(data.results.meisho2){
                    	localScope[returnField2] = data.results.meisho2;
                    }
                }
                if (returnField3) {
                	if(data.results.meisho3){
                    	localScope[returnField3] = data.results.meisho3;
                    }
                }
                if (returnField4) {
                    if(data.results.meisho4){
                    	localScope[returnField4] = data.results.meisho4;
                    }
                }
            });

        };
    }
    
    function _alternateOnOff(list,selectProp,allSelectItem){
    	
        if (isNullOrEmptyList(list) || _hasNoValue(selectProp) || _hasNoValue(allSelectItem)) {
            return;
        }

        if (allSelectItem == ON) {
            selectAll(selectProp, list);
        } else {
            unSelectAll(selectProp, list);
        }
        
    }
    
    function _selectAllIfAllElementSelected(list, selectProp,targetObj, allSelectProp) {

        if (isNullOrEmptyList(list) || _hasNoValue(selectProp)  || _hasNoValue(targetObj) || _hasNoValue(allSelectProp)) {
            return;
        }

        var selectedCnt = countSelectedObject(selectProp, list);

        if (list.length == selectedCnt) {
        	
            targetObj[allSelectProp] = ON;
            
            selectAll(selectProp, list);
            
        } else {
        	 targetObj[allSelectProp]  = OFF;
        }
        
    }
    
    var uiGrid = {
		numberFilter: {
	        biggerOrEqual: function (searchTerm, cellValue, row) {
	            var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
	            if (!isNaN(searchValue) && !isNaN(cellValue)) {
	                return cellValue >= Number(searchValue);
	            }
	            return false;
	        }
	        , bigger: function (searchTerm, cellValue, row) {
	            var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
	            if (!isNaN(searchValue) && !isNaN(cellValue)) {
	                return cellValue > Number(searchValue);
	            }
	            return false;
	        }
	        , equal: function (searchTerm, cellValue, row) {
	            var searchValue = searchTerm.replace(/[^\d|\-+|\.+]/g, '') || NaN;
	            if (!isNaN(searchValue) && !isNaN(cellValue)) {
	                return cellValue == Number(searchValue);
	            }
	            return false;
	        }

	    }
	    , dateFilter: {
	        contain: function (searchTerm, cellValue, row, pattern) {
	            var formatter = 'yyyy/MM/dd';
	            if (pattern) {
	                formatter = pattern;
	            }
	            return NisUtil.nullToBlank(NisUtil.formatDate(cellValue, formatter)).search(searchTerm) >= 0;
	        }
	    }
    };
        
    var _scope = {
	    setupDefault:function($scope,i18nService,AlertLogService,MeishoService,isInitCalendar){
	    	
	    	if(i18nService){
	    		$scope.langs = i18nService.getAllLangs();
	    		i18nService.setCurrentLang('ja');
	    	}
	    	
	    	if(AlertLogService){
	    		//ログ情報の初期化
	    		$scope.alertLogs = AlertLogService.logs();
	        	$scope.alertLogsClear = AlertLogService.clear;
	    	}
	    	
	    	if(MeishoService){
	    		// コードにより名称の取得を初期化
	    		_initGetMeisho($scope,MeishoService);
	    	}
	    	
	    	if(isInitCalendar){
	    		//カレンダーの初期化
	    		_initCalendar($scope);
	    	}
	    } 		
    };
    
    
    // 公開関数
    return {
    	uiGrid:uiGrid,
    	scope:_scope,
    	
    	hasNoValue:_hasNoValue,
    	hasValue:_hasValue,
    	
    	isNullOrEmptyList:isNullOrEmptyList,
    	
    	sortBy:sortBy,
    	contain:contain,
    	
    	countSelectedObject:countSelectedObject,
    	
    	getSingleSelectedObject:getSingleSelectedObject,
    	getUnselectedList:getUnselectedList,
    	getSelectedList:getSelectedList,
    	
    	isAllSelected:isAllSelected,
    	selectAll:selectAll,
    	toSelectAll:selectAll,
    	unSelectAll:unSelectAll,
    	toUnselectAll:unSelectAll,
    	
    	classifyOnOff:_classify,
    	switchOnOff:_alternateOnOff,
    	selectAllIfAllElementSelected:_selectAllIfAllElementSelected,
    	
    	initCalendar:_initCalendar,
    	initGetMeisho:_initGetMeisho,
    	
    	
    }

})();