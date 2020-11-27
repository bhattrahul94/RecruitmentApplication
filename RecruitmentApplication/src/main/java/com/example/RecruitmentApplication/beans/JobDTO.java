package com.example.RecruitmentApplication.beans;


import java.util.Date;

import com.example.RecruitmentApplication.entity.Job;


public class JobDTO {
	
	private Long id;
	
	private String title;
	
	private Date startdate;
	
	private int numberofapplications;
	
	
	public JobDTO() {
		
	}

	public JobDTO(String title, Date startdate, int numberofapplications) {
		super();
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
	
	
	public static JobDTO convertToJobDTO(Job job) {
		JobDTO jobDTO = new JobDTO();
		jobDTO.setId(job.getId());
		jobDTO.setTitle(job.getTitle());
		jobDTO.setStartdate(job.getStartdate());
		jobDTO.setNumberofapplications(job.getNumberofapplications());
        return jobDTO;
    }
	
	public static Job convertToJobEntity(JobDTO jobDTO) {
		Job job = new Job();
		job.setTitle(jobDTO.getTitle());
		job.setStartdate(jobDTO.getStartdate());
		job.setNumberofapplications(0);
		return job;
	}
	
	

}
