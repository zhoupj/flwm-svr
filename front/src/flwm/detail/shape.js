import React, { Component } from 'react';
import Ajax from '../../common/req';
import {Tag} from 'antd';
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
import 'echarts/lib/component/toolbox';
import 'echarts/lib/component/dataZoom';
import 'echarts/lib/chart/candlestick';
import jp from 'jsonpath';
import './base.css'

class Shape extends Component {


  state = {};

  splitData(rawData) {

    var categoryData = [];
    var values = []
    for (var i = 0; i < rawData.length; i++) {
      categoryData.push(rawData[i].dt);
      var item = [];
      item.push(rawData[i].open);
      item.push(rawData[i].close);
      item.push(rawData[i].low);
      item.push(rawData[i].high);
      values.push(item)
    }
    return {
      categoryData: categoryData,
      values: values
    };
  }

  calculateMA(dayCount, data0) {
    var result = [];
    for (var i = 0, len = data0.values.length; i < len; i++) {
      if (i < dayCount) {
        result.push('-');
        continue;
      }
      var sum = 0;
      for (var j = 0; j < dayCount; j++) {
        sum += data0.values[i - j][1];
      }
      result.push((sum / dayCount).toFixed(2));
    }
    return result;
  }


  draw_k(data) {


    var data0 = this.splitData(data);
// 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)


    var option = {
      backgroundColor: '#fff',
      title: {
        text: this.props.name,
        color:'#B22222',
        left: 0
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          animation: false,
          type: 'cross',
          lineStyle: {
            color: '#376df4',
            width: 2,
            opacity: 1
          }
        }
      },
      color:['#000','#FF7F00','#BA55D3','#9AC0CD','#6B8E23'],
      legend: {
        data: ['日K', 'MA10', 'MA20','MA50', 'MA120', 'MA250'],
        inactiveColor: '#777',
        textStyle: {
          color: '#000'
        }
      },
      grid: {
        bottom:80
      },
      xAxis: {
        type: 'category',
        data: data0.categoryData,
        axisLine: { lineStyle: { color: '#8392A5' } }
      },
      yAxis: {
        scale: true,
        axisLine: { lineStyle: { color: '#8392A5' } },
        splitLine: { show: true }
      },
      dataZoom: [{
        textStyle: {
          color: '#8392A5'
        },
        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
        handleSize: '80%',
        dataBackground: {
          areaStyle: {
            color: '#8392A5'
          },
          lineStyle: {
            opacity: 0.8,
            color: '#8392A5'
          }
        },
        handleStyle: {
          color: '#fff',
          shadowBlur: 3,
          shadowColor: 'rgba(0, 0, 0, 0.6)',
          shadowOffsetX: 2,
          shadowOffsetY: 2
        }
      }, {
        type: 'inside'
      }],
      animation:false,
      series: [
        {
          name: '日K',
          type: 'candlestick',
          data: data0.values,
          itemStyle: {
            normal: {
              color: '#FD1050',
              color0: '#00da3c',
              borderColor: '#FD1050',
              borderColor0: '#00da3c'
            }
          }
        },

        {
          name: 'MA10',
          type: 'line',
          data: this.calculateMA(10, data0),
          smooth: true,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1,
              color:'#000',
            }
          }
        },
        {
          name: 'MA20',
          type: 'line',
          data: this.calculateMA(20, data0),
          smooth: true,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1,
              color:'#FF7F00'
            }
          }
        },
        {
          name: 'MA50',
          type: 'line',
          data: this.calculateMA(50, data0),
          smooth: true,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1,
              color:'#BA55D3'
            }
          }
        },
        {
          name: 'MA120',
          type: 'line',
          data: this.calculateMA(120, data0),
          smooth: true,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1,
              color:'#9AC0CD'
            }
          }
        },
        {
          name: 'MA250',
          type: 'line',
          data: this.calculateMA(250, data0),
          smooth: true,
          showSymbol: false,
          lineStyle: {
            normal: {
              width: 1,
              color:'#6B8E23'
            }
          }
        },

      ]
    };

    var myChart = echarts.init(document.getElementById('k'));
    myChart.setOption(option);


  }


  componentDidMount() {
    var that = this;

    Ajax('/search/k', {code: this.props.code}, function (res) {
      that.draw_k(res.data.reverse())
    })
  }
  // <div><Tag color="red">上影线</Tag><Tag color="red">基金减仓</Tag></div>
  render() {
    return (
      <div>

        <div id="k" className="div-picture"></div>

      </div>
    );
  }


}

export  default Shape;
