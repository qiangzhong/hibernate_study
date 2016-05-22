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
		//1.����һ��SessionFactory����
		SessionFactory sessionFactory = null;
		//1).����Configuration���󣺶�Ӧhibernate�Ļ��������ļ��Ͷ����ϵӳ��
		Configuration configuration = new Configuration().configure();
		
		//ע��4.0֮ǰ����ô������SessionFactory sessionFactory = configuration.buildSessionFactory();
		//2).����һ��ServiceRegistry����hibernate4.x�Ժ���������
		//hibernate���κ����úͷ���Ҫ�ڸö�����ע��������Ч
		@SuppressWarnings("deprecation")
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
			.applySettings(configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		//2.����һ��Session����
		Session session = sessionFactory.openSession();
		//3.��������
		Transaction transaction = session.beginTransaction();
		//4.ִ�б���
		User user = new User();
		user.setName("С��");
		user.setAge(18);
		session.save(user);
		//5.�ύ����
		transaction.commit();
		//6.�ر�Session
		session.close();
		//7.�ر�SessionFactory����
		sessionFactory.close();
	}

}
