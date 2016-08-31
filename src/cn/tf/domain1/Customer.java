package cn.tf.domain1;

import java.util.HashSet;
import java.util.Set;

public class Customer {
	
	private Integer cid;
	private String cname;
	
	
	//一个客户拥有多个订单，一对多
	private Set<Order>  orderSet=new HashSet<Order>();


	public Integer getCid() {
		return cid;
	}


	public void setCid(Integer cid) {
		this.cid = cid;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	public Set<Order> getOrderSet() {
		return orderSet;
	}


	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}
	

}
