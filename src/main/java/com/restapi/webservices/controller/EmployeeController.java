package com.restapi.webservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.webservices.model.Employee;
import com.restapi.webservices.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	//build create employee REST API..
	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		
		return new ResponseEntity<Employee>(this.employeeService.saveEmployee(employee), HttpStatus.CREATED);
	}
	
	//build get all employee REST API..
	@GetMapping
	public List<Employee> getAllEmployees(){
		return this.employeeService.getAllEmployees();
	}
	
	//build get Employee by id REST API..
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeebyId(@PathVariable("id") long epid) {
		return new ResponseEntity<Employee>(this.employeeService.getEmployeeById(epid), HttpStatus.OK);
	}
	
	//build update employee REST API
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee){
		return new ResponseEntity<Employee>(this.employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
		
	//build delete employee REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") long id){
		this.employeeService.deleteEmployee(id);
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
}
