package com.rodenbostel.sample;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SampleController {

	@Autowired
	private CustomerRepository repository;

	@RequestMapping("/customer")
	public List<Customer> customer() {

		repository.deleteAll();

		// save a couple of customers
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		List<Customer> customers = new ArrayList<Customer>();

		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
			customers.add(customer);
		}

		return customers;
	}

	@RequestMapping(value = "/timer", method = RequestMethod.GET)
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("timer");

		String str = "Hello World!";
		mav.addObject("message", str);

		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");

		String str = "Hello World!";
		mav.addObject("message", str);

		return mav;
	}
}