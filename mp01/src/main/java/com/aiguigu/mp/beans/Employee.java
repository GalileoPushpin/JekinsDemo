package com.aiguigu.mp.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * javaBean
 * @author Administrator
 *	
 */

//@TableName(value="tbl_employee")
public class Employee {
	/**
	 * MP
	 * value:若与数据库一致，可以省略
	 * type:指定主键策略
	 */
	//@TableId(value="id",type=IdType.AUTO)
	private Integer id; 
	@TableField(value="last_name")
	private String lastName;
	private String email;
	private char gender;
	private Integer age;
	@TableField(exist=false)//此属性不存在于数据库中
	private Double salary;
	
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", lastName=" + lastName + ", email=" + email + ", gender=" + gender + ", age="
				+ age + "]";
	}
	
}
