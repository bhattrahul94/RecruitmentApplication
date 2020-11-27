package com.example.RecruitmentApplication.Controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.RecruitmentApplication.Service.CandidateServiceImpl;
import com.example.RecruitmentApplication.Service.JobServiceImpl;
import com.example.RecruitmentApplication.beans.CandidateDTO;
import com.example.RecruitmentApplication.entity.Candidate;
import com.example.RecruitmentApplication.entity.Job;


@RestController
@RequestMapping("v1/Candidate")
public class CandidateController {
	
		@Autowired
		private CandidateServiceImpl service;
	
		@Autowired
		private JobServiceImpl jobservice;
	
	
		// Apply for job
		@PostMapping
		public ResponseEntity applyForJob(@RequestBody CandidateDTO candidateDTO) {
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<Inside Controller>>>>>>>>>>>>>>>>>>>>>>");
			Job job = jobservice.get(candidateDTO.getJob_id());
			System.out.println("Title ------------->>>>>>"+ job.getTitle());
			Candidate candidate = CandidateDTO.convertToCandidateEntity(candidateDTO);
			
			
			if(job == null) {
				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
			}
			candidate.setJob(job);
			
			if(service.candidateApplied(candidateDTO.getEmail())) {
				throw new ResponseStatusException(HttpStatus.CONFLICT);
			}
			
			System.out.println("creating Job Application");
			service.createJobApplication(candidate);
			
			URI getUri = ServletUriComponentsBuilder.fromCurrentRequest()
	                .path("/{id}")
	                .buildAndExpand(job.getId()).toUri();
	        return ResponseEntity.created(getUri).build();
		}
		
		
		// Get All Candidates by jobId
		@GetMapping(path = "/getByJobId/{jobId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<CandidateDTO>> getCandidatesByJobId(@PathVariable("jobId") Long jobId){
			List<Candidate> jobApplicants = this.service.getAllCandidatesByJobId(jobId);
			
			if(jobApplicants == null || jobApplicants.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			
			return ResponseEntity.ok().body(jobApplicants.stream().map(CandidateDTO::convertToCandidateDTO).collect(Collectors.toList()));
		}
		
		
		//Get Candidate by candidateId
		@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
		public ResponseEntity<CandidateDTO> getCandidateById(@PathVariable("id") Long id){
			Candidate candidate = service.getCandidateById(id);
			if(candidate == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Candidate not found");
			}
			return ResponseEntity.ok().body(CandidateDTO.convertToCandidateDTO(candidate));
		}

}
