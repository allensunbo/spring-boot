package com.rodenbostel.sample.order;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.Lists;

public class Order {
	@Id
	private String id;

	private String customerName;
	private DateTime orderTime;
	private List<String> orderItems;

	public Order() {
		
	}
	
	public Order (String customerName, String ...  orderItems) {
		this.customerName = customerName;
		this.orderTime = new DateTime();
		this.orderItems = Lists.newArrayList(orderItems);
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public DateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(DateTime orderTime) {
		this.orderTime = orderTime;
	}

	public List<String> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<String> orderItems) {
		this.orderItems = orderItems;
	}

}
