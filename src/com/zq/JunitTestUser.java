package com.zq;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

public class JunitTestUser {

	@Test
	public void test() {
		//1.创建一个SessionFactory对象
		SessionFactory sessionFactory = null;
		//1).创建Configuration对象：对应hibernate的基本配置文件和对象关系映射
		Configuration configuration = new Configuration().configure();
		
		//注：4.0之前是这么创建的SessionFactory sessionFactory = configuration.buildSessionFactory();
		//2).创建一个ServiceRegistry对象：hibernate4.x以后新增对象，
		//hibernate的任何配置和服务都要在该对象中注册后才能生效
		@SuppressWarnings("deprecation")
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//2.创建一个Session对象
		Session session = sessionFactory.openSession();
		//3.开启事务
		Transaction transaction = session.beginTransaction();
		//4.执行保存
		User user = new User();
		user.setName("小明");
		user.setAge(18);
		session.save(user);
		//5.提交事务
		transaction.commit();
		//6.关闭Session
		session.close();
		//7.关闭SessionFactory对象
		sessionFactory.close();
	}

}
