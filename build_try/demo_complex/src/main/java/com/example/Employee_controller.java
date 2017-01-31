package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Employee_controller {

	// http://localhost:8080/emp_data

	/*
	 * { "id": 296401, "name": "vj", "permanent": true, "address": { "street":
	 * "phase1", "city": "banagalore", "zipcode": 123456 }, "phoneNumbers": [
	 * 7667449894, 9824580665 ], "role": "project engineer", "cities": [
	 * "surat", "chennai" ], "properties": { "age": "24", "salary": "1000 USD" }
	 * }
	 */

	@RequestMapping(value = "/emp_data", method = RequestMethod.POST)

	public Employee Emp_data(@RequestBody Employee employee) {

		System.out.println("hi , service stared");

		System.out.println(employee);
		return employee;

	}

	@RequestMapping(value = "/emp_data_input", method = RequestMethod.GET)
	public Employee getEmp_data() {

		Employee employee = new Employee();
		
		Address address = new Address();
		address.setCity("banagalore");
		address.setStreet("phase 2");
		address.setZipcode(123);
		
		long phonenos[] = {1234,12334};
		
		List<String > city = new ArrayList<>();
		city.add("chennai");
		city.add("surat");
		
		Map<String,String> prop = new HashMap<>();
		prop.put("age", "24");
		prop.put("hobby","sports");
		
		
		
		employee.setId(123);
		employee.setName("vijay");
		employee.setPermanent(true);
		employee.setAddress(address);
		employee.setPhoneNumbers(phonenos);
		employee.setRole("project engineer");
		employee.setCities(city);
		employee.setProperties(prop);
		
		System.out.println(employee);
		return employee;

	}
}
