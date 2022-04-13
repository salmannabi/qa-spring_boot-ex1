package com.qa.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.example.domain.Customer;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc // Sets up MockMVC Object
@Sql(scripts = {"classpath:customer-schema.sql", 
		"classpath:customer-data.sql"}, executionPhase=ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("test")
public class CustomerControllerIntegrationTest {
	
	@Autowired // Pull the MockMVC object from the context
	private MockMvc mvc; // Class that performs request (Kind of postman equivalent)
	
	@Autowired
	private ObjectMapper mapper; // Java <-> JSON converter that spring uses
	
	@Test
	void testCreate() throws Exception {
		Customer testCustomer = new Customer(null, "Jack", 20, 170);
		String testCustomerAsJson = this.mapper.writeValueAsString(testCustomer);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testCustomerAsJson);
		
		Customer testCreateCustomer = new Customer(3, "Jack", 20, 170);
		String testCreatedCustomerAsJson = this.mapper.writeValueAsString(testCreateCustomer);
		
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(testCreatedCustomerAsJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getAllTest() throws Exception {
		RequestBuilder req = get("/getAll");
		
		List<Customer> testCustomers = List.of(new Customer(1, "Jack", 25, 170), new Customer(2, "Maria", 20, 160));
		String json = this.mapper.writeValueAsString(testCustomers);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByIdTest() throws Exception {
		RequestBuilder req = get("/get/1");
		String json = this.mapper.writeValueAsString(new Customer(1, "Jack", 25, 170));
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void getByNameTest() throws Exception {
		RequestBuilder req = get("/getByName/Jack");
		
		List<Customer> testCustomers = List.of(new Customer(1, "Jack", 25, 170));
		String json = this.mapper.writeValueAsString(testCustomers);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
				
	}
	
	@Test
	void getByAgeTest() throws Exception {
		RequestBuilder req = get("/getByAge/25");
		
		List<Customer> testCustomers = List.of(new Customer(1, "Jack", 25, 170));
		String json = this.mapper.writeValueAsString(testCustomers);
		
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(json);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplace() throws Exception {
		
		Customer testCustomer = new Customer(1, "Jack", 25, 170);
		String json = this.mapper.writeValueAsString(testCustomer);
		
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(json);
		
		Customer testUpdatedCustomer = new Customer(1, "Jack", 25, 170);
		String testUpdatedCustomerAsJson = this.mapper.writeValueAsString(testUpdatedCustomer);
		
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(testUpdatedCustomerAsJson);
		
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testRemove() throws Exception {
		this.mvc.perform(delete("/remove/1")).andExpect(status().isNoContent());
	}
}
