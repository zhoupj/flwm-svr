import React, { Component } from 'react';
import Ajax from '../../common/req';
// 引入 ECharts 主模块
import echarts from 'echarts/lib/echarts';
// 引入柱状图
import  'echarts/lib/chart/bar';
import  'echarts/lib/chart/line';
// 引入提示框和标题组件
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/legend';
import 'echarts/lib/component/toolbox';
import 'echarts/lib/component/grid';
import jp from 'jsonpath';
import './base.css'

class Base extends Component {

  state = {
    code: this.props.code,
    data: []
  }


  componentDidMount() {

    const that = this;
    const param = {code: this.state.code};
    Ajax('/search/basic', param, function (res) {
      that.setState({data: res.data})
      that.draw_sort('盈利强度',res.data.sea.reverse(),'sort')
      that.draw_trend('季度报告',res.data.sea,'season')
      that.draw_trend('年度报告',res.data.year.reverse(),'year')
      that.draw_ll("利率变化",res.data.year,'ll')
    })
  }





  draw_sort(title,data,id) {

    var dates=jp.query(data, '$[*].dt');
    var ss2=jp.query(data, '$[*].s2');
    var ss8 = jp.query(data, '$[*].s8');

    var max =100;// Math.ceil(Math.max(ss2.max(),ss8.max()));
    var inter=10;//Math.round( max/10 * 10) / 10;

    //https://www.cnblogs.com/goloving/p/9113830.html
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(id));
    // 绘制图表
    myChart.setOption({
      title: {text: title},
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      //toolbox: {
      //  feature: {
      //    dataView: {show: true, readOnly: false},
      //    magicType: {show: true, type: ['line', 'bar']},
      //    restore: {show: true},
      //    saveAsImage: {show: true}
      //  }
      //},
      legend: {
        data:['短期强度','长期强度']
      },
      //grid: {
      //  left: '3%',
      //  right: '3%',
      //  top: '12%',
      //  containLabel: true
      //},
      xAxis: {
        data: dates
      },
      yAxis: [
        {
          type: 'value',
          name: '强度',
          interval: inter,
          min:0,
          max:max,
          axisLabel: {
            formatter: '{value}'
          }
        },
      ],
      series: [{
        name: '短期强度',
        type: 'line',
        data: ss2
      },
        {
          name: '长期强度',
          type: 'line',
          data: ss8
        }
       ]
    });
  }


  draw_trend(title,data,id) {

    var dates=jp.query(data, '$[*].dt');
    var gsjlr = jp.query(data, '$[*].gsjlr');
    var gsjlr_tb=jp.query(data, '$[*].gsjlrtbzz');
    var kfjlr = jp.query(data, '$[*].kfjlr');
    var kfjlr_tb=jp.query(data, '$[*].kfjlrtbzz');
    var yysr=jp.query(data, '$[*].yyzsr');
    var yysr_tb=jp.query(data, '$[*].yyzsrtbzz');

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(id));
    // 绘制图表
    myChart.setOption({
      title: {text: title},
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      //toolbox: {
      //  feature: {
      //    dataView: {show: true, readOnly: false},
      //    magicType: {show: true, type: ['line', 'bar']},
      //    restore: {show: true},
      //    saveAsImage: {show: true}
      //  }
      //},
      legend: {
        data:['净利润','扣非净利润','营业同比','净利润同比','扣非同比']
      },
      //grid: {
      //  left: '3%',
      //  right: '3%',
      //  top: '12%',
      //  containLabel: true
      //},
      xAxis: {
        data: dates
      },
      yAxis: [
        {
          type: 'value',
          name: '额度',
          min:0,
          axisLabel: {
            formatter: '{value} 万'
          }
        },
        {
          type: 'value',
          name: '增长率',
          min:0,
          //max:200,
          //interval: 20,
          axisLabel: {
            formatter: '{value} %'
          }
        }
      ],
      series: [
        //{
      //  name: '营业收入',
      //  type: 'bar',
      //  data: yysr
      //},
      {
        name: '净利润',
        type: 'bar',
        data: gsjlr
      },
        {
          name: '扣非净利润',
          type: 'bar',
          data: kfjlr
        },{
          name: '营业同比',
          type: 'line',
          yAxisIndex: 1,
          data: yysr_tb
        },
        {
          name: '净利润同比',
          type: 'line',
          yAxisIndex: 1,
          data: gsjlr_tb
        },
        {
          name: '扣非同比',
          type: 'line',
          yAxisIndex: 1,
          data: kfjlr_tb
        }]
    });
  }

  draw_ll(title, data, id) {

    var dates = jp.query(data, '$[*].dt');
    var v1 = jp.query(data, '$[*].mll');
    var v2 = jp.query(data, '$[*].jll');

    var max = Math.ceil(Math.max(v1.max(),v2.max()));
    var inter=Math.round( max/10 * 10) / 10;

    //https://www.cnblogs.com/goloving/p/9113830.html
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById(id));
    // 绘制图表
    myChart.setOption({
      title: {text: title},
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        }
      },
      //toolbox: {
      //  feature: {
      //    dataView: {show: true, readOnly: false},
      //    magicType: {show: true, type: ['line', 'bar']},
      //    restore: {show: true},
      //    saveAsImage: {show: true}
      //  }
      //},
      legend: {
        data: ['毛利率', '净利率']
      },
      //grid: {
      //  left: '3%',
      //  right: '3%',
      //  top: '12%',
      //  containLabel: true
      //},
      xAxis: {
        data: dates
      },
      yAxis: [
        {
          type: 'value',
          name: '百分比',
          interval: inter,
          min: 0,
          max: max,
          axisLabel: {
            formatter: '{value}%'
          }
        },
      ],
      series: [{
        name: '毛利率',
        type: 'line',
        data: v1
      },
        {
          name: '净利率',
          type: 'line',
          data: v2
        }
      ]
    });
  }


  render() {
    return (
        <div>
          <div id="sort" className="div-picture"></div>
          <div id="season" className="div-picture"></div>
          <div id="year" className="div-picture"></div>
          <div id="ll" className="div-picture"></div>
        </div>
    );
  }
}

export default Base;
