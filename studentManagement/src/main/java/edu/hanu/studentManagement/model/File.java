package edu.hanu.studentManagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class File {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String fileName;
	private String fileType;
	private String week;
	@Lob
	private byte[] data;
	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User users;
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	public File(String fileName, String fileType, byte[] data, User users) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		this.users = users;
	}

	public File() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
}
