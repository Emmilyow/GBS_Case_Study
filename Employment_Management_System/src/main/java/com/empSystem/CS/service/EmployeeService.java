package com.empSystem.CS.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.empSystem.CS.entity.EmployeeEntity;
import com.empSystem.CS.repository.EmployeeRepository;

public interface EmployeeService {
	
	

	List<EmployeeEntity> getAllEmployee();
	
	EmployeeEntity saveEmployee(EmployeeEntity employeeEntity);
	
	EmployeeEntity getEmployeeById (Long id);
	EmployeeEntity updateEmployee(EmployeeEntity employeeEntity);

	boolean validationBdate(Date bdate);

	public boolean validationExistingEmp(EmployeeEntity employeeEntity) ;
	Long searchDuplicate(String fname, String Lname, String Mname, Date Bdate);
		
	//Search Employees with a given Information
	List<EmployeeEntity> searchEmployees(String fname, String Lname, String position);
	
	
	
	//validate 
	
	
	

}
