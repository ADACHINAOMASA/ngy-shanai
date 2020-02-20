/**
 * BackSpace無効化
 *
 */
window.document.onkeydown = function() {
	if (event.keyCode == 8) {
		if (event.srcElement.type == 'text')
			return true;
		if (event.srcElement.type == 'password')
			return true;
		if (event.srcElement.type == 'file')
			return true;
		if (event.srcElement.type == 'textarea')
			return true;
		event.keyCode = 0;
		event.returnValue = false;
		return false;
	}
}

/**
 * ヘルプ無効化
 *
 */
window.document.onhelp = function() {
	return false;
}

/**
 * イベントのキーコードを返す(各種ブラウザ対応版)
 *
 */
function getKeyCode(e) {
	if (document.all)
		return event.keyCode;
	else if (document.getElementById)
		return e.keyCode || e.charCode;
	else if (document.layers)
		return e.which;
}

/**
 * 現在行のラジオボタン以外をクリアする
 *
 */
function selectOne(form, button) {
	turnOffRadioForForm(form);
	common_timeoutSubmitForm(form, button);
	button.checked = true;
}

function turnOffRadioForForm(form) {
	for (i = 0; i < form.elements.length; i++) {
		form.elements[i].checked = false;
	}
}

/**
 * 確認画面を表示させずにウィンドウを閉じる
 *
 */
function windowClose() {
	window.opener = window;
	var win = window.open(location.href, "_self");
	win.close();
}

/**
 * 時計表示
 *
 */
var clockDateObj = null;
var clockTimeObj = null;

function timerDisplay(dateId, timeId) {

	var time = new Date();
	var dateObj = null;
	var timeObj = null;

	if (dateId != null)
		dateObj = document.getElementById(dateId);
	if (timeId != null)
		timeObj = document.getElementById(timeId);

	var yy = time.getYear();
	if (yy < 2000)
		yy = yy + 1900;
	var mm = time.getMonth() + 1;
	if (mm < 10)
		mm = "0" + mm;
	var dd = time.getDate();
	if (dd < 10)
		dd = "0" + dd;
	youbi = [ "日", "月", "火", "水", "木", "金", "土" ][time.getDay()];
	var hh = time.getHours();
	if (hh < 10)
		hh = "0" + hh;
	var nn = time.getMinutes();
	if (nn < 10)
		nn = "0" + nn;
	var ss = time.getSeconds();
	if (ss < 10)
		ss = "0" + ss;

	if (dateObj == null)
		dateObj = clockDateObj;
	if (timeObj == null)
		timeObj = clockTimeObj;
	if (dateObj != null)
		dateObj.innerHTML = yy + "年" + mm + "月" + dd + "日(" + youbi + ")";
	if (timeObj != null)
		timeObj.innerHTML = hh + "時" + nn + "分" + ss + "秒";

	clockDateObj = dateObj;
	clockTimeObj = timeObj;
	window.setTimeout("timerDisplay();", 1000);
}

/**
 * テキスト全選択
 *
 */
function InputTextSelect(obj) {
	if (obj) {
		if (obj.style.visibility == "" || obj.style.visibility == "visible") {
			obj.focus(); // テキストをフォーカス
			obj.select(); // テキストを全選択
			flagSelect = "ON";
		}
	}
}

/**
 * focusIdに指定した要素IDにフォーカスを設定する [使用用途] 入力チェック後にエラー対象にフォーカスを設定するため
 *
 */
function setDefaultFocus() {
	if (document.getElementById("form1:focusId")) {
		fldName = document.getElementById("form1:focusId").value;
		if (fldName != "") {
			if (document.getElementById(fldName)) {
				flgVisible = document.getElementById(fldName).style.visibility;
				flgDisabled = document.getElementById(fldName).disabled;
				if ((flgVisible == "" || flgVisible == "visible")
						&& !flgDisabled) {
					document.getElementById(fldName).focus(); // テキストをフォーカス
					document.getElementById(fldName).select(); // テキストを全選択
				}
			}
		}
	}
}

/**
 * 大文字変換（IE）
 *
 */
function doUpperIE(e) {
	var code = e.keyCode;
	if ((code >= 97) && (code <= 122)) {
		e.keyCode = code - 32;
	}
	return true;

}

/**
 * 大文字変換（IE以外）
 *
 */
function doUpperOther(e) {
	if (document.formedit.wsname.value != null) {
		var str = document.formedit.wsname.value;
		if (navigator.appName != "Microsoft Internet Exproler")
			document.formedit.wsname.value = str.toUpperCase();
	}
}

/**
 * autofocus関数のユーザー選択行為判別フラグ 初期値とクリック時は選択行為を行ったとして次項目遷移を中止
 *
 */
var flagSelect = "ON";

