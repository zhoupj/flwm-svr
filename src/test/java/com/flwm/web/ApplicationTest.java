package com.flwm.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.flwm.service.ActivityService;
import com.flwm.service.StudentService;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {


	@Autowired
	StudentService studentService;
	@Autowired
	ActivityService activityService;

	@Test
	public void contextLoads() {

		@Data
		class TestDo{
			private int x;
			private String a;
			private Date d;
		}

		TestDo testDo=new TestDo();
		testDo.setD(new Date());
		testDo.setA("zhouj");
		System.out.println(JSON.toJSONString(testDo, SerializerFeature.WriteDateUseDateFormat));
	}

	@Test
	public void testDb(){
		List<Map> map=studentService.getList();
		System.out.println(JSON.toJSONString(map));
	}

	@Test
	public void testHM(){

		System.out.println(JSON.toJSONString( activityService.getAll()));
	}




}
