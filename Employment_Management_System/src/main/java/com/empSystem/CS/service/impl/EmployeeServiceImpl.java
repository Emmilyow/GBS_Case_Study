package com.empSystem.CS.service.impl;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.empSystem.CS.entity.EmployeeEntity;
import com.empSystem.CS.repository.EmployeeRepository;
import com.empSystem.CS.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<EmployeeEntity> getAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeEntity saveEmployee(EmployeeEntity employeeEntity) {

		return employeeRepository.save(employeeEntity);
	}

	@Override
	public EmployeeEntity getEmployeeById(Long id) {

		return employeeRepository.findById(id).get();
	}

	@Override
	public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity) {

		return employeeRepository.save(employeeEntity);
	}

	@Override
	public Long searchDuplicate(String fname, String Lname, String Mname, Date Bdate) {
		return employeeRepository.searchDuplicate(fname, Lname, Mname, Bdate);
	}

	@Override
	public boolean validationBdate(Date bdate) {

		return false;
	}

	@Override
	public boolean validationExistingEmp(EmployeeEntity employeeEntity) {
		if (employeeEntity.findExistingEmployee(employeeEntity.getFname(), employeeEntity.getMname(),
				employeeEntity.getLname(), employeeEntity.getBdate()) != null) {
			return true;// the employee already exists
		}
		return false;
	}

	public Boolean validationBirthdate(Date birthd) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strBirth = dateFormat.format(birthd);

		LocalDate birthdate = LocalDate.parse(strBirth);
		LocalDate currentDate = LocalDate.now();

		long numberOfDays = ChronoUnit.DAYS.between(birthdate, currentDate);
		if (numberOfDays < 6570) {// natural year (365) x 18
			return false;
		}
		return true;
	}

	@Override
	public List<EmployeeEntity> searchEmployees(String fname, String Lname, String position) {
		if (fname != null || Lname != null || position != null) {
			return employeeRepository.searchEmployee(fname, Lname, position);
		}
		return employeeRepository.findAll();
	}
}