/**
 * 指定要素の値の文字数が指定文字数（limit）と一致した場合、 次の指定要素（next）へフォーカスを移動して選択状態にする
 *
 */
function autofocus(el, limit, next, evt) {
	evt = (evt) ? evt : event;
	key = getKeyCode(evt);
	if (key == 13) {
		flagSelect = "ON";
		return true;
	}

	var charCode = (evt.charCode) ? evt.charCode : ((evt.keyCode) ? evt.keyCode
			: ((evt.which) ? evt.which : 0));
	if (charCode > 31 && el.value.length == limit && flagSelect == "OFF") {
		if (document.getElementById(next).disabled) {
			next = document.getElementById('form1:focusId').value;
		}
		if (next != "") {
			document.getElementById(next).focus();
			document.getElementById(next).select();
		}
	}
	flagSelect = "OFF";
}
function blockEnter(e){
	evt = e || window.event;
	  if(evt.target) tname = evt.target.id;
	  if(evt.srcElement) tname = evt.srcElement.id;

	  key = getKeyCode(e);
	  if(key != 13) return true;

	  //ボタン押下の場合はそのボタンのonclickイベントを実行する
	  if(evt.target.type == "button"){
		  var clickMe = document.getElementById(tname);
		  clickMe.click();
		  return false;
	  }

	  var flag = false;
	  var firstel = null;

	  for (var i=0; i < document.forms.length; i++) {
	    var els = document.forms[i].elements;
	    for (var j=0; j < els.length; j++) {
	      var el = els[j];
	      if((firstel == null) && ((el.type == "text") || (el.type == "password")) && el.style.visibility != "hidden" && !el.disabled) firstel = el;
	      if((flag) && ((el.type == "text") || (el.type == "password")) && el.style.visibility != "hidden" && !el.disabled) {
	        document.forms[i].elements[j].focus();
	        document.forms[i].elements[j].select();
	        return false;
	      }
	      if(((el.type == "text") || (el.type == "password")) && (tname == el.id)) flag = true;
	    }
	  }
	  if(!firstel.disabled){
	      firstel.focus();
	      firstel.select();
	  }
	  return false;
	}
function blockEnterSetsubiYotei(e){
	evt = e || window.event;

	  if(evt.target) tname = evt.target.id;
	  if(evt.srcElement) tname = evt.srcElement.id;

	  key = getKeyCode(e);
	  if(key != 13) return true;

	  //ボタン押下の場合はそのボタンのonclickイベントを実行する
	  if(evt.target.type == "button"){
		  var clickMe = document.getElementById(tname);
		  clickMe.click();
		  return false;
	  }

	  var flag = false;
	  var firstel = null;

	  var kbnFlg = "2";
	  if (tname == "input_min") {
		  var kbnFlg = "1";
	  }

	  for (var i=0; i < document.forms.length; i++) {
	    var els = document.forms[i].elements;
	    for (var j=0; j < els.length; j++) {
	      var el = els[j];
	      if((firstel == null) && ((el.type == "text") || (el.type == "password")) && el.style.visibility != "hidden" && !el.disabled) firstel = el;
	      if((flag) && ((el.type == "text") || (el.type == "password")) && el.style.visibility != "hidden" && !el.disabled && kbnFlg != "1") {
	        document.forms[i].elements[j].focus();
	        document.forms[i].elements[j].select();
	        return false;
	      }
	      if(((el.type == "text") || (el.type == "password")) && (tname == el.id)) flag = true;
	    }
	  }
	  if(kbnFlg = "2"){
		  document.getElementById("input_dd").focus();
	      document.getElementById("input_dd").select();
	  } else {
		  document.getElementById("input_min").focus();
	      document.getElementById("input_min").select();
	  }
	  return false;
	}
/**
 * タイマー付リダイレクト
 *
 */
function redirTimer() {
	redirTime = "20000"; // （例）：20000ms（20秒）後に↓を開きます。
	redirURL = "http://www.nikkeikin.co.jp/"; // 遷移先ﾍﾟｰｼﾞのURL
	self.setTimeout("self.location.href = redirURL;", redirTime);
}

/**
 * 指定要素（ID）の表示状態を逆にする
 *
 */
function blinkElement(id) {
	var el = document.getElementById(id);
	if (el.style.visibility == "visible") {
		el.style.visibility = "hidden";
	} else {
		el.style.visibility = "visible";
	}
}

/**
 * 数字以外の入力された文字のみ削除
 *
 */
function Num_Comp(inStr) {
	var strMatch = inStr.match(/[0-9]/g);
	var rtnMatch = "";
	try {
		for (i = 0; i < strMatch.length; i++) {
			rtnMatch = rtnMatch + strMatch[i];
		}
	} catch (e) {
	}
	return rtnMatch;
}

