package edu.hanu.studentManagement.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "course")
public class Course {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private String reference;
	@ManyToMany(mappedBy = "course")
	private Set<User> users = new HashSet<>();
	@OneToMany(mappedBy = "course")
	private Set<File> filesTeacher = new HashSet<>();
	@OneToMany(mappedBy = "course")
	private Set<File> filesSubmission = new HashSet<>();
	
	public Course(long id, String name, String description, String reference, Set<User> users, Set<File> filesTeacher,
			Set<File> filesSubmission) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.reference = reference;
		this.users = users;
		this.filesTeacher = filesTeacher;
		this.filesSubmission = filesSubmission;
	}

	public Course() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<File> getFilesTeacher() {
		return filesTeacher;
	}

	public void setFilesTeacher(Set<File> filesTeacher) {
		this.filesTeacher = filesTeacher;
	}

	public Set<File> getFilesSubmission() {
		return filesSubmission;
	}

	public void setFilesSubmission(Set<File> filesSubmission) {
		this.filesSubmission = filesSubmission;
	}
}
