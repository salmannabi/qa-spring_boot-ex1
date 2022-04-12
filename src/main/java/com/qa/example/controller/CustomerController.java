package com.qa.example.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.example.domain.Customer;
import com.qa.example.service.CustomerService;

@RestController
public class CustomerController {
	
	private CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	// Create
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
		Customer created = service.createCustomer(c);
		ResponseEntity<Customer> response = new ResponseEntity<Customer>(created, HttpStatus.CREATED);
		return response;
	}
	
	// ReadAll
	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(service.getAllCustomers());
	}
	
	// Read by id
	@GetMapping("/get/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return service.getCustomer(id);
	}
	
	// Update by id
	@PutMapping("/replace/{id}")
	public ResponseEntity<Customer> replaceCustomer(@PathVariable Integer id, @RequestBody Customer c) {
		Customer customer = service.replaceCustomer(id, c);
		ResponseEntity<Customer> response = new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeCustomer(@PathVariable Integer id) {
		service.removeCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<Customer>> getCustomersByName(@PathVariable String name) {
		List<Customer> data = this.service.getCustomersByName(name);
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("/getByAge/{age}")
	public ResponseEntity<List<Customer>> getCustomersByAge(@PathVariable Integer age) {
		List<Customer> data = this.service.getCustomersByAge(age);
		return ResponseEntity.ok(data);
	}
}
