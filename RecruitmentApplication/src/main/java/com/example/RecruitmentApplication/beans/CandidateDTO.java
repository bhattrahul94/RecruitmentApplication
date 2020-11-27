package com.example.RecruitmentApplication.beans;

import com.example.RecruitmentApplication.entity.Candidate;
import com.example.RecruitmentApplication.entity.Job;
import com.example.RecruitmentApplication.utility.ApplicationStatus;

public class CandidateDTO {
	
	private Long id;
	
	private String name;
	
	private String email;
	
	private String phoneno;
	
	private String resume;
	
	private Long job_id;
	
	private ApplicationStatus applicationStatus;
	
	public CandidateDTO() {
		
	}

	public CandidateDTO(String name, String email, String phoneno, String resume, Long job_id) {
		this.name = name;
		this.email = email;
		this.phoneno = phoneno;
		this.resume = resume;
		this.job_id = job_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public Long getJob_id() {
		return job_id;
	}

	public void setJob_id(Long job_id) {
		this.job_id = job_id;
	}

	public ApplicationStatus getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(ApplicationStatus applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	public static CandidateDTO convertToCandidateDTO(Candidate candidate) {
		CandidateDTO candidateDTO = new CandidateDTO();
		candidateDTO.setEmail(candidate.getEmail());
		candidateDTO.setResume(candidate.getResume());
		candidateDTO.setName(candidate.getName());
		candidateDTO.setApplicationStatus(candidate.getApplication_status());
		candidateDTO.setPhoneno(candidate.getPhoneno());
		candidateDTO.setJob_id(candidate.getJob().getId());
		return candidateDTO;
    }
	
	public static Candidate convertToCandidateEntity(CandidateDTO candidateDTO) {
		Candidate candidate = new Candidate();
		candidate.setEmail(candidateDTO.getEmail());
		candidate.setName(candidateDTO.getName());
		candidate.setPhoneno(candidateDTO.getPhoneno());
		candidate.setResume(candidateDTO.getResume());
		candidate.setApplication_status(ApplicationStatus.APPLIED);
		return candidate;
	}

}
