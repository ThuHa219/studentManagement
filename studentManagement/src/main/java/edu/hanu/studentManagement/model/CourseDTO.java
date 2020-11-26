package edu.hanu.studentManagement.model;

public class CourseDTO {
	private String description;
	private String references;
	private File assigment;
	private File lecture;
	private File tutorial;
	
	public CourseDTO(String description, String references, File assigment, File lecture, File tutorial) {
		super();
		this.description = description;
		this.references = references;
		this.assigment = assigment;
		this.lecture = lecture;
		this.tutorial = tutorial;
	}

	public CourseDTO() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReferences() {
		return references;
	}

	public void setReferences(String references) {
		this.references = references;
	}

	public File getAssigment() {
		return assigment;
	}

	public void setAssigment(File assigment) {
		this.assigment = assigment;
	}

	public File getLecture() {
		return lecture;
	}

	public void setLecture(File lecture) {
		this.lecture = lecture;
	}

	public File getTutorial() {
		return tutorial;
	}

	public void setTutorial(File tutorial) {
		this.tutorial = tutorial;
	}
}
