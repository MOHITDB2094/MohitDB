package com.nt.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nt.model.MyData;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ServiceClass {

	public ResponseEntity<byte[]> downloadExcel(){
		log.info(" Program to download data in Excel");
		
		try(XSSFWorkbook workbook = new XSSFWorkbook();
				ByteArrayOutputStream bos= new ByteArrayOutputStream();
				) {
			XSSFSheet sheet = workbook.createSheet("SheetName");
			
			createHeaderRow(sheet); // to create Excel Head Row
			
			XSSFRow xssfRow;
			int rowId = 1;
			
			List<MyData> datas = new ArrayList<MyData>(); // data to be stored 
			datas.add(new MyData("Mohit", 27, "Software Engineer", 60000l, 9923910843l));
			datas.add(new MyData("Praful", 27,"Software Engineer" , 40000, 6048953761l));
			datas.add(new MyData("Govinda", 27, "QA Engineer", 9004l, 6485328641l));
			
			for (MyData myData : datas) {
				xssfRow = sheet.createRow(rowId++);
				putReportDatainRow(myData, xssfRow); // add data into rows
			}
			
			workbook.write(bos);
			byte[] bytes = bos.toByteArray();
			return ResponseEntity.status(HttpStatus.OK).body(bytes);
		} catch (Exception e) {
			log.error(" Exception came while creating the Excel - error {}", e);
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	private void createHeaderRow(XSSFSheet sheet) {
		Row row = sheet.createRow(0);
		List<String> headers = new ArrayList();
		
		headers.add("NAME");
		headers.add("AGE");
		headers.add("JOB_PROFILE");
		headers.add("CURRENT_BALANCE");
		headers.add("FUTURE_BALANCE");
		
		int cellId = 0;
		for (String header : headers) {
			Cell cell = row.createCell(cellId++);
			cell.setCellValue(header);
		}
	}
	
	private void putReportDatainRow(MyData data,XSSFRow row) {
		List<String> reportValues = new ArrayList<String>();
		// put data in report take care number of row created and these data added should be same for good
		
		reportValues.add(data.getName());
		reportValues.add(String.valueOf(data.getAge()));  // convert non string value to string 
		reportValues.add(data.getJobProfile());
		reportValues.add(String.valueOf(data.getCurentBalance()));
		reportValues.add(String.valueOf(data.getFutureBalance()));
		
		int celId = 0;
		
		for (String value : reportValues) {
			Cell cell = row.createCell(celId++);
			cell.setCellValue(value);
		}
	}
}
