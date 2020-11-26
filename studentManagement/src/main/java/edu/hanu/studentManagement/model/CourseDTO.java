package edu.hanu.studentManagement.model;

public class CourseDTO {
	private String name;
	private String description;
	private String references;
	
	public CourseDTO(String description, String references) {
		super();
		this.description = description;
		this.references = references;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
