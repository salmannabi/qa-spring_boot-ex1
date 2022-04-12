package com.qa.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.example.domain.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	//Spring will auto generate all of the basic CRUD functionality for this repository
	List<Customer> findByNameIgnoreCase(String name); // This needs to be custom built for the column (name here)
	List<Customer> findByAge(Integer age);
}
