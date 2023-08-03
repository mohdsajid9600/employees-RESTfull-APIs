package com.restapi.webservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.webservices.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
}