/**
 * ウィンドウ内側高さを返す(各種ブラウザ対応版) bodyに以下のタグを追加 <div id="IEhackInnerHeight"
 * style="position:absolute;top:100%;left:100px;z-index:1;visibility:hidden;"></div>
 *
 */
function getInnerHeight() {
	// IE innerHeight-HACK by Roman Dissertori (Prototype - but it works)
	if (document.getElementById('IEhackInnerHeight')) {
		var AdaptedHeight = document.getElementById('IEhackInnerHeight').offsetTop - 30;
		document.getElementById('IEhackInnerHeight').style.top = AdaptedHeight
				+ 'px';
		// if browser knows innerHeight
		if (typeof innerHeight == 'number') {
			return window.innerHeight - 20;
		} else {
			// if browser (for example IE) does not know innerHeight
			return document.getElementById('IEhackInnerHeight').offsetTop;
		}
	}
	return screen.availHeight;
}

/**
 * ウィンドウリサイズ(各種ブラウザ対応版)
 *
 */
function windowResize(width, height) {
	if (window.outerWidth) {
		window.outerWidth = width;
		window.outerHeight = height;
	} else {
		window.resizeTo(width, height);
	}
}

/**
 * JSFで生成されたTableにスクロールバーを追加する [事前設定] JSPで <body onload="scrollbarTable(elID, tableHeight)"/> と指定 elID : 要素ID ableHeight : テーブル高さ
 *
 */
function scrollbarTable(elID, tableHeight) {
	if (document.getElementsByTagName('BODY').length == 0)
		document.write('<body>');// ←ダミーのbodyタグを出力
	var div1 = document.createElement('DIV');
	div1.setAttribute('id', 'layer');
	// 元テーブルの位置（top,left）の取得
	div1.style.cssText = "top:" + document.getElementById(elID).style.top + ";"
			+ "left:" + document.getElementById(elID).style.left + ";"
			+ "position:absolute;border:0px;";
	document.body.appendChild(div1);
	// IEの場合、TABLEタグのinnerHTMLへ値が設定できないため、<table>～を直接追加 FireFoxなら可能
	div1.innerHTML = "<table id='header' style='margin:0px;' rules='all' border='0'>"
			+ document.getElementById(elID).childNodes.item(0).outerHTML
			+ "</table>";
	var div2 = document.createElement('DIV');
	div2.setAttribute('id', 'region');
	div2.style.cssText = "border:0px;overflow-x:hidden; overflow-y:auto; overflow:auto;width:860px;";
	// IEの場合、TABLEタグのinnerHTMLへ値が設定できないため、<table>～を直接追加 FireFoxなら可能
	div2.innerHTML = "<table style='' rules='all' border='0'><tbody id='data' border='0'>"
			+ document.getElementById(elID).childNodes.item(1).innerHTML
			+ "</tbody></table>";
	div1.appendChild(div2);
	// テーブル列のスタイル調整
	var data = document.getElementById("data");
	var header = document.getElementById("header");
	for (var i = 0; i < data.rows.length; i++) {
		for (var j = 0; j < data.rows[i].cells.length; j++) {
			strStyle = header.rows[0].cells[j].firstChild.style.cssText;
			// 幅（width）の調整
			var wkWidth = getStyleValue(header.rows[0].cells[j].firstChild.style.cssText, "width:", "px");
			if (wkWidth == "")
				wkWidth = 50;
			header.rows[0].cells[j].width = wkWidth;
			data.rows[i].cells[j].width = wkWidth;
			// 文字揃え（text-align）の調整
			var wkAlign = getStyleValue(data.rows[i].cells[j].firstChild.style.cssText, "text-align:", "");
			if (wkAlign == "")
				wkAlign = "center";
			data.rows[i].cells[j].align = wkAlign;
		}
	}

	// 高さ指定がない場合は、innerHeightを設定
	if (tableHeight == null) {
		// 19pxは行高さ+調整5px
		if (((data.rows.length * 17 + 2) > getInnerHeight())
				|| data.rows.length == 0) {
			if (getInnerHeight() > 10) {
				tableHeight = getInnerHeight();
			} else {
				// 最低限表示に必要な高さ
				tableHeight = 17;
			}
		} else {
			// 19pxは行高さ+調整5px
			tableHeight = data.rows.length * 17 + 2;
		}
	}

	// 検索結果０件の場合、メッセージ表示
	if (data.rows.length == 0) {
		div2.innerHTML = "<table id='gridPanel' style='height: 100%; position: absolute; text-align: center; vertical-align: middle; width: 100%'><tr style='' border='0'><td id='msg' border='0'>スケジュールがありません！！！</td></tr></table>";
	}

	// レイヤーの幅をテーブルに合わせる
	with (document.getElementById("region")) {
		document.getElementById("layer").style.width = header.offsetWidth
				+ "px";
		if (data.offsetWidth == 0) {
			wkWidth = 860;
		} else {
			// 15pxはスクロールバーの幅
			wkWidth = header.offsetWidth + (offsetWidth - data.offsetWidth)
					+ 15;
		}
		style.width = wkWidth + "px"; // スクロールバーを含まない幅に修正
		style.height = tableHeight + "px"; // 指定して高さに修正
	}
	// Ｙ軸スクロールバー非表示
	// 非表示にしないとテーブル下に空白があるため、スクロールバーが無意味にでてしまう（妥協策）
	document.body.style.overflowY = "hidden";
	// 元テーブルの非表示
	document.getElementById(elID).style.visibility = "hidden";

}

