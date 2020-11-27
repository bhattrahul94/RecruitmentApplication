package com.example.RecruitmentApplication.Controller;

import static org.junit.Assert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.RecruitmentApplication.Service.JobService;
import com.example.RecruitmentApplication.entity.Job;

public class JobControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private JobService jobService;
	
	private Job job1;
	private Job job2;
	

	@Before
	public void setup() {
//		Long id, String title, Date startdate, int numberofapplications
		job1 = new Job(1L, "Developer", new Date(),0);
		job2 = new Job(2L, "Tester", new Date(),1);
	}
	
	
	@Test
	public void testGetAllJobs() throws Exception {
		
		Mockito.when(jobService.listAll()).thenReturn(Arrays.asList(job1, job2));	
		
		this.mockMvc.perform(get("/rest/v1/Job/list"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.*", hasSize(2)));
					
	}
	
	@Test
	public void testGetAllJobsEmpty() throws Exception {
		
		Mockito.when(jobService.listAll()).thenReturn(Collections.emptyList());	
		
		this.mockMvc.perform(get("/rest/v1/Job/list"))
					.andExpect(status().isNotFound());
					
	}

	@Test
	public void testGetJobById() throws Exception {
		Mockito.when(jobService.get(1L)).thenReturn(job1);	
		
		this.mockMvc.perform(get("/rest/v1/Job/list/" + job1.getId()))
				.andExpect(status().isOk());
	}

}
