import React, { Component } from 'react';
import { Form, DatePicker, Button,Row,Switch,Select, Col,Tooltip,Icon,InputNumber,Table,Popover } from 'antd';
import './Search.css'
import moment from 'moment';
import Ajax from '../common/req'
import Util from '../common/lib'

const FormItem = Form.Item;
const Option = Select.Option;


const columns = [{
  title: '名称',
  dataIndex: 'name',
  fixed: 'left',
  render: function (v, record) {
    if (record != null) {
      return (<Popover content={record.code} title="代码">
        <a target='_blank' href={'/search/detail?code='+record.code+'&name='+record.name }> {v} </a>
      </Popover>)
    }
  },

}, {
  title: '行业',
  dataIndex: 'industry',

}, {
  title: '上市日期',
  dataIndex: 'timetomarket',

}, {
  title: '总市值',
  dataIndex: 'totals',
  render: function (v) {
    if (v != null) {
      return v.toFixed(2) + '亿'
    }
  },
  sorter: (a, b) => a.totals - b.totals,
}, {
  title: '差值',
  dataIndex: 'difftohigh250',
  sorter: (a, b) => a.difftohigh250 - b.difftohigh250,
}, {
  title: 'RPS120',
  dataIndex: 'rps120',

}, {
  title: 'RPS250',
  dataIndex: 'rps250',
  sorter: (a, b) => a.rps250 - b.rps250,

}, {
  title: 'SSR2',
  dataIndex: 'ssr2',

}, {
  title: '基金持仓',
  dataIndex: 'fundHolding',
  render: fundHolding => `${fundHolding}% `,
  sorter: (a, b) => a.fundHolding - b.fundHolding,

}, {
  title: '港资持仓',
  dataIndex: 'hkHoldingAmount',
  render: function (v) {
    if (v != null && v > 10000) {
      return (v / 10000).toFixed(2) + '亿';
    }else if(v!=null){
      return v.toFixed(2) + '万'
    }else return '0'
  }

}, {
  title: '波动率',
  dataIndex: 'fluof250d',

}, {
  title: '换手率',
  dataIndex: 'turn',

}, {
  title: '市盈率',
  dataIndex: 'pettm',

}];

class SearchCom extends Component {

  state = {
    data: [],
    param: {},
    pagination: {
      pageSize: 20,
      current: 1,
      total: 0,
      onChange(page,pageSize) {

      },
    },
    loading: false,
  };


  handleTableChange = (pagination, filters, sorter) => {
    //const pager = {...this.state.pagination};
    //pager.current = pagination.current;
    //console.log(pagination.current)
    //this.setState({pagination});
    this.fetch({
      pageSize: pagination.pageSize,
      pageNo: pagination.current,
      ...this.state.param
    });
  }


  fetch = (params = {}) => {
    //console.log('params:', JSON.stringify(params));
    this.setState({loading: true});
    const pagination = {...this.state.pagination};

    var that = this;
    Ajax('/search/list', JSON.stringify(params),
      function (result) {
        pagination.total = result.total;
        pagination.current=params.pageNo;
        that.setState({
          data: result.data,
          pagination,
        });
      },true);
    this.setState({loading: false});
  }


//componentDidMount() {
//  this.fetch();
//}


  handleSearch = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      values['tradeDate'] = values['tradeDate'].format('YYYY-MM-DD');
      values['isMR'] = values['isMR']===true ?1:0;
      values['gy'] = values['gy']===true ?1:0;
      const pager = {...this.state.pagination};
      pager.current = 1;
      this.setState({param: values, pagination: pager})
      this.fetch({
        pageSize: this.state.pagination.pageSize,
        pageNo: 1,
        ...values
      });
    })
  }

  handleReset = () => {
    this.props.form.resetFields();
  }

