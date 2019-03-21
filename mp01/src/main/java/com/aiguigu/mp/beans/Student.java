package com.aiguigu.mp.beans;

import org.springframework.beans.factory.BeanNameAware;

//体现Bean的生命周期
public class Student implements BeanNameAware {

	private String name;
	
	
	public Student() {
		super();
	}
	
	/** 
	 * 设置对象属性
	 */
	public void setName(String name) {
		System.out.println("设置对象属性setName()..");
		this.name = name;
	}

	/** 
	 * Bean的初始化方法
	 */
	public void initStudent() {
		System.out.println("Student这个Bean：初始化");
	}
	
	/** 
	 * Bean的销毁方法
	 */
	public void destroyStudent() {
		System.out.println("Student这个Bean：销毁");
	}
	
	/** 
	 * Bean的使用
	 */
	public void play() {
		System.out.println("Student这个Bean：使用");
	}

	/**
	 * 调用BeanNameAware的setBeanName()
	 * 传递Bean的ID
	 */
	@Override
	public void setBeanName(String name) {
		System.out.println("调用BeanNameAware的setBeanName()..." ); 
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}

}
