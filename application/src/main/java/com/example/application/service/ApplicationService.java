package com.example.application.service;

import org.springframework.stereotype.Service;

import com.example.application.bean.Student;

@Service
public class ApplicationService {

	public Student getStudent() {

		return new Student(1, "Srinivas");
	}

	public Object getException() {

		if(0/0==0)
			System.out.println();
		return null;
	}
}
