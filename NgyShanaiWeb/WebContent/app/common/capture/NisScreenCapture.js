
(function () {

    // 必要な依存を列挙
    var injectParams = ['$document', '$window', '$timeout', 'AlertService'];

    // 引数は依存の内容と一致する
    var nisScreenCapture = function ($document, $window, $timeout, AlertService) {
    	// ディレクティブ内容
    	var captureMode = function(){
    		if ($('.screen').size()) {
    			return;
    		}
    		var body = $(document.body);
    		var screen = $('<div class="screen" style="position:absolute;left:0;top:0;height:100%;width:100%;opacity:0.05;background-color:black;z-index:2000"></div>');
    		$(document.body).append(screen)
    		.on('mousedown.capture', function(e){
				var div = $('<div style="position:fixed;background-color:transparent;border:1px dotted black;z-index:999;"></div>')
				var startX = e.clientX;
				var startY = e.clientY;
				var pageX = e.pageX;
				var pageY = e.pageY;
				div.css('left', startX);
				div.css('top', startY);
				body.off('keydown.capture');
				body.append(div)
				.on('mousemove.capture', function(e){
					var width = e.clientX - startX;
					var height = e.clientY - startY;
					div.css('width', width);
					div.css('height', height);
				})
				.on('mouseup.capture', function(e){
					screen.remove();
					var width = div.width();
					var height = div.height();
					div.css('border-width', '0');
					body.off('mousedown.capture');
					body.off('mousemove.capture');
					body.off('mouseup.capture');
					if (width <= 30 && height <= 30) {
						$timeout(function(){
							AlertService.addWarning('指定範囲が狭すぎます');
						}, 200);
						div.remove();
						return;
					}
					// 最新版(5.0.2beta)ならpageでなくstart
					// IEは旧版(4.7)でないと正しく動作しない
					html2canvas(document.body, {
						width:pageX + width,
						height:pageY + height,
				        onrendered: function(canvas) {
				        	var toCanvas = document.createElement('canvas');
				        	toCanvas.height = height;
				        	toCanvas.width = width;
				        	div.append(toCanvas);
				        	if (typeof G_vmlCanvasManager != 'undefined') {
				        		toCanvas = G_vmlCanvasManager.initElement(canvas);
				        	}
				        	var ctx = toCanvas.getContext('2d');
				        	ctx.drawImage(canvas, pageX + 1, pageY + 1, width, height, 0,0,width,height);
				        	div.css({
				        		'border': '1px solid #ddd'
				        		,'padding': '4px'
				        		,'background-color': '#FFF'
				        		,'border-radius': '4px'
				        		,'padding-top': '26px'
				        		,'position': 'absolute'
				        	});
				        	div.width('');
				        	div.height('');
				        	div.css('left', startX - 4);
				        	div.css('top', startY - 26);
				        	div.data('pinned', false);

				        	// コンテキストを生成するべき

				        	var close = $('<span class="btn" style="position:absolute;right:5px;top:2px;"><i class="glyphicon glyphicon-remove"></i></span>');
				        	div.append(close);
				        	close.on('click', function(){
				        		div.remove();
				        	});

				        	var save = $('<span class="btn" style="position:absolute;right:30px;top:2px;"><i class="glyphicon glyphicon-save"></i></span>');
				        	div.append(save);
				        	save.on('click', function(){
				        		toCanvas.toBlob(function(blob) {
				        		    saveAs(blob, "capture.png");
				        		});
				        	});

				        	var min= $('<span class="btn" style="position:absolute;right:55px;top:2px;"><i class="glyphicon glyphicon-minus"></i></span>');
				        	div.append(min);
				        	min.on('click', function(){
				        		$(toCanvas).hide();
					        	div.width(100);
					        	div.height(20);
					        	div.css('opacity', '0.8');
					        	min.hide();
					        	max.show();
				        	});

				        	var max = $('<span class="btn" style="position:absolute;right:55px;top:2px;display:none;"><i class="glyphicon glyphicon-plus"></i></span>');
				        	div.append(max);
				        	max.on('click', function(){
				        		$(toCanvas).show();
					        	div.width('');
					        	div.height('');
					        	div.css('opacity', '1');
					        	max.hide();
					        	min.show();
				        	});

				        	var pin= $('<span class="btn" style="position:absolute;right:80px;top:2px;"><i class="glyphicon glyphicon-pushpin"></i></span>');
				        	div.append(pin);
				        	pin.on('click', function(e){
				        		if (div.data('pinned')) {
				        			div.css('top', div.offset().top);
				        			div.css('left', div.offset().left);
				        			div.css('position', 'absolute');
				        			pin.css('color', 'black');
					        		div.data('pinned', false);
				        		}
				        		else {
				        			div.css('top', div.offset().top - ($window.scrollY || $window.pageYOffset));
				        			div.css('left', div.offset().left - ($window.scrollX || $window.pageXOffset));
				        			div.css('position', 'fixed');
				        			pin.css('color', 'red');
					        		div.data('pinned', true);
				        		}
				        	});
				        }
			        });
				});
				div.bind('mousedown', function(e){
					var body = $(document.body);
					var startX = e.pageX;
					var startY = e.pageY;
					var startLeft = div.offset().left;
					var startTop = div.offset().top;
					body.on('mousemove.capturemove', function(e){
						var eX,eY;
						if (div.data('pinned')) {
							eX = e.clientX;
							eY = e.clientY;
						}
						else {
							eX = e.pageX;
							eY = e.pageY;
						}
						var left = startLeft + (eX - startX);
						var top = startTop + (eY - startY);
						div.css('left', left);
						div.css('top', top);
					});
					body.on('mouseup.capturemove', function(e){
						body.off('mousemove.capturemove');
						body.off('mouseup.capturemove');
					});
				});
			});
    		body.on('keydown.capture', function(e){
				if(e.which === 27) {
					screen.remove();
		        	body.off('mousedown.capture');
		        	body.off('keydown.capture');
				}
			});
    	};
    	return {
			restrict: 'E',
			link: function(scope, elm, attrs) {
				if ($(document).data('capture')) {
					return;
				}
				$(document).data('capture', true);
				$(document).on('keyup.capture', function(e){
					if(e.which !== 44) {
						return;
					}
					captureMode();
				});
				$(document).on('click', '.capture_trigger', function(){
					captureMode();
				});
			}
    	};
    };

    nisScreenCapture.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisScreenCapture', nisScreenCapture);
    } catch(e) {
    	console.error(e);
    }

}());