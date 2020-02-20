/**
 * validateエラーメッセージ表示領域
 * <nis-validate-messages target="tantoshaForm.tantoshaCd"></nis-validate-messages>
 */
(function () {

	// TODO:ラジオ、プルダウンは専用のディレクティブを作ってしまっているので、どうするか考える
	// TODO:linkタグはdisabledが効かないので別の方法

    // 必要な依存を列挙
    var injectParams = ['AuthorityService'];

    // 引数は依存の内容と一致する
    var nisAuthority = function (AuthorityService) {
    	// ディレクティブ内容
    	return {
			restrict: 'A',
			link: function(scope, elm) {
				var auths = NisUtil.property.getProperty(scope, elm.attr('nis-authority')) || elm.attr('nis-authority') || '';
				if (_.isString(auths)) {
					auths = auths.replace(' ', '').split(',');
				}
				if (AuthorityService.has(auths)) {
					return;
				}
				// TODO:範囲でもいけるようにするか
				// TODO:権限で無効化された場合の優先度を最上位に
				elm.addClass('nis-not-authority');
				elm.attr('title', '操作する権限がありません。');
				switch (elm.prop('tagName').toUpperCase()) {
					// TODO:他にあったら都度追加
					case 'BUTTON':
					case 'SELECT':
						elm.prop('disabled', true);
						break;
					case 'A':
						elm.on('click', function(e){
							e.preventDefault();
							return false;
						});
						break;
					case 'INPUT':
						switch (elm.prop('type').toUpperCase()) {
							case 'TEXT':
							case 'NUMBER':
							case 'DATE':
								elm.prop('readonly', true);
								break;
							case 'RADIO':
							case 'BUTTON':
							case 'SUBMIT':
							case 'RESET':
								elm.prop('disabled', true);
								break;
						}
						break;
					case 'TEXTAREA':
						elm.prop('readonly', true);
						break;
				}
			}
    	};
    };

    nisAuthority.$inject = injectParams;

    try {
    	angular.module('app')
    		.directive('nisAuthority', nisAuthority);
    } catch(e) {
    	console.error(e);
    }

}());