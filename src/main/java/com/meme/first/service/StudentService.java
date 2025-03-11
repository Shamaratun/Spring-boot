package com.meme.first.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.meme.first.model.Student;
import com.meme.first.repository.StudentRepository;

//@Service
@Component
public class StudentService {
	public final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public Student saveStudent(Student student) {
		if (student != null)
			return repository.save(student);
		else
			return null;
	}

	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	public Optional<Student> findStudentById(int id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}
	public List<Student> getStudentsByName(String name) {

		return repository.findAllByName( name);
}}
