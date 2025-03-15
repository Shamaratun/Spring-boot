package com.meme.first.service;



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

}