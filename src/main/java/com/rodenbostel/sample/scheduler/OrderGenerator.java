package com.rodenbostel.sample.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.rodenbostel.sample.order.Order;
import com.rodenbostel.sample.order.OrderRepository;

@Component
@EnableAutoConfiguration
@EnableScheduling
public class OrderGenerator {

	@Autowired
	private OrderRepository repository;
	
	List<String> products = Lists.newArrayList();
	
	int numOfCustomers = 20;
	
	Random r = new Random();
	
	// @Scheduled(fixedRateString="#{T(java.lang.Long).MAX_VALUE}")
	@Scheduled(fixedRate = 1000000000) 
	public void clearAllOrders() {
		repository.deleteAll();

		products.add("TV");
		products.add("Dell Laptop");
		products.add("Book");
		products.add("Chair");
		products.add("Table");
		products.add("Seafood");
		products.add("Drink");
		products.add("bacon");
		products.add("XBox");
		products.add("Samsung");


		int PRODUCTS_MAX = products.size();

		for (int i = 0; i < numOfCustomers; i++) {
			List<String> itemsOrdered = Lists.newArrayList();
			int numOfItemsOrdered = r.nextInt(PRODUCTS_MAX) + 1;
			for (int j = 0; j < numOfItemsOrdered; j++) {
				itemsOrdered.add(products.get(r.nextInt(PRODUCTS_MAX)));
			}
			repository.save(new Order("customer-" + i, itemsOrdered));
		}
		System.out.println("Orders are generated successfully!");
	}
	
	@Scheduled(fixedRate = 1000, initialDelay = 2000)
	public void reportCurrentTime() {
		// System.out.println("The time is now " + dateFormat.format(new Date()));
		
		int PRODUCTS_MAX = products.size();
		// pick 3 customers each time
		for (int i = 0; i < 3; i++) {
			int customerIdx = r.nextInt(numOfCustomers);
			List<String> itemsOrdered = Lists.newArrayList();
			int numOfItemsOrdered = r.nextInt(PRODUCTS_MAX) + 1;
			for (int j = 0; j < numOfItemsOrdered; j++) {
				itemsOrdered.add(products.get(r.nextInt(PRODUCTS_MAX)));
			}
			repository.save(new Order("customer-" + customerIdx, itemsOrdered));
		}
	}
}
