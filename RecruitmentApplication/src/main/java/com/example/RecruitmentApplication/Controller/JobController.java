package com.example.RecruitmentApplication.Controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.RecruitmentApplication.Service.CandidateServiceImpl;
import com.example.RecruitmentApplication.Service.JobServiceImpl;
import com.example.RecruitmentApplication.beans.JobDTO;
import com.example.RecruitmentApplication.entity.Job;

@RestController
@RequestMapping("/v1/Job")
public class JobController {
	
		@Autowired
		private CandidateServiceImpl service;
	
		@Autowired
		private JobServiceImpl jobservice;
	
	
		// Get All Job Postings
		@GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<JobDTO>> getAllJobs() {
			System.out.println("Inside Controller");
			List<Job> listOfJobs = this.jobservice.listAll();
			if(listOfJobs.isEmpty()) return ResponseEntity.noContent().build();
			 
			return ResponseEntity.ok().body(listOfJobs.stream().map(JobDTO::convertToJobDTO).collect(Collectors.toList()));
		}
		
		
		
		// Get Job By ID()
		@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<JobDTO> getJobById(@PathVariable("id") Long jobId){
			
			Job job = this.jobservice.get(jobId);
			
			if(job != null) {
				return ResponseEntity.ok().body(JobDTO.convertToJobDTO(job));
			}
			return ResponseEntity.notFound().build();
		}
		
		
		
		
		//Post A New Job
		@PostMapping()
		public ResponseEntity postNewJob(@RequestBody JobDTO jobDTO) {
			Job job = JobDTO.convertToJobEntity(jobDTO);
			
			this.jobservice.save(job);
			
			URI getUri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(job.getId()).toUri();
			return ResponseEntity.created(getUri).build();
		}
		

}
