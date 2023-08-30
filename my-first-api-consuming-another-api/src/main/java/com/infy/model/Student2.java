package com.infy.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Student2 {

	int roll;
	String name;
	String address;

	@Autowired
	List<Subject> subject;

	public int getRoll() {
		return roll;
	}

	public void setRoll(int roll) {
		this.roll = roll;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Subject> getSubject() {
		return subject;
	}

	public void setSubject(List<Subject> subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "Student2 [roll=" + roll + ", name=" + name + ", address=" + address + ", subject=" + subject + "]";
	}

}
