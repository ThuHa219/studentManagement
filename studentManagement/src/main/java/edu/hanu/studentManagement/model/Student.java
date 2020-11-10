package edu.hanu.studentManagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "student")
@DiscriminatorValue("student")
public class Student extends User {
	@Column(name = "student_name", length = 30)
	private String name;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(length = 50)
	private String dateOfBirth;
	@Column(length = 30)
	private String major;
	@Column(length = 30)
	private String department;
	@Column(length = 30)
	private String cohort;
	@Column(length = 30)
	private String academicAdvior;
	@Column(length = 30)
	private String email;

	public Student() {
		super();
	}

	public Student(String id, String name, Gender gender, String dateOfBirth, String major, String department,
			String cohort, String academicAdvior, String userName, String email, String password, boolean enabled,
			Set<String> authorities, String description, Set<New> news, Set<Comment> comments) {
		super(id, password, enabled, authorities, description, news, comments);
		this.name = name;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.major = major;
		this.department = department;
		this.cohort = cohort;
		this.academicAdvior = academicAdvior;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCohort() {
		return cohort;
	}

	public void setCohort(String cohort) {
		this.cohort = cohort;
	}

	public String getAcademicAdvior() {
		return academicAdvior;
	}

	public void setAcademicAdvior(String academicAdvior) {
		this.academicAdvior = academicAdvior;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
