package com.meme.first.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.meme.first.model.Student;
import com.meme.first.repository.StudentRepository;

@Service
public class StudentService {
	public final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public Student saveStudent(Student student) {
		Student saved = repository.save(student);
		return saved;
	}

	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

}
