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
		//实例化配置对象.加载配置文件hibernate.cfg.xml
		configure = new Configuration().configure();
		//创建会话连接工厂
	   sessionFactory = configure.buildSessionFactory();
		//创建会话
		 session = sessionFactory.openSession();
		//开启事务
		 ts = session.beginTransaction();
		
		
	}

	@Test
	public void test() {
		//操作
		User user = new User();
		user.setUsername("小泽");
		user.setPassword("eeee");
		session.save(user);

	}
	@Test
	public void update() {
		//操作
		User user = new User();
		user.setUsername("苍老师");
		user.setPassword("别闹");
		user.setId(1);
		session.update(user);
		
	}
	@Test
	public void update2() {
		//操作
		User user = session.get(User.class, 1);
		user.setUsername("吉老师");
		user.setPassword("快");;
		session.update(user);
		
	}
	@Test
	public void delete() {
		//操作
		User user = session.get(User.class, 1);
		session.delete(user);
		
	}
	@Test
	public void list() {
		//操作
		//编写hql语句
//		String hql = "select new User(username,password) from User";
		String hql = "from User where username=?";
		//获取query
		Query query = session.createQuery(hql);
		query.setParameter(0, "小泽");
		List<User> list = query.list();
		System.out.println(list);
		
	}
	@Test
	public void list2() {
		//操作
		//编写hql语句
		String sql = "select * from user";
		//获取query
		SQLQuery query = session.createSQLQuery(sql);
		List<User> list = query.list();
//		for (User user : list) {
//			System.out.println(user);
//		}
		System.out.println(list);
		
	}
	@Test
	public void list3() {
		//操作
		//编写hql语句
		String sql = "select * from user";
		//获取query
		Criteria criteria = session.createCriteria(User.class);
		List<User> list = criteria.list();

		System.out.println(list);
		
	}
	@Test
	public void list4() {
		//操作
		//编写hql语句
		String sql = "select * from user";
		//获取query
		Criteria criteria = session.createCriteria(User.class);
		criteria.setFirstResult(0);
		criteria.setMaxResults(1);
		List<User> list = criteria.list();
		
		System.out.println(list);
		
	}
	@Test
	public void list5() {
		//操作
		//编写hql语句
		String sql = "select * from user";
		//获取query
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.like("username", "%小%"));
		List<User> list = criteria.list();
		
		System.out.println(list);
		
	}
	@After
	public void setAfter(){
		// 提交事务，释放资源
		ts.commit();
		session.close();
		sessionFactory.close();
	}

}
