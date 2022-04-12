package com.qa.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.qa.example.domain.Customer;
import com.qa.example.repo.CustomerRepo;

@Service
public class CustomerService implements ServiceIF<Customer> {
	
	private CustomerRepo repo;
	
	@Autowired
	public CustomerService(CustomerRepo repo) {
		super();
		this.repo = repo;
	}
	
	public Customer create(Customer c) {
		Customer created = this.repo.save(c);
		return created;
	}
	
	public List<Customer> getAll() {
		return this.repo.findAll();
	}
	
	public Customer getOne(Integer id) {
		Optional<Customer> data = this.repo.findById(id);
		return data.get();
	}
	
	public Customer replace(Integer id, Customer updCustomer) {
		Customer curCustomer = this.repo.findById(id).get();
		curCustomer.setName(updCustomer.getName());
		curCustomer.setAge(updCustomer.getAge());
		curCustomer.setHeight(updCustomer.getHeight());
		
		// Return the current customer as it has been updated
		Customer updated = this.repo.save(curCustomer);
		return updated;
	}
	
	public void remove(@PathVariable Integer id) {
		this.repo.deleteById(id);
	}
	
	public List<Customer> getCustomersByName(String name) {
		List<Customer> data = this.repo.findByNameIgnoreCase(name);
		return data;
	}
	
	public List<Customer> getCustomersByAge(Integer age) {
		List<Customer> data = this.repo.findByAge(age);
		return data;
	}
}
