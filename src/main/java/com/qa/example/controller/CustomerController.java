package com.qa.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.example.domain.Customer;

@RestController
public class CustomerController {

	private List<Customer> customers = new ArrayList<>();
	
	// Create
	@PostMapping("/create")
	public Customer createCustomer(@RequestBody Customer c) {
		this.customers.add(c);
		Customer created = this.customers.get(this.customers.size()-1);
		return created;
	}
	
	// ReadAll
	@GetMapping("/getAll")
	public List<Customer> getAllCustomers() {
		return this.customers;
	}
	
	// Read by id
	@GetMapping("/get/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return this.customers.get(id);
	}
	
	// Update by id
	@PutMapping("/replace/{id}")
	public Customer replaceCustomer(@PathVariable Integer id, @RequestBody Customer c) {
		Customer customer = this.customers.set(id, c);
		return customer;
	}
	
	@DeleteMapping("/remove/{id}")
	public void removeCustomer(@PathVariable Integer id) {
		this.customers.remove(id.intValue());
	}
}
