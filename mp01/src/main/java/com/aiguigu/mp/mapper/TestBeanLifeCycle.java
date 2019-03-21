package com.aiguigu.mp.mapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aiguigu.mp.beans.Student;

//测试Bean的生命周期，与MQ无关
public class TestBeanLifeCycle {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student student = (Student) context.getBean("student");
		//Bean的使用
		student.play();
		System.out.println(student);
		//关闭容器
		((AbstractApplicationContext) context).close();
	}
}
