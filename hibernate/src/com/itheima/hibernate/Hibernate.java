package com.itheima.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.itheima.user.User;

public class Hibernate {
	private Configuration configure;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction ts;

	@Before
	public void setUp() throws Exception {
		//ʵ�������ö���.���������ļ�hibernate.cfg.xml
		configure = new Configuration().configure();
		//�����Ự���ӹ���
	   sessionFactory = configure.buildSessionFactory();
		//�����Ự
		 session = sessionFactory.openSession();
		//��������
		 ts = session.beginTransaction();
		
		
	}

	@Test
	public void test() {
		//����
		User user = new User();
		user.setUsername("С��");
		user.setPassword("eeee");
		session.save(user);

	}
	@Test
	public void update() {
		//����
		User user = new User();
		user.setUsername("����ʦ");
		user.setPassword("����");
		user.setId(1);
		session.update(user);
		
	}
	@Test
	public void update2() {
		//����
		User user = session.get(User.class, 1);
		user.setUsername("����ʦ");
		user.setPassword("��");;
		session.update(user);
		
	}
	@Test
	public void delete() {
		//����
		User user = session.get(User.class, 1);
		session.delete(user);
		
	}
	@Test
	public void list() {
		//����
		//��дhql���
//		String hql = "select new User(username,password) from User";
		String hql = "from User where username=?";
		//��ȡquery
		Query query = session.createQuery(hql);
		query.setParameter(0, "С��");
		List<User> list = query.list();
		System.out.println(list);
		
	}
	@Test
	public void list2() {
		//����
		//��дhql���
		String sql = "select * from user";
		//��ȡquery
		SQLQuery query = session.createSQLQuery(sql);
		List<User> list = query.list();
//		for (User user : list) {
//			System.out.println(user);
//		}
		System.out.println(list);
		
	}
	@Test
	public void list3() {
		//����
		//��дhql���
		String sql = "select * from user";
		//��ȡquery
		Criteria criteria = session.createCriteria(User.class);
		List<User> list = criteria.list();

		System.out.println(list);
		
	}
	@Test
	public void list4() {
		//����
		//��дhql���
		String sql = "select * from user";
		//��ȡquery
		Criteria criteria = session.createCriteria(User.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		List<User> list = criteria.list();
		
		System.out.println(list);
		
	}
	@Test
	public void list5() {
		//����
		//��дhql���
		String sql = "select * from user";
		//��ȡquery
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("username", "%С%"));
		List<User> list = criteria.list();
		
		System.out.println(list);
		
	}
	@After
	public void setAfter(){
		// �ύ�����ͷ���Դ
		ts.commit();
		session.close();
		sessionFactory.close();
	}

}
