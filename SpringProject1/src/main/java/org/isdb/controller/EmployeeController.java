package org.isdb.controller;

import java.util.List;


import org.isdb.model.Employee;
import org.isdb.service.EmployeeService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {


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

	@GetMapping("/{id}")
	public Employee getEmpById(@PathVariable ("id") int id) {
		Employee empById = service.getEmpById(id);
		return empById;
	}

	@GetMapping
	public List<Employee> getAllEmp() {
		List<Employee> allEmp = service.getAllEmp();
		return allEmp;
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable ("id") int id) {
		service.deleteById(id);
	}

	@PutMapping("/{id}")
	public Employee updateEmp(@PathVariable ("id") int id, @RequestBody Employee employee) {
		Employee updated = service.updateEmp(id, employee);
		return updated;
	}
	   @GetMapping("/byName")
	    public List<Employee> getEmpByName(@PathVariable String name) {
		   List<Employee> byName= service.getEmpByName(name); 
		   return byName;
	    }
}
