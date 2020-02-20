// commonAfterForm.jsのグローバル変数設定 //
// ボタンを押した時、その色を一定時間変更する
var globalFormButtonAction = true;
var globalFormButtonActionColor = "#404040";  //ボタン色
// エンターキーの押下で改行を行う(FORM送信は行わない)
var globalBlockEnterKey = true;
// フォームの二重送信を防ぐ(２回目以降の送信を阻む)
var globalFormSubmitLock = true;

/**
 * 固定文字表示
 */
function infoMessageProcessSet() {
  document.getElementById("form1:message").style.color="white";
  document.getElementById("form1:message").innerHTML="処理中です...しばらくお待ち下さい。";
  sobj = document.getElementById("form1:completionMessage");
  if (sobj != null) sobj.innerHTML="";
  setInterval("blinkElement('form1:message')",500);
}

/**
 * チェックボックスのチェック数表示
 */
var counter = 0;
function countCheckbox(list_checkbox) {
    if (list_checkbox.checked)
    {
        //counter = parseInt(document.getElementById("form1:message").value) + counter++;
        counter++;
    } else {
        //counter = parseInt(document.getElementById("form1:message").value) + counter--;
        counter--;
    }
    document.getElementById("form1:message").innerHTML=counter.toString(10);
}

// コンボボックスの値をテキストボックスに設定
function setComboValue(x) {
	id = x.name.split("_");
	var el = document.getElementById(id[0]);
        if((x.value == null) || (x.value == "" )) {
            el.value = "";
        } else {
            if(el.value.indexOf(x.value) == -1) {
                    if((el.value != null) && (el.value != "")) el.value = el.value + " ";
                    el.value = el.value + x.value;
            }
        }
}

// コンボボックスの値をテキストボックス（A～B）に設定
function setComboBetweenValue(x) {
	id = x.name.split("_");
        //alert(id[0]+"1");
        var el = document.getElementById(id[0]+"1");
        if((x.value == null) || (x.value == "" )) {
            el.value = "";
            var el = document.getElementById(id[0]+"2");
            el.value = "";
        } else {
            if((el.value != null) && (el.value != "")) el = document.getElementById(id[0]+"2");
            //if(el.value.indexOf(x.value) == -1) {
                    el.value = x.value;
            //}
        }
}

// コンボボックスの値をテキストボックス（A～B）に設定
function setDateTimeComboBetweenValue(x) {
	id = x.name.split("_");
        //日付1設定
        var el1 = document.getElementById(id[0]+"11");
        var el2 = document.getElementById(id[0]+"12");
        if((x.value == null) || (x.value == "" )) {
            el1.value = "";
            el2.value = "";
            el1 = document.getElementById(id[0]+"21");
            el2 = document.getElementById(id[0]+"22");
            el1.value = "";
            el2.value = "";
        } else {
            if((el1.value != null) && (el1.value != "")) {
                el1 = document.getElementById(id[0]+"21");
                el2 = document.getElementById(id[0]+"22");
            }
            //if(el.value.indexOf(x.value) == -1) {
                    entdate = x.value.split(" ");
                    el1.value = entdate[0];
                    el2.value = entdate[1];
            //}
        }
}

