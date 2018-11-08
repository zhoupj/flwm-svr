/**
 *对Date的扩展，将 Date 转化为指定格式的String
 *月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
 *年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
 *例子：
 *(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
 *(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
 */
Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, //月份
    "d+": this.getDate(), //日
    "h+": this.getHours(), //小时
    "m+": this.getMinutes(), //分
    "s+": this.getSeconds(), //秒
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度
    "S": this.getMilliseconds() //毫秒
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
}

Array.prototype.min = function() {
  var min = this[0];
  var len = this.length;
  for (var i = 1; i < len; i++){
    if (this[i] < min){
      min = this[i];
    }
  }
  return min;
}
//最大值
Array.prototype.max = function() {
  var max = this[0];
  var len = this.length;
  for (var i = 1; i < len; i++){
    if (this[i] > max) {
      max = this[i];
    }
  }
  return max;
}


var Util = {
  getUrlParam: function (parm) {
    const pathname = (window.location.search.length > 0 ? window.location.search.substring(1) : '')//获取到？之后的内容，如：id=2&orderCode=23，或者为空
    const getLocationArgsObj = {};
    const pathnameDetail = pathname.length ? pathname.split('&') : [];
    let items = null;
    let value = null;
    pathnameDetail.map((item) => {
      items = item.split('=');
      value = decodeURIComponent(items[1]);
      var name = decodeURIComponent(items[0]);
      if (name.length) {
        getLocationArgsObj[name] = value;
      }
    });
    if (parm != null) {
      return getLocationArgsObj[parm]
    }
    return getLocationArgsObj;
  },
  getYesterday: function () {
    var td = new Date();
    td.setDate(td.getDate() - 1);
    return td.format("yyyy-MM-dd");
  },
  max:function(lst){
    return lst.max();
  },
  min:function(lst){
    return lst.min();
  }

}

export default Util;


