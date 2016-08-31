package cn.tf.test;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.junit.Test;

import cn.tf.domain.User;
import cn.tf.utils.H3Utils;

public class TestDemo1 {
	
	@Test
	public void test1(){
		Session session=H3Utils.openSession();
		session.beginTransaction();
		
		User user=new User();
		user.setUsername("wqewr");
		
		session.saveOrUpdate(user);
		
		
		session.getTransaction().commit();
		
		session.close();
		
	}
	
	
	@Test
	public void test2(){
		Session session=H3Utils.openSession();
		session.beginTransaction();
		
		
		
		User user=(User) session.get(User.class, 1);
		System.out.println(user);
		
		//清空缓存
		session.clear();
		
		
		User user1=(User) session.get(User.class, 1);
		System.out.println(user1);
		
		session.getTransaction().commit();
		session.close();
		
	}

	//快照
	@Test
	public void test3(){
		Session session=H3Utils.openSession();
		session.beginTransaction();
		
		session.setFlushMode(FlushMode.MANUAL);
		
		User user=(User) session.get(User.class, 1);
		System.out.println(user);
		
		user.setUsername("yyy");
		
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	

}
