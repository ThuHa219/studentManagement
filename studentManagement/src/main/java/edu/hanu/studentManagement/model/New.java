package edu.hanu.studentManagement.model;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "news")
public class New {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 100)
	private String title;
	@Lob
	private String content;
	@ManyToOne
	@JoinColumn(name = "username", nullable = false)
	private User users;
	@Temporal(TemporalType.DATE)
	private Date date;
	@OneToMany(mappedBy = "news")
	private Set<Comment> comments;
	private String subject;
	public New() {
		super();
	}

	public New(String title, String content, User users, Set<Comment> comments, String subject) {
		super();
		this.title = title;
		this.content = content;
		this.date = new Date(System.currentTimeMillis());
		this.subject = subject;
	}
	
	

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = new Date(System.currentTimeMillis());
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "New [id=" + id + ", title=" + title + ", content=" + content + ", users=" + users + ", date=" + date
				+ ", comments=" + comments + ", subject=" + subject + "]";
	}
}
