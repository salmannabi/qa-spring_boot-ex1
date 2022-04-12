package com.qa.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.example.domain.Customer;

@Service
public class CustomerService {
	
	private List<Customer> customers = new ArrayList<>();
	
	public Customer createCustomer(Customer c) {
		this.customers.add(c);
		Customer created = this.customers.get(this.customers.size()-1);
		return created;
	}
	
	public List<Customer> getAllCustomers() {
		return this.customers;
	}
	
	public Customer getCustomer(Integer id) {
		return this.customers.get(id);
	}
	
	public Customer replaceCustomer(Integer id, Customer c) {
		Customer updated = this.customers.set(id, c);
		return updated;
	}
	
	public void removeCustomer(@PathVariable Integer id) {
		this.customers.remove(id.intValue());
	}
}
