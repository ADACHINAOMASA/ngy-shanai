(function () {

	// 必要な依存を列挙
    var injectParams = ['$localStorage', '$timeout', 'OptionsService', 'rowSearcher'];

    // 引数は依存の内容と一致する
    var NisGridUtil = function ($localStorage, $timeout, OptionsService, rowSearcher) {
    	var filterHighlight = function( row, rowRenderIndex, col, colRenderIndex ) {
			if( col.filters[0].term ){
				return 'text-blue';
			} else {
				return '';
			}
		};

    	this.gridOptionsDefault = function(gridOptions, stateName) {
    		var defaultOptions = {
				enableGridMenu: true,
				enableSorting: true,
				enableColumnResizing: true,
				enableFiltering: true,
				paginationPageSizes: [100, 200, 500, 1000],
				paginationPageSize: 100,
				exporterOlderExcelCompatibility: true,
				exporterMenuPdf: false,
				exporterFieldCallback : function ( grid, row, col, value ){
					if (col.cellFilter && col.cellFilter.indexOf('options') >= 0 && col.filter.selectOptions){
						var option = _.find(col.filter.selectOptions, function(option){
							return option.value == value;
						});
						return option ? option.label : value;
					}
					return value;
				}
    		};
    		return angular.extend(defaultOptions, gridOptions);
    	};

    	this.columnDefDefault = function(columnDef) {
    		var defaultDef = {
    			headerCellClass: filterHighlight
    		};
    		return angular.extend(defaultDef, columnDef);
    	};

    	this.columnDefButtonDefault = function(columnDef) {
    		var defaultDef = {
    			pinnedLeft : true,
    			enableSorting : false,
    			cellClass : 'overflow_visible text-center',
    			enableColumnMenu : false,
    			enableFiltering : false,
    			exporterSuppressExport:true
    		};
    		return angular.extend(defaultDef, columnDef);
    	};
    	
    	/*
    	this.setOptionsFilter = function(gridOptions, options, column) {
    		column = column || options;
    		OptionsService.getOptions(options, true).then(function(data){
	    		_.find(gridOptions.columnDefs, function (columnDef){
	    			return columnDef.field == column;
	    		}).filter.selectOptions = data.options;
	    	});
    	};
    	*/
    	
    	this.setOptionsFilter = function(gridOptions, options, column) {
    		var filter = _.find(gridOptions.columnDefs, function (columnDef){
    						return columnDef.field == column;
    					}).filter;
    		if (_.isString(options)) {
    			OptionsService.getOptions(options, true).then(function(data){
        			filter.selectOptions = data.options;
    	    	});
    			return;
    		}
    		if (_.isObject(options)) {
    			filter.selectOptions = options;
    			return;
    		}
    	};
    	

    	this.saveState = function(gridApi, stateName) {
    		$timeout(function(){
    			$localStorage[stateName] = gridApi.saveState.save();
    		});
    	};

    	this.saveDefaultState = function(gridApi, stateName) {
    		$timeout(function(){
    			$localStorage[stateName + 'Default'] = gridApi.saveState.save();
    		});
    	};

    	this.loadState = function($scope, gridApi, stateName) {
    		if (!$localStorage[stateName]) {
				return;
			}
    		$timeout(function(){
    			gridApi.saveState.restore($scope, $localStorage[stateName]);
    		});
    	};

    	this.loadDefaultState = function($scope, gridApi, stateName) {
    		gridApi.saveState.restore($scope, $localStorage[stateName + 'Default']);
    	};

    	this.setStateSaveEvent = function ($scope, gridApi, stateName){
    		var saveStateEvent = angular.bind(this, this.saveState, gridApi, stateName);
    		$timeout(function(){
    			gridApi.colMovable.on.columnPositionChanged($scope, saveStateEvent);
	    		gridApi.colResizable.on.columnSizeChanged($scope, saveStateEvent);
    			gridApi.core.on.columnVisibilityChanged($scope, saveStateEvent);
    			gridApi.core.on.filterChanged($scope, saveStateEvent);
    			gridApi.core.on.sortChanged($scope, saveStateEvent);
    		});
    	};
    	
    	/**
    	 * フィルターを強制実行し、絞り込み後のエンティティを返す
    	 * ページングによる非表示は無視される
    	 * visibleDataChangedイベントが走ってしまうので注意
    	 */
    	this.rowEntitySearch = function(gridApi) {
    		return _.chain(rowSearcher.search(gridApi.grid, gridApi.grid.rows, gridApi.grid.columns))
    			.filter(function(row){
    				return row.visible;
    			})
    			.map(function(row){
    				return row.entity;
    			})
    			.value();
    	};
    	
    };

    NisGridUtil.$inject = injectParams;

    angular.module('app')
    	// factory | service | provider
        .service('NisGridUtil', NisGridUtil);

}());