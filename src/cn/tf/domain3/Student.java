package cn.tf.domain3;

import java.util.HashSet;
import java.util.Set;

public class Student {
	
	private Integer sid;
	private String sname;
	
	//多对多
	private Set<Course> courseSet=new HashSet<Course>();

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Set<Course> getCourseSet() {
		return courseSet;
	}

	public void setCourseSet(Set<Course> courseSet) {
		this.courseSet = courseSet;
	}
	
	
	

}
