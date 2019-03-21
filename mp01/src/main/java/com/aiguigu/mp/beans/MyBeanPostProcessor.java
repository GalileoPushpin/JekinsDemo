package com.aiguigu.mp.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

//Bean的前后置处理器  --实现BeanPostProcessor接口
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("对初始化之前的Bean进行处理,此时我的名字"+bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Student stu = null;
		System.out.println("对初始化之后的Bean进行处理,将Bean的成员变量的值修改了");
		if("name".equals(beanName) || bean instanceof Student) {
			stu = (Student) bean;
			stu.setName("Jack");
		}
		return stu;
	}

}