// 日付を生成
// x	テキストフィールド
function textToDate(x) {
	var vYear;
	var vMonth;
	var vDay;

	var text = x.value;

	if (text ==  "" || text == null) {
		return;
	}

	if (text.match(/^\d{1,4}\/\d{1,2}\/\d{1,2}$/) == null) {
		if (text.match(/^\d{1,2}\/\d{1,2}$/) == null) {
			if (text.match(/^\d{1,2}$/) == null) {
				if (text.match(/^\d{8}$/) == null) {
					if (text.match(/^\d{6}$/) == null) {
						if (text.match(/^\d{4}$/) == null) {
							return;
						} else {
							vYear  = (new Date()).getFullYear();
							vMonth = text.substring(0, 2) - 1;
							vDay = text.substring(2, 4) - 0;
						}
					} else {
						vYear  = text.substring(0, 2) - 0;
						vMonth = text.substring(2, 4) - 1;
						vDay = text.substring(4, 6) - 0;
					}
				} else {
					vYear  = text.substring(0, 4) - 0;
					vMonth = text.substring(4, 6) - 1;
					vDay = text.substring(6, 8) - 0;
				}
			} else {
				vYear  = (new Date()).getFullYear();
				vMonth = (new Date()).getMonth();
				vDay = text - 0;
			}
		} else {
			vYear  = (new Date()).getFullYear();
			var token = text.split('/');
			vMonth = token[0] - 1;
			vDay = token[1] - 0;
		}
	} else {
		var token = text.split('/');
		vYear  = token[0] - 0;
		vMonth = token[1] - 1;
		vDay = token[2] - 0;
	}

	if ((vYear + "").length < 3) {vYear = (((new Date()).getFullYear() + "").substring(0, 2) * 100)  + vYear;}
	var vDt = new Date(vYear, vMonth, vDay);
	if (isNaN(vDt) == true) {
		alert("日付指定に誤りがあります");
                x.value = "";
                x.focus();
		return;
	} else {
		vYear = (new Date(vYear, 0, 1)).getFullYear();
		if (vDt.getFullYear() == vYear && vDt.getMonth() == vMonth && vDt.getDate() == vDay){
			vMonth = vMonth + 1;
			if (vYear < 1000) {vYear = "0" + vYear;}
			if (vMonth < 10) {vMonth = "0" + vMonth;}
			if (vDay < 10) {vDay = "0" + vDay;}
			x.value = vYear + "/" + vMonth  + "/" + vDay;
			return;
		} else {
			alert("日付指定に誤りがあります");
                        x.value = "";
                        x.focus();
			return;
		}
	}
}
// 時間を生成
// x	テキストフィールド
function textToTime(x) {
	var vHours;
	var vMinutes;
	var vSeconds;

	var text = x.value;

	if (text ==  "" || text == null) {
		return;
	}

	if (text.match(/^\d{1,2}\:\d{1,2}\:\d{1,2}$/) == null) {
		if (text.match(/^\d{1,2}\:\d{1,2}$/) == null) {
			if (text.match(/^\d{1,2}$/) == null) {
				if (text.match(/^\d{6}$/) == null) {
					if (text.match(/^\d{4}$/) == null) {
						if (text.match(/^\d{2}$/) == null) {
							return;
						} else {
							vHours = text.substring(0, 2) - 0;
							//vMinutes = (new Date()).getMinutes();
							//vSeconds  = (new Date()).getSeconds();
							vMinutes = 0;
							vSeconds  = 0;
						}
					} else {
						vHours = text.substring(0, 2) - 0;
						vMinutes = text.substring(2, 4) - 0;
						//vSeconds  = (new Date()).getSeconds();
						vSeconds  = 0;
					}
				} else {
					vHours  = text.substring(0, 2) - 0;
					vMinutes = text.substring(2, 4) - 0;
					vSeconds = text.substring(4, 6) - 0;
				}
			} else {
				vHours  = text - 0;
				//vMinutes = (new Date()).getMinutes();
				//vSeconds = (new Date()).getSeconds();
				vMinutes = 0;
				vSeconds = 0;
			}
		} else {
			var token = text.split(':');
			vHours = token[0] - 0;
			vMinutes = token[1] - 0;
			//vSeconds = (new Date()).getSeconds();
			vSeconds = 0;
		}
	} else {
		var token = text.split(':');
		vHours  = token[0] - 0;
		vMinutes = token[1] - 0;
		vSeconds = token[2] - 0;
	}
	var vDt = new Date();
	vDt.setHours(vHours);
	vDt.setMinutes(vMinutes);
	vDt.setSeconds(vSeconds);
	if (isNaN(vDt) == true) {
		alert("時間指定に誤りがあります");
                x.value = "";
                x.focus();
		return;
	} else {
		if (vDt.getHours() == vHours && vDt.getMinutes() == vMinutes && vDt.getSeconds() == vSeconds){
			if (vHours < 10) {vHours = "0" + vHours;}
			if (vMinutes < 10) {vMinutes = "0" + vMinutes;}
			if (vSeconds < 10) {vSeconds = "0" + vSeconds;}
			x.value = vHours + ":" + vMinutes  + ":" + vSeconds;
			return;
		} else {
			alert("時間指定に誤りがあります");
                        x.value = "";
                        x.focus();
			return;
		}
	}
}