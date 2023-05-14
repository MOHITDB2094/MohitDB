package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.ServiceClass;

@RestController
@RequestMapping("/report/excel")
public class ReportExcelController {

	@Autowired
	private ServiceClass serviceClass;
	
	@PostMapping()
	public ResponseEntity<byte[]> downloadtoExcel(){
		return serviceClass.downloadExcel();
	}
}
