package com.example.RecruitmentApplication.Service;

import java.util.List;

import com.example.RecruitmentApplication.entity.Candidate;

public interface CandidateService {
	
	public Candidate getCandidateById(Long ApplicationId);

    public List<Candidate> getAllCandidatesByJobId(Long jobId);

    public boolean candidateApplied(String email);
    
    public void createJobApplication(Candidate candidateApplication);
    
    public List<Candidate> listAll();

}
