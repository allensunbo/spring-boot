package com.rodenbostel.sample.order;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Maps;

@Controller
public class OrderWSController {
	
	@Autowired
	private OrderRepository repository;
	
	@MessageMapping("/order")
	@SendTo("/topic/order")
	public Map<String, List<Order>> allOrders() throws Exception {
		List<Order> orders = repository.findAll();
		Map<String, List<Order>> map = Maps.newHashMap();
		for (Order order : orders) {
			String customerName = order.getCustomerName();
			if(map.get(customerName) == null) {
				map.put(customerName, new LinkedList<Order>());
			}
			map.get(customerName).add(order);
		}
		
		return map;
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order");

		/*String str = "Hello World!";
		mav.addObject("message", str);*/

		return mav;
	}
}