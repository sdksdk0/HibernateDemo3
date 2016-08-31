package cn.tf.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class H3Utils {
	
	//提供一个工厂，链式操作
	private static SessionFactory  factory=new Configuration().configure().buildSessionFactory();
	
	//获得新的会话
	public static Session openSession(){
		return factory.openSession();
	}
	public static Session getCurrSession(){
		
		return factory.getCurrentSession();
	}

}
