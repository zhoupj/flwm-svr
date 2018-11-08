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

class Org extends Component {

  state = {
    code: this.props.code,
    data: []
  }


  componentDidMount() {

    const that = this;
    const param = {code: this.state.code};
    Ajax('/search/org', param, function (res) {
      that.setState({data: res.data})
      that.draw_fin('基金持仓', res.data.fs.reverse(), 'fund')
      that.draw_hk('港资持仓', res.data.fd.reverse(), 'hk')
    })
  }


  draw_fin(title, data, id) {

    var dates = jp.query(data, '$[*].dt');
    var v1 = jp.query(data, '$[*].fur');
    var v2 = jp.query(data, '$[*].sbr');


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
        data: ['基金持仓', '社保持仓']
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
        name: '基金持仓',
        type: 'bar',
        data: v1
      },
        {
          name: '社保持仓',
          type: 'bar',
          data: v2
        }
      ]
    });
  }


  draw_hk(title, data, id) {

    var dates = jp.query(data, '$[*].dt');
    var v1 = jp.query(data, '$[*].hkm');
    var v2 = jp.query(data, '$[*].hkr');

    var max = Math.ceil(v2.max());
    var inter= Math.round( max/10 * 10) / 10;

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
        data: ['持仓金额', '持仓比例']
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
          name: '金额',
          min: 0,
          axisLabel: {
            formatter: '{value} 万'
          },
        },
        {
          type: 'value',
          name: '持有比',
          min:0,
          max:max,
          interval: inter,
          axisLabel: {
            formatter: '{value} %'
          }
        }
      ],
      series: [{
        name: '持仓金额',
        type: 'bar',
        data: v1
      },
        {
          name: '持仓比例',
          type: 'line',
          yAxisIndex: 1,
          data: v2
        }
      ]
    });
  }



  render() {
    return (
      <div>
        <div id="fund" className="div-picture"></div>
        <div id="hk" className="div-picture"></div>
      </div>
    );
  }
}

export default Org;
