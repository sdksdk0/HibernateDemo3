package cn.tf.test;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tf.domain.Customer;
import cn.tf.domain.Order;
import cn.tf.domain.User;
import cn.tf.utils.H3Utils;

public class TestDemo2 {
	
	private SessionFactory factory = new Configuration()
	.configure()
	.addClass(Customer.class)
	.addClass(Order.class)
	.buildSessionFactory();

		/**
		* 创建表
		*/
		@Test
		public void test1(){
		Session session = factory.openSession();
		
		
		session.close();
		}
			
		
/*		//保存客户信息
		@Test
		public void test2(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		Customer customer=new Customer();
		customer.setCname("张三");
		session.save(customer);
		session.getTransaction().commit();
		session.close();
		}
		*/
		
		//保存订单
	/*	@Test
		public void test3(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		Order order=new Order();
		order.setPrice("997");
		session.save(order);
		
		session.getTransaction().commit();
		session.close();
		}*/
		
		
		//提供客户和订单，客户关联订单，只保存客户
		//持久态customer关联order，之后操作要求的数据必须一致,将order  saveyi一下
		
/*		@Test
		public void test4(){
			Session session = factory.openSession();
			session.beginTransaction();
			//客户
			Customer customer=new Customer();
			customer.setCname("李四");
			//订单
			Order order=new Order();
			order.setPrice("1200");
			//关联
			customer.getOrderSet().add(order);
			session.save(customer);
			session.save(order);
			
			
			session.getTransaction().commit();
			session.close();
		}*/
		
		
		@Test
		public void test5(){
			Session session = factory.openSession();
			session.beginTransaction();
			//客户
			Customer customer=new Customer();
			customer.setCname("李四");
			//订单
			Order order=new Order();
			order.setPrice("1200");
			//关联
			customer.getOrderSet().add(order);
			order.setCustomer(customer);
			
			//保存
			session.save(customer);
			session.save(order);
			
			
			session.getTransaction().commit();
			session.close();
		}
		
		//多方必须维护外键信息，如果一方没有OID值，将触发update语句
		
		
		
	
	

}
