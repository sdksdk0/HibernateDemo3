package cn.tf.domain2;

import java.util.HashSet;
import java.util.Set;

public class Course {
	
	private Integer cid;
	private String content;
	private String teacher;
	
	//不同课程可以被不同学生学习
	
	private Set<Student> studentSet=new HashSet<Student>();

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Set<Student> getStudentSet() {
		return studentSet;
	}

	public void setStudentSet(Set<Student> studentSet) {
		this.studentSet = studentSet;
	}
	
	
	
	

}
