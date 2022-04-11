package com.qa.example.controller;

import java.util.ArrayList;
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

@RestController
public class CustomerController {

	private List<Customer> customers = new ArrayList<>();
	
	// Create
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer c) {
		this.customers.add(c);
		Customer created = this.customers.get(this.customers.size()-1);
		ResponseEntity<Customer> response = new ResponseEntity<Customer>(created, HttpStatus.CREATED);
		return response;
	}
	
	// ReadAll
	@GetMapping("/getAll")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		return ResponseEntity.ok(this.customers);
	}
	
	// Read by id
	@GetMapping("/get/{id}")
	public Customer getCustomer(@PathVariable Integer id) {
		return this.customers.get(id);
	}
	
	// Update by id
	@PutMapping("/replace/{id}")
	public ResponseEntity<Customer> replaceCustomer(@PathVariable Integer id, @RequestBody Customer c) {
		Customer customer = this.customers.set(id, c);
		ResponseEntity<Customer> response = new ResponseEntity<Customer>(customer, HttpStatus.ACCEPTED);
		return response;
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> removeCustomer(@PathVariable Integer id) {
		this.customers.remove(id.intValue());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
