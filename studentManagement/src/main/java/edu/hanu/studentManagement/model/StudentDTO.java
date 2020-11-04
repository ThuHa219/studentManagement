package edu.hanu.studentManagement.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import edu.hanu.studentManagement.annotation.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
})
public class StudentDTO {
	@NotEmpty
	private String name;
	@NotEmpty
	private String department;
	private String dob;
	@NotEmpty
	private Gender gender;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String confirmPassword;
	@NotEmpty
	private String studentUsername;
	
	public StudentDTO(String name, String department, String dob, Gender gender, String email, String password,
			String confirmPassword, String studentUsername) {
		super();
		this.name = name;
		this.department = department;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.studentUsername = studentUsername;
	}

	public StudentDTO() {
		super();
	}

	public String getStudentUsername() {
		return this.studentUsername;
	}

	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
