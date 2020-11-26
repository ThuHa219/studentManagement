package edu.hanu.studentManagement.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;

@Entity(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
	@Id
	@Column(length = 10)
	private String username;
	private String password;
	private boolean enabled;
	@Column(length = 30)
	@Email
	private String email;
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "authorities")
	private Set<String> authorities;
	@OneToMany(mappedBy = "users")
	private Set<New> news;
	@OneToMany(mappedBy = "users")
	private Set<Comment> comments;
	@OneToMany(mappedBy = "users")
	private Set<File> files;
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "user_course", 
        joinColumns = { @JoinColumn(name = "username") }, 
        inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
	private Set<Course> course;
	@Lob
	private String description;

	public User() {
		// do nothing
	}

	public User(String email, String username, String password, boolean enabled,
			Set<String> authorities, String description, Set<New> news, Set<Comment> comments) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authorities = authorities;
		this.description = description;
		this.news = news;
		this.comments = comments;
		this.email = email;
	}

	public Set<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<String> authorities) {
		this.authorities = authorities;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<New> getNews() {
		return news;
	}

	public void setNews(Set<New> news) {
		this.news = news;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<File> getFiles() {
		return files;
	}

	public void setFiles(Set<File> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", enabled=" + enabled + ", authorities="
				+ authorities + "]";
	}
}
