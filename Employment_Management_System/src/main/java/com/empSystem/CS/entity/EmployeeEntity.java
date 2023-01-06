package com.empSystem.CS.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Employee")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column(name = "F_name" , nullable = false)
	private String Fname;
	
	@Column(name = "M_name" , nullable = false)
	private String Mname;
	
	@Column(name = "L_name" , nullable = false)
	private String Lname;
	
	@Column(name = "B_date" , nullable = false)
	private Date bdate;
	@Column(name = "pos" , nullable = false)
	private String position;
	
	public EmployeeEntity() {
		
	}



	public EmployeeEntity(Long id, Long employeeId, String fname, String mname, String lname, Date bdate,
			String position) {
		super();
		this.Id = id;
		this.Fname = fname;
		this.Mname = mname;
		this.Lname = lname;
		this.bdate = bdate;
		this.position = position;
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		this.Id = id;
	}

	


	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		this.Fname = fname;
	}

	public String getMname() {
		return Mname;
	}

	public void setMname(String mname) {
		this.Mname = mname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		this.Lname = lname;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}



	public Object findExistingEmployee(String fname2, String mname2, String lname2, Date bdate2) {
		// TODO Auto-generated method stub
		return null;
	}

}
