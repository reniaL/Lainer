//========dom ready函数========

function ready(fn) {
  if (/(?!.*?compatible|.*?webkit)^mozilla|opera/i.test(navigator.userAgent)) {
    document.addEventListener("DOMContentLoaded", fn, false);
  } else {
    window.setTimeout(fn, 0);
  }
}

/**
 * 导入js让其自动执行
 *@param url 要导入的script地址
 *@param callback (可选) 载入script完成后触发的回调
 *@param callbackflag (可选) 设定callback的参数
 */
function importJs(url, callback, callbackflag){
  var head = document.getElementsByTagName("head")[0] || document.documentElement;
  var script = document.createElement("script");
  script.src = url;
  script.type = "text/javascript";
  var done = false;
  script.onload = script.onreadystatechange = function() {
    if (!done && (!this.readyState || this.readyState == "loaded" || this.readyState == "complete")) {
      done = true;
      //下载完成后触发回调函数
      if(typeof(callback) == "function"){
        callback(callbackflag);
      }
      // 加载完成后，可删除script标签，但好像会有问题
//      head.removeChild(this);
    }
  };
  //使用insertBefore而不是appendChild绕过IE的bug
  head.insertBefore(script, head.firstChild);
}


/**
 * 返回n天前的日期，格式为"yyyy年MM月dd日"
 * @param n 如果n为0，表示当天；如果n为负数，表示未来。
 * @return 如果n不是数字，返回当天。
 */
function getDate(n) {
  n = (n && !isNaN(parseInt(n))) ? parseInt(n) : 0;
  var d = new Date();
  d.setDate(d.getDate() - n);
  return d.getFullYear() + "年" + (d.getMonth() + 1) + "月" + d.getDate() + "日";
}
