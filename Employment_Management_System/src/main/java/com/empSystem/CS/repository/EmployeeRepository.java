package com.empSystem.CS.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.empSystem.CS.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	// Duplicate Query
	@Query(value = "SELECT * FROM employee e WHERE e.f_name LIKE CONCAT ('', :fname, '')"
			+ "AND e.m_name LIKE CONCAT('', :mname, '')" + "AND e.l_name LIKE CONCAT('', :lname, '')"
			+ "AND e.b_date LIKE CONCAT('', :bdate, '') LIMIT 1", nativeQuery = true)
	public Long searchDuplicate(String fname, String lname, String mname, Date bdate);

	// Search
	// SEARCH/FILTER
	@Query("SELECT e FROM EmployeeEntity e WHERE " + "e.Fname LIKE CONCAT('%',:fname,'%')"
			+ "AND e.Lname LIKE CONCAT('%',:Lname,'%')" + "AND e.position LIKE CONCAT('%',:position,'%')")
	public List<EmployeeEntity> searchEmployee(String fname, String Lname, String position);

}
