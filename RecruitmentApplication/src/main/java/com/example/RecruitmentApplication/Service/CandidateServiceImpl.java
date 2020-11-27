package com.example.RecruitmentApplication.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RecruitmentApplication.dao.CandidateDAO;
import com.example.RecruitmentApplication.dao.JobDAO;
import com.example.RecruitmentApplication.entity.Candidate;
import com.example.RecruitmentApplication.entity.Job;


@Service
@Transactional
public class CandidateServiceImpl implements CandidateService {
	
	@Autowired
	private CandidateDAO repo;
	
	@Autowired 
	private JobDAO jobRepo;
	
	@Autowired 
	private JobServiceImpl service;
	
	
	@Override
	public List<Candidate> listAll(){
		
		return repo.findAll();
	}
	
	
	@Override
	public void createJobApplication(Candidate candidateApplication) {
		System.out.println("Inside createJobApplication.");
		if(! candidateApplied(candidateApplication.getEmail())){
			System.out.println("Inside if");
			repo.save(candidateApplication);
			jobRepo.increaseNumOfApplication(candidateApplication.getJob().getId());
		}
		
		
	}
	
	@Override
	public Candidate getCandidateById(Long id) {
		return repo.findById(id).orElse(null);
	}
	
	
	@Override
	public List<Candidate> getAllCandidatesByJobId(Long jobId){
		System.out.println("===========>>>> jobId " + jobId);
		Job job = service.get(jobId);
		
		if(job != null) {
			System.out.println("================>>> job " + job.getTitle());
			Set<Candidate> totalCandidates = job.getCandidates();
			return  totalCandidates.stream().collect(Collectors.toList());
		}
		return null;
	}
	
	@Override
	public boolean candidateApplied(String email) {
		List<Candidate> totalCandidates = repo.findAll();
		Optional<Candidate> matchingCandidate = totalCandidates.stream().filter(i -> i.getEmail().equals(email)).findFirst();
		if(matchingCandidate == null) {
			return true;
		}else {
			return false;
		}
	}
	
	

}
