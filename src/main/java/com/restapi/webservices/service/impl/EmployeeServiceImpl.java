package com.restapi.webservices.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.webservices.exception.ResourceNotFoundException;
import com.restapi.webservices.model.Employee;
import com.restapi.webservices.repository.EmployeeRepository;
import com.restapi.webservices.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {

		return this.employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		 Optional<Employee> employee = this.employeeRepository.findById(id);
//		 if(employee.isPresent()) {
//			 return employee.get();
//		 }else {
//			 throw new ResourceNotFoundException("Employee", "Id", id);
//		 }
		
		return this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//we need to check whether employee with given id is exist in DB or not
		Employee existingEmployee=this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//save existing employee to DB
		Employee updateEmployee = this.employeeRepository.save(existingEmployee);
		
		return updateEmployee;
	}

	@Override
	public void deleteEmployee(long id) {

		//check whether a employee exist in DB ot not
		this.employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		
		this.employeeRepository.deleteById(id);
	}

}
