package com.example.RecruitmentApplication.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "job")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "startdate")
	@Temporal(TemporalType.DATE)
	private Date startdate;
	
	@Column(name = "numberofapplications")
	private int numberofapplications;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "job",
			 cascade = {CascadeType.PERSIST, CascadeType.MERGE,
							   CascadeType.DETACH, CascadeType.REFRESH})
	private Set<Candidate> candidates;
		

	public Job() {
		// TODO Auto-generated constructor stub
	}


	public Job(Long id, String title, Date startdate, int numberofapplications) {
		this.id = id;
		this.title = title;
		this.startdate = startdate;
		this.numberofapplications = numberofapplications;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getStartdate() {
		return startdate;
	}


	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}


	public int getNumberofapplications() {
		return numberofapplications;
	}


	public void setNumberofapplications(int numberofapplications) {
		this.numberofapplications = numberofapplications;
	}


	public Set<Candidate> getCandidates() {
		return candidates;
	}


	public void setCandidates(Set<Candidate> candidates) {
		this.candidates = candidates;
	}

	
	
	
	
}
