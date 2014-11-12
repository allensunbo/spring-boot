package com.rodenbostel.sample.order;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {

	public List<Order> findByCustomerName(String customerName);

}