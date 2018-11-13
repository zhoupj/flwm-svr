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
import Util from '../../common/lib'
import './base.css'

class Tech extends Component {

  state = {
    code: this.props.code,
    data: []
  }


  componentDidMount() {

    const that = this;
    const param = {code: this.state.code};
    Ajax('/search/tech', param, function (res) {
      that.setState({data: res.data})
      that.draw_sort('RPS', res.data.reverse(), 'rps')
      that.draw_turn('换手率', res.data, 'turn')
      that.draw_fluc('波动率', res.data, 'fluc')
      that.draw_diff('差值率', res.data, 'diff')
    })
  }


  draw_sort(title, data, id) {

    var dates = jp.query(data, '$[*].dt');
    var v1 = jp.query(data, '$[*].rps120');
    var v2 = jp.query(data, '$[*].rps250');
    var v3 = jp.query(data, '$[*].rps50');

    var max = 100;
      //Math.ceil(Math.max(v1.max(),v2.max()));
    var inter=5;
      //Math.round( max/10 * 10) / 10;

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
        data: ['RPS120', 'RPS250','RPS50']
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
          min: 0,
          max: max,
          axisLabel: {
            formatter: '{value}'
          }
        },
      ],
      series: [{
        name: 'RPS120',
        type: 'line',
        data: v1
      },
        {
          name: 'RPS250',
          type: 'line',
          data: v2
        },
        {
          name: 'RPS50',
          type: 'line',
          data: v3
        }
      ]
    });
  }


  draw_turn(title, data, id) {

    var dates = jp.query(data, '$[*].dt');
    var v1 = jp.query(data, '$[*].turn');
    var v2 = jp.query(data, '$[*].turn50');

    var max = Math.ceil(v1.max());
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
        data: ['换手率', '50日换手率']
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
            formatter: '{value}'
          }
        },
      ],
      series: [{
        name: '换手率',
        type: 'bar',
        data: v1
      },
        {
          name: '50日换手率',
          type: 'line',
          data: v2
        }
      ]
    });
  }

  draw_fluc(title, data, id) {

    var dates = jp.query(data, '$[*].dt');
    var v1 = jp.query(data, '$[*].f250');
    var v2 = jp.query(data, '$[*].f120');
    var v4 = jp.query(data, '$[*].f80');
    var v3 = jp.query(data, '$[*].f10');


    var max = Math.ceil(v1.max());
    var inter=Math.floor(max/10);

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
        data: ['一年波动率', '半年波动率', '80日波动率','10日波动率']
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
        name: '一年波动率',
        type: 'line',
        data: v1
      },
        {
          name: '半年波动率',
          type: 'line',
          data: v2
        },
        {
          name: '80日波动率',
          type: 'line',
          data: v4
        },
        {
          name: '10日波动率',
          type: 'line',
          data: v3
        }
      ]
    });
  }


  draw_diff(title, data, id) {


    var dates = jp.query(data, '$[*].dt');
    var v1 = jp.query(data, '$[*].d250');
    var v2 = jp.query(data, '$[*].d120');

    var max = Math.ceil(v1.max());
    var inter=Math.floor(max/10);


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
        data: ['一年差值率', '半年差值率']
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
        name: '一年差值率',
        type: 'line',
        data: v1,
        areaStyle: {}
      },
        {
          name: '半年差值率',
          type: 'line',
          data: v2,
          areaStyle: {}
        }
      ]
    });
  }


  render() {
    return (
      <div>
        <div id="rps" className="div-picture"></div>
        <div id="turn" className="div-picture"></div>
        <div id="fluc" className="div-picture"></div>
        <div id="diff" className="div-picture"></div>
      </div>
    );
  }
}

export default Tech;
