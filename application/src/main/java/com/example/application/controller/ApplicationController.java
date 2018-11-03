package com.example.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.service.ApplicationService;
import com.example.application.utill.ApplicationUtill;
import com.example.application.utill.ResponseData;
/**
 * 
 * @author Srinivas Nangana
 *
 */
@RestController
public class ApplicationController {

	@Autowired
	ApplicationUtill applicationUtill;

	@Autowired
	ApplicationService applicationService;

	@GetMapping("/getStudent")
	public ResponseEntity<ResponseData> getStudent() {

		return applicationUtill.responseEntityForFetchSuccess(applicationService.getStudent());
	}
	
	@GetMapping("/getException")
	public ResponseEntity<ResponseData> getException() {

		return applicationUtill.responseEntityForFetchSuccess(applicationService.getException());
	}
}
