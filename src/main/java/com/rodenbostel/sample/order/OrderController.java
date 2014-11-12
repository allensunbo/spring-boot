package com.rodenbostel.sample.order;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
@RestController
public class OrderController {

	@Autowired
	private OrderRepository repository;
	
    @RequestMapping("/order")
    public List<Order> allOrders() {
    	
    	repository.deleteAll();

		// save a couple of orders
		repository.save(new Order("Alice Smith", "TV", "Dell Laptop"));
		repository.save(new Order("Bob Smith", "Book", "Chair", "Table"));

		List<Order> orders = new ArrayList<Order>();

		for (Order order : repository.findByCustomerName("Alice Smith")) {
			System.out.println(order);
			orders.add(order);
		}
        return orders;
    }
}