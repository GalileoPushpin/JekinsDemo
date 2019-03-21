package com.atguigu.mp.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aiguigu.mp.beans.Employee;
import com.aiguigu.mp.mapper.EmployeeMapper;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;


public class TestMP {
	
	
	private ApplicationContext aa = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private EmployeeMapper employeeMapper = aa.getBean("employeeMapper",EmployeeMapper.class);
	
	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = aa.getBean("dataSource",DataSource.class);
		System.out.println(dataSource);
		
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}
	
	/**
	 * 通用 插入
	 */
	@Test
	public void testCommonInsert() {
		Employee entity = new Employee();
		entity.setAge(20);
		entity.setEmail("1377777@163.com");
		entity.setGender('1');
		entity.setLastName("lili");
		entity.setSalary(20000.0);
		
		Integer result = employeeMapper.insert(entity);
		System.out.println(result);
		//可直接获取id
		Integer id = entity.getId();
		System.out.println(id);
		
	}
	
	/**
	 * 通用 更改
	 */
	@Test
	public void testCommonUpdate() {
		Employee entity = new Employee();
		entity.setId(11);
		entity.setEmail("mp1@163.com");
		entity.setGender('1');
		entity.setLastName("mp1");
		
		//Integer result = employeeMapper.updateById(entity);
		Integer result = employeeMapper.updateAllColumnById(entity);//更改所有的数据
		System.out.println(result);	
		
	}
	
	/**
	 * 通用 查询
	 */
	@Test
	public void testCommonSelect() {
		//1通过id查询
		Employee result = employeeMapper.selectById(11);
		System.out.println(result);	
		//2通过多个列查询一条
		Employee entity = new Employee();
		entity.setGender('1');
		entity.setLastName("Tom");
		Employee result2 = employeeMapper.selectOne(entity);
		System.out.println(result2);
		//3通过id批量查询
		List<Integer> idList = new ArrayList<>();
		idList.add(1);
		idList.add(2);
		idList.add(3);
		idList.add(11);
		List<Employee> result3 = employeeMapper.selectBatchIds(idList);
		System.out.println(result3);
		//4通过Map封装条件查询
		Map<String,Object> columnMap = new HashMap<String,Object>();
		columnMap.put("last_name", "Tom");
		columnMap.put("gender", '1');
		List<Employee> result4 = employeeMapper.selectByMap(columnMap);
		System.out.println("4"+result4);
		//5.分页查询
		List<Employee> result5 = employeeMapper.selectPage(new Page<>(2,2), null);
		System.out.println("5"+result5);
	}
	
	/**
	 * 通用 删除
	 */
	@Test
	public void testCommonDelete() {
		//1根据id删除
		Integer result = employeeMapper.deleteById(13);
		System.out.println(result);	
		//2通过Map封装条件删除
//		Map<String,Object> columnMap = new HashMap<String,Object>();
//		columnMap.put("last_name", "lili");
//		columnMap.put("email", "1377777@163.com");
//		Integer result = employeeMapper.deleteByMap(columnMap);
//		System.out.println("del "+result);
		//3通过id--List批量删除
//		List<Integer> idList = new ArrayList<>();
//		idList.add(11);
//		idList.add(14);
//		Integer result2 = employeeMapper.deleteBatchIds(idList);
//		System.out.println(result2);
	}
	
	/**
	 * 条件构造器  查询（一些稍复杂的需求）
	 */
	@Test
	public void testEntityWrapperSelect() {
		//查询年龄在18~50、性别为男性、姓名为Tom的所有用户
//		List<Employee> list = employeeMapper.selectPage(new Page<>(1,2), 
//				new EntityWrapper<Employee>()
//				.between("age",18,50)
//				.eq("gender", '1')
//				.eq("last_name", "Tom"));
//		System.out.println(list);
		
		//查询性别为女、名字中带有老师or邮箱中带有a
		List<Employee> list2 = employeeMapper.selectList(new EntityWrapper<Employee>()
				.eq("gender", '0')
				.like("last_name", "老师")
				.or() //前一个 和 后一个 做or 
				//orNew()  前面所有 和 后一个 做or
				.like("email", "a"));
		System.out.println(list2);
		
		//查询 并演示 排序
		List<Employee> list3 = employeeMapper.selectList(new EntityWrapper<Employee>()
				.eq("gender", '0')
				.orderBy("age").last("desc limit 1,2")
				//.orderDesc(Arrays.asList(new String[] {"age"}))
				);
				
		System.out.println(list3);
		
		//演示 Condition
		List list4 = employeeMapper.selectPage(new Page<>(1,2), Condition.create()
				.between("age",18,50)
				.eq("gender", '1'));
		System.out.println("Condition:"+list3);
		
	}
	
	/**
	 * 条件构造器 修改
	 */
	@Test
	public void testEntityWrapperUpdate() {
		Employee entity = new Employee();
		entity.setGender('0');
		entity.setLastName("苍老师");
		entity.setEmail("tokyo@qq.com");
		Integer result = employeeMapper.update(entity, new EntityWrapper<Employee>()
				.eq("last_name", "Tom")
				.eq("age", 44));
		System.out.println(result);
	}
	
	/**
	 * 条件构造器 删除
	 */
	@Test
	public void testEntityWrapperDelete() {
		Integer result = employeeMapper.delete(new EntityWrapper<Employee>()
				.eq("last_name", "Tom")
				.eq("age", 45));
		System.out.println(result);
	}
}
