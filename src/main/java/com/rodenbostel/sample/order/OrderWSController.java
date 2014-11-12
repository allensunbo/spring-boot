package com.rodenbostel.sample.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderWSController {
	
	@Autowired
	private OrderRepository repository;
	
	@MessageMapping("/order")
	@SendTo("/topic/order")
	public List<Order> greeting() throws Exception {
		List<Order> orders = new ArrayList<Order>();
		for (Order order : repository.findAll()) {
			orders.add(order);
		}		
		return orders;
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("order");

		String str = "Hello World!";
		mav.addObject("message", str);

		return mav;
	}
}