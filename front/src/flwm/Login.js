import React, { Component } from 'react';
import { Form, Row,Col,Icon, Input, Button } from 'antd';
import './Login.css'
import Ajax from '../common/req'
import {Redirect ,Link,withRouter} from 'react-router-dom';
const FormItem = Form.Item;

class LoginForm extends Component {

  handleSubmit = (e) => {
    e.preventDefault();
    var that=this;
    this.props.form.validateFields((err, values) => {
      if (!err) {
        Ajax('/login2', values,
          function (rst) {
            that.props.history.push("/index");
            //return (<Redirect to="/index" />);
          });
      }
    })
  }


  render() {
    const { getFieldDecorator } = this.props.form;
    return (

      <Form onSubmit={this.handleSubmit} className="login-form">
        <Row type="flex" justify="center">
          <Col span={8}>
            <FormItem>
              {getFieldDecorator('user', {
                rules: [{required: true, message: 'Please input your username!'}],
              })(
                <Input prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="Username"/>
              )}
            </FormItem>
            <FormItem>
              {getFieldDecorator('pwd', {
                rules: [{required: true, message: 'Please input your Password!'}],
              })(
                <Input prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password"
                       placeholder="Password"/>
              )}
            </FormItem>
          </Col>
        </Row>
        <Row type="flex" justify="center">
          <Col span={8} offset={4}>

            <Button type="primary" htmlType="submit" className="login-form-button">
              登录
            </Button>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <Link to={`/login`}>点击</Link>

          </Col>
        </Row>
      </Form>


    );
  }
}

const Login = Form.create()(LoginForm);
export default Login;
