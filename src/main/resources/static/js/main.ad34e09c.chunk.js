(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{180:function(e,t,a){e.exports=a(367)},185:function(e,t,a){},187:function(e,t,a){},191:function(e,t,a){},367:function(e,t,a){"use strict";a.r(t);var n=a(1),r=a.n(n),l=a(9),i=a.n(l),c=(a(185),a(55)),o=a(56),s=a(63),m=a(57),u=a(64),d=(a(187),a(377)),p=a(368),f=a(372),h=a(380),E=a(381),g=a(378),b=a(379),x=function(e){function t(){return Object(c.a)(this,t),Object(s.a)(this,Object(m.a)(t).apply(this,arguments))}return Object(u.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){return r.a.createElement("div",null,"test hhaa ")}}]),t}(n.Component),y=a(66),j=a(374),O=a(370),v=a(371),S=a(375),I=a(376),k=a(366),w=a(10),C=a(369),Y=a(373),D=(a(190),a(191),a(13)),H=a.n(D),z=a(159),V=a.n(z),F=j.a.Item,M=[{title:"\u540d\u79f0",dataIndex:"name",fixed:"left"},{title:"\u884c\u4e1a",dataIndex:"industry"},{title:"\u4e0a\u5e02\u65e5\u671f",dataIndex:"timetomarket"},{title:"\u603b\u5e02\u503c",dataIndex:"totals",render:function(e){if(null!=e)return e.toFixed(2)+"\u4ebf"},sorter:function(e,t){return e.totals-t.totals}},{title:"\u5dee\u503c",dataIndex:"difftohigh250",sorter:function(e,t){return e.difftohigh250-t.difftohigh250}},{title:"RPS120",dataIndex:"rps120"},{title:"RPS250",dataIndex:"rps250"},{title:"SSR2",dataIndex:"ssr2"},{title:"\u57fa\u91d1\u6301\u4ed3",dataIndex:"fundHolding",render:function(e){return"".concat(e,"% ")},sorter:function(e,t){return e.fundHolding-t.fundHolding}},{title:"\u6e2f\u8d44\u6301\u4ed3",dataIndex:"hkHoldingAmount",render:function(e){if(null!=e&&e>1e4)return(e/1e4).toFixed(2)+"\u4ebf"}},{title:"\u6ce2\u52a8\u7387",dataIndex:"fluof250d"},{title:"\u6362\u624b\u7387",dataIndex:"turn"},{title:"\u5e02\u76c8\u7387",dataIndex:"pettm"}],R=function(e){function t(){var e,a;Object(c.a)(this,t);for(var n=arguments.length,r=new Array(n),l=0;l<n;l++)r[l]=arguments[l];return(a=Object(s.a)(this,(e=Object(m.a)(t)).call.apply(e,[this].concat(r)))).state={data:[],param:{},pagination:{pageSize:10,current:1,total:0},loading:!1},a.handleTableChange=function(e,t,n){var r=Object(y.a)({},a.state.pagination);r.current=e.current,a.setState({pagination:r}),a.fetch(Object(y.a)({pageSize:e.pageSize,pageNo:e.current},a.state.param))},a.fetch=function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{};a.setState({loading:!0}),V()({url:"/search/list",method:"post",headers:{"Content-type":"application/json"},data:JSON.stringify(e),type:"json"}).then(function(e){var t=e.data,n=Object(y.a)({},a.state.pagination);n.total=t.total,"1001"===t.code&&a.setState({data:t.data,pagination:n}),a.setState({loading:!1})})},a.handleSearch=function(e){e.preventDefault(),a.props.form.validateFields(function(e,t){t.tradeDate=t.tradeDate.format("YYYY-MM-DD");var n=Object(y.a)({},a.state.pagination);n.current=1,a.setState({param:t,pagination:n}),a.fetch(Object(y.a)({pageSize:a.state.pagination.pageSize,pageNo:1},t))})},a.handleReset=function(){a.props.form.resetFields()},a}return Object(u.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){var e=this.props.form.getFieldDecorator,t={labelCol:{span:10},wrapperCol:{span:14}},a={rules:[{type:"object",required:!0,message:"Please select date!"}],initialValue:H()("2018-10-25","YYYY-MM-DD")};return r.a.createElement("div",null,r.a.createElement(j.a,{className:"ant-advanced-search-form",onSubmit:this.handleSearch},r.a.createElement(O.a,{type:"flex",justify:"start"},r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:"\u65e5\u671f"}),e("tradeDate",a)(r.a.createElement(S.a,{format:"YYYY-MM-DD"})))),r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:"\u603b\u5e02\u503c(<=)"}),e("totals",{})(r.a.createElement(I.a,{min:0,max:1e4,formatter:function(e){return"".concat(e,"\u4ebf")},parser:function(e){return e.replace("\u4ebf","")}})))),r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:"\u5e02\u76c8\u7387(<=)"}),e("pettm",{})(r.a.createElement(I.a,{min:0,max:1e3})))),r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:"\u6362\u624b\u7387(<=)"}),e("turn",{initialValue:10})(r.a.createElement(I.a,{min:0,max:100,formatter:function(e){return"".concat(e,"%")},parser:function(e){return e.replace("%","")}}))))),r.a.createElement(O.a,{type:"flex",justify:"start"},r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:"RPS(>=)"}),e("rps250",{initialValue:87})(r.a.createElement(I.a,{min:0,max:100})))),r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:r.a.createElement("span",null,"PS2 (>=)\xa0",r.a.createElement(k.a,{title:"\u8fd1\u4e24\u4e2a\u5b63\u5ea6\u51c0\u5229\u6da6\u6392\u540d,\u503c\u8d8a\u5927\u8d8a\u5f3a"},r.a.createElement(w.a,{type:"question-circle-o"})))}),e("ssr2",{initialValue:70})(r.a.createElement(I.a,{min:0,max:100})))),r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:r.a.createElement("span",null,"\u65b0\u9ad8\u5dee\u503c (<=)\xa0",r.a.createElement(k.a,{title:"(\u4e00\u5e74\u6700\u9ad8\u4ef7-\u6536\u76d8\u4ef7)/\u6536\u76d8\u4ef7"},r.a.createElement(w.a,{type:"question-circle-o"})))}),e("difftohigh250",{initialValue:15})(r.a.createElement(I.a,{min:0,max:50,step:.5,formatter:function(e){return"".concat(e,"%")},parser:function(e){return e.replace("%","")}})))),r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:"\u57fa\u91d1\u6301\u4ed3(>=)"}),e("fundHolding",{initialValue:3})(r.a.createElement(I.a,{min:0,max:50,formatter:function(e){return"".concat(e,"%")},parser:function(e){return e.replace("%","")}}))))),r.a.createElement(O.a,{type:"flex",justify:"start"},r.a.createElement(v.a,{span:6},r.a.createElement(F,Object.assign({},t,{label:"\u6e2f\u8d44\u6301\u4ed3(>=)"}),e("hkHoldingAmount",{initialValue:3e3})(r.a.createElement(I.a,{min:0,max:1e6,formatter:function(e){return"".concat(e,"\u4e07")},parser:function(e){return e.replace("\u4e07","")}}))))),r.a.createElement(O.a,{type:"flex",justify:"end"},r.a.createElement(C.a,{type:"primary",htmlType:"submit"},"Search"),r.a.createElement(C.a,{style:{marginLeft:8},onClick:this.handleReset},"Clear"))),r.a.createElement(Y.a,{columns:M,rowKey:function(e){return e.code},dataSource:this.state.data,pagination:this.state.pagination,loading:this.state.loading,onChange:this.handleTableChange,scroll:{x:800}}))}}]),t}(n.Component),N=j.a.create()(R),P=d.a.Header,A=d.a.Content,T=d.a.Footer,q=function(){return r.a.createElement(d.a,null,r.a.createElement(P,{style:{position:"fixed",zIndex:1,width:"100%"}},r.a.createElement("div",{className:"logo"}),r.a.createElement(p.a,{theme:"dark",mode:"horizontal",defaultSelectedKeys:["1"],style:{lineHeight:"64px"}},r.a.createElement(p.a.Item,{key:"1"},r.a.createElement(f.a,{to:"/index"},"\u9996\u9875")),r.a.createElement(p.a.Item,{key:"2"},r.a.createElement(f.a,{to:"/search"},"\u67e5\u627e")),r.a.createElement(p.a.Item,{key:"3"},r.a.createElement(f.a,{to:"/mine"},"\u6211\u7684")))),r.a.createElement(A,{style:{padding:"0 50px",marginTop:64}},r.a.createElement("div",{style:{background:"#fff",padding:24,minHeight:380}},r.a.createElement(h.a,null,r.a.createElement(E.a,{path:"/",exact:!0,component:x}),r.a.createElement(E.a,{path:"/index",component:x}),r.a.createElement(E.a,{path:"/search",component:N}),r.a.createElement(g.a,{to:"/"})))),r.a.createElement(T,{style:{textAlign:"center"}},"\u7248\u6743\u6240\u6709,\u76d7\u7248\u5fc5\u7a76 | \u8054\u7cfb\u90ae\u7bb1:zhoupj1987@163.com"))},J=function(e){function t(){return Object(c.a)(this,t),Object(s.a)(this,Object(m.a)(t).apply(this,arguments))}return Object(u.a)(t,e),Object(o.a)(t,[{key:"render",value:function(){return r.a.createElement(b.a,null,r.a.createElement(q,null))}}]),t}(n.Component);Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));i.a.render(r.a.createElement(J,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})}},[[180,2,1]]]);
//# sourceMappingURL=main.ad34e09c.chunk.js.map