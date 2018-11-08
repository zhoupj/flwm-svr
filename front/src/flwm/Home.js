import React, { Component } from 'react';
import markdown from 'markdown'

var article=`

### 经验
* 历史告诉我们
### 总结
* 总结是对的
`

class Home extends Component {
  render() {

    var txt= markdown.markdown.toHTML(article);
    return (
      <div dangerouslySetInnerHTML={{__html:txt}}>
      </div>
    );
  }
}

export default Home;