//
//<FormItem
//{...formItemLayout}
//label="RPS"
//hasFeedback
//>
//{getFieldDecorator('rps', {initialValue:'RPS250'})(
//  <Select >
//    <Option value="RPS250">RPS250</Option>
//    <Option value="RPS120">RPS120</Option>
//    <Option value="RPS50">RPS50</Option>
//  </Select>
//)}
//</FormItem>
  render() {

    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: {span: 10},
      wrapperCol: {span: 14},
    };


    const dateFormat = 'YYYY-MM-DD';
    var yesterday = Util.getYesterday();

    const config = {
      rules: [{type: 'object', required: true, message: 'Please select date!'}],
      initialValue: moment(yesterday, dateFormat),
    };

    return (
      <div>
        <Form className="ant-advanced-search-form" onSubmit={this.handleSearch}>
          <Row type="flex" justify="start">
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="日期"
              >
                {getFieldDecorator('tradeDate', config)(
                  <DatePicker format={dateFormat}/>
                )}
              </FormItem>
            </Col>
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="总市值(<=)"
              >
                {getFieldDecorator('totals', {})(
                  <InputNumber
                    min={0}
                    max={10000}
                    formatter={value => `${value}亿`}
                    parser={value => value.replace('亿', '')}

                  />
                )}
              </FormItem>
            </Col>
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="市盈率(<=)"
              >
                {getFieldDecorator('pettm', {})(
                  <InputNumber
                    min={0}
                    max={1000}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="换手率(<=)"
              >
                {getFieldDecorator('turn', {
                  initialValue: 10
                })(
                  <InputNumber
                    min={0}
                    max={100}
                    formatter={value => `${value}%`}
                    parser={value => value.replace('%', '')}

                  />
                )}
              </FormItem>
            </Col>

          </Row>
          <Row type="flex" justify="start">
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="RPS250(>=)"
              >
                {getFieldDecorator('rps250', {initialValue: 87})(
                  <InputNumber
                    min={0}
                    max={100}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="RPS50(>=)"
              >
                {getFieldDecorator('rps50', {initialValue: 87})(
                  <InputNumber
                    min={0}
                    max={100}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label={(
                <span>
                  PS2 (>=)&nbsp;
                  <Tooltip title="近两个季度净利润排名,值越大越强">
                    <Icon type="question-circle-o" />
                  </Tooltip>
                </span>
               )}
              >
                {getFieldDecorator('ssr2', {initialValue: 70})(
                  <InputNumber
                    min={0}
                    max={100}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label={(
              <span>
                  新高差值 (&lt;=)&nbsp;
                <Tooltip title="(一年最高价-收盘价)/收盘价">
                  <Icon type="question-circle-o" />
                </Tooltip>
                </span>
            )}
              >
                {getFieldDecorator('difftohigh250', {initialValue: 15})(
                  <InputNumber
                    min={0}
                    max={50}
                    step={0.5}
                    formatter={value => `${value}%`}
                    parser={value => value.replace('%', '')}
                  />
                )}
              </FormItem>
            </Col>

          </Row>
          <Row type="flex" justify="start">

            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="波动率(<=)"
              >
                {getFieldDecorator('fluof250d', {initialValue: 25})(
                  <InputNumber
                    min={0}
                    max={100}
                    formatter={value => `${value}%`}
                    parser={value => value.replace('%', '')}
                  />
                )}
              </FormItem>
            </Col>

            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="基金持仓(>=)"
              >
                {getFieldDecorator('fundHolding', {initialValue: 3})(
                  <InputNumber
                    min={0}
                    max={50}
                    formatter={value => `${value}%`}
                    parser={value => value.replace('%', '')}
                  />
                )}
              </FormItem>
            </Col>

            <Col span={6}>
              <FormItem
                {...formItemLayout}
                label="港资持仓(>=)"
              >
                {getFieldDecorator('hkHoldingAmount', {initialValue: 3000})(
                  <InputNumber
                    min={0}
                    max={1000000}
                    formatter={value => `${value}万`}
                    parser={value => value.replace('万', '')}
                  />
                )}
              </FormItem>
            </Col>
            <Col span={3}>
              <FormItem
                {...formItemLayout}
                label="月线反转"
              >
                {getFieldDecorator('isMR', {})(
                  <Switch checkedChildren="是" unCheckedChildren="无"/>
                )}
              </FormItem>
            </Col>
            <Col span={3}>
              <FormItem
                {...formItemLayout}
                label="站上年线"
              >
                {getFieldDecorator('gy', {})(
                  <Switch checkedChildren="是" unCheckedChildren="无"/>
                )}
              </FormItem>
            </Col>

          </Row>
          <Row type="flex" justify="end">
            <Button type="primary" htmlType="submit">Search</Button>
            <Button style={{ marginLeft: 8 }} onClick={this.handleReset}>
              Clear
            </Button>
          </Row>


        </Form>
        <Table
          columns={columns}
          rowKey={record => record.code}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}
          scroll={{ x: 800 }}
        />
      </div>
    );
  }
}

const Search = Form.create()(SearchCom);
export default Search;

