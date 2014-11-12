package com.rodenbostel.sample.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
public class OrderController {

	@Autowired
	private OrderRepository repository;

	@RequestMapping("/order")
	public List<Order> allOrders() {
		List<Order> orders = new ArrayList<Order>();
		for (Order order : repository.findAll()) {
			orders.add(order);
		}
		return orders;
	}
}