package com.example.RecruitmentApplication.Controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.RecruitmentApplication.Service.JobServiceImpl;
import com.example.RecruitmentApplication.entity.Job;


@SpringBootTest
//@ExtendWith(MockitoExtension.class)
@TestInstance(value = Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@MockitoSettings(strictness = Strictness.LENIENT)
public class JobControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private JobServiceImpl jobService;
	
	private Job job1;
	private Job job2;
	

	@BeforeAll
	public void setup() {
//		Long id, String title, Date startdate, int numberofapplications
		job1 = new Job(1L, "Developer", new Date(),0);
		job2 = new Job(2L, "Tester", new Date(),1);
	}
	
	
	@Test
	public void testGetAllJobs() throws Exception {
		
		Mockito.when(jobService.listAll()).thenReturn(Arrays.asList(job1, job2));	
		
		this.mockMvc.perform(get("/v1/Job/list"))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.*", hasSize(2)));
					
	}
	
	@Test
	public void testGetAllJobsEmpty() throws Exception {
		
		Mockito.when(jobService.listAll()).thenReturn(Collections.emptyList());	
		
		this.mockMvc.perform(get("/v1/Job/list"))
					.andExpect(status().isNoContent());
					
	}

	@Test
	public void testGetJobById() throws Exception {
		Mockito.when(jobService.get(1L)).thenReturn(job1);	
		
		this.mockMvc.perform(get("/v1/Job/" + job1.getId()))
				.andExpect(status().isOk());
	}

}
