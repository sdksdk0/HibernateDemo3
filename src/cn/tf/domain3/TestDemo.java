package cn.tf.domain3;

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
	
	
	

	
	/*//查询
	@Test
	public void test1(){
		Session session = factory.openSession();
		session.beginTransaction();
		
		
				//1 学生
				Student student = new Student();
				student.setSname("jack");
				Student student2 = new Student();
				student2.setSname("rose");
				Student student3 = new Student();
				student3.setSname("tom");
				
				//2课程
				Course course = new Course();
				course.setContent("java 基础");
				Course course2 = new Course();
				course2.setContent("java web");
				Course course3 = new Course();
				course3.setContent("java ssh");
				
				//3 学生 关联 课程
				student.getCourseSet().add(course);
				student.getCourseSet().add(course2);
				student.getCourseSet().add(course3);
				
				student2.getCourseSet().add(course2);
				student2.getCourseSet().add(course3);
				
				student3.getCourseSet().add(course3);
				
				//4
				session.save(student);
				session.save(student2);
				session.save(student3);
				session.save(course);
				session.save(course2);
				session.save(course3);

		session.getTransaction().commit();
		session.close();
	}
	*/
	//双向级联删除
	
	
	@Test
	public void test2(){
		Session session = factory.openSession();
		session.beginTransaction();
			
		Student student = (Student) session.get(Student.class, 14);
		session.delete(student);
		
		
		

		session.getTransaction().commit();
		session.close();
	}
	
	
	

}
