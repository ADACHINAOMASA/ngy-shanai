/**
 * 確認画面を表示させずにウィンドウを閉じる
 */
function windowClose() {
  window.opener = window;
  var win = window.open(location.href,"_self");
  win.close();
}

/**
 * テキスト全選択
 */
function InputTextSelect(inputText)
{
	inputText.focus();	//テキストをフォーカス
	inputText.select();	//テキストを全選択
}

/**
 * 大文字変換
 */
function doUpperIE(e){
    // A～Z
	var code = e.keyCode;
	if((code >= 97) && (code <= 122)) e.keyCode =  code - 32;
	return true;
}

/**
 * 小文字変換
 */
function doLowerIE(e){
    // A～Z
	var code = e.keyCode;
	if((code >= 65) && (code <= 90)) e.keyCode =  code + 32;
	return true;
}

function doUpperOther(e)
{
  if (document.formedit.wsname.value != null) {
    var str = document.formedit.wsname.value;
    if(navigator.appName != "Microsoft Internet Exproler") document.formedit.wsname.value = str.toUpperCase();
  }
}

/**
 * タイマー付リダイレクト
 */
function redirTimer() {
    // （例）：20000ms（20秒）後に↓を開きます。
    redirTime = "20000";
    //　遷移先ﾍﾟｰｼﾞのURL　
    redirURL = "http://www.nikkeikin.co.jp/";
    self.setTimeout("self.location.href = redirURL;",redirTime);
}

function blinkElement(id){
  var el = document.getElementById(id);
  if ( el.style.visibility == "visible" ) {
  	el.style.visibility = "hidden";
  } else {
  	el.style.visibility = "visible";
  }
}

/**
 * イベントのキーコードを返す(各種ブラウザ対応版)
 */
function getKeyCode(e){
  if(document.all) return event.keyCode;
  else
   if(document.getElementById) return e.keyCode || e.charCode;
   else
     if(document.layers) return e.which;
}

//
// Tid スクロール機構を付加するテーブルのID
//
// tHeight テーブルの高さの指定
//
var TARGET_TABLE = "form1:tabSet1:tab1:schList1";
var TARGET_DIV   = "form1:tabSet1:tab1:groupPanel1";
//var TARGET_TABLE = "form1:dataTable1";
//var TARGET_DIV   = "form1:twrapper";

//window.onload = function( )
//{
//	expansionTable( 500, 500, 70, "1.2em", "3em" );
//	expansionTable( 800, 500, 70, "1.2em", "8em" );
//}

function getUrlParameter(){
  window.alert("けっこうがんばったっぽい?");
  var url = document.URL;
  var lk = document.getElementById("saiLinkKey");

//var url = window.location.search;
//  window.alert(url);
  window.alert(lk.value());
}

