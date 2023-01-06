package com.empSystem.CS.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.empSystem.CS.entity.CompensationEntity;
import com.empSystem.CS.repository.CompensationRepository;
import com.empSystem.CS.service.CompensationService;

@Service
public class CompensationServiceImpl implements CompensationService{

	
	private CompensationRepository CompRepo;

	public CompensationServiceImpl(CompensationRepository compRepo) {
		super();
		this.CompRepo = compRepo;
	}
	
	@Override
	public List<CompensationEntity> getCompensationHistory(Long employeeID) {
		return CompRepo.getCompensationHistory(employeeID);
	}

	@Override
	public CompensationEntity saveCompensation(CompensationEntity compensationEntity) {
		return CompRepo.save(compensationEntity);
	}

	@Override
	public CompensationEntity getCompensationById(Long CompnID) {
		return CompRepo.findById(CompnID).get();
	}

	@Override
	public List<CompensationEntity> getCompensationBreakdown(Date date, Long employeeId) {
		return CompRepo.getCompensationBreakdown(date, employeeId);
	}

	@Override
	public Long checkEmployeeSalary(Date date, Long employeeID) {
		return CompRepo.checkEmployeeSalary(date, employeeID);
	}
	
}
