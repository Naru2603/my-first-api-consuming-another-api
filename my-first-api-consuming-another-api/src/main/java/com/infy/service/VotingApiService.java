package com.infy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.infy.intercepters.MyRequestLoggerInterceptor;
import com.infy.model.Person;
import com.infy.model.Student;
import com.infy.model.Student2;
import com.infy.model.Subject;

@Service
public class VotingApiService {

	@Autowired
	RestTemplate restTemplate;

	public ResponseEntity<List<Student>> getAllStudent() {
		String url = "http://localhost:8001/my-first-rest-api-using-spring-mvc/students";
		
		List<ClientHttpRequestInterceptor> myInterceptorList = new ArrayList<>();
		myInterceptorList.add(new MyRequestLoggerInterceptor());
		restTemplate.setInterceptors(myInterceptorList);
		
//		restTemplate.setInterceptors(Collections.singletonList(new MyRequestLoggerIntercepter()));

		List<Student> list = restTemplate.getForObject(url, List.class);

//		ResponseEntity<List<Student>> result = new ResponseEntity<List<Student>>(list, HttpStatus.OK);
//		return result;

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("header1", "123");
		resHeaders.add("myheader", "Accept/Header");

		return ResponseEntity.ok().headers(resHeaders).body(list);
	}

	public ResponseEntity<Student> createStudent(Student std) {

		String url = "http://localhost:8001/my-first-rest-api-using-spring-mvc/students";

		Student retrivedStd = restTemplate.postForObject(url, std, Student.class);

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");

		return ResponseEntity.ok().headers(resHeaders).body(retrivedStd);
	}

	public ResponseEntity<String> updateStudent(Student std) {

		String url = "http://localhost:8001/my-first-rest-api-using-spring-mvc/students";

		try {
			restTemplate.put(url, std);
		} catch (Exception e) {
			System.out.println("Exception while calling update api " + e.getMessage());
			return new ResponseEntity<String>("Error while updating the student", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");

		return ResponseEntity.ok().headers(resHeaders).body("Updated Successfully");
	}

	public ResponseEntity<String> doVoting() {

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("header1", "Update");
		resHeaders.add("myheader", "Accept/Header");

		Person person = new Person(14, "Sukhada", 21, "India");

		String body = restTemplate.postForObject("http://localhost:8001/my-first-rest-api-using-spring-mvc/vote",
				person, String.class);

		ResponseEntity result = new ResponseEntity(body, resHeaders, HttpStatus.ALREADY_REPORTED);
		return result;
	}

	public ResponseEntity<List<Student2>> getAllStudentsWithParticularSubject(String sub) {

		String url = "http://localhost:8001/rest-api-assignment-using-springMVC/students";

		Student2[] studentsArr = restTemplate.getForObject(url, Student2[].class);

		List<Student2> filteredStudents = new ArrayList();

		for (Student2 s : studentsArr) {
			for (Subject subject : s.getSubject()) {
				if (subject.getName().equalsIgnoreCase(sub))
					filteredStudents.add(s);
			}
		}

		return ResponseEntity.ok().body(filteredStudents);
	}

	public ResponseEntity<String> deleteStudent(int id) {
		
		String url = "http://localhost:8001/my-first-rest-api-using-spring-mvc/students/ "+id;

		try {
			restTemplate.delete(url,id);
		} catch (Exception e) {
			System.out.println("Exception while calling Delete api " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<String>("Error while deleting the student..!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("xyz", "123");
		resHeaders.add("pqr", "aaaa");

		return ResponseEntity.ok().headers(resHeaders).body("Deleted Successfully..!!");
	}
}
