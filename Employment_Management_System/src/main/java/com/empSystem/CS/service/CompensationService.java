package com.empSystem.CS.service;

import java.sql.Date;
import java.util.List;

import com.empSystem.CS.entity.CompensationEntity;

public interface CompensationService {

	//change variables from Entity
	
	// Fetch all Compensation Data of an Employee
	List<CompensationEntity> getCompensationHistory(Long employeeID);

	// Fetch sum of all compensations per month each year
	// List<Compensation> getCompensationPerMonth();

	// Insert New Compensation Data in Database
	CompensationEntity saveCompensation(CompensationEntity compensationEntity);

	// Fetch Compensation by ID
	CompensationEntity getCompensationById(Long CompnID);

	// Fetch Compensations breakdown
	List<CompensationEntity> getCompensationBreakdown(Date date , Long employeeId);

	// Search if Salary already exists
	Long checkEmployeeSalary(Date date, Long employeeID);

}
