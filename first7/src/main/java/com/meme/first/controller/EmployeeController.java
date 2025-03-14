package com.meme.first.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meme.first.model.Employee;
import com.meme.first.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
	


	
//	@Autowired // field injection @ autowired doesn't support final keyword , initial value like null;
//	private  StudentService service;
//	private final StudentService service=null; 
	
	//Dependency injection  employee
	//Constructor injection
	private EmployeeService service;
	public EmployeeController(EmployeeService service) {
		this.service = service;
	}

//	RequestMapping()
	@PostMapping
	public Employee saveEmployee(@RequestBody Employee employee) {
		Employee saveEmployee = service.saveEmployee(employee);
		return saveEmployee;
	
	}

	@PostMapping
	public Employee saveEmp(@RequestBody Employee employee) {
		Employee savedEmp = service.saveEmployee(employee);
		return savedEmp;
	}

	@GetMapping("/{id}")
	public Employee getEmpById(@PathVariable int id) {
		Employee empById = service.getEmpById(id);
		return empById;
	}

}