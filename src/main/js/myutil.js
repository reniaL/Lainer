//========dom ready����========

function ready(fn) {
  if (/(?!.*?compatible|.*?webkit)^mozilla|opera/i.test(navigator.userAgent)) {
    document.addEventListener("DOMContentLoaded", fn, false);
  } else {
    window.setTimeout(fn, 0);
  }
}

/**
 * ����js�����Զ�ִ��
 *@param url Ҫ�����script��ַ
 *@param callback (��ѡ) ����script��ɺ󴥷��Ļص�
 *@param callbackflag (��ѡ) �趨callback�Ĳ���
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
      //������ɺ󴥷��ص�����
      if(typeof(callback) == "function"){
        callback(callbackflag);
      }
      // ������ɺ󣬿�ɾ��script��ǩ���������������
//      head.removeChild(this);
    }
  };
  //ʹ��insertBefore������appendChild�ƹ�IE��bug
  head.insertBefore(script, head.firstChild);
}


/**
 * ����n��ǰ�����ڣ���ʽΪ"yyyy��MM��dd��"
 * @param n ���nΪ0����ʾ���죻���nΪ��������ʾδ����
 * @return ���n�������֣����ص��졣
 */
function getDate(n) {
  n = (n && !isNaN(parseInt(n))) ? parseInt(n) : 0;
  var d = new Date();
  d.setDate(d.getDate() - n);
  return d.getFullYear() + "��" + (d.getMonth() + 1) + "��" + d.getDate() + "��";
}
