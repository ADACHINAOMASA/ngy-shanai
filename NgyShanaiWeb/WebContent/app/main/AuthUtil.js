var AuthUtil = (function() {

	var _authorizedURLPattern = [ '/mitsumoriToroku/shonin/', '/seisakuShijiIchiran/nac/','/seisakuShijiToroku/nac/','/seisakuShijiToroku/nacSansho/' ];

	function _isAuthorizedUrl(url) {
		//console.log('url='+url);
		if (!url) {
			return false;
		}
		for (var i = 0; i < _authorizedURLPattern.length; i++) {
			if (_authorizedURLPattern[i] != null && _authorizedURLPattern[i] != '') {
				if (url.indexOf(_authorizedURLPattern[i]) == 0) {
					//console.log('url=' + url + ' authorization -> OK');
					return true;
				}
			}
		}
		return false;
	}
	
	function _isHomeState(url) {
		if (!url) {
			return false;
		}
		if(url=='home' || url=='/home'){
			return true;
		}
		return false;
	}	

	// 公開関数
	return {
		isAuthorizedUrl : _isAuthorizedUrl,
		isHomeState : _isHomeState,
	}

})();