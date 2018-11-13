//import axios from 'axios';
import { message } from 'antd'
import $ from 'jquery'

const domain_name = '';////'http://127.0.0.1:8080'
const domain_front_name = '';//http://127.0.0.1:3002


var Ajax = function (url, dat, suc_fun,js) {

  //var header_default = {'Content-type': 'application/x-www-form-urlencoded'};
  //if (js != null && js == true) {
  //  header_default = {'Content-type': 'application/json'};
  //}
  //
  //console.log(header_default)
  //console.log(dat)
  //
  //axios({
  //  url: domain_name + url,
  //  method: 'post',
  //  headers: header_default,
  //  data: dat
  //}).then((rst) => {
  //  //console.log(rst.data);
  //  const result = rst.data;
  //  if (result.code === '1001') {
  //    if (suc_fun) {
  //      suc_fun(result)
  //    }
  //  } else {
  //    message.error(result.code + ":" + result.msg);
  //  }
  //}).catch(function (error) {
  //  message.error("network error");
  //});

  $.ajax({
    type: "POST",
    url:domain_name + url,
    data: dat,
    dataType: "json",
    contentType:js===true?'application/json':'application/x-www-form-urlencoded',
    success: function(result){
      if(result.code=== '1001' && suc_fun){
        suc_fun(result);
      }else{
        message.error(result.code + ":" + result.msg);
        if(result.code==='2003'){
          //return (<Redirect to="/login" />);
          window.location.href=domain_front_name+'/login';
        }

      }
    },
    error:function(){
      message.error('network error');
    }
  });

};


export default Ajax;
