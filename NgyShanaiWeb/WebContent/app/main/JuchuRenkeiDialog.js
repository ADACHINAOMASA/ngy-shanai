
var JuchuRenkeiDialog = (function() {
    var DEFAULT_URL = '';
    var TAB_URL = '&tab=0';
    var _subWinObj = null;

    var _DEFAULT_WIDTH = "1024";
    var _DEFAULT_HEIGHT = "745";

    var open = function(url) {
        close();

        if (url == null || url == '') {
            alert('受注連携のURLを指定してください。');
            return;
        }
      
        var features = _getFeatures();
        _subWinObj = window.open(url, "_blank", features);
        
        return _subWinObj;
    };

    var _getFeatures = function(w, h) {
        var winWidth = _DEFAULT_WIDTH;
        var winHeight = _DEFAULT_HEIGHT;

        var winLeft = (window.screen.availWidth - winWidth) / 2;
        var winTop = (window.screen.availHeight - winHeight) / 2;
        if (w) {
            winWidth = w;
        }
        if (h) {
            winHeight = h;
        }

        var features = '';
        features += ',width=' + winWidth;
        features += ',height=' + winHeight;
        features += ',left=' + winLeft;
        features += ',top=' + winTop;
        features += ',toolbar=no';
        features += ',location=no';
        features += ',directories=no';
        features += ',status=no';
        features += ',menubar=yes';
        features += ',resizable=yes';

        return features;
    };

    var close = function() {
        if (_subWinObj) {
            _subWinObj.close();
            _subWinObj = null;
        }
    };

    return {
        open: open,
        close: close
    };
    
})();


