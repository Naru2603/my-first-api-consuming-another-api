package com.infy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.model.Student;
import com.infy.model.Student2;
import com.infy.service.VotingApiService;

@RestController
public class MyRestControllerForStudentResource {

	@Autowired
	VotingApiService apiService;

	@GetMapping("/get_all_students")
	public ResponseEntity<List<Student>> getAllStudents() {
		System.out.println("inside getAllStudents controller...");
		return apiService.getAllStudent();
	}

	@GetMapping("/get_all_students/{subjects}")
	public ResponseEntity<List<Student2>> getAllStudentsWithParticularSubject(@PathVariable("subject") String subject) {
		System.out.println("inside getAllStudents controller...");
		return apiService.getAllStudentsWithParticularSubject(subject);
	}

	@PostMapping("/create_student")
	public ResponseEntity<Student> createNewStudent(@RequestBody Student std) {
		System.out.println("inside create student controller...");
		return apiService.createStudent(std);
	}

	@PutMapping("/update_student")
	public ResponseEntity<String> updateStudent(@RequestBody Student std) {
		System.out.println("inside update student controller...");
		return apiService.updateStudent(std);
	}
	
	@GetMapping("/delete_student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int id) {
		System.out.println("inside Delete student controller...");
		return apiService.deleteStudent(id);
	}
}
