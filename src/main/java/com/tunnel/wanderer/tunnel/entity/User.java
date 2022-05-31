package com.tunnel.wanderer.tunnel.entity;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String firtName;
	private String lastName;
	private LocalDate dob;
	private Long mobileNumber;
	private Integer age;
	private String email;

	public User () {};
	
	public User(String firtName, String lastName, LocalDate dob, Long mobileNumber, String email) {
		super();
		this.firtName = firtName;
		this.lastName = lastName;
		this.setDob(dob);
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firtName=" + firtName + ", lastName=" + lastName + ", dob=" + dob
				+ ", mobileNumber=" + mobileNumber + ", age=" + age + ", email=" + email + "]";
	}

	public String getFirtName() {
		return firtName;
	}

	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		Period period = Period.between(dob, LocalDate.now());
		this.age = period.getYears();
		this.dob = dob;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public Integer getAge() {
		return age;
	}
	
}