/**
 * JSFで生成されたテーブルの列のスタイルを調整する JSFで指定した列のスタイルは、生成後<span>に設定されてしまうため、列に対するスタイルの指定が反映しない
 * これは、IEではブロック要素内のインライン要素の幅、文字揃えは直接設定できないためである
 * 今回はJavaScriptで強制設定して回避する（10行目以降は何故か列のスタイルが反映する）
 *
 */
function ajustTableStyle(elID) {
	var data = document.getElementById(elID).childNodes.item(1)
	var header = document.getElementById(elID).childNodes.item(0);
	for (var i = 0; i < data.rows.length; i++) {
		// if(data.rows[i].className != "row-white-black") {
		var cmax = data.rows[i].cells.length;
		if (cmax > 8)
			cmax = 9;
		for (var j = 0; j < cmax; j++) {
			// for(var j=0;j<data.rows[i].cells.length;j++){
			// 文字揃え（text-align）の調整
			if (data.rows[i].cells[j].firstChild != null) {
				var wkAlign = getStyleValue(data.rows[i].cells[j].firstChild.style.cssText, "text-align:", "");
				// j==1なのは1カラム目にチェックボックスの場合があるため
				// 設備停止のtext-alignは1～8カラム目まで必ずleftにしている
				if (j == 1 && wkAlign != "left")
					break;
				if (wkAlign == "")
					wkAlign = "center";
				data.rows[i].cells[j].align = wkAlign;
			}
			// 幅（width）の調整
			if (header.rows[0].cells[j].firstChild != null) {
				strStyle = header.rows[0].cells[j].firstChild.style.cssText;
				var wkWidth = getStyleValue(
						header.rows[0].cells[j].firstChild.style.cssText,
						"width:", "px");
				if (wkWidth == "")
					wkWidth = 50;
				header.rows[0].cells[j].width = wkWidth;
				data.rows[i].cells[j].width = wkWidth;
			}
		}
		// }
	}
}

/**
 * スタイル内容（cssText）から指定要素名（elname）[単位（unit）も指定]の値を戻す
 *
 */
function getStyleValue(cssText, elname, unit) {
	// 文字列から空白を除去
	var wkStr = delChar(cssText, " ");
	// 文字列を小文字に強制変換
	wkStr = wkStr.toLowerCase();
	// widthの位置の取得
	var sta = wkStr.indexOf(elname, 0);
	if (sta == -1) {
		return "";
	} else {
		if (unit == "")
			unit = ";";
		var end = wkStr.indexOf(unit, sta + elname.length);
		if (end == -1)
			end = cssText.length;
		return wkStr.substring(sta + elname.length, end);
	}
}

/**
 * 文字列から指定文字を削除する
 *
 */
function delChar(strValue, charValue) {
	var result = "";
	for (i = 0; i < strValue.length; i++) {
		if (strValue.charAt(i) != charValue)
			result = result + strValue.charAt(i);
	}
	return result;
}

/**
 * 指定（key）したCookieに値（value）を設定する 有効期限（days）の指定も可能
 *
 */
function WriteCookie(key, value, days) {
	var str = escape(key) + "=" + escape(value) + ";";
	if (days != 0) {
		var dt = new Date();
		dt.setDate(dt.getDate() + days);
		str += "expires=" + dt.toGMTString() + ";";
	}
	document.cookie = str;
}

/**
 * 指定（key）したCookieに値（value）を戻す
 *
 */
function ReadCookie(key) {
	var sCookie = document.cookie;
	var aData = sCookie.split(";");
	var oExp = new RegExp(" ", "g");
	key = key.replace(oExp, "");
	var i = 0;
	while (aData[i]) {
		var aWord = aData[i].split("=");
		aWord[0] = unescape(aWord[0]).replace(oExp, "");
		if (key == aWord[0])
			return unescape(aWord[1]);
		if (++i >= aData.length)
			break;
	}
	return "";
}

/**
 * 指定（key）したCookieを削除する
 *
 */
function DeleteCookie(key) {
	var dt = new Date();
	var str = key + "=;expires=" + dt.toGMTString();
	document.cookie = str;
}