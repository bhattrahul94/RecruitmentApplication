package com.example.RecruitmentApplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.RecruitmentApplication.utility.ApplicationStatus;


@Entity
@Table(name = "candidate")
public class Candidate {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phoneno")
	private String phoneno;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "resume")
	private String resume;

		
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "job_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Job job;
	
	
	@Enumerated(EnumType.STRING)
	private ApplicationStatus application_status;
	
	public Candidate() {
		// TODO Auto-generated constructor stub
	}
	
	public Candidate(Long id, String name, String phoneno, String email, String resume, Job job,
			ApplicationStatus application_status) {
		this.id = id;
		this.name = name;
		this.phoneno = phoneno;
		this.email = email;
		this.resume = resume;
		this.job = job;
		this.application_status = application_status;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ApplicationStatus getApplication_status() {
		return application_status;
	}

	public void setApplication_status(ApplicationStatus application_status) {
		this.application_status = application_status;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getResume() {
		return resume;
	}


	public void setResume(String resume) {
		this.resume = resume;
	}


	public Job getJob() {
		return job;
	}


	public void setJob(Job job) {
		this.job = job;
	}


	
}
