package cn.tf.domain1;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tf.domain1.Customer;
import cn.tf.domain1.Order;

public class TestDemo {
	private SessionFactory factory = new Configuration()
	.configure()
	.addClass(Customer.class)
	.addClass(Order.class)
	.buildSessionFactory();
	
	
	//级联保存或更新   save-update:A关联瞬时态B，当保存A时，自动将瞬时态的B转换成持久态B
	//cascade="save-update"
	
	/*@Test
	public void test1(){
		Session session = factory.openSession();
		session.beginTransaction();
		//客户
		Customer customer=new Customer();
		customer.setCname("王五");
		//订单
		Order order=new Order();
		order.setPrice("1200");
		//关联
		customer.getOrderSet().add(order);
		
		//保存
		session.save(customer);
		
		
		session.getTransaction().commit();
		session.close();
	}*/
	
	//级联删除,默认是将与其关联的外键设置为null,然后再删除
	//级联：当删除客户时，订单也一起删除
	
	
	@Test
	public void test2(){
		Session session = factory.openSession();
		session.beginTransaction();
		//客户
		Customer customer=(Customer) session.get(Customer.class, 4);
		session.delete(customer);
		
		session.getTransaction().commit();
		session.close();
	}
	
	
	//孤儿删除，解除关系之后，那个表外键设置null,从表记录就是孤儿，一并进行删除
	@Test
	public void test3(){
		Session session = factory.openSession();
		session.beginTransaction();
		//查询客户
		Customer customer=(Customer) session.get(Customer.class, 5);
		//查询订单
		Order order=(Order) session.get(Order.class, 3);
		
		//将指定订单与客户解除关系
		customer.getOrderSet().remove(order);
		

		
		session.getTransaction().commit();
		session.close();
	}
	

}
