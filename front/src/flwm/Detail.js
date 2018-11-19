import React, { Component } from 'react';
import { Tabs ,Input} from 'antd';
import Base from './detail/base'
import Tech from './detail/tech'
import Util from '../common/lib'
import Org from './detail/org'
import Shape from './detail/shape'

const TabPane = Tabs.TabPane;
//const Search = Input.Search;
//
//<div>
//  <Search
//    placeholder="input search text"
//    onSearch={value => console.log(value)}
//    style={{ width: 200 }}
//  />
class Detail extends Component {

  state = {
    code: Util.getUrlParam('code'),
    name: Util.getUrlParam('name')
  }

  componentDidMount() {
  }


  render() {
    return (
      <div>


        <div>
          <Shape code={this.state.code} name={this.state.name}/>
        </div>


        <Tabs
          defaultActiveKey="1"
          tabPosition='top'
        >
          <TabPane tab="基本面" key="1"><Base code={this.state.code}/></TabPane>
          <TabPane tab="技术面" key="2"><Tech code={this.state.code}/></TabPane>
          <TabPane tab="机构动向" key="3"><Org code={this.state.code}/></TabPane>
          <TabPane tab="新闻动向" key="4">开发中</TabPane>
          <TabPane tab="走势概率" key="5">开发中</TabPane>
          <TabPane tab="相似形态" key="6">开发中</TabPane>
        </Tabs>
      </div>
    );
  }
}

export default Detail;
