package com.infy.model;

import java.util.List;

public class StudentList {

	private List<Student2> students;

	public StudentList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentList(List<Student2> students) {
		super();
		this.students = students;
	}

	public List<Student2> getStudents() {
		return students;
	}

	public void setStudents(List<Student2> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "StudentList [students=" + students + "]";
	}
}
