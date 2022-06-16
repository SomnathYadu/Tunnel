package com.tunnel.entity;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.tunnel.exceptions.*;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User {
	
	@Id
//	@SequenceGenerator(name = "user_id_gen", sequenceName = "user_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=4, message="Username should be atleast four characters")
	@Column(name = "username")
	private String username;
	
	@Size(min=1, message="First name should be atleast one characters")
	@Column(name = "firt_name")
	private String firtName;
	
	@Size(min=1, message="Last name should be atleast one characters")
	@Column(name = "last_name")
	private String lastName;
	
	@Past(message="Date of birth should be in past")
	@Column(name = "dob")
	private LocalDate dob;
	
	@Column(name = "mobile_number")
	private Long mobileNumber;
	
	@Column(name = "age")
	private Integer age;
	@Column(name = "email")
	private String email;

	public User () {};
	
	public User(String username, String firtName, String lastName, LocalDate dob, Long mobileNumber, String email) {
		super();
		this.username = username;
		this.firtName = firtName;
		this.lastName = lastName;
		this.setDob(dob);
		if(this.age < 18) {
			throw new AgeValidateionException("User should be atleast 18 years old");
		} else if(this.age > 100) {
			throw new AgeValidateionException("Are you really that old!");
		}
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public User(@Valid User user) {
		super();
		this.username = user.username;
		this.firtName = user.firtName;
		this.lastName = user.lastName;
		//verifying the age of user
		//which should be above 18
		this.setDob(user.dob);
		if(this.age < 18) {
			throw new AgeValidateionException("User should be atleast 18 years old");
		} else if(this.age > 100) {
			throw new AgeValidateionException("Are you really that old!");
		}
		this.mobileNumber = user.mobileNumber;
		this.email = user.email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", firtName=" + firtName + ", lastName=" + lastName + ", dob=" + dob
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

	public void setDob(LocalDate dob) throws AgeValidateionException{
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
