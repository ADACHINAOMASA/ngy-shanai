// 互換性を考えて、Utilないでは_以外のライブラリのユーティリティを使わないようにする？

// TODO:数が増えてきたので、処理の種別ごとにある程度分けたい(Date,Number等）

var NisUtil = (function(){
	// 内部関数
	function checkKeyDownExKey(keyCode){
		return _.contains(
				['0','8','9','13','16','17','18','19','20','27','32','33','34','35','36','37','38','39','40','45','46']
				, String(keyCode));
	}

	function checkFilterKeyCode(key, filterValue) {
		return filterValue.indexOf(key) == -1;
	}

	function testFilterKeyCode(key, filterValue) {
		return !filterValue.test(key);
	}

	function validDate(str) {
		if (!str) {
			return true;
		}
	    var ymd = str.split('/');
	    var y   = ymd[0];
	    var m   = parseInt(ymd[1],10) - 1;
	    var d   = parseInt(ymd[2],10);
	    var vd  = new Date(y, m, d);
	    if (vd.toString()!='Invalid Date' && vd.getFullYear()==y && vd.getMonth()==m && vd.getDate()==d) {
	        return true;
	    }
	    return false;
	}

	function isTrue(obj) {
		if (typeof obj === 'string') {
			return obj === 'true';
		}
		return !!obj;
	}

	function isFalse(obj) {
		return !isTrue(obj);
	}

	function getByteLength(str) {
		var len = 0;
		for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
			// Shift_JIS: 0x0 ～ 0x80, 0xa0  , 0xa1   ～ 0xdf  , 0xfd   ～ 0xff
			// Unicode  : 0x0 ～ 0x80, 0xf8f0, 0xff61 ～ 0xff9f, 0xf8f1 ～ 0xf8f3
			if ( (c >= 0x0 && c < 0x81) || (c == 0xf8f0) || (c >= 0xff61 && c < 0xffa0) || (c >= 0xf8f1 && c < 0xf8f4)) {
				len += 1;
			}
			else {
				len += 2;
			}
	    }
	    return len;
	}

	function left(str, length) {
		var retStr = String(str) || '';
		if (retStr.length > length) {
			retStr = retStr.substring(0, length);
		}
		return retStr;
	}

	function leftPad(str, length, padVal) {
		var retStr = String(str) || '';
		padVal = padVal || ' ';
		for (var i = retStr.length;i < length;i++) {
			retStr = padVal.concat(retStr);
		}
		return retStr;
	}

	/**
	 * 半角カナを全角カナに変換
	 * ※moji.js利用を推奨
	 */
	function hanKana2zenKana(str) {
		var kanaMap = {
		        'ｶﾞ': 'ガ', 'ｷﾞ': 'ギ', 'ｸﾞ': 'グ', 'ｹﾞ': 'ゲ', 'ｺﾞ': 'ゴ',
		        'ｻﾞ': 'ザ', 'ｼﾞ': 'ジ', 'ｽﾞ': 'ズ', 'ｾﾞ': 'ゼ', 'ｿﾞ': 'ゾ',
		        'ﾀﾞ': 'ダ', 'ﾁﾞ': 'ヂ', 'ﾂﾞ': 'ヅ', 'ﾃﾞ': 'デ', 'ﾄﾞ': 'ド',
		        'ﾊﾞ': 'バ', 'ﾋﾞ': 'ビ', 'ﾌﾞ': 'ブ', 'ﾍﾞ': 'ベ', 'ﾎﾞ': 'ボ',
		        'ﾊﾟ': 'パ', 'ﾋﾟ': 'ピ', 'ﾌﾟ': 'プ', 'ﾍﾟ': 'ペ', 'ﾎﾟ': 'ポ',
		        'ｳﾞ': 'ヴ', 'ﾜﾞ': 'ヷ', 'ｦﾞ': 'ヺ',
		        'ｱ': 'ア', 'ｲ': 'イ', 'ｳ': 'ウ', 'ｴ': 'エ', 'ｵ': 'オ',
		        'ｶ': 'カ', 'ｷ': 'キ', 'ｸ': 'ク', 'ｹ': 'ケ', 'ｺ': 'コ',
		        'ｻ': 'サ', 'ｼ': 'シ', 'ｽ': 'ス', 'ｾ': 'セ', 'ｿ': 'ソ',
		        'ﾀ': 'タ', 'ﾁ': 'チ', 'ﾂ': 'ツ', 'ﾃ': 'テ', 'ﾄ': 'ト',
		        'ﾅ': 'ナ', 'ﾆ': 'ニ', 'ﾇ': 'ヌ', 'ﾈ': 'ネ', 'ﾉ': 'ノ',
		        'ﾊ': 'ハ', 'ﾋ': 'ヒ', 'ﾌ': 'フ', 'ﾍ': 'ヘ', 'ﾎ': 'ホ',
		        'ﾏ': 'マ', 'ﾐ': 'ミ', 'ﾑ': 'ム', 'ﾒ': 'メ', 'ﾓ': 'モ',
		        'ﾔ': 'ヤ', 'ﾕ': 'ユ', 'ﾖ': 'ヨ',
		        'ﾗ': 'ラ', 'ﾘ': 'リ', 'ﾙ': 'ル', 'ﾚ': 'レ', 'ﾛ': 'ロ',
		        'ﾜ': 'ワ', 'ｦ': 'ヲ', 'ﾝ': 'ン',
		        'ｧ': 'ァ', 'ｨ': 'ィ', 'ｩ': 'ゥ', 'ｪ': 'ェ', 'ｫ': 'ォ',
		        'ｯ': 'ッ', 'ｬ': 'ャ', 'ｭ': 'ュ', 'ｮ': 'ョ',
		        '｡': '。', '､': '、', 'ｰ': 'ー', '｢': '「', '｣': '」', '･': '・'
		    };
		    var reg = new RegExp('(' + Object.keys(kanaMap).join('|') + ')', 'g');
		    return str
		            .replace(reg, function (match) {
		                return kanaMap[match];
		            })
		            .replace(/ﾞ/g, '゛')
		            .replace(/ﾟ/g, '゜');
	}

	/**
	 * ひらがなをカタカナに
	 * ※moji.js利用を推奨
	 */
	function hira2kana(str, opt) {
	    str = str
        .replace(/[ぁ-ゔ]/g, function (s) {
            return String.fromCharCode(s.charCodeAt(0) + 0x60);
        })
        .replace(/ﾞ/g, '゛')
        .replace(/ﾟ/g, '゜')
        .replace(/(ウ゛)/g, 'ヴ')
        .replace(/(ワ゛)/g, 'ヷ')
        .replace(/(ヰ゛)/g, 'ヸ')
        .replace(/(ヱ゛)/g, 'ヹ')
        .replace(/(ヲ゛)/g, 'ヺ')
        .replace(/(ゝ゛)/g, 'ヾ')
        .replace(/ゝ/g, 'ヽ')
        .replace(/ゞ/g, 'ヾ');
	    if (opt !== false) {
	    	str = str.replace(/ゕ/g, 'ヵ').replace(/ゖ/g, 'ヶ');
	    }
	    return str;
	}

	/**
	 * 半角英数字を全角に
	 * ※moji.js利用を推奨
	 */
	function han2zen(str) {
		return str.replace(/[A-Za-z0-9]/g, function(s) {
			return String.fromCharCode(s.charCodeAt(0) + 0xFEE0);
		});
	}

	/**
	 * 全角英数字を半角に
	 * ※moji.js利用を推奨
	 */
	function zen2han(str) {
		return str.replace(/[Ａ-Ｚａ-ｚ０-９]/g, function(s) {
			return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
		});
	}

	/**
	 * 英数字は全て半角に
	 * 日本語ひらがな、カタカナは全て全角カタカナに
	 * 漢字はそのまま
	 */
	function normalize(str) {
		return zen2han(hira2kana(hanKana2zenKana(str)));
	}

	function round(num, scale) {
		var digit = Math.pow(10, scale || 0);
		return Math.round(num * digit) / digit;
	}

	/**
	 * 指定範囲内の乱数発生
	 * 精度に関しては適当
	 */
	function rand (min, max) {
		return Math.floor(Math.random() * (max + 1 - min) + min);
	};

	function toDate(val) {
		if(_.isDate(val)) {
			return val;
		}
		if(_.isString(val)) {
			var retVal = parseDate(val);
			return retVal.toString() != 'Invalid Date' ? retVal : null;
		}
		if(_.isNumber(val)) {
			var retVal = new Date(val);
			return retVal.toString() != 'Invalid Date' ? retVal : null;
		}
		return null;
	}

	/**
	 * 日付をフォーマットする
	 * @param  {Date}   date     日付
	 * @param  {String} [format] フォーマット
	 * @return {String}          フォーマット済み日付
	 */
	function formatDate(date, format) {
		if(!date) {
			return null;
		}
		if (!_.isDate(date)) {
			//console.log('date型以外が入力されました。', date);
			date = toDate(date);
			if (date == null) {
				return null;
			}
		}
		if (date.toString() == 'Invalid Date') {
			return null;
		}
	  format = format || 'yyyy-MM-dd hh:mm:ss.SSS';
	  format = format.replace(/yyyy/g, date.getFullYear());
	  format = format.replace(/yy/g, String(date.getFullYear()).slice(-2));
	  format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
	  format = format.replace(/dd/g, ('0' + date.getDate()).slice(-2));
	  format = format.replace(/hh/g, ('0' + date.getHours()).slice(-2));
	  format = format.replace(/mm/g, ('0' + date.getMinutes()).slice(-2));
	  format = format.replace(/ss/g, ('0' + date.getSeconds()).slice(-2));
	  if (format.match(/S/g)) {
	    var milliSeconds = ('00' + date.getMilliseconds()).slice(-3);
	    var length = format.match(/S/g).length;
	    for (var i = 0; i < length; i++) {
	    	format = format.replace(/S/, milliSeconds.substring(i, i + 1));
	    }
	  }
	  return format;
	}

	function parseDate(val) {
		// 要見直し
		// 時分秒考慮
		if(!val) {
			return;
		}
		var retVal = new Date();
		var newVal = val.replace(/\/|\-/g, '');
		var year = newVal.length >= 4 ? new Number(newVal.substring(0, 4)) : null;
		var month = newVal.length >= 6 ? new Number(newVal.substring(4, 6)) - 1 : 0;
		var date = newVal.length >= 8 ? new Number(newVal.substring(6, 8)) : 1;
		retVal.setFullYear(year, month, date);
		if (!retVal.getFullYear() || retVal.toString() === 'Invalid Date') {
			return null;
		}
		return retVal;
	}

	function addDate(date, val, mode, safeMode) {
		// monthで加減算した場合、単純に月に対して増減させる為、存在しない日付になる事がある
		// その場合jsのDate型は溢れた分の日付を勝手に計算する
		// addDate(new Date('2015-10-31'), -1, 'month') -> 2015-09-31 -> 2015-10-01
		// 9/31は存在しないので、この場合、2015-10-01となる（9月は30日までなので、9/30の1日後である10/1になる）
		// 2015-09-40 -> 2015-10-10 といった事も出来る

		// 20160914 セーフモードを実装
		// セーフモード＝月及び年の加減算の時、溢れた日付による再計算を防ぐモード
		// addDate(new Date('2015-10-31'), -1, 'month')  -> 2015/09/30 となる
		// セーフモードはデフォルトでon
		var retDate = new Date(date.getTime());
		if (safeMode === undefined) {
			safeMode = true;
		}
		switch(mode || 'day') {
			case 'day':
				retDate.setDate(retDate.getDate() + val);
				break;
			case 'month':
				if (safeMode) {
					var toMonth = (retDate.getMonth() + val) % 12;
					if (toMonth < 0) {
						toMonth += 12;
					}
					retDate.setMonth(retDate.getMonth() + val);
					while (toMonth != retDate.getMonth()) {
						retDate = addDate(retDate, -1);
					}
				}
				else {
					retDate.setMonth(retDate.getMonth() + val);
				}
				break;
			case 'year':
				if (safeMode) {
					retDate = addDate(retDate, + (val * 12), 'month', true);
				}
				else {
					retDate.setFullYear(retDate.getFullYear() + val);
				}
				break;
		}
		return retDate;
	}

	function getStartOfMonth(date) {
		var retDate = new Date(date.getTime());
		retDate.setDate(1);
		return retDate;
	}

	function getEndOfMonth(date) {
		var retDate = new Date(date.getTime());
		retDate.setDate(1);
		retDate.setMonth(retDate.getMonth() + 1);
		retDate.setDate(retDate.getDate() - 1);
		return retDate;
	}

	function equalsDate(val1, val2, format) {
		format = format || 'yyyyMMdd';
		return formatDate(toDate(val1), format) == formatDate(toDate(val2), format);
	}

	// TODO:もう少し練りこむ
	function createRequestData(inputData, map) {
		map = map || {};
		var createData = {};
		if (angular.isArray(inputData)) {
			createData = [];
		}
		for (var propName in inputData) {
			if (angular.isObject(inputData[propName]) && !angular.isDate(inputData[propName])) {
				createData[propName] = createRequestData(inputData[propName], map);
				continue;
			}
			var converter = map[propName] || function(value){
				if (angular.isDate(value)) {
					return NisUtil.formatDate(value, "yyyy-MM-dd");
				}
				else {
					return value;
				}
			};
			if (typeof converter === 'string') {
				switch(converter) {
					case 'yyyyMM':
						converter = function(value){
							return NisUtil.formatDate(value, "yyyyMM");
						};
						break;
					case 'yyyyMMdd':
						converter = function(value){
							return NisUtil.formatDate(value, "yyyyMMdd");
						};
						break;
				}
			}
			createData[propName] = converter(inputData[propName]);
		}
		return createData;
	}

	// 同名のプロパティだけをコピー
	// 浅いコピーなので注意
	function copyProperties(to, from, properties) {
		if (!to || !from) {
			return;
		}
		properties = properties || _.keys(from);
		_.each(properties, function(property){
			if (_.isString(property)) {
				if (!_.isUndefined(to[property]) && !_.isUndefined(from[property])) {
					to[property] = from[property];
				}
			}
			else if (_.isArray(property)) {
				if (!_.isUndefined(to[property[0]]) && !_.isUndefined(from[property[1]])) {
					to[property[0]] = from[property[1]];
				}
			}
			else if (_.isObject(property)) {
				if (!_.isUndefined(to[property['to']]) && !_.isUndefined(from[property['from']])) {
					to[property['to']] = from[property['from']];
				}
			}
		});
	}

	function createContainer() {
		return (function(){
    		var container = {};
    		var put = function(key, value){
    			container[key] = value;
    		},
    		get = function(key) {
    			return container[key];
    		},
    		clear = function() {
    			container = {};
    		},
    		containKey = function(key) {
    			return Boolean(container[key]);
    		}
    		return {
    			put : put,
    			get : get,
    			clear : clear,
    			containKey : containKey
    		};
    	})();
	}

	function getProperty(obj, name) {
		if (!obj) {
			return undefined;
		}
		if (_.isString(name)) {
			return getProperty(obj, name.split('.'));
		}
		if (_.isArray(name)) {
			if (name.length > 1) {
				return getProperty(obj[name[0]], _.rest(name, 1));
			}
			return obj[name[0]];
		}
	}

	function setProperty(obj, name, value) {
		if (_.isString(name)) {
			setProperty(obj, name.split('.'), value);
		}
		if (_.isArray(name)) {
			if (name.length > 1) {
				if(!obj[name[0]]) {
					obj[name[0]] = {};
				}
				setProperty(obj[name[0]], _rest(name, 1), value);
			}
			else {
				obj[name[0]] = value;
			}
		}
	}

	function checkFormValid(form) {
		if (form.$invalid) {
		    angular.forEach(form.$error, function (field) {
		        angular.forEach(field, function(errorField){
		        	// TODO:innerFormである事を判別する条件はこれでいいだろうか
		        	if (angular.isDefined(errorField.$submitted)) {
		        		checkFormValid(errorField);
		        		return;
		        	}
		            errorField.$setTouched();
		        })
		    });
		    return false;
		}
		return true;
	}

	function concat(array1, array2) {
		if (!(_.isArray(array1) && _.isArray(array2))) {
			return;
		}
		array1.push.apply(array1, array2);
	}

	function sum(array, propertyName) {
		return _.reduce(_.pluck(array, propertyName), function(sum, val){
			return sum + (Number(val) || 0);
		}, 0) || 0;
	}

	// TODO:serviceとしてutilサービスを作って、そこで依存性を解決させるべきか
	function wrapPromise($q, check, func){
		var deferred = $q.defer();
		if (!check) {
			deferred.resolve(true);
			return deferred.promise;
		}
		func().then(function(data){
			deferred.resolve(true);
		}, function(){
			deferred.reject();
		});
		return deferred.promise;
	};

	function compare(val1, val2) {
		var LEFT = 1,
			RIGHT = -1,
			EQUAL = 0;
		if (!val1 && !val2) {
			return EQUAL;
		}
		if (!val1) {
			return RIGHT;
		}
		if (!val2) {
			return LEFT;
		}
		// 必要ならばタイプ毎のコンペア
		if (val1 === val2) {
			return EQUAL;
		}
		if (val1 > val2) {
			return LEFT;
		}
		if (val1 < val2) {
			return RIGHT;
		}
	}

	// シングルクォートを前後追加する
	function sq(val) {
	    return '\'' + val + '\'';
	}
	function nullToBlank(val) {
	    return(val == null ? '' : val);
	}
	function toBlank(val) {
	    if(val === undefined) {
	        return '';
	    }
	    return(val == null ? '' : val);
	}
	function hasValue(val) {
	    if(val === undefined) {
	        return false;
	    }
	    if(val == null || val == '') {
	        return false;
	    }
	    return true;
	}
	function getMessage(template, replacement) {
	    if(typeof replacement != "object") {
	        replacement = Array.prototype.slice.call(arguments, 1);
	    }
	    return template.replace(/\{(.+?)\}/g, function (m, c) {
	        return(replacement[c] != null) ? replacement[c] : m
	    });
	}
	
	function preventBackspace($document){
		if($document){
			$document.on('keydown', function(e){
			    if(e.which === 8 && ( e.target.nodeName !== "INPUT" && e.target.nodeName !== "SELECT" ) ){ 
			      console.log("input type="+e.target.type+" Backspaceが押されました。！");
			      e.preventDefault();
			    }
			    else{
			    	if(e.target.type==='radio' || e.target.type==='checkbox' || e.target.type==='select-one'){
			    		console.log("input type="+e.target.type+" Backspaceが押されました。！");
			    		e.preventDefault();
			    	}
			    }
			});
		}
	}
	
	function createKeyPath(data) {
		if (!angular.isArray(data)) {
			data = _.toArray(arguments);
		}
		return data.join('/');
	}
	
	function nvl(val,val1){
		if(hasValue(val)){
			return val;
		}
		if(val1 === undefined) {
	        return '';
	    }
	    if(val1 == null || val1 == '') {
	    	return '';
	    }
		return val1;
	}
	
	// 公開関数
	return {
		checkKeyDownExKey : checkKeyDownExKey
		,checkFilterKeyCode : checkFilterKeyCode
		,testFilterKeyCode : testFilterKeyCode
		,getByteLength : getByteLength
		,isTrue : isTrue
		,isFalse : isFalse
		,toDate : toDate
		,formatDate : formatDate
		,parseDate : parseDate
		,addDate : addDate
		,getStartOfMonth : getStartOfMonth
		,getEndOfMonth : getEndOfMonth
		,equalsDate : equalsDate
		,createRequestData : createRequestData
		,copyProperties : copyProperties
		,createContainer : createContainer
		,getProperty : getProperty
		,setProperty : setProperty
		,checkFormValid : checkFormValid
		,concat : concat
		,sum : sum
		// ここから↑はタイプ毎に分ける前のもの 中身は同じ いずれ撤去予定
		,date: {
			toDate : toDate
			,formatDate : formatDate
			,format : formatDate
			,parseDate : parseDate
			,parse : parseDate
			,addDate : addDate
			,getStartOfMonth : getStartOfMonth
			,getEndOfMonth : getEndOfMonth
			,equalsDate : equalsDate
			,equals : equalsDate
		}
		,bool: {
			isTrue : isTrue
			,isFalse : isFalse
		}
		,string: {
			checkKeyDownExKey : checkKeyDownExKey
			,checkFilterKeyCode : checkFilterKeyCode
			,testFilterKeyCode : testFilterKeyCode
			,getByteLength : getByteLength
			,left : left
			,leftPad : leftPad
			,hanKana2zenKana : hanKana2zenKana
			,hira2kana : hira2kana
			,han2zen : han2zen
			,zen2han : zen2han
			,normalize : normalize
		}
		,number: {
			round : round,
			rand : rand
		}
		,object: {
			compare : compare
		}
		,request: {
			createRequestData : createRequestData,
			createKeyPath : createKeyPath
		}
		,property: {
			copyProperties : copyProperties
			,getProperty : getProperty
			,setProperty : setProperty
		}
		,form: {
			checkFormValid : checkFormValid
		}
		,container: {
			createContainer : createContainer
		}
		,array: {
			concat : concat
			,sum : sum
		}
		,angular: {
			wrapPromise : wrapPromise
		}
		
		// 2017/02/01 サイ　追加----------------------------------------------------------------↓
		, sq: sq
	    , nullToBlank: nullToBlank
	    , toBlank: toBlank
	    , hasValue: hasValue
	    , getMessage: getMessage
	    , registerPreventBackspace: preventBackspace
	    , nvl: nvl
	    
	}
})();