// ここがメイン ==========================================================***
function expansionTable( tWidth, tHeight, cWidth, rHeight, cellWidth )
{
	function gid( id )
	{
		return document.getElementById( id );
	}

	function gidtag( id, tag )
	{
		return gid( id ).getElementsByTagName( tag );
	}

	function ele( tag )
	{
		return document.createElement( tag );
	}

	//マウス座標取得
	function getMouseX( e )
	{
		if( window.opera )
			return e.clientX;
		else if( document.all )
			return document.body.scrollLeft + event.clientX;
		else if( document.layers || document.getElementById )
			return e.pageX;
	}
	function getMouseY( e )
	{
		if( window.opera )
			return e.clientY;
		else if( document.all )
			return document.body.scrollTop + event.clientY;
		else if( document.layers || document.getElementById )
			return e.pageY;
	}

	function is_gecko( )
	{
		return navigator.userAgent.indexOf( 'Gecko/' ) != -1;
	}

	function InsertRule( selector, property, index )
	{
		if( !index ) index = 0;
		if( is_gecko( ) )
			document.styleSheets[0].insertRule( selector + "{" + property + "}", index );
		else
			document.styleSheets[0].addRule( selector, "{" + property + "}", index );
	}

	//ドラッグ終了
	function setDragPosition( e ) {
		lastSX	= gid( "tbody" ).scrollLeft;
		lastSY	= gid( "tbody" ).scrollTop;
		lastMX	= getMouseX( e );
		lastMY	= getMouseY( e );
	}

	//tableをdivに格納
	var table		= gid( TARGET_TABLE );
	var twrapper	= gid( TARGET_DIV );
	var tcontainer	= ele( "div" );
	tcontainer.id	= "tcontainer";
	var tbody		= ele( "div" );
	tbody.id		= "tbody";

	twrapper.appendChild( tcontainer );
	tcontainer.appendChild( tbody );
	tbody.appendChild( table );

	with( tbody.style ) {
		if( is_gecko( ) ) {
			overflow	= "scroll";
//			cssFloat	= "right";
			cssFloat	= "left";
		}
		else {
			overflowX	= "scroll";
			overflowY	= "scroll";
			overflow	= "auto";
//			styleFloat	= "right";
			styleFloat	= "left";
		}
		width	= tWidth + "px";
		height	= tHeight + "px";
	}

  // 左サイドの固定列
	var tside		= ele( "div" );
        //var tside		= ele( "span" );
	tside.id		= "tside";
	var tsidetable          = ele( "table" );
	tsidetable.id           = "tsidetable";
	var tsthead		= ele( "thead" );
	tsthead.id		= "tsthead";

	tcontainer.insertBefore( tside, tcontainer.firstChild );
	tside.appendChild( tsidetable );
	tsidetable.appendChild( tsthead );

  // 左タイトルを取り出しセットする
        //alert("tr start");
	var tr		= gidtag( TARGET_TABLE, "tr" );
        //alert("tr end");
	var buffer	= "";
	for( var i = 1; i < tr.length; i++ ) {
		var intr = ele( "tr" );
		tside.firstChild.firstChild.appendChild( intr );

//		intr.appendChild( tr[i].firstChild );
		for( var j = 0; j < tr[i].childNodes.length; j++) {
			var td = tr[i].childNodes.item(j);
			if(td.nodeName == "TD") {
				intr.appendChild( td );
				break;
			}
		}
	}
	with( tside.style ) {
		if( is_gecko( ) ) {
			overflow	= "hidden";
			cssFloat	= "left";
		}
		else {
			overflowX	= "hidden";
			overflowY	= "hidden";
			overflow	= "auto";
			styleFloat	= "left";
		}
		width		= cWidth + "px";
		height		= tbody.clientHeight + "px";
	}

  // ヘッダ部
	var thead		= ele( "div" );
	thead.id		= "thead";
	var theadtable	= ele( "table" );
	theadtable.id	= "theadtable";

	twrapper.insertBefore( thead, twrapper.firstChild );
	thead.appendChild( theadtable );
	theadtable.appendChild( table.childNodes[is_gecko()?1:0] );

	with( gid( "thead" ).style ) {
		if( is_gecko( ) ) {
			overflow	= "hidden";
		}
		else {
			overflowX	= "hidden";
			overflowY	= "hidden";
			overflow	= "auto";
		}
		height		= rHeight;
		marginLeft	= cWidth + "px";
		width		= tbody.clientWidth + "px";
	}

	thead.firstChild.firstChild.childNodes[is_gecko()?1:0].childNodes[0].style.display = "none";

	if( is_gecko( ) ) {
		table.style.width		= ( cWidth * ( tr.length - 1 ) ) + "px";
		theadtable.style.width	= ( cWidth * ( tr.length - 1 ) ) + "px";
	}

	twrapper.style.width = ( tWidth + cWidth ) + "px";

	InsertRule( "table", "word-break:keep-all;table-layout:fixed;" );
	InsertRule( "#tbody td", "text-align:center;width:" + cellWidth );
	InsertRule( "#thead th", "text-align:center;width:" + cellWidth );
	InsertRule( "#tside td", "text-align:center;width:" + cWidth + "px" );

	tbody.onmousedown	= function(e) {
		var mx = getMouseX( e ) - gid( "tside" ).offsetWidth;
		var my = getMouseY( e ) - gid( "thead" ).offsetHeight;
		if( ( mx >= gid( "tbody" ).clientWidth && mx <= gid( "tbody" ).offsetWidth ) ||
			( my >= gid( "tbody" ).clientHeight && my <= gid( "tbody" ).offsetHeight ) ) {
			drag = false;
			return false;
		}
		if( drag ) {
			drag = false;
		}
		else {
			setDragPosition( e );
			drag = true;
		}
		return false;
        };

	tbody.onmouseup		= function() {
		drag = false;
        };

	tbody.onmousemove	= function( e ) {
		var mx = getMouseX( e ) - gid( "tside" ).offsetWidth;
		var my = getMouseY( e ) - gid( "thead" ).offsetHeight;
		if( ( mx >= gid( "tbody" ).clientWidth && mx <= gid( "tbody" ).offsetWidth ) ||
			( my >= gid( "tbody" ).clientHeight && my <= gid( "tbody" ).offsetHeight ) ) {
			drag = false;
			return false;
		}
		if( drag ) {
			var X = getMouseX( e );
			var Y = getMouseY( e );

			gid( "thead" ).scrollLeft	= lastSX - ( X - lastMX ) * moveRate;
			gid( "tbody" ).scrollLeft	= lastSX - ( X - lastMX ) * moveRate;
			gid( "tside" ).scrollTop	= lastSY - ( Y - lastMY ) * moveRate;
			gid( "tbody" ).scrollTop	= lastSY - ( Y - lastMY ) * moveRate;

			setDragPosition( e );
		}
        }

	tbody.onscroll		= function() {
		gid( "thead" ).scrollLeft	= gid( "tbody" ).scrollLeft;
		gid( "tside" ).scrollTop	= gid( "tbody" ).scrollTop;
        };
}

var moveRate		= 3.0;
var drag		= false;
var lastSX		= 0;
var lastSY		= 0;
var lastMX		= 0;
var lastMY		= 0;