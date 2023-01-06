package com.empSystem.CS.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.empSystem.CS.entity.CompensationEntity;

public interface CompensationRepository extends JpaRepository<CompensationEntity, Long>{
	//change variables from Entity
	@Query(value="SELECT compnid, SUM(amount) AS amount, descrpt, date, Type, employeeID "
			+ "FROM Compensation "
			+ "WHERE employeeID = ?1 GROUP BY MONTH(date) , YEAR(date)", nativeQuery=true)
	public List<CompensationEntity> getCompensationHistory(Long employeeID);
	
	@Query(value="SELECT * FROM compensation WHERE date LIKE CONCAT(YEAR(?1), '-', MONTH(?1), '-%') AND employeeId= ?2 ORDER BY date DESC", nativeQuery=true)
	public List<CompensationEntity> getCompensationBreakdown(Date date, Long employeeId);
	
	@Query(value="SELECT compnid FROM compensation "
			+ "WHERE date LIKE CONCAT(YEAR(?1), '-', MONTH(?1), '-%') AND employeeId = ?2 AND type = 'Salary' LIMIT 1", nativeQuery=true)
	public Long checkEmployeeSalary(Date date, Long employeeID);

}
