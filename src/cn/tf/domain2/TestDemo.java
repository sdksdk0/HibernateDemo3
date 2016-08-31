package cn.tf.domain2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.tf.domain1.Customer;
import cn.tf.domain1.Order;

public class TestDemo {
	private SessionFactory factory = new Configuration()
	.configure()
	.addClass(Student.class)
	.addClass(Course.class)
	.buildSessionFactory();
	
	
	
	@Test
	public void test1(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		session.getTransaction().commit();
		session.close();
	}
	
	//保存学生信息
	@Test
	public void test2(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		Student student=new Student();
		student.setSname("李斯");
		session.save(student);
		
		
		
		session.getTransaction().commit();
		session.close();
	}
	
	//保存课程信息
	@Test
	public void test3(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		Course course=new Course();
		course.setContent("数据结构");
		session.save(course);
		
		session.getTransaction().commit();
		session.close();
	}
	
/*	//查询
	@Test
	public void test4(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		
		Student student=(Student) session.get(Student.class, 1);
		Course course=(Course) session.get(Course.class,1);
		
		student.getCourseSet().add(course);

		session.getTransaction().commit();
		session.close();
	}*/
	
	
	//提供学生和课程，学生去关联课程，只保存学生
	//持久对象关联了瞬时对象
  @Test
	public void test5(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		
		Student student=new Student();
		student.setSname("大锤");
		
		Course course=new Course();
		course.setContent("安卓编程");
		
		//学生关联课程
		student.getCourseSet().add(course);
		
		//保存学生
		session.save(student);
		session.save(course);

		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void test6(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		
		Student student=new Student();
		student.setSname("大锤2");
		
		Course course=new Course();
		course.setContent("安卓编程2");
		
		//课程关联学生
		course.getStudentSet().add(student);
		
		
		//保存学生
		session.save(student);
		session.save(course);

		session.getTransaction().commit();
		session.close();
	}
	
   
   

}
