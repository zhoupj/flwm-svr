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

    var upColor = '#ec0000';
    var upBorderColor = '#8A0000';
    var downColor = '#00da3c';
    var downBorderColor = '#008F28';

    var data0 = this.splitData(data);
// 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)


    var option = {
      title: {
        text: this.props.name,
        left: 0
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross'
        }
      },
      legend: {
        data: ['日K', 'MA10', 'MA50', 'MA120', 'MA250']
      },
      grid: {
        left: '10%',
        right: '10%',
        bottom: '15%'
      },
      xAxis: {
        type: 'category',
        data: data0.categoryData,
        scale: true,
        boundaryGap: false,
        axisLine: {onZero: false},
        splitLine: {show: false},
        splitNumber: 20,
        min: 'dataMin',
        max: 'dataMax'
      },
      yAxis: {
        scale: true,
        splitArea: {
          show: true
        }
      },
      dataZoom: [
        {
          type: 'inside',
          start: 50,
          end: 100
        },
        {
          show: true,
          type: 'slider',
          y: '90%',
          start: 50,
          end: 100
        }
      ],
      series: [
        {
          name: '日K',
          type: 'candlestick',
          data: data0.values,
          itemStyle: {
            normal: {
              color: upColor,
              color0: downColor,
              borderColor: upBorderColor,
              borderColor0: downBorderColor
            }
          },
          markPoint: {
            label: {
              normal: {
                formatter: function (param) {
                  return param != null ? Math.round(param.value) : '';
                }
              }
            },
            data: [
              {
                name: 'XX标点',
                coord: ['2013-10-09', 2300],
                value: 2300,
                itemStyle: {
                  normal: {color: 'rgb(41,60,85)'}
                }
              },
              {
                name: 'highest value',
                type: 'max',
                valueDim: 'highest'
              },
              {
                name: 'lowest value',
                type: 'min',
                valueDim: 'lowest'
              },
              {
                name: 'average value on close',
                type: 'average',
                valueDim: 'close'
              }
            ],
            tooltip: {
              formatter: function (param) {
                return param.name + '<br>' + (param.data.coord || '');
              }
            }
          },
          markLine: {
            symbol: ['none', 'none'],
            data: [
              [
                {
                  name: 'from lowest to highest',
                  type: 'min',
                  valueDim: 'lowest',
                  symbol: 'circle',
                  symbolSize: 10,
                  label: {
                    normal: {show: false},
                    emphasis: {show: false}
                  }
                },
                {
                  type: 'max',
                  valueDim: 'highest',
                  symbol: 'circle',
                  symbolSize: 10,
                  label: {
                    normal: {show: false},
                    emphasis: {show: false}
                  }
                }
              ],
              {
                name: 'min line on close',
                type: 'min',
                valueDim: 'close'
              },
              {
                name: 'max line on close',
                type: 'max',
                valueDim: 'close'
              }
            ]
          }
        },
        {
          name: 'MA10',
          type: 'line',
          data: this.calculateMA(10, data0),
          smooth: true,
          lineStyle: {
            normal: {opacity: 0.5}
          }
        },
        {
          name: 'MA50',
          type: 'line',
          data: this.calculateMA(50, data0),
          smooth: true,
          lineStyle: {
            normal: {opacity: 0.5}
          }
        },
        {
          name: 'MA120',
          type: 'line',
          data: this.calculateMA(120, data0),
          smooth: true,
          lineStyle: {
            normal: {opacity: 0.5}
          }
        },
        {
          name: 'MA250',
          type: 'line',
          data: this.calculateMA(250, data0),
          smooth: true,
          lineStyle: {
            normal: {opacity: 0.5}
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

  render() {
    return (
      <div>
        <div id="k" className="div-picture"></div>
      </div>
    );
  }


}

export  default Shape;
