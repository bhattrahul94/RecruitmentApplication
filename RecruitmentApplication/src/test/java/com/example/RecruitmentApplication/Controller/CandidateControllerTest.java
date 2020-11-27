package com.example.RecruitmentApplication.Controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;

import com.example.RecruitmentApplication.Service.CandidateService;
import com.example.RecruitmentApplication.Service.JobService;
import com.example.RecruitmentApplication.beans.CandidateDTO;
import com.example.RecruitmentApplication.entity.Candidate;
import com.example.RecruitmentApplication.entity.Job;
import com.example.RecruitmentApplication.utility.ApplicationStatus;



@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@TestInstance(value = Lifecycle.PER_CLASS)
@WebMvcTest(CandidateControllerTest.class)
public class CandidateControllerTest {
	
	
	@Autowired 
	private MockMvc mockMvc;
	
	@MockBean
	private JobService jobService;
	
	@MockBean
	private CandidateService candidateService;
	
//	@InjectMocks
//	private CandidateController candidateController;
	
	
	private Candidate candidate1;
	private Candidate candidate2;
	private final Job job = new Job(1L, "firstJob", new Date(), 0);
	
	private CandidateDTO candidateDto;
	
	
	@Before
	public void setup() {
		candidate1 = new Candidate(1L,"rahul","123","a@b.com","qerty",job, ApplicationStatus.APPLIED);
		candidate2 = new Candidate(2L,"ravi","129","q@p.com","pofy",job, ApplicationStatus.APPLIED);
		candidateDto = new CandidateDTO("rahul","a@b.com","123","asdfgh",1L); 
	}
	
	@Test
	public void testGetCandidateById() throws Exception{
		
		Long id = 1L;
		
		Mockito.when(candidateService.getCandidateById(id)).thenReturn(candidate1);	
		
		this.mockMvc.perform(get("/rest/v1/Candidate/" + candidate1.getId()))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.id").value(candidate1.getId()))
					.andExpect(jsonPath("$.email").value(candidate1.getEmail()))
					.andExpect(jsonPath("$.resume").value(candidate1.getResume()));
	}
	
	
	@Test
	public void testGetCandidateByIdNotFound() throws Exception {
		Long id = 1L;
		Mockito.when(candidateService.getCandidateById(id)).thenReturn(candidate1);
		
		this.mockMvc.perform(get("/rest/v1/Candidate/" + 1L))
        .andExpect(status().isNotFound());
	}
	
	@Test
	public void testApplyForJob() throws Exception {
		Long id = 1L;
		Mockito.when(jobService.get(id)).thenReturn(job);
		
		Mockito.doNothing().when(candidateService).createJobApplication(candidate1);
		
		mockMvc.perform(post("/rest/v1/Candidate")
				.contentType("application/json")
				.content("{\"name\": \"rahul1\",\"email\": \"3.b@gmail.com\",\"phoneno\": \"123\",\"resume\": \"adfsc\",\"job_id\": \"1\"}"))
				.andExpect(status().isCreated());
		
	}
	
	@Test
	 public void testApplyForJobInvalidJobId() throws Exception {
		Long id = 1L;
		Mockito.when(jobService.get(id)).thenReturn(null);
		
		Mockito.doNothing().when(candidateService).createJobApplication(candidate1);
		
		mockMvc.perform(post("/rest/v1/Candidate")
						.contentType("application/json")
						.content("{\"name\": \"rahul1\",\"email\": \"3.b@gmail.com\",\"phoneno\": \"123\",\"resume\": \"adfsc\",\"job_id\": \"1\"}"))
						.andExpect(status().isUnprocessableEntity());
										
	 }
	
	@Test
	public void testGetCandidatesByJobId() throws Exception {
		Long id = 1L;
		Mockito.when(candidateService.getAllCandidatesByJobId(id)).thenReturn(Arrays.asList(candidate1,candidate2));
	
		this.mockMvc.perform(get("/rest/v1/application/getAllByOffer/" + 1L))
							.andExpect(status().isOk())
							.andExpect(jsonPath("$.*", hasSize(2)));
	}
	
	@Test
	public void testGetCandidateByJobIdEmpty() throws Exception {
		Long id = 1L;
		Mockito.when(candidateService.getAllCandidatesByJobId(id)).thenReturn(Collections.emptyList());
		this.mockMvc.perform(get("/rest/v1/application/getAllByOffer/" + 1L))
									.andExpect(status().isNoContent());
	
	}
	
//	@InjectMocks
//	CandidateController candidateController;
//	
//	@Mock
//	CandidateDAO candidateDAO;
//	
//	@Mock
//	CandidateServiceImpl candidateService;
//	
//	@Mock
//	private JobServiceImpl jobService;
//	
//	private Candidate candidate1;
//	
//	private Candidate candidate2;
//	
//	private CandidateDTO candidate3;
//	
//	private List<Candidate> candidateList;
//	
//	private Job job;
//	
	
//	@BeforeAll()
//	void setup() {
//		job = new Job(1L, "freak", new Date(), 0);
//		System.out.println(job.getTitle());
//		
//		//Long id, String name, String phoneno, String email, String resume, Job job,
//		//ApplicationStatus application_status
//		candidateList = new ArrayList<Candidate>();
//		candidate1 = new Candidate(1L,"rahul","123","a@b.com","qerty",job, ApplicationStatus.APPLIED);
//		candidate2 = new Candidate(2L,"ravi","129","q@p.com","pofy",job, ApplicationStatus.APPLIED);
//		candidateList.add(candidate1);
//		candidateList.add(candidate2);
//		//String email, String phoneno, String resume, Long job_id
//		candidate3 = new CandidateDTO("rahul","a@b.com","123","asdfgh",1L); 
//
//	}


//	@Test
//	void testApplyForJob() {
//		fail("Not yet implemented");
//	}
//	
//	@Test
//    public void testCreateJobApplicationInvalidOffer() throws Exception {
//		
////		JobServiceImpl service = new JobServiceImpl();
//		
////		Mockito.when(jobService.get(1L)).thenReturn(null);
////		
////		candidateController.applyForJob(candidate3);
////		Mockito.when(candidateService.getCandidateById())
////		System.out.println("testing");
////		service.listAll().stream().forEach(System.out::println);
////		service.listAll().stream().forEach(System.out::println);
////		System.out.println(service.get(1L));
//	 
//	 	Mockito.when(jobService.get(1L)).thenReturn(null);
//        
//        Mockito.doNothing().when(candidateService).createJobApplication(candidate1);
//
//
//        this.mvc
//                .perform(post("/rest/v1/Candidate").contentType("application/json")
//                		.content("{\"name\": \"rahul\",\"email\": \"a.b@gmail.com\",\"phoneno\": \"123\",\"resume\": \"adfsc\",\"job_id\": \"1\"}"))
//                .andExpect(status().isUnprocessableEntity());
//    }

}
