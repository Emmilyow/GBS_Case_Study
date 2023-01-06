package com.empSystem.CS.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Compensation")
public class CompensationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CompnID;

	@Column(name = "employeeID", nullable = false)
	private Long employeeID;

	@Column(name = "Type", nullable = false)
	private String Type;

	@Column(name = "amount", nullable = false)
	private double amount;

	@Column(name = "descrpt", nullable = false)
	private String descrpt;

	@Column(name = "date", nullable = false)
	private Date date;

	public CompensationEntity() {

	}

	public CompensationEntity(Long employeeID, Long CompnID, String type, double amount, String descrpt, Date date) {
		super();
		this.CompnID = CompnID;
		this.employeeID = employeeID;
		this.Type = type;
		this.amount = amount;
		this.descrpt = descrpt;
		this.date = date;

	}

	public Long getemployeeID() {
		return employeeID;
	}

	public void setFK(Long employeeID) {
		this.employeeID = employeeID;
	}

	public Long getCompnID() {
		return CompnID;
	}

	public void setCompnID(Long CompnID) {
		this.CompnID = CompnID;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		this.Type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescrpt() {
		return descrpt;
	}

	public void setDescrpt(String descrpt) {
		this.descrpt = descrpt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getCompensationDate() {
		
		return date;
	}

	public void setCompensationDate(Date date) {
		this.date = date;
	}

	public Long getEmployeeId() {
		// TODO Auto-generated method stub
		return employeeID;
	}
	public void setEmployeeId(Long employeeID) {
		this.employeeID = employeeID;
	}
	
	

	

}
