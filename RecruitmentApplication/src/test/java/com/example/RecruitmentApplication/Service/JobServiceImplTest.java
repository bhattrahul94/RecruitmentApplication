package com.example.RecruitmentApplication.Service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.example.RecruitmentApplication.dao.JobDAO;
import com.example.RecruitmentApplication.entity.Candidate;
import com.example.RecruitmentApplication.entity.Job;


@ExtendWith(MockitoExtension.class)
@TestInstance(value = Lifecycle.PER_CLASS)
class JobServiceImplTest {
	
	@Autowired 
	MockMvc mvc;
	
	@Mock
	JobDAO jobDAO; 
	
	@InjectMocks
	JobServiceImpl jobService = new JobServiceImpl();
	
	private Job job1;
	private Job job2;
	private Job job3;
	
	private List<Job> listOfJobs;
	
	 
	@BeforeAll
	void setup() {
		listOfJobs = new ArrayList<Job>();
		job1 = new Job(1L, "teacher", new Date(), 0);
		job2 = new Job(2L, "mechanic", new Date(), 0);
		job2 = new Job(3L, "soldier", new Date(), 0);
		listOfJobs.add(job1);
		listOfJobs.add(job2);
		listOfJobs.add(job3);
	}

	@Test
	void testListAll() {
		Mockito.when(jobDAO.findAll()).thenReturn(listOfJobs);
		
		List<Job> jobs = jobService.listAll();
		
		Assertions.assertNotNull(jobs);
		
		Assertions.assertEquals(jobs, listOfJobs);
	}

	@Test
	void testGet() {
		Long id = 1L;
		Optional<Job> job = Optional.of(listOfJobs.get(0));
		
		Mockito.when(jobDAO.findById(id)).thenReturn(job);
		
		Job requiredJob = jobService.get(id);
		
		Assertions.assertNotNull(requiredJob);
		Assertions.assertEquals(job.get(), requiredJob);
	}

}
