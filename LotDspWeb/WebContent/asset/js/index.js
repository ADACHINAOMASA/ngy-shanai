var machineName = document.getElementById("machineName").value;
var contextRoute = document.getElementById("contextRoute").value;
var port = document.getElementById("port").value;
//alert(machineName + ", " + contextRoute + ", " + port);

function openWindowSchedule() {
  if(machineName != "" && contextRoute != "") {
      window.open("http://" + machineName + port + contextRoute + "/?mode=0", "_blank", "fullscreen=yes");
  } else {
      alert("マシン名とコンテキスト・ルートが取得できませんでした。");
  }
}
function openWindowCycle() {
  if(machineName != "" && contextRoute != "") {
      window.open("http://" + machineName  + port + contextRoute + "/?mode=1", "_blank", "fullscreen=yes");
  } else {
      alert("マシン名とコンテキスト・ルートが取得できませんでした。");
  }
}
function openWindowLotDsp() {
  window.open("http://nlmfangyweb1a/lotdsp/?mode=0&site=1", "_blank", "fullscreen=yes");
}