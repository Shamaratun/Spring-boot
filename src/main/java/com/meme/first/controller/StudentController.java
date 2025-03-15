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

import com.meme.first.model.Student;
import com.meme.first.service.StudentService;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);

	
//	@Autowired // field injection @ autowired doesn't support final keyword , initial value like null;
//	private  StudentService service;
//	private final StudentService service=null; 
	
	//Dependency injection
	//Constructor injection
	private StudentService service;
	public StudentController(StudentService service) {
		this.service = service;
	}

	

	@PostMapping
	public Student saveStudent(@RequestBody Student student) {
		Student saveStudent = service.saveStudent(student);
		return saveStudent;
	}

//	public Student saveStudent(Student student) {
//		service.saveStudent(student);
	@GetMapping
	public List<Student> getStudents() {

		return service.getStudents();

	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable int id) {
		service.deleteById(id);
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody Student student) {
		Optional<Student> existingStudent = service.findStudentById(id);
		Student st = null;
		if (existingStudent.isPresent()) {
			st = existingStudent.get();
			if (st.getName() != student.getName())
				st.setName(student.getName());
			if (st.getAddress() != student.getAddress())
				st.setAddress(student.getAddress());
			if (st.getAge() != student.getAge())
				st.setAge(student.getAge());
			if (st.getClazz() != student.getClazz())
				st.setClazz(student.getClazz());
			if (st.getDob() != student.getDob())
				st.setDob(student.getDob());

		}
		return service.saveStudent(st);
	}
	@GetMapping("/byName")
	public List<Student> getStudentsByName(String name) {
		List<Student> students= service.getStudentsByName(name);
		return students;
	}
}
