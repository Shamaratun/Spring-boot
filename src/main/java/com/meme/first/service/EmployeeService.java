package com.meme.first.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.meme.first.model.Employee;
import com.meme.first.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Service

public class EmployeeService {
	public final EmployeeRepository repository;

	public EmployeeService(EmployeeRepository repository) {
		this.repository = repository;
	}

	public Employee saveEmployee(Employee employee) {
		int save = repository.save(employee);
		return getEmpById(save);
	}

	public Employee getEmpById(int id) {
		Optional<Employee> byId = repository.findById(id);
		return byId.get();
	}

	public List<Employee> getAllEmp() {
		List<Employee> all = repository.findAll();
		return all;

	}

	public void deleteById(int id) {
		repository.deleteById(id);

	}

	public Employee updateEmp(int id, Employee employee) { 
		employee.setId(id);
		repository.update(employee);
		return getEmpById(id);
	}

	      
	    

	public List<Employee> getEmpByName(String name) {
		 return repository.findByName(name); 
	}
	}