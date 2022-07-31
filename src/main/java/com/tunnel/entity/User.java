package com.tunnel.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.tunnel.exceptions.*;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Embeddable
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "mobile_number", "email"})})
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Size(min=4, max = 15, message="Username should be more than 4 and less than 15 character")
	@Column(name = "username")
	private String username;

	@Size(min=6, max = 20, message = "password should be more than 6 and less than 20 character")
	@Column(name = "password")
	@JsonIgnore
	private String password;

	@Column(name = "active")
	private boolean active;

	@OneToMany(mappedBy = "user")
	private Set<Post> posts;

	@Size(min=1, message="First name should be at-least one characters")
	@Column(name = "first_name")
	private String firstName;
	
	@Size(min=1, message="Last name should be at-least one characters")
	@Column(name = "last_name")
	private String lastName;
	
	@Past(message="Date of birth should be in past")
	@Column(name = "dob")
	private LocalDate dob;

	@Column(name = "created_date")
	private LocalDate createDate;
	
	@Column(name = "mobile_number")
	private Long mobileNumber;
	
	@Column(name = "age")
	private Integer age;

	@Column(name = "email")
	private String email;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "userRoleId", referencedColumnName = "userRoleId")
	private UserRole userRole;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public User () {
		this.createDate = LocalDate.now();
	};
	
	public User(String username,Boolean active, String firstName, String lastName, LocalDate dob, Long mobileNumber, String email) {
		super();
		this.username = username;
		this.active = active;
		this.firstName = firstName;
		this.lastName = lastName;
		this.setDob(dob);
		if(this.age < 18) {
			throw new AgeValidateionException("User should be atleast 18 years old");
		} else if(this.age > 100) {
			throw new AgeValidateionException("Are you really that old!");
		}
		this.createDate = LocalDate.now();
		this.mobileNumber = mobileNumber;
		this.email = email;
	}

	public User(@Valid User user) {
		super();
		this.username = user.username;
		this.active = user.active;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		//verifying the age of user
		//which should be above 18
		this.setDob(user.dob);
		if(this.age < 18) {
			throw new AgeValidateionException("User should be at-least 18 years old");
		} else if(this.age > 100) {
			throw new AgeValidateionException("Are you really that old!");
		}
		this.createDate = user.createDate;
		this.mobileNumber = user.mobileNumber;
		this.email = user.email;
		this.userRole = user.userRole;
		this.posts = user.posts;
		this.setPassword(user.password);
	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", mobileNumber=" + mobileNumber + ", age=" + age + ", email=" + email + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public Long getUserId() {
		return userId;
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

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	protected String getPassword(){
		return password;
	}

	public String getRoleName(){
		UserRole userRole = this.getUserRole();
		Role role = userRole.getRole();
		String roleName = role.getRoleName();
		return roleName;
	}

	public Long getRoleId(){
		UserRole userRole = this.getUserRole();

		return userRole.getUserRoleId();
	}
}
